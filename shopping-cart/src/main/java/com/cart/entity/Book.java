package com.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private int Price;
    private long quantity;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private ShoppingCart cart;
    private int rating;
}
