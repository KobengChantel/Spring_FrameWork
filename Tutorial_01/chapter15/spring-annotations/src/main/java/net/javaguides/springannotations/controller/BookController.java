package net.javaguides.springannotations.controller;

import net.javaguides.springannotations.beans.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

// This class is a REST controller that defines CRUD endpoints for managing Book objects.

@RestController
@RequestMapping("/api") // Base URL path for all endpoints in this controller
public class BookController {

    // Simple endpoint that returns a plain text message
    @RequestMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    // GET request that returns a Book object in JSON format
    @GetMapping(
            value = {"/book", "/java"},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Book getBook() {
        Book book = new Book(1, "Core Java", "Learn Core Java and latest Features");
        return book;
    }

    // POST request to create a new Book (returns 201 Created status)
    @PostMapping(value = "/books/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        // Print book details for debugging
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getDescription());

        // Return created book with HTTP 201 status
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // PUT request to update an existing book by its ID
    // Example: http://localhost:8080/api/books/update/1
    @PutMapping(value = "/books/update/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable int id,
            @RequestBody Book updateBook) {
        System.out.println(updateBook.getId());
        System.out.println(updateBook.getTitle());
        System.out.println(updateBook.getDescription());

        // Ensure the path variable ID is set in the updated book
        updateBook.setId(id);

        return ResponseEntity.ok().body(updateBook);
    }

    // DELETE request to remove a book by ID
    @DeleteMapping(value = "/books/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        System.out.println(id);
        return ResponseEntity.ok().body("Book Deleted Successfully!.");
    }
}
