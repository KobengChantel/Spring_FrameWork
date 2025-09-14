package tacos;

// Java utility classes for serialization, dates, and lists
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Validation annotations to enforce constraints on fields
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// Hibernate annotation for credit card validation
import org.hibernate.validator.constraints.CreditCardNumber;

// Spring Data JDBC annotations for table mapping and ID
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString
import lombok.Data;

// 👉 This class represents a TacoOrder entity with delivery info, payment details, creation timestamp, and a list of tacos, mapped to a database table with validation constraints.

@Data
@Table  // Maps this class to a database table
public class TacoOrder implements Serializable {

  private static final long serialVersionUID = 1L;
  // Ensures serialization compatibility

  @Id
  private Long id;
  // Primary key of the order

  private Date placedAt = new Date();
  // Timestamp for when the order was placed

  @NotBlank(message="Delivery name is required")
  private String deliveryName;
  // Customer's name

  @NotBlank(message="Street is required")
  private String deliveryStreet;
  // Delivery street

  @NotBlank(message="City is required")
  private String deliveryCity;
  // Delivery city

  @NotBlank(message="State is required")
  private String deliveryState;
  // Delivery state

  @NotBlank(message="Zip code is required")
  private String deliveryZip;
  // Delivery zip code

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;
  // Credit card number

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
          message="Must be formatted MM/YY")
  private String ccExpiration;
  // Credit card expiration date in MM/YY format

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;
  // CVV number of credit card

  private List<Taco> tacos = new ArrayList<>();
  // List of tacos included in the order

  // Adds a taco to the order
  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }

}
