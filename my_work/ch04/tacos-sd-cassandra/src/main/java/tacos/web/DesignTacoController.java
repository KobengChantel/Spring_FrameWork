package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.TacoUDT;
import tacos.data.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  // Add all ingredients to the model, grouped by type
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

  // Add a TacoOrder to the session model if not already present
  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  // Add a Taco to the model for the form
  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design"; // returns the design view
  }

  // Handle form submission
  @PostMapping
  public String processTaco(@Valid Taco taco, Errors errors,
                            @ModelAttribute TacoOrder tacoOrder) {
    if (errors.hasErrors()) {
      return "design"; // redisplay form if validation fails
    }

    // Convert Taco to TacoUDT before adding to order
    tacoOrder.addTaco(new TacoUDT(taco.getName(), taco.getIngredients()));

    return "redirect:/orders/current";
  }

  // Helper method to filter ingredients by type
  private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
  }

}
