package main.domain.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una estantería que contiene productos identificados por sus IDs.
 * Cada estantería tiene un nombre único y una lista de productos.
 */
public class Shelf {
    private String name;              // Nombre de la estantería
    private List<Integer> products;   // Lista con los IDs de los productos en la estantería

    /**
     * Constructor de Shelf
     * Inicializa una estantería con un nombre específico y una lista de productos.
     *
     * @param name     Nombre de la estantería
     * @param products Lista de productos a incluir en la estantería
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public Shelf(String name, List<Integer> products) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la estantería no puede estar vacío.");
        }
        this.name = name;
        this.products = new ArrayList<>();
        if (products != null) {
            for (Integer product : products) {
                if (product == null || product < 0) {
                    throw new IllegalArgumentException("La lista de productos no puede contener valores nulos o negativos.");
                }
                addProduct(product); // Evitar duplicados durante la creación
            }
        }
    }

    /**
     * Obtiene el nombre de la estantería.
     *
     * @return El nombre de la estantería
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la lista de productos en la estantería.
     *
     * @return Lista de productos en la estantería
     */
    public List<Integer> getProducts() {
        return new ArrayList<>(products); // Retornar una copia para evitar modificaciones externas
    }

    /**
     * Añade un producto a la estantería si no está duplicado.
     *
     * @param product Producto a añadir
     * @return true si el producto fue añadido, false si ya estaba en la estantería
     * @throws IllegalArgumentException si el ID del producto es negativo o nulo
     */
    public boolean addProduct(int product) {
        if (product < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }
        if (!products.contains(product)) {
            products.add(product);
            return true;
        }
        return false;
    }

    /**
     * Elimina un producto de la estantería.
     *
     * @param product Producto a eliminar
     * @return true si el producto fue eliminado, false si no estaba en la estantería
     * @throws IllegalArgumentException si el ID del producto es nulo
     */
    public boolean removeProduct(Integer product) {
        if (product == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo.");
        }
        return products.remove(product);
    }

    /**
     * Limpia completamente la estantería eliminando todos los productos.
     */
    public void clearShelf() {
        products.clear();
    }

    /**
     * Devuelve el número de productos en la estantería.
     *
     * @return Número de productos en la estantería
     */
    public int size() {
        return products.size();
    }

    /**
     * Representa los detalles de la estantería en forma de cadena.
     *
     * @return Una representación en texto de la estantería
     */
    @Override
    public String toString() {
        return "Estantería [Nombre='" + name + "', Productos=" + products + "]";
    }
}
