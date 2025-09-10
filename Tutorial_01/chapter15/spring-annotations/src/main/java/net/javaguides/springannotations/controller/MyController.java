package net.javaguides.springannotations.controller;

import org.springframework.stereotype.Controller;

// This class is a simple Spring MVC controller that returns a greeting string.

@Controller
public class MyController {

    // A method that returns a greeting message (would need @ResponseBody or a view resolver to display it in a browser)
    public String hello() {
        return "Hello controller!";
    }
}
