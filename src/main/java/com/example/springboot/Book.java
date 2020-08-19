package com.example.springboot;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String isbn;
    private String title;
    private String author;
    
}
