package main.domain.exceptions;

/**
 * Excepción personalizada que se lanza cuando un valor de similitud entre productos
 * está fuera del rango permitido.
 *
 * <p>El valor de similitud debe estar dentro del rango permitido, típicamente entre 0.0 y 1.0.
 * Esta excepción ayuda a manejar errores relacionados con valores fuera de límites en operaciones
 * de similitud.</p>
 */
public class OutOfBoundSimilarityException extends Exception {

    /**
     * Crea una nueva instancia de {@code OutOfBoundSimilarityException} con un mensaje detallado.
     *
     * @param message El mensaje que describe el motivo del error, indicando los límites válidos.
     */
    public OutOfBoundSimilarityException(String message) {
        super(message);
    }
}
