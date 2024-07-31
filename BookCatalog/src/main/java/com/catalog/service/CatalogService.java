package com.catalog.service;

import com.catalog.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    String createBook(Book bookDto);

    List<Book> getAllBooks();

    Book getBookById(int id);

    Book updateBook(int id, Book bookDto);

    String deleteBook(int id);
}
