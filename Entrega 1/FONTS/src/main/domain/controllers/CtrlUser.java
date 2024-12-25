package main.domain.controllers;

import main.domain.classes.Product;
import main.domain.classes.SimilarityMatrix;
import main.domain.classes.User;
import main.domain.exceptions.UserNotAuthenticatedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador encargado de la gestión de usuarios.
 * 
 * - Registra nuevos usuarios en el sistema.
 * - Maneja la autenticación y cierre de sesión.
 * - Permite actualizar credenciales (nombre de usuario y contraseña).
 * - Consulta detalles de los usuarios registrados.
 */
public class CtrlUser {

    private static Map<String, User> users; // Almacena los usuarios
    private static Map<String, SimilarityMatrix> similarityMatrix;
    private static String usrSelected;                        // Usuario actual autenticado
    private static CtrlUser singletonObject;

    /**
     * Constructor de CtrlUser
     * Inicializa el controlador
     */
    public static CtrlUser getInstance() {
        if (singletonObject == null) {
            singletonObject = new CtrlUser();
        }
        return singletonObject;
    }

    private CtrlUser() {
        this.users= new HashMap<>();
        this.similarityMatrix = new HashMap<>();
        this.usrSelected = null;
    }


    // ==============================
    // Gestión de Usuario y Autenticación
    // ==============================

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param name     Nombre completo del usuario.
     * @param username Nombre de usuario único.
     * @param password Contraseña del usuario.
     * @return true si el usuario fue registrado exitosamente, false si el nombre de usuario ya existe.
     */
    public  boolean registerUser(String name, String username, String password) {
        // Verifica si el nombre de usuario ya existe
        if (users.containsKey(username)) return false;

        // Crea y agrega un nuevo usuario al sistema
        users.put(username, new User(name, username, password));
        return true; // Registro exitoso
    }

    /**
     * Autentica un usuario en el sistema y lo selecciona como usuario actual.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return true si la autenticación es exitosa, false si el nombre de usuario o la contraseña son incorrectos.
     */
    public  boolean loginUser(String username, String password) {
        // Obtiene el usuario asociado al nombre de usuario
        User user = users.get(username);

        // Verifica si el usuario existe y la contraseña es correcta
        if (user != null && user.authenticate(username, password)) {
            usrSelected = username; // Establece el usuario autenticado como el usuario actual
            return true;
        }
        return false; // Error de autenticación
    }

    /**
     * Cierra la sesión del usuario actual.
     *
     * @return true si no hay usuario autenticado (cierre exitoso), false si algún usuario sigue autenticado.
     */
    public  boolean logoutUser() {
        if(usrSelected != null){  //si hay sesion iniciada cierra la sesión
            usrSelected = null;
            return true;
        }
        return false; //false si no hay sesion iniciada
    }

    /**
     * Actualiza el nombre de usuario del usuario autenticado.
     *
     * @param oldUsername Nombre de usuario actual.
     * @param newUsername Nuevo nombre de usuario.
     * @return true si el cambio de nombre fue exitoso, false si no hay usuario autenticado.
     */
    public  boolean updateUsername(String oldUsername, String newUsername) {
        // Obtiene el usuario actualmente autenticado
        User user = users.get(oldUsername);
        if (user == null) return false; // No hay usuario autenticado

        // Actualiza el nombre de usuario del usuario autenticado
        user.setUsername(newUsername);
        return true;
    }

    /**
     * Cambia la contraseña del usuario autenticado si la contraseña actual es correcta.
     *
     * @param currentPassword Contraseña actual.
     * @param newPassword     Nueva contraseña.
     * @return true si la contraseña fue actualizada, false si la contraseña actual es incorrecta o no hay usuario autenticado.
     */
    public  boolean updatePassword(String currentPassword, String newPassword) {
        // Obtiene el usuario actualmente autenticado
        User user = users.get(usrSelected);
        if (user == null) return false; // No hay usuario autenticado

        // Actualiza la contraseña si la contraseña actual coincide
        user.setPassword(currentPassword, newPassword);
        return true;
    }

    /**
     * Verifica si un usuario con el nombre de usuario dado existe en el sistema.
     *
     * @param username Nombre de usuario.
     * @return true si el usuario existe, false si no.
     */
    public  boolean existUser(String username) {
        return users.containsKey(username); // Devuelve true si el nombre de usuario existe en el sistema
    }

    /**
     * Obtiene el nombre de usuario del usuario actualmente autenticado.
     *
     * @return El nombre de usuario del usuario autenticado.
     * @throws UserNotAuthenticatedException Si no hay ningún usuario autenticado.
     */
    public String getUsernameSelected() throws UserNotAuthenticatedException {
        if (usrSelected == null) {
            throw new UserNotAuthenticatedException();
        }
        if (users.get(usrSelected) != null) {
            return users.get(usrSelected).getUsername();
        }
        throw new UserNotAuthenticatedException();
    }

    // ==============================
    // Métodos de Gestión de Similitud
    // ==============================

    /**
     * Establece la similitud entre dos productos para el usuario autenticado.
     *
     * @param productIndex1 Índice del primer producto
     * @param productIndex2 Índice del segundo producto
     * @param similarity    Valor de similitud entre 0 y 1
     * @return true si se estableció la similitud, false si la matriz no existe o si los índices son inválidos
     */
    public boolean setProductSimilarityForUser(int productIndex1, int productIndex2, double similarity) {
        // Verificar si el usuario está autenticado
        if (usrSelected == null) return false;

        // Obtener la matriz de similitud del usuario autenticado
        SimilarityMatrix simMatrix = similarityMatrix.computeIfAbsent(usrSelected, k -> new SimilarityMatrix());

        // Establecer la similitud entre los productos en la matriz
        simMatrix.setSimilarity(productIndex1, productIndex2, similarity);
        return true;
    }

