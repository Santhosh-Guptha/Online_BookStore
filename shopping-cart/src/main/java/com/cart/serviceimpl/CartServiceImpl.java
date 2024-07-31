package com.cart.serviceimpl;

import com.cart.entity.Book;
import com.cart.entity.CartItem;
import com.cart.entity.ShoppingCart;
import com.cart.entity.ShoppingCartCheckoutResult;
import com.cart.repositories.CartRepository;
import com.cart.repositories.CatalogRepository;
import com.cart.repositories.ShoppingCartRepository;
import com.cart.service.CartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private CatalogRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ShoppingCart createShoppingCart(int userId) {
        if (shoppingCartRepository.findById(userId).isEmpty()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(userId);
            shoppingCart.setCreatedDate(LocalDateTime.now());
            shoppingCart.setUpdatedDate(LocalDateTime.now());
            shoppingCart.setStatus("open");
            shoppingCartRepository.save(shoppingCart);
            if (shoppingCart != null) {
                return shoppingCart;
            } else {
                throw new RuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }
        else throw new RuntimeException("Cart is already allocated to the user"+userId);
    }

    @Override
    public ShoppingCart getUserShoppingCart(int userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).get();
        return shoppingCart;
    }

    @Override
    public List<CartItem> getCartItems(int userId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByUserId(userId);
        return shoppingCart.map(ShoppingCart::getItems).orElse(null);
    }

    @Override
    public ShoppingCart addItemsToCart(int userId, long bookId, int quantity) {
        // Retrieve the book from the repository using the provided bookId
        Optional<Book> bookOptional = bookRepository.findByBookId((int) bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            // Check if the requested quantity is available
            if (book.getQuantity() >= quantity) {
                // Update the book quantity
                book.setQuantity(book.getQuantity() - quantity);
                bookRepository.save(book);

                // Proceed with adding the book to the cart
                Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUserId(userId);
                if (shoppingCartOptional.isPresent()) {
                    ShoppingCart cart = shoppingCartOptional.get();

                    // Check if the item already exists in the cart
                    boolean itemExists = false;
                    for (CartItem item : cart.getItems()) {
                        if (item.getProductId() == bookId) {
                            // If item already exists, update quantity
                            item.setQuantity(item.getQuantity() + quantity);
                            itemExists = true;
                            break;
                        }
                    }

                    if (!itemExists) {
                        // If item doesn't exist, create a new CartItem and add it to the cart
                        CartItem cartItem = new CartItem();
                        cartItem.setProductId(bookId);
                        cartItem.setQuantity(quantity);
                        cartItem.setPrice(book.getBookPrice()); // Use book's price for the cart item
                        cartItem.setCart(cart);
                        cart.getItems().add(cartItem);
                    }

                    // Update cart's total price
                    cart.setTotalPrice(calculateTotalPrice(cart));
                    cart.setUpdatedDate(LocalDateTime.now());

                    // Save the updated cart
                    return shoppingCartRepository.save(cart);
                }
            } else {
                // Handle insufficient quantity error
                throw new RuntimeException("Insufficient quantity of book with ID: " + bookId);
            }
        }
        return null;
    }




    @Override
    public String removeItemFromCart(int userId , int itemId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId).get();
        CartItem cartItem = cartItemRepository.findById(itemId).get();
        cart.setTotalPrice(cart.getTotalPrice().subtract(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))));
        cartItemRepository.deleteById(itemId);
        shoppingCartRepository.save(cart);
        return "Successfully deleted"+itemId;
    }

    @Override
    public ShoppingCartCheckoutResult checkout(int userId) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findById(userId);
        if (optionalCart.isPresent()) {
            ShoppingCart cart = optionalCart.get();
            // Get the cart items
            List<CartItem> cartItems = cart.getItems();

            // Calculate delivery date
            LocalDateTime now = LocalDateTime.now();
            int randomDays = (int) (Math.random() * 3) + 1; // Random number between 1 and 3
            LocalDateTime deliveryDate = now.plusDays(randomDays);
            // Save the updated cart
            shoppingCartRepository.save(cart);
            // Prepare checkout result
            ShoppingCartCheckoutResult result = new ShoppingCartCheckoutResult();
            result.setSuccess(true);
            result.setMessage("Checkout successful");
            result.setCartItems(cartItems);
            result.setTotalPrice(cart.getTotalPrice());
            result.setDeliveryDate(deliveryDate);
            return result ;
        } else {
            // Cart not found for the user
            ShoppingCartCheckoutResult result = new ShoppingCartCheckoutResult();
            result.setSuccess(false);
            result.setMessage("Shopping cart not found for user ID: " + userId);
            return result;
        }
    }

    private BigDecimal calculateTotalPrice(ShoppingCart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            totalPrice = totalPrice.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalPrice;
    }

    public void clearCartItems(int userId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUserId(userId);
        shoppingCartOptional.ifPresent(shoppingCart -> {
            shoppingCart.getItems().removeAll(getCartItems(userId));
            shoppingCartRepository.save(shoppingCart);
        });
    }
}
