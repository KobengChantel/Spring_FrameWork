package tacos.data;
// Declares the package name "tacos.data", used for data access/repository classes.

import java.util.Optional;
// Optional is used to handle cases where a TacoOrder may or may not exist.

import tacos.TacoOrder;
// Imports the TacoOrder entity to be used in repository methods.

// This interface defines the contract for saving and retrieving TacoOrder objects.

public interface OrderRepository {

  TacoOrder save(TacoOrder order);
  // Persists a TacoOrder to the database and returns the saved instance.

  Optional<TacoOrder> findById(Long id);
  // Retrieves a TacoOrder by its ID, wrapped in Optional to handle missing results.

}
