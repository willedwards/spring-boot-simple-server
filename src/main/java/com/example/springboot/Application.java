package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class Application {

	@Autowired
	private BookRepository booksRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	public void initApplication() throws IOException {
		booksRepo.addBook(new Book("111-1","Java 8 Lamdas","Richard Warburton"));
		booksRepo.addBook(new Book("111-2","An Introduction to Programming in Go","Caleb Doxsey"));
	}

}
