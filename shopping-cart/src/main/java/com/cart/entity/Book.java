package com.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "catalog_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private BigDecimal bookPrice;
    private int rating;
    private int quantity;
}
