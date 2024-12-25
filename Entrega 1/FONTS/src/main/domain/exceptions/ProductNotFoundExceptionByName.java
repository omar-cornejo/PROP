package main.domain.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un producto por su nombre.
 */
public class ProductNotFoundExceptionByName extends RuntimeException {

    /**
     * Constructor de la excepción con un mensaje predefinido para un nombre de producto.
     *
     * @param productName Nombre del producto que no se encontró
     */
    public ProductNotFoundExceptionByName(String productName) {
        super("El producto con nombre '" + productName + "' no se encontró en el sistema.");
    }
}