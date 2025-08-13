package tacos; // Declares that this class belongs to the 'tacos' package

import org.springframework.stereotype.Controller; // Allows us to define a class as a Spring MVC Controller
import org.springframework.web.bind.annotation.GetMapping; // Used to map HTTP GET requests to a method

// This is our HomeController for the Taco Cloud application
@Controller // <1> Marks this class as a Spring MVC controller so it can handle web requests
public class HomeController {

  @GetMapping("/") // <2> Maps HTTP GET requests for the root path ("/") to the home() method
  public String home() {
    return "home"; // <3> Returns the logical view name "home" (Spring looks for home.html or home.jsp in templates)
  }

}
