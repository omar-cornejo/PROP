package main.domain.controllers;

import main.domain.classes.DTO.SimilarityMatrixDTO;
import main.domain.classes.Product;
import main.domain.classes.SimilarityMatrix;
import main.domain.classes.User;
import main.domain.exceptions.InvalidPasswordException;
import main.domain.exceptions.UserNotAuthenticatedException;
import main.domain.exceptions.UserAlreadyExistsException;
import main.persistence.controllers.CtrlPersistence;
import main.domain.classes.DTO.UserDTO;

import java.util.*;

/**
 * Controlador encargado de la gestión de usuarios.
 *
 * - Registra nuevos usuarios en el sistema.
 * - Maneja la autenticación y cierre de sesión.
 * - Permite actualizar credenciales (nombre de usuario y contraseña).
 * - Consulta detalles de los usuarios registrados.
 */
public class CtrlUser {

    // Old Version
    private static Map<String, User> users;                     // Almacena los usuarios
    private static Map<String, SimilarityMatrix> similarityMatrix;
    private static String usrSelected;                          // Usuario actual autenticado

    // Updated Version
    private User user;
    private SimilarityMatrix userSimilarityMatrix;
    private static CtrlUser singletonObject;
    private final CtrlPersistence ctrlPersistence;


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
        this.users = new HashMap<>();
        this.similarityMatrix = new HashMap<>();
        this.usrSelected = null;

        // Updated Version

        this.user = null;
        this.userSimilarityMatrix = null;
        this.ctrlPersistence = CtrlPersistence.getInstance();

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
     * @return true si el usuario fue registrado exitosamente, false de lo contrario.
     */
    public boolean registerUser(String name, String username, String password) {
        // Updated version
        if (!ctrlPersistence.existsUser(username)) {
            if (ctrlPersistence.createUser(new UserDTO(name, username, password))) return true;
        }
        return false;
    }

    /**
     * Autentica un usuario en el sistema y lo selecciona como usuario actual.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return true si la autenticación es exitosa, false si el nombre de usuario o la contraseña son incorrectos.
     */
    public boolean loginUser(String username, String password) {
        // Updated Version
        if (ctrlPersistence.existsUser(username)) {
            UserDTO userDTO = ctrlPersistence.getUser(username);

            if (!ctrlPersistence.userHasSimMatrix(username)) {
                userSimilarityMatrix = new SimilarityMatrix();
            } else {
                SimilarityMatrixDTO similarityMatrixDTO = ctrlPersistence.getUserSimMatrix(username);
                if (!Objects.isNull(similarityMatrixDTO)) {
                    userSimilarityMatrix = new SimilarityMatrix(similarityMatrixDTO);
                } else throw new RuntimeException("Error al obtener la matriz de similitud del usuario");
            }

            if (!Objects.isNull(userDTO) && password.equals(userDTO.getPassword())) {
                user = new User(userDTO);
                return true;
            }
            System.out.println("Error en el inicio de sesión");
        }
        return false;
    }

    /**
     * Cierra la sesión del usuario actual.
     *
     * @return true si no hay usuario autenticado (cierre exitoso), false si algún usuario sigue autenticado.
     */
    public boolean logoutUser() {
        // Updated Version
        if (!Objects.isNull(user)) {
            user = null;
            return true;
        }
        return false;
    }

    /**
     * Actualiza el nombre de usuario del usuario autenticado.
     *
     * @param newUsername Nuevo nombre de usuario.
     * @return true si el cambio de nombre fue exitoso, false si no hay usuario autenticado.
     */
    public boolean updateUsername(String newUsername, String actualPassword) {
        // Updated Version
        String oldUsername = user.getUsername();

        if (!Objects.isNull(user)) {
            if (!ctrlPersistence.existsUser(newUsername)) {
                user.setUsername(newUsername);
                if (!(user.getPassword().equals(actualPassword))) {
                    user.setUsername(oldUsername);
                    return false;
                }
                if (ctrlPersistence.updateUserUsername(new UserDTO(user.getName(), user.getUsername(), user.getPassword()), oldUsername)) {
                    return true;
                }
            }
        }

        // Abort operation -> reverse changes
        user.setUsername(oldUsername);
        return false;
    }

