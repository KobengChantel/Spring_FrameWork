package tacos.web; // Declares that this class belongs to the 'tacos.web' package

import javax.validation.Valid; // Enables validation on objects

import org.springframework.stereotype.Controller; // Marks this class as a Spring MVC controller
import org.springframework.validation.Errors; // Holds validation error details
import org.springframework.web.bind.annotation.GetMapping; // Maps GET requests to methods
import org.springframework.web.bind.annotation.PostMapping; // Maps POST requests to methods
import org.springframework.web.bind.annotation.RequestMapping; // Maps class-level request paths
import org.springframework.web.bind.annotation.SessionAttributes; // Keeps specified attributes in the session
import org.springframework.web.bind.support.SessionStatus; // Allows clearing session attributes

import lombok.extern.slf4j.Slf4j; // Lombok annotation for logging
import tacos.TacoOrder; // Represents an order containing tacos

@Slf4j // Enables logging
@Controller // Marks this as a Spring MVC controller
@RequestMapping("/orders") // All request mappings in this class start with /orders
@SessionAttributes("tacoOrder") // Stores "tacoOrder" in session so it persists across multiple requests
public class OrderController {

  @GetMapping("/current") // Handles GET requests to /orders/current
  public String orderForm() {
    return "orderForm"; // Returns the orderForm.html view
  }

  /*
  // Old version without validation
  @PostMapping
  public String processOrder(TacoOrder order,
        SessionStatus sessionStatus) {
    log.info("Order submitted: {}", order);
    sessionStatus.setComplete(); // Clears "tacoOrder" from the session
    return "redirect:/"; // Redirects to the homepage
  }
  */

  @PostMapping // Handles POST requests to /orders
  public String processOrder(
          @Valid TacoOrder order, Errors errors, // Validates the order object
          SessionStatus sessionStatus) {

    // If validation fails, return the order form again
    if (errors.hasErrors()) {
      return "orderForm";
    }

    // Log the order details and clear session
    log.info("Order submitted: {}", order);
    sessionStatus.setComplete(); // Ends the session for "tacoOrder"

    // Redirect to homepage after successful submission
    return "redirect:/";
  }
}
