package net.javaguides.springannotations.beans;

// This class represents a simple Book model with id, title, and description fields.

public class Book {
    // Unique identifier for the book
    private int id;

    // Title of the book
    private String title;

    // Short description of the book
    private String description;


    // Constructor to initialize a Book object with id, title, and description
    public Book(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getter method for book id
    public int getId() {
        return id;
    }

    // Setter method for book id
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for book title
    public String getTitle() {
        return title;
    }

    // Setter method for book title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for book description
    public String getDescription() {
        return description;
    }

    // Setter method for book description
    public void setDescription(String description) {
        this.description = description;
    }
}
