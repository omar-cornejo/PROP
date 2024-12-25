package main.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta consultar un conjunto de productos pero el usuario no tiene productos.
 *
  */
public class UserHasNoProductsException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code UserHasNoProducts}.
     *
     * @param username El usuario no tiene productos.
     */
    public UserHasNoProductsException(String username) {
        super("El usuario con nombre de usuario '" + username + "' no tiene productos registrados.");
    }
}
