package main.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta registrar o cambiar a un nombre de usuario que ya existe.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructor de la excepción con el nombre de usuario duplicado.
     *
     * @param username Nombre de usuario que ya existe.
     */
    public UserAlreadyExistsException(String username) {
        super("El nombre de usuario '" + username + "' ya existe en el sistema.");
    }
}
