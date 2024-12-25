package test;

import main.domain.classes.Product;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProduct {

    @Test
    public void testConstructor() {
        Product product = new Product(1, "Product1", 100);
        assertEquals(1, product.getId());
        assertEquals("Product1", product.getName());
        assertEquals(100, product.getPrice());
    }

    @Test
    public void testSetPrice() {
        Product product = new Product(1, "Product1", 100);
        product.setPrice(150);
        assertEquals(150, product.getPrice());
    }

    @Test
    public void testToString() {
        Product product = new Product(1, "Product1", 100);
        assertEquals("Producto [ID=1, Nombre=Product1, Precio=100]", product.toString());
    }
}
