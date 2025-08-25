package tacos.data;

// Spring Data interface for basic CRUD operations
import org.springframework.data.repository.CrudRepository;

// Application-specific class for taco orders
import tacos.TacoOrder;

// 👉 This interface provides CRUD operations for TacoOrder objects using Spring Data JPA, with the ID type as Long.

public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
    // By extending CrudRepository, it automatically provides methods like save, findById, findAll, delete, etc.
}
