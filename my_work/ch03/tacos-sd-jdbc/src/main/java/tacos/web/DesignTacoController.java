package tacos.web;

// Java utility classes for working with lists and streams
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Validation support for input validation
import javax.validation.Valid;

// Spring MVC annotations and classes for controllers, request handling, model binding, and session management
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

// Application-specific classes for tacos, orders, ingredients, and repository
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.TacoOrder;
import tacos.Taco;
import tacos.data.IngredientRepository;

// 👉 This controller manages the taco design process: loading ingredients, binding them to the model, validating submitted tacos, and adding them to a session-scoped TacoOrder.

@Controller  // Marks this class as a Spring MVC controller
@RequestMapping("/design")  // Maps all requests starting with "/design" here
@SessionAttributes("tacoOrder")  // Keeps TacoOrder in session across multiple requests
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;
  // Repository to fetch all available ingredients

  // Constructor injection of IngredientRepository
  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  // Adds all ingredient types to the model so the view can display them
  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
    // Collects all ingredients from the repository

    Type[] types = Ingredient.Type.values();
    // Gets all possible ingredient types (WRAP, PROTEIN, etc.)

    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
              filterByType(ingredients, type));
      // Adds a list of ingredients filtered by type to the model
    }
  }

  // Ensures a TacoOrder exists in the session
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
    return "design";  // Returns the view named "design.html"
  }

  // Handles POST requests when a taco is submitted
  @PostMapping
  public String processTaco(
          @Valid Taco taco,  // Validates taco fields
          Errors errors,     // Holds validation errors
          @ModelAttribute TacoOrder tacoOrder) { // Retrieves session-scoped order

    if (errors.hasErrors()) {
      return "design";  // Redisplay form if validation fails
    }

    tacoOrder.addTaco(taco);  // Add taco to the current order

    return "redirect:/orders/current";  // Redirect to current order page
  }

  // Helper method to filter ingredients by their type
  private Iterable<Ingredient> filterByType(
          List<Ingredient> ingredients, Type type) {
    return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
    // Returns a list containing only ingredients of the specified type
  }

}
