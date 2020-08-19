package com.example.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class BooksController {

    @Autowired
    private BookRepository booksRepo;

    @RequestMapping(value="/book/{isbn}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book result = booksRepo.getBook(isbn);
        HttpStatus httpStatus = (result != null) ? OK : NOT_FOUND;
        return new ResponseEntity<>(result, httpStatus);
    }


    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        booksRepo.addBook(book);
        return new ResponseEntity<>(book, CREATED);
    }

    @RequestMapping(value="/books",method = RequestMethod.GET,produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = booksRepo.getAllBooks();
        return new ResponseEntity<>(books, OK);
    }

     @RequestMapping(value = "/book/{isbn}",method = RequestMethod.PUT,consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        HttpStatus httpStatus = NOT_FOUND;
        if(booksRepo.isBookAvailable(isbn)) {
            book.setIsbn(isbn);
            booksRepo.addBook(book);
            httpStatus = OK;
        }
        return new ResponseEntity<>(book,httpStatus);
    }

    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        HttpStatus httpStatus = booksRepo.removeBook(isbn) ? OK : NOT_FOUND;
        return new ResponseEntity<>(httpStatus);
    }
}