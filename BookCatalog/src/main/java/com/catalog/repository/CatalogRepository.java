package com.catalog.repository;

import com.catalog.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByBookId(int id);

    void deleteByBookId(int id);
}
