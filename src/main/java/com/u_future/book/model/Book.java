package com.u_future.book.model;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String description;
    private Double price;
}
