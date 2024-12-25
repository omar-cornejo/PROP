]package main.domain.controllers;

import main.domain.classes.algorithm.BruteForceAlgorithm;
import main.domain.classes.algorithm.ShelfOrganizer;
import main.domain.classes.algorithm.TwoAproxAlgorithm;
import main.domain.enums.AlgorithmType;
import main.domain.exceptions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador principal del dominio, encargado de gestionar la interacción entre usuarios,
 * productos, estanterías y algoritmos.
 *
 * <p>Este controlador centraliza las operaciones principales del sistema, como la gestión de usuarios,
 * productos, estanterías y la organización de productos utilizando algoritmos específicos.</p>
 */
public class CtrlDomain {
    private static CtrlProduct ctrlProduct;
    private static CtrlShelf ctrlShelf;
    private static CtrlUser ctrlUser;
    private static CtrlDomain singletonObject;

    /**
     * Obtiene la instancia única (Singleton) del controlador de dominio.
     *
     * @return Instancia única de {@code CtrlDomain}.
     */
    public static CtrlDomain getInstance() {
        if (singletonObject == null) {
            singletonObject = new CtrlDomain();
        }
        return singletonObject;
    }

    /**
     * Constructor privado de {@code CtrlDomain}.
     * Inicializa las dependencias de controladores secundarios.
     */
    private CtrlDomain() {
        ctrlShelf = CtrlShelf.getInstance();
        ctrlProduct = CtrlProduct.getInstance();
        ctrlUser = CtrlUser.getInstance();
    }

    // ==============================
    // Gestión de Usuario y Autenticación
    // ==============================

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param name     Nombre completo del usuario.
     * @param username Nombre único de usuario.
     * @param password Contraseña del usuario.
     * @return {@code true} si el registro fue exitoso; {@code false} si el nombre de usuario ya existe.
     */
    public static boolean registerUser(String name, String username, String password) {
        return ctrlUser.registerUser(name, username, password);
    }

    /**
     * Autentica un usuario en el sistema.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return {@code true} si la autenticación es exitosa; {@code false} en caso contrario.
     */
    public static boolean loginUser(String username, String password) {
        return ctrlUser.loginUser(username, password);
    }

    /**
     * Cierra la sesión del usuario autenticado.
     *
     * @return {@code true} si el cierre de sesión fue exitoso; {@code false} si no hay usuario autenticado.
     */
    public static boolean logoutUser() {
        return ctrlUser.logoutUser();
    }

    /**
     * Cambia el nombre de usuario del usuario autenticado.
     *
     * @param oldUsername Nombre de usuario actual.
     * @param newUsername Nuevo nombre de usuario.
     * @return {@code true} si el cambio fue exitoso; {@code false} si el nuevo nombre ya existe.
     */
    public static boolean updateUsername(String oldUsername, String newUsername) {
        return ctrlUser.updateUsername(oldUsername, newUsername);
    }

    /**
     * Cambia la contraseña del usuario autenticado.
     *
     * @param currentPassword Contraseña actual.
     * @param newPassword     Nueva contraseña.
     * @return {@code true} si el cambio fue exitoso; {@code false} si la contraseña actual no coincide.
     */
    public static boolean updatePassword(String currentPassword, String newPassword) {
        return ctrlUser.updatePassword(currentPassword, newPassword);
    }

    /**
     * Verifica si un usuario existe en el sistema.
     *
     * @param username Nombre de usuario.
     * @return {@code true} si el usuario existe; {@code false} en caso contrario.
     */
    public static boolean existUser(String username) {
        return ctrlUser.existUser(username);
    }

    /**
     * Obtiene el nombre de usuario del usuario autenticado.
     *
     * @return Nombre de usuario autenticado o {@code null} si no hay usuario autenticado.
     */
    public static String getUsernameSelected() {
        return ctrlUser.getUsernameSelected();
    }

    /**
     * Obtiene los detalles del usuario autenticado.
     *
     * @return Detalles del usuario autenticado en formato de cadena.
     * @throws UserNotAuthenticatedException Si no hay usuario autenticado.
     */
    public static String getUserDetails() throws UserNotAuthenticatedException {
        return ctrlUser.getAuthenticatedUserDetails();
    }

    // ==============================
    // Gestión de Productos
    // ==============================

