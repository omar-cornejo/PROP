package main.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta acceder a un usuario que no existe en el sistema.
 *
 * <p>Esta excepción se utiliza para indicar que un usuario con el nombre de usuario especificado
 * no fue encontrado en la base de datos o sistema actual.</p>
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code UserNotFoundException}.
     *
     * @param username El nombre de usuario que no fue encontrado.
     */
    public UserNotFoundException(String username) {
        super("Usuario con nombre de usuario '" + username + "' no encontrado.");
    }
}
