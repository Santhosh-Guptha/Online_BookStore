package com.catalog.serviceimpl;


import com.catalog.entities.Book;
import com.catalog.repository.CatalogRepository;
import com.catalog.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
//import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createBook(Book book) {
        catalogRepository.save(book);
        return "Successfully Created a Book"+book.getId();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books =catalogRepository.findAll();
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = catalogRepository.findById(id).get();
        return book;
    }

    @Override
    public Book updateBook(int id, Book bookDto) {
        Book book = catalogRepository.findById(id).get();
        if(StringUtils.hasText(bookDto.getBookAuthor())) {
            book.setBookAuthor(bookDto.getBookAuthor());
        }
        if(StringUtils.hasText(bookDto.getBookName())) {
            book.setBookName(bookDto.getBookName());
        }
        if(StringUtils.hasText(bookDto.getBookDescription())) {
            book.setBookDescription(bookDto.getBookDescription());
        }
        if(StringUtils.hasText(String.valueOf(bookDto.getPrice()))) {
            book.setPrice(bookDto.getPrice());
        }
        book.setRating(bookDto.getRating());
        catalogRepository.save(book);
        return bookDto;
    }

    @Override
    public String deleteBook(int id) {
        if(catalogRepository.findById(id)!= null){
            catalogRepository.deleteById(id);
        }
        return "User deleted with id: "+ id;
    }

}
