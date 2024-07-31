package com.catalog.service;

import com.catalog.dto.BookDto;
import com.catalog.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    String createBook(BookDto bookDto);

    List<Book> getAllBooks();

    BookDto getBookById(int id);

    BookDto updateBook(int id, BookDto bookDto);

    String deleteBook(int id);
}
