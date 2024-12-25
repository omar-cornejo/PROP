package test;

import main.domain.classes.SimilarityMatrix;
import main.domain.exceptions.ProductNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSimilarityMatrix {

    @Test
    public void testAddProduct() {
        SimilarityMatrix matrix = new SimilarityMatrix();

        // Añade dos productos con IDs específicos y verifica el tamaño de la matriz
        matrix.addProduct(0);
        matrix.addProduct(1);

        // Verifica que las posiciones por defecto sean 0.0
        assertEquals(0.0, matrix.getSimilarity(0, 0), 0.0);
        assertEquals(0.0, matrix.getSimilarity(0, 1), 0.0);
        assertEquals(0.0, matrix.getSimilarity(1, 0), 0.0);
        assertEquals(0.0, matrix.getSimilarity(1, 1), 0.0);
    }

    @Test
    public void testSetAndGetSimilarity() {
        SimilarityMatrix matrix = new SimilarityMatrix();

        // Añade productos con IDs específicos antes de establecer similitudes
        matrix.addProduct(0);
        matrix.addProduct(1);

        // Ahora, establecer y comprobar la similitud debería funcionar correctamente
        matrix.setSimilarity(0, 1, 0.5);
        assertEquals(0.5, matrix.getSimilarity(0, 1), 0.0);
        assertEquals(0.5, matrix.getSimilarity(1, 0), 0.0);
    }

    @Test
    public void testRemoveProduct() {
        SimilarityMatrix matrix = new SimilarityMatrix();

        // Añade tres productos con IDs específicos
        matrix.addProduct(0);
        matrix.addProduct(1);
        matrix.addProduct(2);

        // Establece algunas similitudes
        matrix.setSimilarity(0, 1, 0.3);
        matrix.setSimilarity(1, 2, 0.7);

        // Elimina el segundo producto (ID 1)
        matrix.removeProduct(1);

        // Verifica que ahora la matriz tiene solo los productos con IDs 0 y 2
        assertEquals(0.0, matrix.getSimilarity(0, 0), 0.0);
        assertEquals(0.0, matrix.getSimilarity(0, 2), 0.0);
        assertEquals(0.0, matrix.getSimilarity(2, 0), 0.0);
        assertEquals(0.0, matrix.getSimilarity(2, 2), 0.0);
    }

    @Test
    public void testInvalidIndex() {
        SimilarityMatrix matrix = new SimilarityMatrix();

        // Verifica que obtener la similitud entre productos inexistentes arroje una excepción
        try {
            matrix.getSimilarity(0, 1);
            fail("Se esperaba una ProductNotFoundException al intentar obtener similitud de productos no existentes.");
        } catch (ProductNotFoundException e) {
            assertEquals("El producto con ID 0 no existe en la matriz de similitud.", e.getMessage());
        }

        // Añade un solo producto y verifica que al intentar obtener similitud con otro ID inexistente arroje la excepción
        matrix.addProduct(0);
        try {
            matrix.getSimilarity(1, 0);
            fail("Se esperaba una ProductNotFoundException al intentar obtener similitud de productos no existentes.");
        } catch (ProductNotFoundException e) {
            assertEquals("El producto con ID 1 no existe en la matriz de similitud.", e.getMessage());
        }
    }
}
