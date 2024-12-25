package main.domain.exceptions;

/**
 * Excepción personalizada que se lanza cuando se intenta realizar una operación inválida
 * sobre una estantería.
 *
 * <p>Esta excepción extiende {@link RuntimeException} y se utiliza para señalar errores
 * específicos relacionados con la gestión de estanterías en el sistema.</p>
 */
public class InvalidShelfOperationException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code InvalidShelfOperationException} con un mensaje detallado.
     *
     * @param message Mensaje que describe la operación inválida que causó la excepción.
     */
    public InvalidShelfOperationException(String message) {
        super(message);
    }
}
