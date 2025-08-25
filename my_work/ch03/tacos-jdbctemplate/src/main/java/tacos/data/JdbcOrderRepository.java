package tacos.data;
// Declares the package name "tacos.data" for repository classes.

import java.sql.Types;
// Defines SQL type constants used in prepared statements.

import java.util.Arrays;
// Provides utility methods for working with arrays.

import java.util.Date;
// Represents dates (used for order and taco creation times).

import java.util.List;
// Provides the List interface for ordered collections.

import java.util.Optional;
// Represents a value that may or may not be present (to avoid nulls).

import org.springframework.asm.Type;
// Represents a type descriptor (⚠️ looks like a mistaken import — should be java.sql.Types or remove if unused).

import org.springframework.dao.IncorrectResultSizeDataAccessException;
// Exception thrown when a query returns an unexpected number of rows.

import org.springframework.jdbc.core.JdbcOperations;
// Interface for JdbcTemplate-like database operations.

import org.springframework.jdbc.core.PreparedStatementCreator;
// Used to create prepared SQL statements for queries/updates.

import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
// Factory for creating prepared statement creators with parameter types.

import org.springframework.jdbc.support.GeneratedKeyHolder;
// Holds auto-generated keys (e.g., database primary keys).

import org.springframework.stereotype.Repository;
// Marks this class as a repository bean in the Spring context.

import org.springframework.transaction.annotation.Transactional;
// Indicates that a method should run within a database transaction.

import tacos.IngredientRef;
// Represents a reference to an Ingredient (used when saving taco ingredients).

import tacos.Taco;
// Imports the Taco entity.

import tacos.TacoOrder;
// Imports the TacoOrder entity.

// This class implements OrderRepository using JdbcOperations to persist and retrieve TacoOrders, Tacos, and their ingredients from the database.

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private JdbcOperations jdbcOperations;
    // Provides database access methods.

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        // Constructor injection of JdbcOperations for DB access.
    }

    @Override
    @Transactional
    // Ensures the save operation runs inside a database transaction.
    public TacoOrder save(TacoOrder order) {
        // Prepares SQL for inserting a TacoOrder into the database.
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco_Order "
                                + "(delivery_name, delivery_street, delivery_city, "
                                + "delivery_state, delivery_zip, cc_number, "
                                + "cc_expiration, cc_cvv, placed_at) "
                                + "values (?,?,?,?,?,?,?,?,?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);  // Capture generated primary key.

        order.setPlacedAt(new Date());  // Set the current time as order placement.
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);  // Execute insert and store key.
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);  // Assign generated ID to the order.

        // Save tacos belonging to the order.
        List<Taco> tacos = order.getTacos();
        int i=0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }

        return order;  // Return the persisted order.
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());  // Set taco creation time.

        // Prepare SQL for inserting Taco linked to the order.
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, taco_order, taco_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);  // Assign generated taco ID.

        saveIngredientRefs(tacoId, taco.getIngredients());
        // Save ingredients linked to this taco.

        return tacoId;
    }

    private void saveIngredientRefs(
            long tacoId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs) {
            // Insert each ingredient reference for the taco.
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), tacoId, key++);
        }
    }

    @Override
    public Optional<TacoOrder> findById(Long id) {
        try {
            // Query for a TacoOrder by ID, mapping result set to TacoOrder.
            TacoOrder order = jdbcOperations.queryForObject(
                    "select id, delivery_name, delivery_street, delivery_city, "
                            + "delivery_state, delivery_zip, cc_number, cc_expiration, "
                            + "cc_cvv, placed_at from Taco_Order where id=?",
                    (row, rowNum) -> {
                        TacoOrder tacoOrder = new TacoOrder();
                        tacoOrder.setId(row.getLong("id"));
                        tacoOrder.setDeliveryName(row.getString("delivery_name"));
                        tacoOrder.setDeliveryStreet(row.getString("delivery_street"));
                        tacoOrder.setDeliveryCity(row.getString("delivery_city"));
                        tacoOrder.setDeliveryState(row.getString("delivery_state"));
                        tacoOrder.setDeliveryZip(row.getString("delivery_zip"));
                        tacoOrder.setCcNumber(row.getString("cc_number"));
                        tacoOrder.setCcExpiration(row.getString("cc_expiration"));
                        tacoOrder.setCcCVV(row.getString("cc_cvv"));
                        tacoOrder.setPlacedAt(new Date(row.getTimestamp("placed_at").getTime()));
                        tacoOrder.setTacos(findTacosByOrderId(row.getLong("id")));  // Load tacos for the order.
                        return tacoOrder;
                    }, id);
            return Optional.of(order);  // Wrap result in Optional.
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();  // Return empty if no result.
        }
    }

    private List<Taco> findTacosByOrderId(long orderId) {
        // Query for tacos belonging to an order.
        return jdbcOperations.query(
                "select id, name, created_at from Taco "
                        + "where taco_order=? order by taco_order_key",
                (row, rowNum) -> {
                    Taco taco = new Taco();
                    taco.setId(row.getLong("id"));
                    taco.setName(row.getString("name"));
                    taco.setCreatedAt(new Date(row.getTimestamp("created_at").getTime()));
                    taco.setIngredients(findIngredientsByTacoId(row.getLong("id")));
                    // Load ingredients for each taco.
                    return taco;
                },
                orderId);
    }

    private List<IngredientRef> findIngredientsByTacoId(long tacoId) {
        // Query for ingredients belonging to a taco.
        return jdbcOperations.query(
                "select ingredient from Ingredient_Ref "
                        + "where taco = ? order by taco_key",
                (row, rowNum) -> {
                    return new IngredientRef(row.getString("ingredient"));
                },
                tacoId);
    }
}
