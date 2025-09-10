package tacos.data;

// Spring Data repository interface for CRUD operations
import org.springframework.data.repository.CrudRepository;

// Application-specific entity
import tacos.TacoOrder;

// 👉 This interface provides CRUD operations for TacoOrder entities using Spring Data JDBC, with Long as the ID type.

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
    // By extending CrudRepository, this interface inherits methods like save(), findById(), findAll(), delete(), etc.
}
