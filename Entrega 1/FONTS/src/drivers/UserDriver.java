package drivers;

import main.domain.classes.User;
import main.domain.exceptions.InvalidPasswordException;
import main.domain.exceptions.InvalidUsernameException;

import java.util.Scanner;

/**
 * Driver para probar la funcionalidad de la clase User.
 * Permite realizar pruebas relacionadas con la creación y manejo de usuarios.
 */
public class UserDriver {
    private static User user;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del driver.
     * Proporciona un menú interactivo para probar la clase User.
     */
    public static void main(String[] args) {
        initializeUser();
        boolean exit = false;
        while (!exit) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1": testAuthenticate(); break;
                case "2": testGetName(); break;
                case "3": testGetUsername(); break;
                case "4": testUpdateUsername(); break;
                case "5": testGetPassword(); break;
                case "6": testUpdatePassword(); break;
                case "7": testToString(); break;
                case "0": exit = true; break;
                default: System.out.println("Opción no válida."); break;
            }
        }
    }

    /**
     * Inicializa un objeto User solicitando al usuario su nombre completo, nombre de usuario y contraseña.
     * Finaliza el programa si ocurre un error al crear el usuario.
     */
    private static void initializeUser() {
        System.out.print("Ingrese el nombre completo: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        try {
            user = new User(name, username, password);
            System.out.println("Usuario creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            System.exit(1); // Salida del programa si falla la creación
        }
    }

    /**
     * Muestra el menú de opciones para interactuar con la clase User.
     */
    private static void showMenu() {
        System.out.println("\n==== User Driver ====");
        System.out.println("1. Autenticar Usuario");
        System.out.println("2. Obtener Nombre del Usuario");
        System.out.println("3. Obtener Nombre de Usuario");
        System.out.println("4. Actualizar Nombre de Usuario");
        System.out.println("5. Obtener Contraseña");
        System.out.println("6. Actualizar Contraseña");
        System.out.println("7. Mostrar Detalles del Usuario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Prueba la autenticación de un usuario.
     * Solicita el nombre de usuario y la contraseña, e intenta autenticarlos.
     */
    private static void testAuthenticate() {
        System.out.print("Ingrese el nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();

        try {
            boolean authenticated = user.authenticate(username, password);
            System.out.println(authenticated ? "Autenticación exitosa." : "Credenciales incorrectas.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prueba la obtención del nombre completo del usuario.
     * Muestra el nombre completo del usuario.
     */
    private static void testGetName() {
        System.out.println("Nombre completo del usuario: " + user.getName());
    }

    /**
     * Prueba la obtención del nombre de usuario.
     * Muestra el nombre de usuario asociado al objeto User.
     */
    private static void testGetUsername() {
        System.out.println("Nombre de usuario: " + user.getUsername());
    }

    /**
     * Prueba la actualización del nombre de usuario.
     * Solicita un nuevo nombre de usuario e intenta actualizarlo.
     */
    private static void testUpdateUsername() {
        System.out.print("Ingrese el nuevo nombre de usuario: ");
        String newUsername = scanner.nextLine();

        try {
            user.setUsername(newUsername);
            System.out.println("Nombre de usuario actualizado a: " + newUsername);
        } catch (InvalidUsernameException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prueba la obtención de la contraseña del usuario.
     * Muestra la contraseña actual del usuario.
     */
    private static void testGetPassword() {
        System.out.println("Contraseña actual del usuario: " + user.getPassword());
    }

    /**
     * Prueba la actualización de la contraseña del usuario.
     * Solicita la contraseña actual y una nueva contraseña, e intenta actualizarlas.
     */
    private static void testUpdatePassword() {
        System.out.print("Ingrese la contraseña actual: ");
        String currentPassword = scanner.nextLine();
        System.out.print("Ingrese la nueva contraseña: ");
        String newPassword = scanner.nextLine();

        try {
            user.setPassword(currentPassword, newPassword);
            System.out.println("Contraseña actualizada correctamente.");
        } catch (InvalidPasswordException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prueba la representación en cadena del usuario.
     * Muestra los detalles del usuario usando el método toString().
     */
    private static void testToString() {
        if (user == null) {
            System.out.println("Primero debe crear un usuario.");
            return;
        }
        System.out.println("Detalles del usuario: " + user.toString());
    }
}
