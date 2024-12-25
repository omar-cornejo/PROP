package test.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBruteForceAlgorithm extends GenericTestAlgorithm {
    @Test
    public void should_work_return_max_is_0_95() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType1(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertTrue(cost >= 0.94 && cost <= 0.96);
    }

    @Test
    public void should_work_return_max_is_5_48() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType2(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertTrue(cost >= 5.47 && cost <= 5.49);
    }

    @Test
    public void should_work_shelf_has_one_product_return_max_is_0() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType3(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertEquals(0.0, cost, 0.0);
    }

    @Test
    public void should_work_shelf_has_two_products_return_max_is_0_4() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType4(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertEquals(0.4, cost, 0.0);
    }

    @Test
    public void should_work_return_max_is_4_20() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType5(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertEquals(4.2, cost, 0.0);
    }

    @Test
    public void should_work_return_max_is_2_40() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType7(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertEquals(2.4, cost, 0.001);
    }

    @Test
    public void should_work_return_max_is_4_60() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType8(products, simMatrix);

        List<Integer> hamiltonianCycle = bruteForceAlgorithm.organize(products, simMatrix);
        double cost = bruteForceAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertEquals(4.6, cost, 0.001);
    }

    // VALIDATE PARAMETERS TESTS

    @Test
    public void test_validateParameters_when_products_is_null() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 1);
        assertValidationError(null, simMatrix, "The products list cannot be null or empty.", bruteForceAlgorithm);
    }

    @Test
    public void test_validateParameters_when_products_is_empty() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 1);
        assertValidationError(new ArrayList<>(), simMatrix, "The products list cannot be null or empty.", bruteForceAlgorithm);
    }

    @Test
    public void test_validateParameters_when_simmatrix_is_null() {
        List<Integer> products = List.of(1);
        assertValidationError(products, null, "The similarity matrix cannot be null.", bruteForceAlgorithm);
    }

    @Test
    public void test_validateParameters_when_product_size_is_different_to_simmatrix_size() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 2);
        List<Integer> products = List.of(1, 2);
        assertValidationError(products, simMatrix, "The similarity matrix dimensions do not match the number of products.", bruteForceAlgorithm);
    }

    @Test
    public void test_validateParameters_when_product_size_is_different_to_row_simmatrix_size() {
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        simMatrix.add(new ArrayList<>(List.of(3.0, 5.0)));
        simMatrix.add(new ArrayList<>(List.of(1.0)));

        List<Integer> products = List.of(1, 2);
        assertValidationError(products, simMatrix, "Each row in the similarity matrix must match the number of products.", bruteForceAlgorithm);
    }
}
