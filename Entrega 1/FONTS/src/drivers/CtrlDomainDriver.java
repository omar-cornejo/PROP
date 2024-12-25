package drivers;

import main.domain.controllers.CtrlDomain;
import main.domain.enums.AlgorithmType;
import main.domain.exceptions.*;
import main.domain.exceptions.UserNotAuthenticatedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static main.domain.enums.AlgorithmType.BRUTE_FORCE;
import static main.domain.enums.AlgorithmType.TWO_APPROXIMATION;

public class CtrlDomainDriver {
    private static CtrlDomain ctrlDomain = CtrlDomain.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            try {
                showMenu();
                String option = scanner.nextLine();
                switch (option) {
                    // Gestión de Usuario
                    case "1": testRegisterUser(); break;
                    case "2": testLoginUser(); break;
                    case "3": testLogoutUser(); break;
                    case "4": testLoggedUser(); break;
                    case "5": testUpdateUsername(); break;
                    case "6": testUpdatePassword(); break;
                    case "7": testExistUser(); break;
                    case "8": testGetUserDetails(); break;

                    // Gestión de Productos
                    case "9": testAddProduct(); break;
                    case "10": testRemoveProduct(); break;
                    case "11": testUpdateProduct(); break;
                    case "12": testGetProductAttributes(); break;
                    case "13": testGetProductIdsForUser(); break;

                    // Gestión de Estanterías
                    case "14": testCreateShelf(); break;
                    case "15": testAddProductToShelf(); break;
                    case "16": testRemoveProductFromShelf(); break;
                    case "17": testRemoveShelfForUser(); break;
                    case "18": testGetProductsInShelf(); break;

                    // Gestión de Similitudes
                    case "19": testSetProductSimilarity(); break;
                    case "20": testGetProductSimilarity(); break;
                    case "21": testRemoveProductSimilarity(); break;

                    // Gestión de Algoritmos
                    case "22": testOrganizeProductsAndCreateShelf(); break;

                    // Salir
                    case "0": exit = true; break;

                    default: System.out.println("Opción no válida."); break;
                }
            } catch (UserNotAuthenticatedException | ProductNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error: Entrada inválida. Intente nuevamente.");
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n==== CtrlDomain Driver ====");
        System.out.println("=========================================");

        // Gestión de Usuario
        System.out.println("         *** Gestión de Usuario ***       ");
        System.out.println("  1. Registrar Usuario");
        System.out.println("  2. Iniciar Sesión de Usuario");
        System.out.println("  3. Cerrar Sesión de Usuario");
        System.out.println("  4. Consultar Usuario Autenticado");
        System.out.println("  5. Cambiar Nombre de Usuario");
        System.out.println("  6. Cambiar Contraseña");
        System.out.println("  7. Verificar Existencia de Usuario");
        System.out.println("  8. Ver Detalles del Usuario Autenticado");
        System.out.println("-----------------------------------------");

        // Gestión de Productos
        System.out.println("        *** Gestión de Productos ***      ");
        System.out.println("  9. Añadir Producto al Usuario");
        System.out.println(" 10. Eliminar Producto del Usuario");
        System.out.println(" 11. Actualizar Precio de Producto del Usuario");
        System.out.println(" 12. Obtener Atributos de Producto");
        System.out.println(" 13. Obtener Ids de Productos del Usuario");
        System.out.println("-----------------------------------------");

        // Gestión de Estanterías
        System.out.println("      *** Gestión de Estanterías ***      ");
        System.out.println(" 14. Crear Estantería");
        System.out.println(" 15. Añadir Producto a Estantería");
        System.out.println(" 16. Eliminar Producto de Estantería");
        System.out.println(" 17. Eliminar Estantería");
        System.out.println(" 18. Obtener Productos de una Estantería");
        System.out.println("-----------------------------------------");

        // Gestión de Similitudes
        System.out.println("     *** Gestión de Similitudes ***       ");
        System.out.println(" 19. Establecer Similitud entre Productos");
        System.out.println(" 20. Obtener Similitud entre Productos");
        System.out.println(" 21. Eliminar Similitud entre Productos");
        System.out.println("-----------------------------------------");

        // Gestión de Algoritmos
        System.out.println("       *** Gestión de Algoritmos ***      ");
        System.out.println(" 22. Organizar Productos y Crear Estantería");
        System.out.println("-----------------------------------------");

        // Salir
        System.out.println("              *** General ***             ");
        System.out.println("  0. Salir");
        System.out.println("=========================================");
        System.out.print("Seleccione una opción: ");
    }



    /**
     * Prueba el registro de un nuevo usuario.
     */
    private static void testRegisterUser() {
        System.out.print("Nombre completo: ");
        String name = scanner.nextLine();
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        boolean success = ctrlDomain.registerUser(name, username, password);
        System.out.println(success ? "Usuario registrado exitosamente." : "El nombre de usuario ya existe.");
    }

