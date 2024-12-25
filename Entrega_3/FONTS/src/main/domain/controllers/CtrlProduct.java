package main.domain.controllers;

import main.domain.classes.DTO.ProductDTO;
import main.domain.classes.Product;
import main.domain.exceptions.*;
import main.persistence.controllers.CtrlPersistence;

import java.util.*;

/**
 * Controlador dedicado a la gestión de productos en el sistema.
 *
 * - Permite añadir, modificar y eliminar productos asociados a usuarios.
 * - Administra la relación entre productos y similitudes.
 * - Proporciona acceso a los atributos y detalles de los productos.
 */
public class CtrlProduct {

    // Old Version
    private Map<String, HashMap<String, Product>> products; // Almacena los productos de cada usuario

    // Updated Version
    private Map<String, Product> userProducts;
    private int productIdCounter;
    private String currentUserUsername;
    private static CtrlProduct singletonObject;
    private final CtrlPersistence ctrlPersistence;

    /**
     * Constructor de CtrlProduct
     * Inicializa el controlador con un conjunto vacío de productos.
     */
    public static CtrlProduct getInstance() {
        if (singletonObject == null) {
            singletonObject = new CtrlProduct();
        }
        return singletonObject;
    }

    private CtrlProduct() {
        this.products = new HashMap<>();

        // Updated Version
        this.userProducts = null;
        this.currentUserUsername = null;
        this.ctrlPersistence = CtrlPersistence.getInstance();
    }

    /**
     * Inicia sesión con un usuario específico.
     *
     * @param username Nombre del usuario
     * @return true si el inicio de sesión es exitoso
     */
    public boolean loginUser(String username) {
        currentUserUsername = username;

        userProducts = new HashMap<>();
        if (userProducts.isEmpty()) ctrlPersistence.createUserProductDirectory(currentUserUsername);
        productIdCounter = ctrlPersistence.loadProductCounter(username);

        // Carga todos los productos del usuario en memoria
        List<ProductDTO> currentProducts = ctrlPersistence.getUserProducts(username);

        if (!currentProducts.isEmpty()) {
            currentProducts.forEach(product -> {
                userProducts.put(product.getName(), new Product(product));
            });
        }
        return true;
    }

    /**
     * Cierra sesión del usuario actual, guardando el estado del productCounter.
     */
    public boolean logoutUser() {
        if (currentUserUsername != null) {
            ctrlPersistence.saveProductCounter(currentUserUsername, productIdCounter);
        }
        currentUserUsername = null;
        userProducts = null;

        return true;
    }

    /**
     * Añade un producto al inventario del usuario seleccionado si no existe otro con el mismo nombre.
     *
     * @param userName Nombre del usuario
     * @param name     Nombre del producto
     * @param price    Precio del producto
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductAlreadyExistsException si el producto ya existe
     */
    public void addProductUser(String userName, String name, int price) {
        int a = productIdCounter;
        Product newProduct = new Product(a, name, price);
        if (userProducts.isEmpty()) ctrlPersistence.createUserProductDirectory(currentUserUsername);
        if (!ctrlPersistence.addProductToUser(new ProductDTO(newProduct.getId(), newProduct.getName(), newProduct.getPrice()), currentUserUsername)) {
            throw new RuntimeException("Producto ya existe");
        }
        else {
            productIdCounter++;
            userProducts.put(name, newProduct);
        }
    }

    /**
     * Elimina un producto del inventario del usuario seleccionado.
     * @param productName Nombre del producto
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductNotFoundExceptionByName si el producto no existe
     */
    public void removeProductUser(String productName) {
        userProducts.remove(productName);
        if (!ctrlPersistence.removeProductOfUser(productName, currentUserUsername)) {
            throw new RuntimeException("Error al eliminar un producto del usuario autenticado");
        }
    }

    /**
     * Modifica el precio de un producto en el inventario del usuario seleccionado.
     *
     * @param userName Nombre del usuario
     * @param name     Nombre del producto
     * @param newPrice Nuevo precio del producto
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductNotFoundExceptionByName si el producto no existe
     */
    public void updateProductUser(String userName, String name, int newPrice) {
        if (userProducts == null || userProducts.isEmpty()) {
            throw new UserNotFoundException(userName);
        }

        Product currentProduct = userProducts.get(name);

        if (currentProduct == null) {
            throw new ProductNotFoundExceptionByName(name);
        }

        currentProduct.setPrice(newPrice);

        ctrlPersistence.addProductToUser(
                new ProductDTO(currentProduct.getId(), currentProduct.getName(), currentProduct.getPrice()),
                currentUserUsername
        );
    }

    /**
     * Obtiene los detalles de un producto específico del usuario seleccionado.
     *
     * @param userName Nombre del usuario
     * @param name     Nombre del producto
     * @return Cadena con los atributos del producto
     * @throws UserNotFoundException si el usuario no tiene productos registrados
     * @throws ProductNotFoundExceptionByName si el producto no existe
     */
    public String getProductAttributesUser(String userName, String name) {
        if (userProducts == null || userProducts.isEmpty()) {
            throw new UserNotFoundException(userName);
        }

        Product product = userProducts.get(name);

        if (product == null) {
            throw new ProductNotFoundExceptionByName(name);
        }

        return product.toString();
    }

    /**
     * Devuelve una lista con los IDs de todos los productos de un usuario específico.
     *
     * @param userName Nombre del usuario
     * @return Lista de IDs de productos
     * @throws UserNotFoundException si el usuario no existe
     */
    public List<Integer> getProductIdsForUser(String userName) {
        List<Integer> productIds = new ArrayList<>();
        for (Product product : userProducts.values()) {
            productIds.add(product.getId());
        }
        return productIds;
    }

    /**
     * Devuelve una lista con los nombres de todos los productos de un usuario específico.
     *
     * @param userName Nombre del usuario
     * @return Lista de nombres de productos
     * @throws UserNotFoundException si el usuario no existe
     */
    public List<String> getProductNamesForUser(String userName) {
        List<String> productNames = new ArrayList<>();
        for (Product product : userProducts.values()) {
            productNames.add(product.getName());
        }
        return productNames;
    }

    /**
     * Devuelve el ID de un producto dado su nombre y el usuario propietario.
     *
     * @param userName    Nombre del usuario
     * @param productName Nombre del producto
     * @return ID del producto
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductNotFoundExceptionByName si el producto no existe
     */
    public Integer getProductIdByName(String userName, String productName) {
        if (!userProducts.containsKey(productName)) {
            throw new ProductNotFoundExceptionByName(productName);
        }
        return userProducts.get(productName).getId();
    }

    /**
     * Devuelve los nombres de los productos dado sus Ids y el usuario propietario.
     *
     * @param userName    Nombre del usuario
     * @param productsIds Ids de los productos
     * @return Lista de nombres de productos
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductNotFoundException si alguno de los productos no existe
     */
    public List<String> getProductsNamesByIds(String userName, List<Integer> productsIds) {
        Map<String, Product> userProducts = this.userProducts;

        if (userProducts == null) {
            throw new UserNotFoundException(userName);
        }

        List<String> productNames = new ArrayList<>();
        for (Integer id : productsIds) {
            boolean found = false;
            for (Product product : userProducts.values()) {
                if (product.getId() == id) { // Comparar IDs
                    productNames.add(product.getName());
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new ProductNotFoundException(id);
            }
        }
        return productNames;
    }
}
