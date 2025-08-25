package tacos.data;
// Declares the package name as "tacos.data", grouping repository-related classes.

//class provides a JDBC-based implementation of the IngredientRepository, using JdbcTemplate to query, insert, and map database records into Ingredient objects.

import java.sql.ResultSet;
// Provides a way to work with database query results.

import java.sql.SQLException;
// Exception thrown when database access errors occur.

import java.util.List;
// Provides the List interface for working with ordered collections.

import java.util.Optional;
// Wraps return values that may or may not be present, avoiding nulls.

import org.springframework.jdbc.core.JdbcTemplate;
// Core Spring class that simplifies interaction with relational databases.

import org.springframework.stereotype.Repository;
// Marks this class as a Spring Repository component for dependency injection.

import tacos.Ingredient;
// Imports the Ingredient entity to map database rows into objects.

// This class implements IngredientRepository using Spring's JdbcTemplate to perform SQL queries for Ingredient entities.

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;
  // JdbcTemplate is used to execute SQL queries and updates.

  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    // Constructor injection of JdbcTemplate for database access.
  }

  @Override
  public Iterable<Ingredient> findAll() {
    // Executes a SQL query to retrieve all rows from Ingredient table.
    return jdbcTemplate.query(
            "select id, name, type from Ingredient",
            this::mapRowToIngredient);  // Maps each row to an Ingredient object.
  }

  @Override
  public Optional<Ingredient> findById(String id) {
    // Executes a SQL query to find an Ingredient by its ID.
    List<Ingredient> results = jdbcTemplate.query(
            "select id, name, type from Ingredient where id=?",
            this::mapRowToIngredient,
            id);
    // Returns Optional.empty() if no result is found, otherwise wraps the found Ingredient.
    return results.size() == 0 ?
            Optional.empty() :
            Optional.of(results.get(0));
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    // Executes an insert statement to save a new Ingredient into the database.
    jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType().toString());
    return ingredient;  // Returns the saved Ingredient.
  }

  private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
          throws SQLException {
    // Maps a single row of the ResultSet into an Ingredient object.
    return new Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type")));
  }

  /*
  Alternative implementation of findById using queryForObject with a RowMapper.
  Shows another way to map database results to an Ingredient object.
   */
}
