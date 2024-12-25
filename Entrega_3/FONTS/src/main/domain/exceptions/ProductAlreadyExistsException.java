package main.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta añadir un producto que ya existe en la matriz de similitud.
 */
public class ProductAlreadyExistsException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code ProductAlreadyExistsException} con un mensaje detallado.
     *
     * @param productId El identificador del producto que ya existe.
     */
    public ProductAlreadyExistsException(int productId) {
        super("El producto con ID " + productId + " ya existe en la matriz de similitud.");
    }
}
