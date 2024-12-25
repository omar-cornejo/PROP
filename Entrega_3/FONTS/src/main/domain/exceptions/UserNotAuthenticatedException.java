package main.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta realizar una operación que requiere
 * que un usuario esté autenticado, pero no hay ningún usuario autenticado en el sistema.
 *
 * <p>Esta excepción se utiliza para garantizar que ciertas acciones solo puedan
 * ejecutarse si un usuario ha iniciado sesión previamente.</p>
 */
public class UserNotAuthenticatedException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code UserNotAuthenticatedException} con un
     * mensaje predeterminado indicando que no hay un usuario autenticado.
     */
    public UserNotAuthenticatedException() {
        super("No hay un usuario autenticado en el sistema.");
    }
}
