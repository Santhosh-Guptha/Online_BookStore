package com.catalog.controller;

import com.catalog.dto.BookDto;
import com.catalog.entities.Book;
import com.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(catalogService.createBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(catalogService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable int id){
        return new ResponseEntity<>(catalogService.getBookById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable int id, @RequestBody BookDto bookDto){
        return new ResponseEntity<>(catalogService.updateBook(id,bookDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return new ResponseEntity<>(catalogService.deleteBook(id),HttpStatus.OK);
    }

}
