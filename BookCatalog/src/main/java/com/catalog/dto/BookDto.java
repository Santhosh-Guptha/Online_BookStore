package com.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private int id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookPrice;
    private int rating;
    private int quantity;
}
