package tacos.data;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

// 👉 Repository interface for CRUD operations on TacoOrder entities using Spring Data JPA with UUID as the ID type.
public interface OrderRepository
        extends CrudRepository<TacoOrder, UUID> {

}
