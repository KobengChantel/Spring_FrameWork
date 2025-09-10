package tacos.web;

// Validation annotations
import javax.validation.Valid;

// Spring MVC annotations and classes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

// Application-specific classes
import tacos.TacoOrder;
import tacos.data.OrderRepository;

// 👉 This controller handles the TacoOrder form, validates input, saves the order, and completes the session.

@Controller
@RequestMapping("/orders")       // Maps all methods to /orders
@SessionAttributes("tacoOrder")  // Keeps TacoOrder in session across multiple requests
public class OrderController {

  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  // Shows the order form page
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  // Processes submitted order form
  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm"; // Return to form if validation fails
    }

    orderRepo.save(order);      // Save the order to the database
    sessionStatus.setComplete(); // Mark the session as complete, removing tacoOrder

    return "redirect:/";        // Redirect to home page
  }

}