    /**
     * Prueba el inicio de sesión de un usuario existente.
     */
    private static void testLoginUser() {
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        boolean success = ctrlDomain.loginUser(username, password);
        System.out.println(success ? "Inicio de sesión exitoso." : "Nombre de usuario o contraseña incorrectos.");
    }

    /**
     * Prueba el cierre de sesión del usuario autenticado.
     */
    private static void testLogoutUser() {
        boolean success = ctrlDomain.logoutUser();
        System.out.println(success ? "Cierre de sesión exitoso." : "No hay usuario autenticado.");
    }

    /**
     * Prueba la obtención del usuario autenticado.
     */
    private static void testLoggedUser() {
        try {
            String username = ctrlDomain.getUsernameSelected();
            System.out.println("El usuario autenticado es: '" + username + "'.");
        } catch (UserNotAuthenticatedException e) {
            System.out.println("No hay usuario autenticado.");
        }
    }

    /**
     * Prueba la actualización del nombre de usuario autenticado.
     */
    private static void testUpdateUsername() {
        try {
            System.out.print("Nombre de usuario actual: ");
            String oldUsername = scanner.nextLine();
            System.out.print("Nuevo nombre de usuario: ");
            String newUsername = scanner.nextLine();
            boolean success = ctrlDomain.updateUsername(oldUsername, newUsername);
            System.out.println(success ? "Nombre de usuario cambiado exitosamente." : "El nuevo nombre de usuario ya existe.");
        } catch (UserNotAuthenticatedException | UserAlreadyExistsException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prueba la actualización de la contraseña del usuario autenticado.
     */
    private static void testUpdatePassword() {
        try {
            System.out.print("Contraseña actual: ");
            String currentPassword = scanner.nextLine();
            System.out.print("Nueva contraseña: ");
            String newPassword = scanner.nextLine();
            boolean success = ctrlDomain.updatePassword(currentPassword, newPassword);
            System.out.println(success ? "Contraseña cambiada exitosamente." : "Contraseña actual incorrecta.");
        } catch (UserNotAuthenticatedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Verifica si un usuario existe en el sistema.
     */
    private static void testExistUser() {
        System.out.print("Nombre de usuario a verificar: ");
        String username = scanner.nextLine();
        boolean exists = ctrlDomain.existUser(username);
        System.out.println(exists ? "El usuario existe." : "El usuario no existe.");
    }

    /**
     * Prueba la obtención de los detalles del usuario autenticado.
     */
    private static void testGetUserDetails() {
        try {
            String userDetails = ctrlDomain.getUserDetails();
            System.out.println("Detalles del usuario autenticado: " + userDetails);
        } catch (UserNotAuthenticatedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prueba la adición de un producto asociado al usuario autenticado.
     */
    private static void testAddProduct() {
        System.out.print("Nombre del producto: ");
        String productName = scanner.nextLine();
        System.out.print("Precio del producto: ");
        int price = Integer.parseInt(scanner.nextLine());
        boolean success = ctrlDomain.addProductUser(productName, price);
        System.out.println(success ? "Producto añadido exitosamente." : "Error al añadir el producto.");
    }

    /**
     * Prueba la eliminación de un producto del usuario autenticado.
     */
    private static void testRemoveProduct() {
        try {
            System.out.print("Nombre del producto a eliminar: ");
            String productName = scanner.nextLine();
            ctrlDomain.removeProductUser(productName);
            System.out.println("Producto eliminado exitosamente.");
        } catch (UserNotAuthenticatedException | ProductNotFoundExceptionByName e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la actualización del precio de un producto del usuario autenticado.
     */
    private static void testUpdateProduct() {
        try {
            System.out.print("Nombre del producto: ");
            String productName = scanner.nextLine();
            System.out.print("Nuevo precio: ");
            int newPrice = Integer.parseInt(scanner.nextLine());
            ctrlDomain.updateProductUser(productName, newPrice);
            System.out.println("Producto actualizado exitosamente.");
        } catch (UserNotAuthenticatedException | ProductNotFoundExceptionByName e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Entrada inválida. El precio debe ser un número entero.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la obtención de los atributos de un producto del usuario autenticado.
     */
    private static void testGetProductAttributes() {
        System.out.print("Nombre del producto: ");
        String productName = scanner.nextLine();
        String attributes = ctrlDomain.getProductAttributesUser(productName);
        System.out.println(attributes != null ? attributes : "El producto no existe.");
    }

    /**
     * Prueba la obtención de los IDs de productos del usuario autenticado.
     */
    private static void testGetProductIdsForUser() {
        try {
            List<Integer> productIds = ctrlDomain.getProductIdsForUser(ctrlDomain.getUsernameSelected());
            System.out.println("Productos del usuario: " + productIds);
        } catch (UserNotAuthenticatedException e) {
            System.out.println("No hay usuario autenticado.");
        }
    }

    /**
     * Prueba el establecimiento de la similitud entre dos productos.
     */
    private static void testSetProductSimilarity() {
        System.out.print("Nombre Producto 1: ");
        String product1 = scanner.nextLine();
        System.out.print("Nombre Producto 2: ");
        String product2 = scanner.nextLine();
        System.out.print("Similitud (0 a 1): ");
        double similarity = Double.parseDouble(scanner.nextLine());
        boolean success = ctrlDomain.setProductSimilarityForUser(product1, product2, similarity);
        System.out.println(success ? "Similitud establecida exitosamente." : "Error al establecer la similitud.");
    }

    /**
     * Prueba la obtención de la similitud entre dos productos.
     */
    private static void testGetProductSimilarity() {
        System.out.print("Nombre Producto 1: ");
        String product1 = scanner.nextLine();
        System.out.print("Nombre Producto 2: ");
        String product2 = scanner.nextLine();
        double similarity = ctrlDomain.getProductSimilarityForUser(product1, product2);
        System.out.println("Similitud: " + similarity);
    }

    /**
     * Prueba la eliminación de la similitud entre dos productos.
     */
    private static void testRemoveProductSimilarity() {
        System.out.print("Nombre Producto 1: ");
        String product1 = scanner.nextLine();
        System.out.print("Nombre Producto 2: ");
        String product2 = scanner.nextLine();
        boolean success = ctrlDomain.removeProductSimilarityForUser(product1, product2);
        System.out.println(success ? "Similitud eliminada exitosamente." : "Error al eliminar la similitud.");
    }

    /**
     * Prueba la creación de una estantería para el usuario autenticado.
     */
    private static void testCreateShelf() {
        try {
            System.out.print("Nombre de la estantería: ");
            String shelfName = scanner.nextLine();
            List<Integer> products = new ArrayList<>();
            ctrlDomain.addShelfForUser(shelfName, products);
            System.out.println("Estantería creada exitosamente.");
        } catch (ShelfAlreadyExistsException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la adición de un producto a una estantería específica.
     */
    private static void testAddProductToShelf() {
        try {
            System.out.print("Nombre de la estantería: ");
            String shelfName = scanner.nextLine();
            System.out.print("ID del producto: ");
            int productID = Integer.parseInt(scanner.nextLine());
            ctrlDomain.addProductToShelfForUser(shelfName, productID);
            System.out.println("Producto añadido a la estantería.");
        } catch (ShelfNotFoundException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la eliminación de un producto de una estantería específica.
     */
    private static void testRemoveProductFromShelf() {
        try {
            System.out.print("Nombre de la estantería: ");
            String shelfName = scanner.nextLine();
            System.out.print("ID del producto: ");
            int productID = Integer.parseInt(scanner.nextLine());
            ctrlDomain.removeProductFromShelfForUser(shelfName, productID);
            System.out.println("Producto eliminado de la estantería.");
        } catch (ShelfNotFoundException | ProductNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: ID del producto inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la eliminación de una estantería para el usuario autenticado.
     */
    private static void testRemoveShelfForUser() {
        try {
            System.out.print("Nombre de la estantería a eliminar: ");
            String shelfName = scanner.nextLine();
            ctrlDomain.removeShelfForUser(shelfName);
            System.out.println("Estantería eliminada exitosamente.");
        } catch (ShelfNotFoundException | UserNotAuthenticatedException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la obtención de los productos almacenados en una estantería específica.
     */
    private static void testGetProductsInShelf() {
        try {
            System.out.print("Nombre de la estantería: ");
            String shelfName = scanner.nextLine();
            List<Integer> products = ctrlDomain.getProductsInUserShelf(shelfName);
            System.out.println("Productos en la estantería '" + shelfName + "': " + products);
        } catch (ShelfNotFoundException | UserNotAuthenticatedException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Prueba la organización de productos utilizando un algoritmo y la creación de una nueva estantería.
     */
    private static void testOrganizeProductsAndCreateShelf() {
        try {
            System.out.print("Nombre de la nueva estantería: ");
            String shelfName = scanner.nextLine();
            System.out.println("Seleccionando el algoritmo: ");
            System.out.println("1. Two Approximation");
            System.out.println("2. Brute Force");
            System.out.print("Seleccione una opción: ");
            int option = Integer.parseInt(scanner.nextLine());

            AlgorithmType algorithmType;
            if (option == 1) {
                algorithmType = TWO_APPROXIMATION;
            }
            else if (option == 2) {
                algorithmType = BRUTE_FORCE;
            }
            else {
                throw new IllegalArgumentException("Algoritmo seleccionado no es válido o no está implementado.");
            }

            boolean success = ctrlDomain.organizeProductsAndCreateShelf(shelfName, algorithmType);
            System.out.println(success ? "Estantería organizada y creada exitosamente." : "Error al organizar los productos o la estantería ya existe.");
        } catch (TriangleInequalityViolationException e) {
            System.err.println("Error: Violación de la desigualdad triangular. " + e.getMessage());
        } catch (ShelfAlreadyExistsException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
