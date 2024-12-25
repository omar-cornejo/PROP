package main.domain.controllers;

import main.domain.classes.DTO.ProductDTO;
import main.domain.classes.DTO.ShelfDTO;
import main.domain.classes.Product;
import main.domain.classes.Shelf;
import main.domain.exceptions.ShelfAlreadyExistsException;
import main.domain.exceptions.ShelfNotFoundException;
import main.domain.exceptions.ProductNotFoundInShelfException;
import main.persistence.controllers.CtrlPersistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la gestión de estanterías.
 * 
 * - Permite crear, actualizar y eliminar estanterías asociadas a un usuario.
 * - Administra la asignación y eliminación de productos dentro de las estanterías.
 * - Proporciona métodos para consultar los productos organizados en cada estantería.
 */

public class CtrlShelf {



    // Old Version
    private Map<String, HashMap<String, Shelf>> shelves; // Almacena los productos de cada usuario


    // Updated Version
    private Map<String, Shelf> userShelves;
    // Almacena las estanterías de cada usuario
    private static CtrlShelf singletonObject;
    private final CtrlPersistence ctrlPersistence;

    /**
     * Constructor de CtrlShelf
     * Inicializa el controlador con un conjunto vacío de estanterías.
     */
    public static CtrlShelf getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlShelf();
        return singletonObject;
    }

    /**
     * Constructora privada.
     */
    private CtrlShelf() {
        this.shelves = new HashMap<>();
        this.userShelves = new HashMap<>();
        this.ctrlPersistence = CtrlPersistence.getInstance();
    }

    public boolean loginUser(String username) {
        userShelves = new HashMap<>();

        // Carga todos las estanterias del usuario en memoria
        List<ShelfDTO> currentProducts = ctrlPersistence.getUserShelves(username);

        if (!currentProducts.isEmpty()) {
            currentProducts.forEach(shelf -> {
                userShelves.put(shelf.getName(), new Shelf(shelf));
            });
        }
        return true;
    }

    /**
     * Añade una nueva estantería al sistema si no existe otra con el mismo nombre.
     *
     * @param name         Nombre de la estantería
     * @param products     Lista de productos inicial para la estantería
     * @param userselected Nombre del usuario que obtendrá esta estantería
     * @throws ShelfAlreadyExistsException si la estantería ya existe
     */
    public void addShelf(String name, List<String> products, String userselected) throws ShelfAlreadyExistsException {
        // Updated Version
        Shelf newShelf = new Shelf(name, products);
        if (userShelves.isEmpty()) ctrlPersistence.createUserShelvesDirectory(userselected);
        if (!ctrlPersistence.addShelfToUser(new ShelfDTO(newShelf.getName(), newShelf.getProducts()), userselected)) {
            throw new RuntimeException("Error guardando la estantería");
        }else {
            userShelves.put(name, newShelf);
        }
    }

    /**
     * Elimina una estantería del sistema.
     *
     * @param name         Nombre de la estantería a eliminar
     * @param userselected Usuario que posee la estantería
     * @throws ShelfNotFoundException si la estantería no existe
     */
    public void removeShelfForUser(String name, String userselected) throws ShelfNotFoundException {
        // Updated Version
        userShelves.remove(name);
        if (!ctrlPersistence.removeShelfOfUser(name, userselected)) {
            throw new RuntimeException("Error al eliminar una estantería del usuario autenticado");
        }
    }

    /**
     * Obtiene la lista de productos de una estantería específica.
     *
     * @param shelfName    Nombre de la estantería
     * @param userselected Usuario que posee la estantería
     * @return Lista de productos en la estantería
     * @throws ShelfNotFoundException si la estantería no existe
     */
    public List<String> getProductsInUserShelf(String shelfName, String userselected) throws ShelfNotFoundException {
        // Updated Version

        ShelfDTO shelfDTO = ctrlPersistence.getShelfOfUser(shelfName, userselected);

        if (shelfDTO == null) {
            throw new ShelfNotFoundException(shelfName);
        }

        return shelfDTO.getProducts();
    }

    /**
     * Añade un producto a una estantería específica si el producto no está duplicado.
     *
     * @param shelfName    Nombre de la estantería
     * @param product      Producto a añadir
     * @param userselected Usuario que posee la estantería
     * @throws ShelfNotFoundException si la estantería no existe
     * @throws IllegalArgumentException si el producto ya está en la estantería
     */
    public void addProductToShelfForUser(String shelfName, String product, String userselected) throws ShelfNotFoundException {
        ShelfDTO shelfDTO = ctrlPersistence.getShelfOfUser(shelfName, userselected);
        if (shelfDTO == null) {
            throw new ShelfNotFoundException(shelfName);
        }

        List<String> products = shelfDTO.getProducts();
        if (products.contains(product)) {
            throw new IllegalArgumentException("El producto " + product + " ya existe en la estantería " + shelfName);
        }

        products.add(product);

        // Actualiza la persistencia
        if (!ctrlPersistence.removeShelfOfUser(shelfName, userselected)) {
            throw new RuntimeException("Error al actualizar la estantería " + shelfName + " para el usuario " + userselected);
        }
        if (!ctrlPersistence.addShelfToUser(new ShelfDTO(shelfDTO.getName(), products), userselected)) {
            throw new RuntimeException("Error al actualizar la estantería " + shelfName + " para el usuario " + userselected);
        }

    }

    /**
     * Elimina un producto de una estantería específica.
     *
     * @param shelfName    Nombre de la estantería
     * @param product      Producto a eliminar
     * @param userselected Usuario que posee la estantería
     * @throws ShelfNotFoundException si la estantería no existe
     * @throws ProductNotFoundInShelfException si el producto no está en la estantería
     */
    public void removeProductFromShelfForUser(String shelfName, String product, String userselected)
            throws ShelfNotFoundException, ProductNotFoundInShelfException {
        ShelfDTO shelfDTO = ctrlPersistence.getShelfOfUser(shelfName, userselected);
        if (shelfDTO == null) {
            throw new ShelfNotFoundException(shelfName);
        }

        List<String> products = shelfDTO.getProducts();
        if (!products.contains(product)) {
            throw new ProductNotFoundInShelfException(product);
        }

        products.remove(product);

        // Actualiza la persistencia
        if (!ctrlPersistence.removeShelfOfUser(shelfName, userselected)) {
            throw new RuntimeException("Error al actualizar la estantería " + shelfName + " para el usuario " + userselected);
        }
        if (!ctrlPersistence.addShelfToUser(new ShelfDTO(shelfDTO.getName(), products), userselected)) {
            throw new RuntimeException("Error al actualizar la estantería " + shelfName + " para el usuario " + userselected);
        }
    }

    /**
     * Verifica si el usuario ya tiene una estantería con el nombre dado.
     *
     * @param shelfName    Nombre de la estantería a verificar
     * @param userselected Nombre del usuario que posee la estantería
     * @return true si la estantería ya existe para el usuario, false si no
     */
    public boolean hasShelf(String shelfName, String userselected)  {
        ShelfDTO shelfDTO = ctrlPersistence.getShelfOfUser(shelfName, userselected);
        return shelfDTO != null;
    }
}
