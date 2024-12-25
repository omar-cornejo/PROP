package main.domain.classes;

import main.domain.classes.DTO.ShelfDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una estantería que contiene productos identificados por sus IDs.
 * Cada estantería tiene un nombre único y una lista de productos.
 */
public class Shelf {
    private String name;              // Nombre de la estantería
    private List<String> products;   // Lista con los nombres de los productos en la estantería

    /**
     * Constructor de Shelf
     * Inicializa una estantería con un nombre específico y una lista de productos.
     *
     * @param name     Nombre de la estantería
     * @param products Lista de productos a incluir en la estantería
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public Shelf(String name, List<String> products) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la estantería no puede estar vacío.");
        }
        this.name = name;
        this.products = new ArrayList<>();
        if (products != null) {
            for (String product : products) {
                if (product == null) {
                    throw new IllegalArgumentException("La lista de productos no puede contener valores nulos.");
                }
                addProduct(product); // Evitar duplicados durante la creación
            }
        }
    }

    public Shelf(ShelfDTO shelfDTO) {
        this.name = shelfDTO.getName();
        this.products = shelfDTO.getProducts();
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
    public List<String> getProducts() {
        return new ArrayList<>(products); // Retornar una copia para evitar modificaciones externas
    }

    /**
     * Añade un producto a la estantería si no está duplicado.
     *
     * @param product Producto a añadir
     * @return true si el producto fue añadido, false si ya estaba en la estantería
     * @throws IllegalArgumentException si el nombre del producto es nulo
     */
    public boolean addProduct(String product) {
        if (product==null) {
            throw new IllegalArgumentException("El nombre del producto no puede ser negativo.");
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
     * @throws IllegalArgumentException si el nombre del producto es nulo
     */
    public boolean removeProduct(String product) {
        if (product == null) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo.");
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
