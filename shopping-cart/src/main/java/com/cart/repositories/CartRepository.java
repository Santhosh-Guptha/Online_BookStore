package com.cart.repositories;

import com.cart.entity.CartItem;
import com.cart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Integer> {

    void deleteByProductId(int productId);
}
