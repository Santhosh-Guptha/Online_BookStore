package com.cart.repositories;

import com.cart.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Book,Integer> {

    Optional<Book> findByBookId(int bookId);
}
