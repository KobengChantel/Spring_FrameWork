package tacos.web;

// Java utilities for lists and streams
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Validation annotations
import javax.validation.Valid;

// Spring MVC annotations and classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

// Application-specific classes
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.TacoOrder;
import tacos.Taco;
import tacos.data.IngredientRepository;

// 👉 This controller manages the taco design page, providing ingredient data, handling form submissions, and updating the current TacoOrder in the session.

@Controller
@RequestMapping("/design")          // Maps all methods to /design
@SessionAttributes("tacoOrder")    // Keeps TacoOrder in session across multiple requests
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  // Adds all ingredients to the model grouped by type (wrap, protein, veggies, etc.)
  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
              filterByType(ingredients, type));
    }
  }

  // Adds a TacoOrder to the model if none exists in session
  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  // Adds an empty Taco to the model for the form binding
  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";  // Shows the taco design form
  }

  @PostMapping
  public String processTaco(
          @Valid Taco taco, Errors errors,
          @ModelAttribute TacoOrder tacoOrder) {

    if (errors.hasErrors()) {
      return "design";  // Return to form if validation fails
    }

    tacoOrder.addTaco(taco);  // Add the designed taco to the current order

    return "redirect:/orders/current";  // Redirect to order form
  }

  // Filters the ingredient list by a given type
  private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
  }

}
