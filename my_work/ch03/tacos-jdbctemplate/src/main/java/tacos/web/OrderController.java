package tacos.web;

// Validation support for ensuring input is correct
import javax.validation.Valid;

// Spring MVC annotations and classes for controllers, request handling, and session management
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

// Application-specific classes for taco orders and repository
import tacos.TacoOrder;
import tacos.data.OrderRepository;

// 👉 This controller handles displaying the order form, processing submitted TacoOrders, saving them to the database, and managing the session.

@Controller  // Marks this class as a Spring MVC controller
@RequestMapping("/orders")  // Maps requests starting with "/orders" here
@SessionAttributes("tacoOrder")  // Keeps TacoOrder in session across multiple requests
public class OrderController {

  private OrderRepository orderRepo;
  // Repository used to save TacoOrder objects

  // Constructor injection of the OrderRepository
  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  // Handles GET requests to display the order form page
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";  // Returns the view named "orderForm.html"
  }

  // Handles POST requests when a TacoOrder is submitted
  @PostMapping
  public String processOrder(
          @Valid TacoOrder order, // Validates the TacoOrder input
          Errors errors,          // Holds validation errors
          SessionStatus sessionStatus) { // Allows clearing the session attribute

    if (errors.hasErrors()) {
      return "orderForm";  // Redisplay form if there are validation errors
    }

    orderRepo.save(order);     // Save the order to the database
    sessionStatus.setComplete(); // Clear the session-scoped TacoOrder

    return "redirect:/";       // Redirect to the home page after successful submission
  }

}
