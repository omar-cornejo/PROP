package main.domain.exceptions;

/**
 * Excepción personalizada que se lanza cuando una contraseña es inválida o incorrecta.
 *
 * <p>Esta excepción extiende {@link RuntimeException} y se utiliza para señalar errores
 * relacionados con el manejo de contraseñas, como contraseñas incorrectas o que no cumplen
 * con ciertos criterios.</p>
 */
public class InvalidPasswordException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code InvalidPasswordException} con un mensaje detallado.
     *
     * @param message Mensaje que describe el motivo por el cual la contraseña es inválida.
     */
    public InvalidPasswordException(String message) {
        super(message);
    }
}
