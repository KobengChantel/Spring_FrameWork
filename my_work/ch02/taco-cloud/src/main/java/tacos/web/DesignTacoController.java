package tacos.web; // Declares that this class belongs to the 'tacos.web' package

import java.util.Arrays; // For creating a fixed-size list of ingredients
import java.util.List; // For using List collections
import java.util.stream.Collectors; // For filtering ingredients by type using streams
import org.springframework.stereotype.Controller; // Marks this class as a Spring MVC controller
import org.springframework.ui.Model; // Used to pass data to the view
import org.springframework.web.bind.annotation.GetMapping; // Maps GET requests to methods
import org.springframework.web.bind.annotation.ModelAttribute; // Binds a method parameter or return value to a model attribute
import org.springframework.web.bind.annotation.PostMapping; // Maps POST requests to methods
import org.springframework.web.bind.annotation.RequestMapping; // Maps class-level request paths
import org.springframework.web.bind.annotation.SessionAttributes; // Stores model attributes in the session between requests

import lombok.extern.slf4j.Slf4j; // Lombok annotation to enable logging
import tacos.Ingredient; // Represents an ingredient
import tacos.Ingredient.Type; // Enum for ingredient types
import tacos.Taco; // Represents a taco
import tacos.TacoOrder; // Represents an order containing tacos

import javax.validation.Valid; // Enables validation on objects
import org.springframework.validation.Errors; // Holds validation errors

@Slf4j // Enables logging for this class
@Controller // Marks this class as a Spring MVC controller
@RequestMapping("/design") // Maps all methods to URLs starting with /design
@SessionAttributes("tacoOrder") // Keeps "tacoOrder" in session across multiple requests
public class DesignTacoController {

    @ModelAttribute // Adds common data to the model before any request method runs
    public void addIngredientsToModel(Model model) {
        // Creates a list of available ingredients
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        // Groups ingredients by their type and adds them to the model
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder") // Creates a TacoOrder model attribute if it doesn't exist
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco") // Creates a Taco model attribute for form binding
    public Taco taco() {
        return new Taco();
    }

    @GetMapping // Handles GET requests to /design
    public String showDesignForm() {
        return "design"; // Returns the design.html view
    }

  /*
  // Old version without validation
  @PostMapping
  public String processTaco(Taco taco,
          @ModelAttribute TacoOrder tacoOrder) {
    tacoOrder.addTaco(taco);
    log.info("Processing taco: {}", taco);
    return "redirect:/orders/current";
  }
  */

    @PostMapping // Handles POST requests to /design
    public String processTaco(
            @Valid Taco taco, Errors errors, // Validates the taco object
            @ModelAttribute TacoOrder tacoOrder) {

        // If validation fails, return to the design form
        if (errors.hasErrors()) {
            return "design";
        }

        // Add the taco to the order and log the process
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        // Redirect to the current order page
        return "redirect:/orders/current";
    }

    // Helper method to filter ingredients by type
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
