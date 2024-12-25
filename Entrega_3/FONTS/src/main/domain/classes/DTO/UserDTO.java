package main.domain.classes.DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) for the {@code User} class.
 * Contains all necessary fields for transferring user data securely.
 */
public class UserDTO implements Serializable {
    private String name;     // Full name of the user
    private String username; // Unique username
    private String password; // User's password

    /**
     * Default constructor for serialization/deserialization.
     */
    public UserDTO() {}

    /**
     * Parameterized constructor for initializing {@code UserDTO}.
     *
     * @param name     Full name of the user.
     * @param username Unique username.
     * @param password User's password.
     */
    public UserDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // ==========================
    // Getters and Setters
    // ==========================

    /**
     * Gets the full name of the user.
     *
     * @return The full name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the user.
     *
     * @param name The full name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the username of the user.
     *
     * @return The unique username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The unique username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // ==========================
    // toString
    // ==========================

    /**
     * Represents the {@code UserDTO} object as a string.
     *
     * @return A string representation of the {@code UserDTO}.
     */
    @Override
    public String toString() {
        return "UserDTO [Name='" + name + "', Username='" + username + "', Password='" + password + "']";
    }
}
