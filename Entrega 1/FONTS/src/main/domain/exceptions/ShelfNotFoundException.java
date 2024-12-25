package main.domain.exceptions;

/**
 * Excepción lanzada cuando no se encuentra una estantería con el nombre especificado.
 */
public class ShelfNotFoundException extends RuntimeException {

    /**
     * Crea una nueva instancia de {@code ShelfNotFoundException} con un mensaje detallado.
     *
     * @param shelfName El nombre de la estantería que no se encontró.
     */
    public ShelfNotFoundException(String shelfName) {
        super("La estantería '" + shelfName + "' no se encontró.");
    }
}
