package com.cart.serviceimpl;

import com.cart.entity.Book;
import com.cart.entity.ShoppingCart;
import com.cart.repositories.BookRepository;
import com.cart.repositories.ShoppingCartRepository;
import com.cart.service.CartService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;
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
                throw new RuntimeException("Cart Not Created.. Try Again...");
            }
        }
        else throw new RuntimeException("Cart is already allocated to the user"+userId);
    }

    @Override
    public ShoppingCart getUserShoppingCart(int userId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByUserId(userId);
        if(shoppingCart.isPresent()){
            ShoppingCart shoppingCart1 = shoppingCart.get();
            return shoppingCart1;
        }else {
            ShoppingCart shoppingCart1 = createShoppingCart(userId);
            return shoppingCart1;
        }
    }

    @Transactional
    @Override
    public ShoppingCart addBookToCart(int userId, int bookId, int quantity) {
        // Fetch the book from the BookRepository
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            // Find or create a shopping cart for the user
            ShoppingCart cart = shoppingCartRepository.findByUserId(userId).get();

            // Update total price based on the quantity and book price
            BigDecimal totalPriceIncrement = BigDecimal.valueOf(book.getPrice());
            BigDecimal totalPrice = cart.getTotalPrice().add(totalPriceIncrement);
            cart.setTotalPrice(totalPrice);

            // Add the book to the shopping cart
            cart.getBooks().add(book);

            // Save the updated shopping cart
            return shoppingCartRepository.save(cart);
        } else {
            // Handle case where book is not found
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
    }

}

