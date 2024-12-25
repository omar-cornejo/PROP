package main.domain.controllers;

import main.domain.classes.Shelf;
import main.domain.exceptions.ShelfAlreadyExistsException;
import main.domain.exceptions.ShelfNotFoundException;
import main.domain.exceptions.ProductNotFoundException;

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

    private Map<String, HashMap<String, Shelf>> shelves; // Almacena las estanterías de cada usuario
    private static CtrlShelf singletonObject;

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
    }

    /**
     * Añade una nueva estantería al sistema si no existe otra con el mismo nombre.
     *
     * @param name         Nombre de la estantería
     * @param products     Lista de productos inicial para la estantería
     * @param userselected Nombre del usuario que obtendrá esta estantería
     * @throws ShelfAlreadyExistsException si la estantería ya existe
     */
    public void addShelf(String name, List<Integer> products, String userselected) throws ShelfAlreadyExistsException {
        HashMap<String, Shelf> userShelves = shelves.computeIfAbsent(userselected, k -> new HashMap<>());
        if (userShelves.containsKey(name)) {
            throw new ShelfAlreadyExistsException(name);
        }
        Shelf newShelf = new Shelf(name, products);
        userShelves.put(name, newShelf);
    }

    /**
     * Elimina una estantería del sistema.
     *
     * @param name         Nombre de la estantería a eliminar
     * @param userselected Usuario que posee la estantería
     * @throws ShelfNotFoundException si la estantería no existe
     */
    public void removeShelfForUser(String name, String userselected) throws ShelfNotFoundException {
        HashMap<String, Shelf> userShelves = shelves.get(userselected);
        if (userShelves == null || userShelves.remove(name) == null) {
            throw new ShelfNotFoundException(name);
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
    public List<Integer> getProductsInUserShelf(String shelfName, String userselected) throws ShelfNotFoundException {
        HashMap<String, Shelf> userShelves = shelves.get(userselected);
        if (userShelves == null || !userShelves.containsKey(shelfName)) {
            throw new ShelfNotFoundException(shelfName);
        }
        return userShelves.get(shelfName).getProducts();
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
    public void addProductToShelfForUser(String shelfName, int product, String userselected) throws ShelfNotFoundException {
        HashMap<String, Shelf> userShelves = shelves.get(userselected);
        if (userShelves == null || !userShelves.containsKey(shelfName)) {
            throw new ShelfNotFoundException(shelfName);
        }
        Shelf shelf = userShelves.get(shelfName);
        if (!shelf.addProduct(product)) {
            throw new IllegalArgumentException("El producto " + product + " ya está en la estantería.");
        }
    }

    /**
     * Elimina un producto de una estantería específica.
     *
     * @param shelfName    Nombre de la estantería
     * @param product      Producto a eliminar
     * @param userselected Usuario que posee la estantería
     * @throws ShelfNotFoundException si la estantería no existe
     * @throws ProductNotFoundException si el producto no está en la estantería
     */
    public void removeProductFromShelfForUser(String shelfName, int product, String userselected)
            throws ShelfNotFoundException, ProductNotFoundException {
        HashMap<String, Shelf> userShelves = shelves.get(userselected);
        if (userShelves == null || !userShelves.containsKey(shelfName)) {
            throw new ShelfNotFoundException(shelfName);
        }
        Shelf shelf = userShelves.get(shelfName);
        if (!shelf.removeProduct(product)) {
            throw new ProductNotFoundException(product);
        }
    }

    /**
     * Verifica si el usuario ya tiene una estantería con el nombre dado.
     *
     * @param shelfName    Nombre de la estantería a verificar
     * @param userselected Nombre del usuario que posee la estantería
     * @return true si la estantería ya existe para el usuario, false si no
     */
    public boolean hasShelf(String shelfName, String userselected) {
        HashMap<String, Shelf> userShelves = shelves.get(userselected);
        return userShelves != null && userShelves.containsKey(shelfName);
    }
}