    /**
     * Elimina la similitud entre dos productos para el usuario autenticado,
     * estableciendo el valor de similitud a 0.0 en la matriz de similitudes.
     *
     * @param productIndex1 Índice del primer producto en la matriz de similitud.
     * @param productIndex2 Índice del segundo producto en la matriz de similitud.
     * @return true si la similitud fue eliminada exitosamente,
     * false si no hay usuario autenticado, la matriz de similitud no existe,
     * o si los índices son inválidos.
     */
    public boolean removeProductSimilarityForUser(int productIndex1, int productIndex2) {
        // Verifica si hay un usuario autenticado
        if (usrSelected == null) return false;

        // Obtiene la matriz de similitud del usuario autenticado
        SimilarityMatrix simMatrix = similarityMatrix.get(usrSelected);

        // Verifica si el usuario tiene una matriz de similitud
        if (simMatrix == null) return false;

        // Llama al metodo removeSimilarity en SimilarityMatrix para eliminar la similitud entre los productos
        simMatrix.setSimilarity(productIndex1, productIndex2,0);

        // Retorna true si la eliminación de la similitud fue exitosa
        return true;
    }


    /**
     * Obtiene la similitud entre dos productos para el usuario autenticado.
     *
     * @param productIndex1 Índice del primer producto en la matriz de similitud
     * @param productIndex2 Índice del segundo producto en la matriz de similitud
     * @return El valor de similitud entre los productos o Double.NaN si no hay usuario autenticado
     * o si el usuario no tiene una matriz de similitud asignada
     */
    public double getSimilarityForUser(int productIndex1, int productIndex2) {
        // Verificar si hay un usuario autenticado
        if (usrSelected == null) {
            return Double.NaN; // Retornar NaN si no hay usuario autenticado
        }

        // Obtener la matriz de similitud del usuario autenticado
        SimilarityMatrix simMatrix = similarityMatrix.get(usrSelected);

        // Verificar si el usuario tiene una matriz de similitud
        if (simMatrix == null) {
            return Double.NaN; // Retornar NaN si la matriz de similitud no existe
        }

        // Retornar la similitud entre los productos en la matriz
        return simMatrix.getSimilarity(productIndex1, productIndex2);
    }

    /**
     * Obtiene la matriz de similitud para el usuario autenticado.
     *
     * @return La instancia de SimilarityMatrix del usuario autenticado,
     * o null si no hay usuario autenticado o si no se ha inicializado una matriz de similitud para el usuario.
     */
    public ArrayList<ArrayList<Double>> getSimilarityMatrixForUser() {
        // Verificar si hay un usuario autenticado
        if (usrSelected == null) {
            return null; // No hay usuario autenticado
        }

        // Obtener o inicializar la matriz de similitud del usuario autenticado
        return similarityMatrix.computeIfAbsent(usrSelected, k -> new SimilarityMatrix()).getMatrix();
    }


    /**
     * Añade un nuevo producto a la matriz de similitudes del usuario autenticado.
     * Si la matriz no existe para el usuario, se crea una nueva.
     */
    public void addProductIntoMatrix(int id) {
        // Obtiene la matriz de similitudes del usuario autenticado
        SimilarityMatrix simMatrix = similarityMatrix.get(usrSelected);

        // Verifica si la matriz de similitud existe; si no, la crea
        if (simMatrix == null) {
            simMatrix = new SimilarityMatrix(); // Crea una nueva instancia de SimilarityMatrix
            simMatrix.addProduct(id); // Añade el nuevo producto en la nueva matriz
            similarityMatrix.put(usrSelected, simMatrix); // Asocia la nueva matriz con el usuario autenticado
        } else {
            simMatrix.addProduct(id); // Si la matriz ya existe, simplemente añade el nuevo producto
        }
    }

    /**
     * Elimina un producto de la matriz de similitudes del usuario autenticado.
     * Si la matriz no existe para el usuario, muestra un mensaje de error.
     *
     * @param productIndex Índice del producto a eliminar en la matriz de similitudes.
     */
    public void removeProductIntoMatrix(int productIndex) {
        // Obtiene la matriz de similitudes del usuario autenticado
        SimilarityMatrix simMatrix = similarityMatrix.get(usrSelected);

        // Verifica si la matriz de similitudes existe para el usuario
        if (simMatrix == null) {
            System.err.println("Error: La matriz de similitud no existe para el usuario seleccionado."); // Mensaje de error si no existe
            return;
        }

        // Llama al metodo removeProduct en SimilarityMatrix para eliminar el producto en el índice dado
        simMatrix.removeProduct(productIndex);
    }

    /**
     * Obtiene los detalles del usuario autenticado actualmente.
     *
     * @return Una cadena que contiene el nombre completo y el nombre de usuario del usuario autenticado.
     * @throws UserNotAuthenticatedException Si no hay ningún usuario autenticado o si el usuario autenticado no se encuentra en la lista de usuarios.
     */
    public String getAuthenticatedUserDetails() throws UserNotAuthenticatedException {
        if (usrSelected == null) { // Verifica si hay un usuario autenticado
            throw new UserNotAuthenticatedException();
        }

        User user = users.get(usrSelected); // Busca al usuario autenticado en el mapa de usuarios
        if (user == null) {
            throw new UserNotAuthenticatedException();
        }

        return "Nombre: " + user.getName() + ", Nombre de Usuario: " + user.getUsername();
    }

    public List<Integer> getIdList(List<Integer> idxList) {
        return similarityMatrix.get(usrSelected).listIdxToId(idxList);
    }

}