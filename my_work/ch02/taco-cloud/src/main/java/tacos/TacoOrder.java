package tacos;
// Declares the package name as "tacos", grouping related classes together.

import javax.validation.constraints.Digits;
// Ensures numeric values match digit constraints (e.g., max digits before/after decimal).

import javax.validation.constraints.NotBlank;
// Validates that a string field is not null and contains at least one non-whitespace character.

import javax.validation.constraints.Pattern;
// Validates string fields against a specified regular expression pattern.

import org.hibernate.validator.constraints.CreditCardNumber;
// Validates that a string value is a valid credit card number (using the Luhn algorithm).

import java.util.List;
// Provides the List interface for handling collections of objects.

import java.util.ArrayList;
// Provides the ArrayList implementation of the List interface.

import lombok.Data;
// Generates boilerplate methods like getters, setters, equals, hashCode, and toString automatically.

// This class represents a Taco order, holding customer delivery info, payment details, and a list of tacos with validation rules.

@Data
public class TacoOrder {

  @NotBlank(message="Delivery name is required")
  // Ensures delivery name cannot be blank.
  private String deliveryName;

  @NotBlank(message="Street is required")
  // Ensures street field is not empty.
  private String deliveryStreet;

  @NotBlank(message="City is required")
  // Ensures city field is not empty.
  private String deliveryCity;

  @NotBlank(message="State is required")
  // Ensures state field is not empty.
  private String deliveryState;

  @NotBlank(message="Zip code is required")
  // Ensures zip code field is not empty.
  private String deliveryZip;

  @CreditCardNumber(message="Not a valid credit card number")
  // Ensures the number entered is a valid credit card number.
  private String ccNumber;

  @Pattern(
          regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
          message="Must be formatted MM/YY")
  // Ensures expiration date matches the format MM/YY.
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  // Ensures CVV is exactly 3 digits with no decimal part.
  private String ccCVV;

  private List<Taco> tacos = new ArrayList<>();
  // Holds the list of tacos in the order, initialized as an empty list.

  public void addTaco(Taco taco) {
    this.tacos.add(taco);
    // Method to add a taco to the order’s list.
  }
}
