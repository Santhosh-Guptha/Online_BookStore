package com.catalog.serviceimpl;

import com.catalog.dto.BookDto;
import com.catalog.entities.Book;
import com.catalog.repository.CatalogRepository;
import com.catalog.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient webClient;
    @Override
    public String createBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto,Book.class);
        catalogRepository.save(book);
        return "Successfully Created a Book"+book.getBookId();
    }

    @Override
    public List<Book> getAllBooks() {
        return catalogRepository.findAll();
    }

    @Override
    public BookDto getBookById(int id) {
        Book book = catalogRepository.findByBookId(id).get();
        return modelMapper.map(book,BookDto.class);
    }

    @Override
    public BookDto updateBook(int id, BookDto bookDto) {
        Book book = catalogRepository.findByBookId(id).get();
        if(StringUtils.hasText(bookDto.getBookAuthor())) {
            book.setBookAuthor(bookDto.getBookAuthor());
        }
        if(StringUtils.hasText(bookDto.getBookName())) {
            book.setBookName(bookDto.getBookName());
        }
        if(StringUtils.hasText(bookDto.getBookDescription())) {
            book.setBookDescription(bookDto.getBookDescription());
        }
        if(StringUtils.hasText(bookDto.getBookPrice())) {
            book.setBookPrice(bookDto.getBookPrice());
        }
        book.setRating(bookDto.getRating());
        catalogRepository.save(book);
        return modelMapper.map(book,BookDto.class);
    }

    @Override
    public String deleteBook(int id) {
        if(catalogRepository.findByBookId(id).isPresent()){
            catalogRepository.deleteByBookId(id);
        }
        return "User deleted with id: "+ id;
    }

}
