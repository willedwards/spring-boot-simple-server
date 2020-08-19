package com.example.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksControllerIT {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getBooks()  {
        String expected = "[{\"isbn\":\"111-1\",\"title\":\"Java 8 Lamdas\",\"author\":\"Richard Warburton\"},{\"isbn\":\"111-2\",\"title\":\"An Introduction to Programming in Go\",\"author\":\"Caleb Doxsey\"}]";
        ResponseEntity<String> response = fireAtRelativeUrl("/api/books");
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    public void getBookOne()  {
        String expected = "{\"isbn\":\"111-1\",\"title\":\"Java 8 Lamdas\",\"author\":\"Richard Warburton\"}";
        ResponseEntity<String> response = fireAtRelativeUrl("/api/book/111-1");
        assertThat(response.getBody()).isEqualTo(expected);
    }
    
    private ResponseEntity<String> fireAtRelativeUrl(String relUrl){
        return template.getForEntity(base.toString() + relUrl, String.class);
    }
}
