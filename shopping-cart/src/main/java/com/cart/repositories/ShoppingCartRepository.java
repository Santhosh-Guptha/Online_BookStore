package com.cart.repositories;

import com.cart.entity.ShoppingCart;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

    Optional<ShoppingCart> findByUserId(int cartId);
}
