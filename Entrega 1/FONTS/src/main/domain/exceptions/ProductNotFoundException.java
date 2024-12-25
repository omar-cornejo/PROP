package main.domain.exceptions;

/**
 * Excepción lanzada cuando un producto no se encuentra en la matriz de similitud.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code ProductNotFoundException} con un mensaje detallado.
     *
     * @param productId El identificador del producto que no se encontró.
     */
    public ProductNotFoundException(int productId) {
        super("El producto con ID " + productId + " no existe en la matriz de similitud.");
    }
}
