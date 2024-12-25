package drivers;

import main.domain.classes.Product;

import java.util.Scanner;

/**
 * Driver para probar la funcionalidad de la clase Product.
 * Permite realizar pruebas relacionadas con la creación y manejo de productos.
 */
public class ProductDriver {
    private static Product product;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del driver.
     * Proporciona un menú interactivo para realizar pruebas sobre la clase Product.
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            try {
                showMenu();
                String option = scanner.nextLine();
                switch (option) {
                    case "1": testCreateProduct(); break;
                    case "2": testGetProductName(); break;
                    case "3": testGetProductPrice(); break;
                    case "4": testGetProductId(); break;
                    case "5": testSetProductPrice(); break;
                    case "6": testToString(); break;
                    case "0": exit = true; break;
                    default: System.out.println("Opción no válida."); break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Entrada inválida. Asegúrese de ingresar un número cuando se solicite.");
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Muestra el menú de opciones para interactuar con la clase Product.
     */
    private static void showMenu() {
        System.out.println("\n==== Product Driver ====");
        System.out.println("1. Crear Producto");
        System.out.println("2. Obtener Nombre del Producto");
        System.out.println("3. Obtener Precio del Producto");
        System.out.println("4. Obtener ID del Producto");
        System.out.println("5. Modificar Precio del Producto");
        System.out.println("6. Mostrar Detalles del Producto");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Prueba la creación de un nuevo producto.
     * Solicita al usuario el ID, nombre y precio del producto.
     * Verifica que el precio no sea negativo.
     */
    private static void testCreateProduct() {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese el nombre del producto: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese el precio del producto: ");
            int price = Integer.parseInt(scanner.nextLine());

            if (price < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo.");
            }

            product = new Product(id, name, price);
            System.out.println("Producto creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prueba la obtención del nombre del producto.
     * Muestra el nombre del producto si este ha sido creado.
     */
    private static void testGetProductName() {
        if (checkProductExists()) {
            System.out.println("Nombre del Producto: " + product.getName());
        }
    }

    /**
     * Prueba la obtención del precio del producto.
     * Muestra el precio del producto si este ha sido creado.
     */
    private static void testGetProductPrice() {
        if (checkProductExists()) {
            System.out.println("Precio del Producto: " + product.getPrice());
        }
    }

    /**
     * Prueba la obtención del ID del producto.
     * Muestra el ID del producto si este ha sido creado.
     */
    private static void testGetProductId() {
        if (checkProductExists()) {
            System.out.println("ID del Producto: " + product.getId());
        }
    }

    /**
     * Prueba la modificación del precio del producto.
     * Solicita al usuario el nuevo precio y verifica que no sea negativo.
     */
    private static void testSetProductPrice() {
        if (checkProductExists()) {
            try {
                System.out.print("Ingrese el nuevo precio del producto: ");
                int newPrice = Integer.parseInt(scanner.nextLine());
                if (newPrice < 0) {
                    throw new IllegalArgumentException("El precio no puede ser negativo.");
                }
                product.setPrice(newPrice);
                System.out.println("Precio actualizado exitosamente.");
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Prueba la representación en cadena del producto.
     * Muestra los detalles del producto usando el método toString().
     */
    private static void testToString() {
        if (checkProductExists()) {
            System.out.println("Detalles del Producto: " + product.toString());
        }
    }

    /**
     * Verifica si el producto ha sido creado.
     *
     * @return true si el producto existe, false en caso contrario.
     */
    private static boolean checkProductExists() {
        if (product == null) {
            System.err.println("Primero debe crear un producto.");
            return false;
        }
        return true;
    }
}
