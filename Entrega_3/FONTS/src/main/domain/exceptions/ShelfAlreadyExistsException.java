package main.domain.exceptions;

/**
 * Excepción lanzada cuando se intenta crear una estantería que ya existe.
 */
public class ShelfAlreadyExistsException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code ShelfAlreadyExistsException} con un mensaje detallado.
     *
     * @param shelfName El nombre de la estantería que ya existe.
     */
    public ShelfAlreadyExistsException(String shelfName) {
        super("La estantería '" + shelfName + "' ya existe.");
    }
}
