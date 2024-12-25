package drivers;

import main.domain.classes.SimilarityMatrix;
import main.domain.exceptions.ProductAlreadyExistsException;
import main.domain.exceptions.ProductNotFoundException;
import main.domain.exceptions.InvalidSimilarityValueException;

import java.util.Scanner;

/**
 * Driver para probar la funcionalidad de la clase SimilarityMatrix.
 * Permite realizar pruebas relacionadas con la gestión de productos y sus similitudes.
 */
public class SimilarityMatrixDriver {
    private static SimilarityMatrix similarityMatrix;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del driver.
     * Proporciona un menú interactivo para probar la clase SimilarityMatrix.
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            String option = scanner.nextLine();
            try {
                switch (option) {
                    case "1": testCreateSimilarityMatrix(); break;
                    case "2": testAddProduct(); break;
                    case "3": testSetSimilarity(); break;
                    case "4": testGetSimilarity(); break;
                    case "5": testRemoveProduct(); break;
                    case "6": testGetMatrix(); break;
                    case "0": exit = true; break;
                    default: System.out.println("Opción no válida."); break;
                }
            } catch (ProductAlreadyExistsException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (ProductNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (InvalidSimilarityValueException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Muestra el menú de opciones para interactuar con la clase SimilarityMatrix.
     */
    private static void showMenu() {
        System.out.println("\n==== SimilarityMatrix Driver ====");
        System.out.println("1. Crear Matriz de Similitud");
        System.out.println("2. Añadir Producto a la Matriz");
        System.out.println("3. Establecer Similitud entre Productos");
        System.out.println("4. Obtener Similitud entre Productos");
        System.out.println("5. Eliminar Producto de la Matriz");
        System.out.println("6. Mostrar Matriz Completa");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Prueba la creación de una nueva matriz de similitud.
     * Inicializa un objeto de la clase SimilarityMatrix.
     */
    private static void testCreateSimilarityMatrix() {
        similarityMatrix = new SimilarityMatrix();
        System.out.println("Matriz de similitud creada.");
    }

    /**
     * Prueba la adición de un producto a la matriz de similitud.
     * Solicita el ID del producto al usuario y lo añade a la matriz.
     */
    private static void testAddProduct() {
        if (similarityMatrix == null) {
            System.out.println("Primero debe crear una matriz de similitud.");
            return;
        }
        System.out.print("ID del producto a añadir: ");
        int productId = Integer.parseInt(scanner.nextLine());
        similarityMatrix.addProduct(productId);
        System.out.println("Producto añadido a la matriz con ID: " + productId + ". Tamaño actual: " + similarityMatrix.getNumProducts());
    }

    /**
     * Prueba el establecimiento de una similitud entre dos productos.
     * Solicita los IDs de los productos y el valor de la similitud al usuario.
     */
    private static void testSetSimilarity() {
        if (similarityMatrix == null) {
            System.out.println("Primero debe crear una matriz de similitud.");
            return;
        }
        System.out.print("ID del primer producto: ");
        int productId1 = Integer.parseInt(scanner.nextLine());
        System.out.print("ID del segundo producto: ");
        int productId2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor de similitud (0 a 1): ");
        double similarity = Double.parseDouble(scanner.nextLine());
        similarityMatrix.setSimilarity(productId1, productId2, similarity);
        System.out.println("Similitud establecida entre los productos con ID " + productId1 + " y " + productId2);
    }

    /**
     * Prueba la obtención de la similitud entre dos productos.
     * Solicita los IDs de los productos al usuario y muestra el valor de similitud.
     */
    private static void testGetSimilarity() {
        if (similarityMatrix == null) {
            System.out.println("Primero debe crear una matriz de similitud.");
            return;
        }
        System.out.print("ID del primer producto: ");
        int productId1 = Integer.parseInt(scanner.nextLine());
        System.out.print("ID del segundo producto: ");
        int productId2 = Integer.parseInt(scanner.nextLine());
        double similarity = similarityMatrix.getSimilarity(productId1, productId2);
        System.out.println("Similitud entre productos con ID " + productId1 + " y " + productId2 + ": " + similarity);
    }

    /**
     * Prueba la eliminación de un producto de la matriz de similitud.
     * Solicita el ID del producto al usuario y lo elimina de la matriz.
     */
    private static void testRemoveProduct() {
        if (similarityMatrix == null) {
            System.out.println("Primero debe crear una matriz de similitud.");
            return;
        }
        System.out.print("ID del producto a eliminar: ");
        int productId = Integer.parseInt(scanner.nextLine());
        similarityMatrix.removeProduct(productId);
        System.out.println("Producto con ID " + productId + " eliminado de la matriz.");
    }

    /**
     * Prueba la obtención de la matriz completa de similitudes.
     * Muestra la representación de la matriz en forma de cadena.
     */
    private static void testGetMatrix() {
        if (similarityMatrix == null) {
            System.out.println("Primero debe crear una matriz de similitud.");
            return;
        }
        System.out.println("Matriz de similitudes: ");
        System.out.println(similarityMatrix.getMatrix());
    }
}
