package test.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestShelfOrganizer extends GenericTestAlgorithm {

    @Test
    public void test_validateParameters_when_products_is_null() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 1);
        assertValidationError(null, simMatrix, "The products list cannot be null or empty.", shelfOrganizer);
    }

    @Test
    public void test_validateParameters_when_products_is_empty() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 1);
        assertValidationError(new ArrayList<>(), simMatrix, "The products list cannot be null or empty.", shelfOrganizer);
    }

    @Test
    public void test_validateParameters_when_simmatrix_is_null() {
        List<Integer> products = List.of(1);
        assertValidationError(products, null, "The similarity matrix cannot be null.", shelfOrganizer);
    }

    @Test
    public void test_validateParameters_when_product_size_is_different_to_simmatrix_size() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 2);
        List<Integer> products = List.of(1, 2);
        assertValidationError(products, simMatrix, "The similarity matrix dimensions do not match the number of products.", shelfOrganizer);
    }

    @Test
    public void test_validateParameters_when_product_size_is_different_to_row_simmatrix_size() {
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        simMatrix.add(new ArrayList<>(List.of(1.0, 2.0)));
        simMatrix.add(new ArrayList<>(List.of(1.0)));

        List<Integer> products = List.of(1, 2);
        assertValidationError(products, simMatrix, "Each row in the similarity matrix must match the number of products.", shelfOrganizer);
    }
}
