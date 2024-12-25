package main.domain.classes.DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for the {@code Product} class.
 * Contains all necessary fields for transferring product data securely.
 */
public class ProductDTO implements Serializable {
    private int id;          // Unique ID of the product
    private String name;     // Name of the product
    private int price;       // Current price of the product

    /**
     * Default constructor for serialization/deserialization.
     */
    public ProductDTO() {}

    /**
     * Parameterized constructor for initializing {@code ProductDTO}.
     *
     * @param id    Unique ID of the product.
     * @param name  Name of the product.
     * @param price Current price of the product.
     */
    public ProductDTO(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // ==========================
    // Getters and Setters
    // ==========================

    /**
     * Gets the unique ID of the product.
     *
     * @return The unique ID of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the product.
     *
     * @param id The unique ID of the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current price of the product.
     *
     * @return The current price of the product.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the current price of the product.
     *
     * @param price The current price of the product.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    // ==========================
    // toString
    // ==========================

    /**
     * Represents the {@code ProductDTO} object as a string.
     *
     * @return A string representation of the {@code ProductDTO}.
     */
    @Override
    public String toString() {
        return "ProductDTO [ID=" + id + ", Name='" + name + "', Price=" + price + "]";
    }
}
