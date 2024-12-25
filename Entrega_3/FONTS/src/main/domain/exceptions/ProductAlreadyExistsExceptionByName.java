package main.domain.exceptions;

/**
 * Excepción que se lanza cuando se intenta añadir un producto que ya existe por nombre.
 */
public class ProductAlreadyExistsExceptionByName extends RuntimeException {

  /**
   * Constructor de la excepción con un mensaje personalizado.
   *
   * @param message Mensaje descriptivo del error
   */
  public ProductAlreadyExistsExceptionByName(String message) {
    super(message);
  }
}
