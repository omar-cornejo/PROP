package main.domain.classes.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) for the {@code Shelf} class.
 * Contains all necessary fields for transferring shelf data securely.
 */
public class ShelfDTO implements Serializable {
    private String name;              // Unique name of the shelf
    private List<String> products;   // List of product names in the shelf

    /**
     * Default constructor for serialization/deserialization.
     */
    public ShelfDTO() {
        this.products = new ArrayList<>();
    }

    /**
     * Parameterized constructor for initializing {@code ShelfDTO}.
     *
     * @param name     Unique name of the shelf.
     * @param products List of product names in the shelf.
     */
    public ShelfDTO(String name, List<String> products) {
        this.name = name;
        this.products = (products != null) ? new ArrayList<>(products) : new ArrayList<>();
    }


    // ==========================
    // Getters and Setters
    // ==========================

    /**
     * Gets the name of the shelf.
     *
     * @return The name of the shelf.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the shelf.
     *
     * @param name The name of the shelf.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of product names in the shelf.
     *
     * @return The list of product names.
     */
    public List<String> getProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Sets the list of product names in the shelf.
     *
     * @param products The list of product names.
     */
    public void setProducts(List<String> products) {
        this.products = (products != null) ? new ArrayList<>(products) : new ArrayList<>();
    }

    // ==========================
    // toString
    // ==========================

    /**
     * Represents the {@code ShelfDTO} object as a string.
     *
     * @return A string representation of the {@code ShelfDTO}.
     */
    @Override
    public String toString() {
        return "ShelfDTO [Name='" + name + "', Products=" + products + "]";
    }
}
