package tacos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

// 👉 Represents a TacoOrder entity that stores customer, payment, and taco information, validated and persisted in the database.

@Data
@Entity // Marks this class as a JPA entity
public class TacoOrder implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates primary key
  private Long id;

  private Date placedAt = new Date(); // Timestamp when the order was created

  @NotBlank(message="Delivery name is required")
  private String deliveryName;

  @NotBlank(message="Street is required")
  private String deliveryStreet;

  @NotBlank(message="City is required")
  private String deliveryCity;

  @NotBlank(message="State is required")
  private String deliveryState;

  @NotBlank(message="Zip code is required")
  private String deliveryZip;

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
          message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  @OneToMany(cascade = CascadeType.ALL) // Cascades all operations to associated Tacos
  private List<Taco> tacos = new ArrayList<>();

  // Adds a taco to this order
  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }

}
