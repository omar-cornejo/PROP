package drivers;

import main.domain.classes.Shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver para probar la funcionalidad de la clase Shelf.
 * Permite realizar pruebas relacionadas con la gestión de estanterías.
 */
public class ShelfDriver {
    private static Shelf shelf;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del driver.
     * Proporciona un menú interactivo para probar la clase Shelf.
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            String option = scanner.nextLine();
            try {
                switch (option) {
                    case "1": testCreateShelf(); break;
                    case "2": testGetShelfName(); break;
                    case "3": testGetProducts(); break;
                    case "4": testAddProduct(); break;
                    case "5": testRemoveProduct(); break;
                    case "6": testClearShelf(); break;
                    case "7": testGetShelfSize(); break;
                    case "0": exit = true; break;
                    default: System.out.println("Opción no válida."); break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Muestra el menú de opciones para interactuar con la clase Shelf.
     */
    private static void showMenu() {
        System.out.println("\n==== Shelf Driver ====");
        System.out.println("1. Crear Estantería");
        System.out.println("2. Obtener Nombre de la Estantería");
        System.out.println("3. Obtener Lista de Productos");
        System.out.println("4. Añadir Producto a la Estantería");
        System.out.println("5. Eliminar Producto de la Estantería");
        System.out.println("6. Vaciar Estantería");
        System.out.println("7. Consultar Número de Productos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Prueba la creación de una nueva estantería.
     * Solicita al usuario el nombre de la estantería y permite reemplazar una existente.
     */
    private static void testCreateShelf() {
        if (shelf != null) {
            System.out.println("Ya existe una estantería. ¿Desea reemplazarla? (s/n)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("s")) {
                return;
            }
        }
        System.out.print("Ingrese el nombre de la estantería: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("El nombre de la estantería no puede estar vacío.");
            return;
        }
        shelf = new Shelf(name, new ArrayList<>());
        System.out.println("Estantería creada exitosamente.");
    }

    /**
     * Prueba la obtención del nombre de la estantería.
     * Muestra el nombre si la estantería ha sido creada.
     */
    private static void testGetShelfName() {
        if (shelf == null) {
            System.out.println("Primero debe crear una estantería.");
            return;
        }
        System.out.println("Nombre de la estantería: " + shelf.getName());
    }

    /**
     * Prueba la obtención de la lista de productos de la estantería.
     * Muestra los productos si existen, o indica si la estantería está vacía.
     */
    private static void testGetProducts() {
        if (shelf == null) {
            System.out.println("Primero debe crear una estantería.");
            return;
        }
        List<Integer> products = shelf.getProducts();
        if (products.isEmpty()) {
            System.out.println("La estantería está vacía.");
        } else {
            System.out.print("Productos en la estantería (IDs): ");
            products.forEach(productId -> System.out.print(productId + " "));
            System.out.println();
        }
    }

    /**
     * Prueba la adición de un producto a la estantería.
     * Solicita al usuario el ID del producto y lo añade si no está ya presente.
     */
    private static void testAddProduct() {
        if (shelf == null) {
            System.out.println("Primero debe crear una estantería.");
            return;
        }
        System.out.print("Ingrese el ID del producto a añadir: ");
        int productId = Integer.parseInt(scanner.nextLine());

        boolean success = shelf.addProduct(productId);
        System.out.println(success ? "Producto añadido a la estantería." : "El producto ya existe en la estantería.");
    }

    /**
     * Prueba la eliminación de un producto de la estantería.
     * Solicita al usuario el ID del producto y lo elimina si está presente.
     */
    private static void testRemoveProduct() {
        if (shelf == null) {
            System.out.println("Primero debe crear una estantería.");
            return;
        }
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int productId = Integer.parseInt(scanner.nextLine());

        boolean success = shelf.removeProduct(productId);
        System.out.println(success ? "Producto eliminado de la estantería." : "El producto no está en la estantería.");
    }

    /**
     * Prueba el vaciado de la estantería.
     * Elimina todos los productos de la estantería.
     */
    private static void testClearShelf() {
        if (shelf == null) {
            System.out.println("Primero debe crear una estantería.");
            return;
        }
        shelf.clearShelf();
        System.out.println("Estantería vaciada exitosamente.");
    }

    /**
     * Prueba la obtención del número de productos en la estantería.
     * Muestra el tamaño de la estantería.
     */
    private static void testGetShelfSize() {
        if (shelf == null) {
            System.out.println("Primero debe crear una estantería.");
            return;
        }
        System.out.println("Número de productos en la estantería: " + shelf.size());
    }
}