    /**
     * Cambia la contraseña del usuario autenticado si la contraseña actual es correcta.
     *
     * @param currentPassword Contraseña actual.
     * @param newPassword     Nueva contraseña.
     * @return true si la contraseña fue actualizada, false si la contraseña actual es incorrecta o no hay usuario autenticado.
     */
    public boolean updatePassword(String currentPassword, String newPassword) {
        // Updated Version
        if (!Objects.isNull(user)) {
            try {
                user.setPassword(currentPassword, newPassword);
                if (ctrlPersistence.updateUserPassword(new UserDTO(user.getName(), user.getUsername(), user.getPassword()))) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Verifica si un usuario con el nombre de usuario dado existe en el sistema.
     *
     * @param username Nombre de usuario.
     * @return true si el usuario existe, false si no.
     */
    public boolean existUser(String username) {
        // Updated Version
        return ctrlPersistence.existsUser(username);
    }

    /**
     * Obtiene el nombre de usuario del usuario actualmente autenticado.
     *
     * @return El nombre de usuario del usuario autenticado.
     * @throws UserNotAuthenticatedException Si no hay ningún usuario autenticado.
     */
    public String getUsernameSelected() throws UserNotAuthenticatedException {
        // Updated Version
        if (!Objects.isNull(user)) {
            return user.getUsername();
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
        // Updated Version
        if (!Objects.isNull(user)) {
            if (!Objects.isNull(userSimilarityMatrix)) {
                userSimilarityMatrix.setSimilarity(productIndex1, productIndex2, similarity);
                return ctrlPersistence.updateUserSimMatrix(new SimilarityMatrixDTO(userSimilarityMatrix.getIdToIndex(), userSimilarityMatrix.getIndexToId(), userSimilarityMatrix.getIdToSimilarities()), user.getUsername());
            }
        }
        return false;
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
        // Updated Version
        if (!Objects.isNull(user)) {
            if (!Objects.isNull(userSimilarityMatrix)) {
                userSimilarityMatrix.setSimilarity(productIndex1, productIndex2, 0);
                return ctrlPersistence.updateUserSimMatrix(new SimilarityMatrixDTO(userSimilarityMatrix.getIdToIndex(), userSimilarityMatrix.getIndexToId(), userSimilarityMatrix.getIdToSimilarities()), user.getUsername());
            }
        }
        return false;
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
        // Updated Version
        if (!Objects.isNull(user)) {
            if (!Objects.isNull(userSimilarityMatrix)) {
                return userSimilarityMatrix.getSimilarity(productIndex1, productIndex2);
            }
        }
        return Double.NaN;
    }

    /**
     * Obtiene la matriz de similitud para el usuario autenticado.
     *
     * @return La instancia de SimilarityMatrix del usuario autenticado,
     * o null si no hay usuario autenticado o si no se ha inicializado una matriz de similitud para el usuario.
     */
    public ArrayList<ArrayList<Double>> getSimilarityMatrixForUser() {
        // Updated Version
        if (!Objects.isNull(user)) return userSimilarityMatrix.getMatrix();
        return null;
    }

    /**
     * Obtiene la matriz de similitud para el usuario autenticado.
     *
     * @return La instancia de SimilarityMatrix del usuario autenticado,
     * o null si no se ha inicializado una matriz de similitud para el usuario.
     */
    public SimilarityMatrix getSimilarityMatrix(String username) {
        if (!Objects.isNull(username)) {
            SimilarityMatrixDTO simMatrixUser = ctrlPersistence.getUserSimMatrix(username);
            if(simMatrixUser == null) {
                return null;
            }
            SimilarityMatrix copiedMatrix = new SimilarityMatrix();
            copiedMatrix.setIdToSimilarities(simMatrixUser.getIdToSimilarities());
            copiedMatrix.setIndexToId(simMatrixUser.getIdToIndex());
            copiedMatrix.setIdToIndex(simMatrixUser.getIdToIndex());

            return copiedMatrix;
        }

        return null;
    }

    /**
     * Actualiza la matriz de similitud para el usuario autenticado.
     *
     * Si el usuario es null devuelve false, true en caso contrario
     */
    public boolean setSimilarityMatrix(String username, SimilarityMatrix simMatrix) {
        /*
        if(username!=null) {
            similarityMatrix.put(username, simMatrix);
            return true;
        }
        return false;
        */

        if (!Objects.isNull(username)) {
            if (!Objects.isNull(simMatrix)) {
                userSimilarityMatrix = simMatrix;
                ctrlPersistence.updateUserSimMatrix(new SimilarityMatrixDTO(simMatrix.getIdToIndex(), simMatrix.getIndexToId(), simMatrix.getIdToSimilarities()), username);
            }
        }
        return false;
    }

    /**
     * Añade un nuevo producto a la matriz de similitudes del usuario autenticado.
     * Si la matriz no existe para el usuario, se crea una nueva.
     */
    public boolean addProductIntoMatrix(int id) {
        // Updated Version
        if (!Objects.isNull(user)) {
            userSimilarityMatrix.addProduct(id);
            return ctrlPersistence.updateUserSimMatrix(new SimilarityMatrixDTO(userSimilarityMatrix.getIdToIndex(), userSimilarityMatrix.getIndexToId(), userSimilarityMatrix.getIdToSimilarities()), user.getUsername());
        }
        return false;
    }

    /**
     * Elimina un producto de la matriz de similitudes del usuario autenticado.
     * Si la matriz no existe para el usuario, muestra un mensaje de error.
     *
     * @param productId Id del producto a eliminar en la matriz de similitudes.
     */
    public void removeProductIntoMatrix(int productId) {
        // Updated Version
        if (!Objects.isNull(user)) {
            userSimilarityMatrix.removeProduct(productId);
            ctrlPersistence.updateUserSimMatrix(new SimilarityMatrixDTO(userSimilarityMatrix.getIdToIndex(), userSimilarityMatrix.getIndexToId(), userSimilarityMatrix.getIdToSimilarities()), user.getUsername());
        }
    }

    /**
     * Obtiene los detalles del usuario autenticado actualmente.
     *
     * @return Una cadena que contiene el nombre completo y el nombre de usuario del usuario autenticado.
     * @throws UserNotAuthenticatedException Si no hay ningún usuario autenticado o si el usuario autenticado no se encuentra en la lista de usuarios.
     */
    public String getAuthenticatedUserDetails() {
        // Updated Version
        if (!Objects.isNull(user)) {
            return "Nombre: " + user.getName() + ", Nombre de Usuario: " + user.getUsername();
        }
        return null;
    }

    public List<Integer> getIdList(List<Integer> idxList) {
        // Updated Version
        return userSimilarityMatrix.listIdxToId(idxList);
    }

}
