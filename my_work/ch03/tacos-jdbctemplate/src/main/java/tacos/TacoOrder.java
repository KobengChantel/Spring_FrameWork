package tacos;

// Java utility classes for serialization, dates, and lists
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Validation annotations for input constraints
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// Hibernate validator for credit card number validation
import org.hibernate.validator.constraints.CreditCardNumber;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString
import lombok.Data;

// 👉 This class represents a TacoOrder with customer delivery info, payment details, a list of tacos, and validation rules, and it implements Serializable for object persistence.

@Data
public class TacoOrder implements Serializable {

  private static final long serialVersionUID = 1L;
  // Version ID for serialization to ensure compatibility

  private Long id;
  // Unique identifier for the order

  private Date placedAt;
  // Timestamp when the order was placed

  @NotBlank(message="Delivery name is required")
  private String deliveryName;
  // Customer's delivery name (required)

  @NotBlank(message="Street is required")
  private String deliveryStreet;
  // Delivery street (required)

  @NotBlank(message="City is required")
  private String deliveryCity;
  // Delivery city (required)

  @NotBlank(message="State is required")
  private String deliveryState;
  // Delivery state (required)

  @NotBlank(message="Zip code is required")
  private String deliveryZip;
  // Delivery postal code (required)

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;
  // Credit card number with validation

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
          message="Must be formatted MM/YY")
  private String ccExpiration;
  // Credit card expiration date in MM/YY format

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;
  // CVV number validation (3 digits)

  private List<Taco> tacos = new ArrayList<>();
  // List of tacos included in the order

  // Adds a Taco to the order
  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }

}
