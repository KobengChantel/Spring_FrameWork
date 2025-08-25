package tacos.data;
// Declares this class as part of the tacos.data package, which contains data access logic.

import java.sql.Connection;        // Represents a connection to the database.
import java.sql.PreparedStatement; // Precompiled SQL statement for executing queries.
import java.sql.ResultSet;         // Stores results from SQL queries.
import java.sql.SQLException;      // Exception handling for database access errors.
import java.util.ArrayList;        // Used to store lists of Ingredient objects.
import java.util.List;             // Interface for ordered collections (lists).
import java.util.Optional;         // Wraps values that might be null, avoiding NullPointerExceptions.

import javax.sql.DataSource;       // Provides database connections (connection pool).

import org.springframework.jdbc.core.JdbcTemplate;
// Imported for comparison (not directly used here), to show how Spring simplifies JDBC work.

import tacos.Ingredient;
// Imports the Ingredient domain object.

// This class is a manual JDBC implementation of IngredientRepository, written to compare
// the complexity of raw JDBC with the simplicity of using Spring's JdbcTemplate.

public class RawJdbcIngredientRepository implements IngredientRepository {

  private DataSource dataSource;
  // DataSource provides the database connections needed for queries.

  public RawJdbcIngredientRepository(DataSource dataSource) {
    this.dataSource = dataSource;
    // Constructor initializes repository with a DataSource dependency.
  }

  @Override
  public Iterable<Ingredient> findAll() {
    List<Ingredient> ingredients = new ArrayList<>();
    // Holds all ingredients retrieved from the database.

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    // JDBC objects declared for manual DB operations.

    try {
      connection = dataSource.getConnection();
      // Open a database connection.

      statement = connection.prepareStatement(
              "select id, name, type from Ingredient");
      // SQL query to fetch all ingredients.

      resultSet = statement.executeQuery();
      // Execute query and store results.

      while(resultSet.next()) {
        // Iterate through results.
        Ingredient ingredient = new Ingredient(
                resultSet.getString("id"),   // Get ID column.
                resultSet.getString("name"), // Get name column.
                Ingredient.Type.valueOf(resultSet.getString("type"))); // Convert type to enum.
        ingredients.add(ingredient);
        // Add ingredient to the list.
      }
    } catch (SQLException e) {
      // Error handling block (currently not implemented).
    } finally {
      // Always close JDBC resources in reverse order of opening.
      if (resultSet != null) {
        try { resultSet.close(); } catch (SQLException e) {}
      }
      if (statement != null) {
        try { statement.close(); } catch (SQLException e) {}
      }
      if (connection != null) {
        try { connection.close(); } catch (SQLException e) {}
      }
    }
    return ingredients;
    // Return the list of ingredients.
  }

  @Override
  public Optional<Ingredient> findById(String id) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = dataSource.getConnection();
      // Open database connection.

      statement = connection.prepareStatement(
              "select id, name, type from Ingredient where id=?");
      // SQL query with placeholder for ID.

      statement.setString(1, id);
      // Replace ? with the provided ID.

      resultSet = statement.executeQuery();
      // Execute query and fetch result.

      Ingredient ingredient = null;
      if(resultSet.next()) {
        // If ingredient found, map row to Ingredient object.
        ingredient = new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
      }
      return Optional.of(ingredient);
      // Wrap the ingredient in Optional (⚠️ can throw exception if null).
    } catch (SQLException e) {
      // Error handling block (currently not implemented).
    } finally {
      // Close JDBC resources.
      if (resultSet != null) {
        try { resultSet.close(); } catch (SQLException e) {}
      }
      if (statement != null) {
        try { statement.close(); } catch (SQLException e) {}
      }
      if (connection != null) {
        try { connection.close(); } catch (SQLException e) {}
      }
    }
    return Optional.empty();
    // Return empty Optional if nothing found or error occurred.
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    // Not implemented – this was left out because only one method was needed for comparison.
    return null;
  }

}
