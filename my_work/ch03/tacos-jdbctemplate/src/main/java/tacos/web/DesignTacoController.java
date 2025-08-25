package tacos.web;

// Java utility classes for working with collections and streams
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// Validation support for ensuring input is correct
import javax.validation.Valid;

// Spring Framework annotations and utilities for MVC controllers
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

// Application-specific classes for tacos, orders, and ingredients
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.TacoOrder;
import tacos.Taco;
import tacos.data.IngredientRepository;

// 👉 This controller handles displaying the taco design form,
// retrieving ingredients, validating user taco designs, and
// adding them to an active taco order stored in the session.

@Controller  // Marks this class as a Spring MVC controller
@RequestMapping("/design") // Maps requests starting with "/design" here
@SessionAttributes("tacoOrder") // Keeps TacoOrder in session between requests
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  // Constructor injection of the repository that retrieves ingredients from DB
  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  // Adds all ingredient types to the model so the view can render them
  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    Iterable<Ingredient> ingredients = ingredientRepo.findAll();
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(
              type.toString().toLowerCase(),
              filterByType(ingredients, type));
    }
  }

  // Ensures a TacoOrder object exists in the session
  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  // Ensures a Taco object exists in the model for form binding
  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  // Handles GET requests to display the taco design form
  @GetMapping
  public String showDesignForm() {
    return "design"; // returns the view named "design.html"
  }

  // Handles POST requests when a user submits a taco design
  @PostMapping
  public String processTaco(
          @Valid Taco taco, // Validates the Taco input
          Errors errors,    // Holds validation errors
          @ModelAttribute TacoOrder tacoOrder) { // Retrieves current order from session

    if (errors.hasErrors()) {
      return "design"; // If invalid, redisplay form
    }

    tacoOrder.addTaco(taco); // Add taco to the order

    return "redirect:/orders/current"; // Redirect to order page
  }

  // Helper method to filter ingredients by type (e.g., wrap, protein, etc.)
  private Iterable<Ingredient> filterByType(
          Iterable<Ingredient> ingredients, Type type) {
    return StreamSupport.stream(ingredients.spliterator(), false)
            .filter(i -> i.getType().equals(type))
            .collect(Collectors.toList());
  }
}
