package test.algorithm;

import main.domain.classes.algorithm.TwoAproxAlgorithm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTwoAproxAlgorithm extends GenericTestAlgorithm {

    @Test
    public void should_work_return_max_is_between_0_475_and_0_95() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType1(products, simMatrix);

        List<Integer> hamiltonianCycle = twoAproxAlgorithm.organize(products, simMatrix);
        double cost = twoAproxAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertTrue(cost >= 0.475 && cost <= 0.95);
    }

    @Test
    public void should_return_triangle_inequality_violation_1() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType2(products, simMatrix);

        assertFalse(TwoAproxAlgorithm.isTriangleInequalityPropertyTrue(products, simMatrix));
    }

    @Test
    public void should_work_shelf_has_one_product_return_max_is_0() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType3(products, simMatrix);

        List<Integer> hamiltonianCycle = twoAproxAlgorithm.organize(products, simMatrix);
        double cost = twoAproxAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertEquals(0.0, cost, 0.0);
    }

    @Test
    public void should_work_shelf_has_two_products_return_max_is_between_0_2_and_0_4() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType4(products, simMatrix);

        List<Integer> hamiltonianCycle = twoAproxAlgorithm.organize(products, simMatrix);
        double cost = twoAproxAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertTrue(cost >= 0.2 && cost <= 0.4);
    }

    @Test
    public void should_return_triangle_inequality_violation_2() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType5(products, simMatrix);

        assertFalse(TwoAproxAlgorithm.isTriangleInequalityPropertyTrue(products, simMatrix));
    }

    @Test
    public void should_return_triangle_inequality_violation_3() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType6(products, simMatrix);

        assertFalse(TwoAproxAlgorithm.isTriangleInequalityPropertyTrue(products, simMatrix));
    }

    @Test
    public void should_work__return_max_is_between_1_2_and_2_5() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType7(products, simMatrix);

        List<Integer> hamiltonianCycle = twoAproxAlgorithm.organize(products, simMatrix);
        double cost = twoAproxAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertTrue(cost >= 1.3 && cost <= 2.5);
    }

    @Test
    public void should_work__return_max_is_between_2_3_and_4_7() {
        List<Integer> products = new ArrayList<>();
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        createInputType8(products, simMatrix);

        List<Integer> hamiltonianCycle = twoAproxAlgorithm.organize(products, simMatrix);
        double cost = twoAproxAlgorithm.calculateCost(hamiltonianCycle, simMatrix);

        assertTrue(cost >= 2.3 && cost <= 4.7);
    }

    // VALIDATE PARAMETERS TESTS

    @Test
    public void test_validateParameters_when_products_is_null() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(6, 6);
        assertValidationError(null, simMatrix, "The products list cannot be null or empty.", twoAproxAlgorithm);
    }

    @Test
    public void test_validateParameters_when_products_is_empty() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(3, 3);
        assertValidationError(new ArrayList<>(), simMatrix, "The products list cannot be null or empty.", twoAproxAlgorithm);
    }

    @Test
    public void test_validateParameters_when_simmatrix_is_null() {
        List<Integer> products = List.of(5);
        assertValidationError(products, null, "The similarity matrix cannot be null.", twoAproxAlgorithm);
    }

    @Test
    public void test_validateParameters_when_product_size_is_different_to_simmatrix_size() {
        ArrayList<ArrayList<Double>> simMatrix = createMockSimMatrix(1, 2);
        List<Integer> products = List.of(1, 2);
        assertValidationError(products, simMatrix, "The similarity matrix dimensions do not match the number of products.", twoAproxAlgorithm);
    }

    @Test
    public void test_validateParameters_when_product_size_is_different_to_row_simmatrix_size() {
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        simMatrix.add(new ArrayList<>(List.of(1.5, 4.0)));
        simMatrix.add(new ArrayList<>(List.of(1.25)));

        List<Integer> products = List.of(1, 2);
        assertValidationError(products, simMatrix, "Each row in the similarity matrix must match the number of products.", twoAproxAlgorithm);
    }

}