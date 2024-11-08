package net.javaguides.springannotations.controller;

import net.javaguides.springannotations.beans.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;


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

    //assining multiple url
    @RequestMapping(value ={"/book","/core-java","/java"},
    //using method example for the @RequestAnnotation

//    method = {RequestMethod.PUT, RequestMethod.DELETE},
//            method = RequestMethod.POST
    method = RequestMethod.GET,
            //specify return type
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            //consuming json file sent by a client
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )

//    @ResponseBody
    public Book getBook(){
        Book book = new Book(1, "Core Java", "Learn Core Java and latest Features");
        return book;
    }
}
