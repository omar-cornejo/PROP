package main.domain.classes;

import main.domain.classes.DTO.UserDTO;
import main.domain.exceptions.InvalidPasswordException;
import main.domain.exceptions.InvalidUsernameException;

/**
 * Clase que representa un usuario con nombre completo, nombre de usuario y contraseña.
 */
public class User {
    private final String name; // Nombre completo del usuario
    private String username;   // Nombre único de usuario
    private String password;   // Contraseña del usuario

    /**
     * Constructor de la clase {@code User}.
     *
     * @param name     Nombre completo del usuario.
     * @param username Nombre único de usuario.
     * @param password Contraseña del usuario.
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    public User(String name, String username, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }

    // ==========================
    // Getters
    // ==========================

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    // ==========================
    // Métodos
    // ==========================

    /**
     * Autentica al usuario con un nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario proporcionado.
     * @param password Contraseña proporcionada.
     * @return {@code true} si las credenciales son correctas, {@code false} en caso contrario.
     * @throws IllegalArgumentException si el nombre de usuario o la contraseña son nulos.
     */
    public boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("El nombre de usuario y la contraseña no pueden ser nulos.");
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Actualiza el nombre de usuario.
     *
     * @param newUsername El nuevo nombre de usuario.
     * @throws InvalidUsernameException si el nuevo nombre de usuario es nulo o vacío.
     */
    public void setUsername(String newUsername) {
        if (newUsername == null || newUsername.trim().isEmpty()) {
            throw new InvalidUsernameException("El nuevo nombre de usuario no puede estar vacío.");
        }
        this.username = newUsername;
    }

    /**
     * Actualiza la contraseña del usuario.
     *
     * @param currentPassword Contraseña actual.
     * @param newPassword     Nueva contraseña.
     * @throws InvalidPasswordException si la contraseña actual no coincide con la almacenada
     *                                   o la nueva contraseña es inválida.
     * @throws IllegalArgumentException si la nueva contraseña está vacía.
     */
    public void setPassword(String currentPassword, String newPassword) throws InvalidPasswordException {
        if (currentPassword == null || newPassword == null) {
            throw new IllegalArgumentException("Las contraseñas no pueden ser nulas.");
        }
        if (newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("La nueva contraseña no puede estar vacía.");
        }
        if (!this.password.equals(currentPassword)) {
            throw new InvalidPasswordException("La contraseña actual es incorrecta.");
        }
        this.password = newPassword;
    }

    /**
     * Representa al usuario en formato de texto.
     *
     * @return Una cadena que incluye el nombre completo, nombre de usuario y contraseña del usuario.
     */
    @Override
    public String toString() {
        return "Usuario [Nombre completo='" + name + "', Nombre de usuario='" + username + "', Contraseña='" + password + "']";
    }
}