    /**
     * Añade un producto al inventario del usuario autenticado.
     *
     * @param name  Nombre del producto.
     * @param price Precio del producto.
     * @return {@code true} si el producto fue añadido; {@code false} si ya existe.
     * @throws IllegalArgumentException Si el precio es negativo.
     */
    public static boolean addProductUser(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser != null) {
            ctrlProduct.addProductUser(currentUser, name, price);
            ctrlUser.addProductIntoMatrix(ctrlProduct.getProductIdByName(currentUser, name));
            return true;
        }
        return false;
    }

    /**
     * Elimina un producto del inventario del usuario autenticado.
     *
     * @param name Nombre del producto.
     * @throws UserNotAuthenticatedException  Si no hay usuario autenticado.
     * @throws ProductNotFoundExceptionByName Si el producto no existe.
     */
    public static void removeProductUser(String name) {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        Integer productId = ctrlProduct.getProductIdByName(currentUser, name);
        if (productId != null) {
            ctrlUser.removeProductIntoMatrix(productId);
            ctrlProduct.removeProductUser(currentUser, name);
        } else {
            throw new ProductNotFoundExceptionByName("El producto '" + name + "' no existe para el usuario '" + currentUser + "'.");
        }
    }

    /**
     * Modifica el precio de un producto en el inventario del usuario autenticado.
     *
     * @param name     Nombre del producto.
     * @param newPrice Nuevo precio del producto.
     * @throws IllegalArgumentException       Si el precio es negativo.
     * @throws UserNotAuthenticatedException  Si no hay usuario autenticado.
     * @throws ProductNotFoundExceptionByName Si el producto no existe.
     */
    public static void updateProductUser(String name, Integer newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        ctrlProduct.updateProductUser(currentUser, name, newPrice);
    }

    /**
     * Obtiene los atributos de un producto del usuario autenticado.
     *
     * @param name Nombre del producto.
     * @return Atributos del producto en formato de cadena.
     */
    public static String getProductAttributesUser(String name) {
        String currentUser = ctrlUser.getUsernameSelected();
        return currentUser != null ? ctrlProduct.getProductAttributesUser(currentUser, name) : "Usuario no autenticado o producto no encontrado.";
    }

    /**
     * Obtiene la lista de IDs de productos para un usuario específico.
     *
     * @param username Nombre del usuario.
     * @return Lista de IDs de productos asociados al usuario.
     */
    public List<Integer> getProductIdsForUser(String username) {
        return username != null ? ctrlProduct.getProductIdsForUser(username) : null;
    }

    // ==============================
    // Gestión de Similitudes entre Productos
    // ==============================

    /**
     * Modifica la similitud entre dos productos para el usuario autenticado.
     *
     * @param productName1  Nombre del primer producto
     * @param productName2  Nombre del segundo producto
     * @param newSimilarity Nuevo valor de similitud entre 0 y 1
     * @return true si la similitud fue modificada, false si los productos no existen,
     * no hay usuario autenticado o el valor está fuera del rango [0, 1]
     */
    public boolean setProductSimilarityForUser(String productName1, String productName2, double newSimilarity) {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null || newSimilarity < 0 || newSimilarity > 1) return false;

        Integer productId1 = ctrlProduct.getProductIdByName(currentUser, productName1);
        Integer productId2 = ctrlProduct.getProductIdByName(currentUser, productName2);

        return productId1 != null && productId2 != null &&
                ctrlUser.setProductSimilarityForUser(productId1, productId2, newSimilarity);
    }

    /**
     * Elimina la similitud entre dos productos para el usuario autenticado.
     *
     * @param productName1 Nombre del primer producto
     * @param productName2 Nombre del segundo producto
     * @return true si la similitud fue eliminada, false si no existía ninguna similitud, algún producto no existe o no hay usuario autenticado
     */
    public boolean removeProductSimilarityForUser(String productName1, String productName2) {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) return false;

        Integer productId1 = ctrlProduct.getProductIdByName(currentUser, productName1);
        Integer productId2 = ctrlProduct.getProductIdByName(currentUser, productName2);

        return productId1 != null && productId2 != null &&
                ctrlUser.removeProductSimilarityForUser(productId1, productId2);
    }

    /**
     * Obtiene la similitud entre dos productos para el usuario autenticado.
     *
     * @param productName1 Nombre del primer producto
     * @param productName2 Nombre del segundo producto
     * @return el valor de similitud entre los productos o -1 si no hay usuario autenticado
     */
    public double getProductSimilarityForUser(String productName1, String productName2) {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) return -1;

        Integer productId1 = ctrlProduct.getProductIdByName(currentUser, productName1);
        Integer productId2 = ctrlProduct.getProductIdByName(currentUser, productName2);

        return (productId1 != null && productId2 != null) ?
                ctrlUser.getSimilarityForUser(productId1, productId2) : -1;
    }

    // ==============================
    // Gestión de Estanterías
    // ==============================

    /**
     * Añade una estantería para el usuario autenticado.
     *
     * @param shelfName Nombre de la estantería
     * @param products  Lista con los IDs de los productos para inicializar la estantería
     * @throws ShelfAlreadyExistsException   si la estantería ya existe
     * @throws UserNotAuthenticatedException si no hay un usuario autenticado
     */
    public void addShelfForUser(String shelfName, List<Integer> products) throws ShelfAlreadyExistsException, UserNotAuthenticatedException {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        ctrlShelf.addShelf(shelfName, products, currentUser);
    }

    /**
     * Elimina una estantería para el usuario autenticado.
     *
     * @param shelfName Nombre de la estantería a eliminar
     * @throws ShelfNotFoundException        si la estantería no existe
     * @throws UserNotAuthenticatedException si no hay un usuario autenticado
     */
    public void removeShelfForUser(String shelfName) throws ShelfNotFoundException, UserNotAuthenticatedException {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        ctrlShelf.removeShelfForUser(shelfName, currentUser);
    }

    /**
     * Obtiene la lista de productos en una estantería específica del usuario autenticado.
     *
     * @param shelfName Nombre de la estantería
     * @return Lista de productos en la estantería
     * @throws ShelfNotFoundException        si la estantería no existe
     * @throws UserNotAuthenticatedException si no hay un usuario autenticado
     */
    public List<Integer> getProductsInUserShelf(String shelfName) throws ShelfNotFoundException, UserNotAuthenticatedException {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        return ctrlShelf.getProductsInUserShelf(shelfName, currentUser);
    }

    /**
     * Añade un producto a una estantería del usuario autenticado.
     *
     * @param shelfName Nombre de la estantería
     * @param product   ID del producto a añadir
     * @throws ShelfNotFoundException        si la estantería no existe
     * @throws IllegalArgumentException      si el producto ya está en la estantería
     * @throws UserNotAuthenticatedException si no hay un usuario autenticado
     */
    public void addProductToShelfForUser(String shelfName, int product) throws ShelfNotFoundException, UserNotAuthenticatedException {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        ctrlShelf.addProductToShelfForUser(shelfName, product, currentUser);
    }

    /**
     * Elimina un producto de una estantería del usuario autenticado.
     *
     * @param shelfName Nombre de la estantería
     * @param product   ID del producto a eliminar
     * @throws ShelfNotFoundException        si la estantería no existe
     * @throws ProductNotFoundException      si el producto no está en la estantería
     * @throws UserNotAuthenticatedException si no hay un usuario autenticado
     */
    public void removeProductFromShelfForUser(String shelfName, int product) throws ShelfNotFoundException, ProductNotFoundException, UserNotAuthenticatedException {
        String currentUser = ctrlUser.getUsernameSelected();
        if (currentUser == null) {
            throw new UserNotAuthenticatedException();
        }
        ctrlShelf.removeProductFromShelfForUser(shelfName, product, currentUser);
    }

    // ==============================
    // Gestión de Algoritmo
    // ==============================

    /**
     * Organiza los productos del usuario autenticado y crea una nueva estantería.
     *
     * @param shelfName Nombre de la nueva estantería
     * @return true si la estantería fue creada con éxito, false si ya existe o no hay usuario autenticado
     */
    public boolean organizeProductsAndCreateShelf(String shelfName, AlgorithmType algorithmType) throws TriangleInequalityViolationException {
        if (ctrlUser.getUsernameSelected() == null) return false;  // Verifica si hay un usuario autenticado

        if (ctrlShelf.hasShelf(shelfName, ctrlUser.getUsernameSelected())) return false;

        List<Integer> products = ctrlProduct.getProductIdsForUser(ctrlUser.getUsernameSelected());
        ArrayList<ArrayList<Double>> simMatrix = ctrlUser.getSimilarityMatrixForUser();
        ShelfOrganizer shelfOrganizer;

        switch (algorithmType) {
            case TWO_APPROXIMATION:
                if (TwoAproxAlgorithm.isTriangleInequalityPropertyTrue(products, simMatrix)) {
                    shelfOrganizer = new TwoAproxAlgorithm();
                } else {
                    throw new TriangleInequalityViolationException("The TwoAproxAlgorithm cant be used for this shelf");
                }
                break;

            case BRUTE_FORCE:
                shelfOrganizer = new BruteForceAlgorithm();
                if (products.size() > 8) System.out.println("Estás usando un algoritmo de coste factorial con más" +
                        " de 8 productos. La respuesta puede tardar mucho en llegar");
                break;

            default:
                throw new IllegalArgumentException("Unsupported algorithm type: " + algorithmType);
        }

        List<Integer> organizedIdx = shelfOrganizer.organize(products, simMatrix);

        // Añade la estantería al usuario
        List<Integer> organizedProducts = ctrlUser.getIdList(organizedIdx);
        ctrlShelf.addShelf(shelfName, organizedProducts, ctrlUser.getUsernameSelected());
        return true;
    }
}