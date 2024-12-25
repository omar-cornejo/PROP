package main.domain.controllers;

import main.domain.classes.Product;
import main.domain.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador dedicado a la gestión de productos en el sistema.
 * 
 * - Permite añadir, modificar y eliminar productos asociados a usuarios.
 * - Administra la relación entre productos y similitudes.
 * - Proporciona acceso a los atributos y detalles de los productos.
 */


public class CtrlProduct {

    private Map<String, HashMap<String, Product>> products; // Almacena los productos de cada usuario
    private static CtrlProduct singletonObject;
    private int productIdCounter;

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
        this.productIdCounter = 1;
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
        HashMap<String, Product> userProducts = products.computeIfAbsent(userName, k -> new HashMap<>());
        if (userProducts.containsKey(name)) {
            throw new ProductAlreadyExistsExceptionByName("El producto '" + name + "' ya existe para el usuario '" + userName + "'.");
        }
        Product newProduct = new Product(productIdCounter++, name, price); // Asigna el ID y luego incrementa
        userProducts.put(name, newProduct);
    }

    /**
     * Elimina un producto del inventario del usuario seleccionado.
     *
     * @param userName Nombre del usuario
     * @param name     Nombre del producto
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductNotFoundExceptionByName si el producto no existe
     */
    public void removeProductUser(String userName, String name) {
        HashMap<String, Product> userProducts = products.get(userName);
        if (userProducts == null) {
            throw new UserNotFoundException("El usuario '" + userName + "' no tiene productos registrados.");
        }
        if (!userProducts.containsKey(name)) {
            throw new ProductNotFoundExceptionByName("El producto '" + name + "' no existe para el usuario '" + userName + "'.");
        }
        userProducts.remove(name);
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
        HashMap<String, Product> userProducts = products.get(userName);
        if (userProducts == null) {
            throw new UserNotFoundException("El usuario '" + userName + "' no tiene productos registrados.");
        }
        Product product = userProducts.get(name);
        if (product == null) {
            throw new ProductNotFoundExceptionByName("El producto '" + name + "' no existe para el usuario '" + userName + "'.");
        }
        product.setPrice(newPrice);
    }

    /**
     * Obtiene los detalles de un producto específico del usuario seleccionado.
     *
     * @param userName Nombre del usuario
     * @param name     Nombre del producto
     * @return Cadena con los atributos del producto
     * @throws UserNotFoundException si el usuario no existe
     * @throws ProductNotFoundExceptionByName si el producto no existe
     */
    public String getProductAttributesUser(String userName, String name) {
        HashMap<String, Product> userProducts = products.get(userName);
        if (userProducts == null) {
            throw new UserNotFoundException("El usuario '" + userName + "' no tiene productos registrados.");
        }
        Product product = userProducts.get(name);
        if (product == null) {
            throw new ProductNotFoundExceptionByName("El producto '" + name + "' no existe para el usuario '" + userName + "'.");
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
        HashMap<String, Product> userProducts = products.get(userName);
        if (userProducts == null) {
            throw new UserNotFoundException("El usuario '" + userName + "' no tiene productos registrados.");
        }
        List<Integer> productIds = new ArrayList<>();
        for (Product product : userProducts.values()) {
            productIds.add(product.getId());
        }
        return productIds;
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
        HashMap<String, Product> userProducts = products.get(userName);
        if (userProducts == null) {
            throw new UserNotFoundException("El usuario '" + userName + "' no tiene productos registrados.");
        }
        Product product = userProducts.get(productName);
        if (product == null) {
            throw new ProductNotFoundExceptionByName(productName);
        }
        return product.getId();
    }
}
