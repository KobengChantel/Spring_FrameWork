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
import org.springframework.http.HttpStatus;



//@Controller
////@ResponseBody
//combination of @Controller and @ResponseBody annotations
@RestController
@RequestMapping("/api")
public class BookController {

    @RequestMapping("/hello-world")
//    @ResponseBody
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping(value = {"/book", "/java"},
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public Book getBook(){
        Book book = new Book(1, "Core Java", "Learn Core Java and latest Features");
        return book;
    }

    @PostMapping(value = "/books/create",
    consumes = MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getDescription());
        return new ResponseEntity<>(book, HttpStatus.CREATED);

        //    @RequestMapping(value = "/books/create",method = RequestMethod.POST)
//    public Book createBook(Book book){
//        System.out.println(book.getId());
//        System.out.println(book.getTitle());
//        System.out.println(book.getDescription());
//        return book;

    }

    //http:localhost:8080/api/update/1
    //shortcut for put method annotation for @RequestMapping
    @PutMapping(value ="/books/update/{id}")
//    @RequestMapping(value = "/books/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(
            @PathVariable int id,
                                           @RequestBody Book updateBook){
        System.out.println(updateBook.getId());
        System.out.println(updateBook.getTitle());
        System.out.println(updateBook.getDescription());
        updateBook.setId(id);
        return ResponseEntity.ok().body(updateBook);
    }

    }


