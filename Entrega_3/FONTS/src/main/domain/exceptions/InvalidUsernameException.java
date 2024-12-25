package main.domain.exceptions;

/**
 * Excepción personalizada que se lanza cuando el nombre de usuario proporcionado es inválido.
 *
 * <p>Se considera inválido un nombre de usuario que sea nulo, esté vacío o no cumpla con los
 * criterios definidos en la lógica de negocio del sistema.</p>
 */
public class InvalidUsernameException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code InvalidUsernameException} con un mensaje detallado.
     *
     * @param message El mensaje que describe la razón por la cual el nombre de usuario es inválido.
     */
    public InvalidUsernameException(String message) {
        super(message);
    }
}
