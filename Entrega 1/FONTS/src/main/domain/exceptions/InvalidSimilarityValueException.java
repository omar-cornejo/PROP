package main.domain.exceptions;

/**
 * Excepción personalizada que se lanza cuando un valor de similitud proporcionado es inválido.
 *
 * <p>El valor de similitud debe estar en el rango de 0.0 a 1.0. Si se proporciona un valor fuera
 * de este rango, se lanza esta excepción para indicar el error.</p>
 */
public class InvalidSimilarityValueException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code InvalidSimilarityValueException} con un mensaje detallado.
     *
     * @param similarity El valor de similitud que causó la excepción.
     */
    public InvalidSimilarityValueException(double similarity) {
        super("El valor de similitud " + similarity + " es inválido. Debe estar entre 0.0 y 1.0.");
    }
}
