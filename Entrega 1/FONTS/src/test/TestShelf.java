package test;

import main.domain.classes.Shelf;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestShelf {

    @Test
    public void testConstructor() {
        List<Integer> products = new ArrayList<>();
        Shelf shelf = new Shelf("Shelf1", products);

        assertEquals("Shelf1", shelf.getName());
        assertTrue(shelf.getProducts().isEmpty());
    }

    @Test
    public void testConstructorWithProducts() {
        List<Integer> products = Arrays.asList(1, 2, 3, 3);
        Shelf shelf = new Shelf("ShelfWithProducts", products);

        assertEquals("ShelfWithProducts", shelf.getName());
        assertEquals(3, shelf.getProducts().size()); // Filtra duplicados
        assertTrue(shelf.getProducts().contains(3));
        assertEquals(List.of(1, 2, 3), shelf.getProducts()); // Orden y valores esperados
    }

    @Test
    public void testAddProduct() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        int product = 1;

        assertTrue(shelf.addProduct(product)); // Producto añadido exitosamente
        assertFalse(shelf.addProduct(product)); // Producto duplicado no debe añadirse
    }

    @Test
    public void testRemoveProduct() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        int product = 1;

        shelf.addProduct(product);
        assertTrue(shelf.removeProduct(product)); // Producto eliminado exitosamente
        assertFalse(shelf.removeProduct(product)); // Producto ya no está
    }

    @Test
    public void testGetProducts() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        shelf.addProduct(1);
        shelf.addProduct(2);

        List<Integer> products = shelf.getProducts();
        assertEquals(2, products.size());
        assertTrue(products.contains(1));
        assertTrue(products.contains(2));
    }

    @Test
    public void testEmptyShelf() {
        Shelf shelf = new Shelf("EmptyShelf", new ArrayList<>());

        assertTrue(shelf.getProducts().isEmpty());
        assertEquals("EmptyShelf", shelf.getName());
    }

    @Test
    public void testRemoveNonExistentProduct() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        int nonExistentProduct = 999;

        assertFalse(shelf.removeProduct(nonExistentProduct)); // Producto no estaba en la estantería
    }

    @Test
    public void testOrderOfProducts() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        shelf.addProduct(1);
        shelf.addProduct(2);
        shelf.addProduct(3);

        List<Integer> expectedOrder = List.of(1, 2, 3);
        assertEquals(expectedOrder, shelf.getProducts());
    }


    @Test
    public void testClearShelf() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        shelf.addProduct(1);
        shelf.addProduct(2);

        assertEquals(2, shelf.getProducts().size()); // Verificar tamaño antes de vaciar
        shelf.clearShelf();
        assertTrue(shelf.getProducts().isEmpty());  // Verificar que esté vacía
    }

    @Test
    public void testShelfSize() {
        Shelf shelf = new Shelf("Shelf1", new ArrayList<>());
        shelf.addProduct(1);
        shelf.addProduct(2);

        assertEquals(2, shelf.size()); // Verifica el tamaño correcto
    }
}
