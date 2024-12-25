package main.domain.classes;

import main.domain.classes.DTO.ProductDTO;

/**
 * Representa un producto con un ID único, un nombre y un precio.
 */
public class Product {
    private int id;          // ID único del producto
    private String name;     // Nombre del producto
    private int price;       // Precio actual del producto

    /**
     * Constructor de Product
     * Inicializa un producto con su ID, nombre y precio.
     *
     * @param id    Identificación única del producto
     * @param name  Nombre del producto
     * @param price Precio inicial del producto
     * @throws IllegalArgumentException si el ID es negativo, el nombre es nulo o vacío, o el precio es negativo
     */
    public Product(int id, String name, int price) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser negativo.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío o ser nulo.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(ProductDTO productDTO) {
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
    }

    // ==============================
    // Caso de Uso: Consultar Atributos de Producto
    // ==============================

    /**
     * getName
     * Retorna el nombre del producto.
     *
     * @return El nombre del producto
     */
    public String getName() {
        return name;
    }

    /**
     * getPrice
     * Retorna el precio actual del producto.
     *
     * @return El precio actual del producto
     */
    public int getPrice() {
        return price;
    }

    /**
     * getId
     * Devuelve el ID único del producto.
     *
     * @return El ID del producto
     */
    public int getId() {
        return id;
    }

    // ==============================
    // Caso de Uso: Modificar Producto
    // ==============================

    /**
     * setPrice
     * Establece un nuevo precio para el producto.
     *
     * @param newPrice El nuevo precio del producto
     * @throws IllegalArgumentException si el nuevo precio es negativo
     */
    public void setPrice(int newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        this.price = newPrice;
    }

    // ==============================
    // Información general y depuración
    // ==============================

    /**
     * toString
     * Devuelve una representación en forma de texto del producto, incluyendo su ID, nombre y precio.
     *
     * @return Una cadena con los detalles del producto
     */
    @Override
    public String toString() {
        return "Producto [ID=" + id + ", Nombre=" + name + ", Precio=" + price + "]";
    }
}
