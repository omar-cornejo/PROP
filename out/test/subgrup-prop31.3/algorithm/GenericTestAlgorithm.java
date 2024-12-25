package test.algorithm;

import main.domain.classes.algorithm.BruteForceAlgorithm;
import main.domain.classes.algorithm.ShelfOrganizer;
import main.domain.classes.algorithm.TwoAproxAlgorithm;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GenericTestAlgorithm {

    protected ShelfOrganizer twoAproxAlgorithm;
    protected ShelfOrganizer bruteForceAlgorithm;
    protected ShelfOrganizerImpl shelfOrganizer;

    protected static class ShelfOrganizerImpl extends ShelfOrganizer {
        @Override
        public List<Integer> organize(final List<Integer> products, final ArrayList<ArrayList<Double>> simMatrix) {
            return products; // Dummy impl.
        }
    }

    @Before
    public void setUp() {
        twoAproxAlgorithm = new TwoAproxAlgorithm();
        bruteForceAlgorithm = new BruteForceAlgorithm();
        shelfOrganizer = new ShelfOrganizerImpl();
    }

    final protected void createInputType1(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(4));

        double[][] simMatrixValues = {
                {0.0, 0.10, 0.15, 0.20},
                {0.10, 0.0, 0.35, 0.25},
                {0.15, 0.35, 0.0, 0.30},
                {0.20, 0.25, 0.30, 0.0}
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected void createInputType2(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(9));

        double[][] simMatrixValues = {
                {0.0, 0.29, 0.82, 0.46, 0.68, 0.52, 0.72, 0.42, 0.51},
                {0.29, 0.0, 0.55, 0.46, 0.42, 0.43, 0.43, 0.23, 0.23},
                {0.82, 0.55, 0.0, 0.68, 0.46, 0.55, 0.23, 0.43, 0.41},
                {0.46, 0.46, 0.68, 0.0, 0.82, 0.15, 0.72, 0.31, 0.62},
                {0.68, 0.42, 0.46, 0.82, 0.0, 0.74, 0.23, 0.52, 0.21},
                {0.52, 0.43, 0.55, 0.15, 0.74, 0.0, 0.61, 0.23, 0.55},
                {0.72, 0.43, 0.23, 0.72, 0.23, 0.61, 0.0, 0.42, 0.23},
                {0.42, 0.23, 0.43, 0.31, 0.52, 0.23, 0.42, 0.0, 0.33},
                {0.51, 0.23, 0.41, 0.62, 0.21, 0.55, 0.23, 0.33, 0.0}
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));

    }

    final protected void createInputType3(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(1));

        double[][] simMatrixValues = {
                {0.0},
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected void createInputType4(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(2));

        double[][] simMatrixValues = {
                {0.0, 0.2},
                {0.2, 0.0}
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected void createInputType5(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(5));

        double[][] simMatrixValues = {
                {0.0, 0.9, 0.4, 0.5, 0.8},
                {0.9, 0.0, 0.9, 0.3, 0.2},
                {0.4, 0.9, 0.0, 0.8, 0.2},
                {0.5, 0.3, 0.8, 0.0, 0.8},
                {0.8, 0.2, 0.2, 0.8, 0.0},
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected void createInputType6(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(15));

        double[][] simMatrixValues = {
                {0.00, 0.29, 0.82, 0.46, 0.68, 0.52, 0.72, 0.42, 0.51, 0.55, 0.29, 0.74, 0.23, 0.72, 0.46},
                {0.29, 0.00, 0.55, 0.46, 0.42, 0.43, 0.43, 0.23, 0.23, 0.31, 0.41, 0.51, 0.11, 0.52, 0.21},
                {0.82, 0.55, 0.00, 0.68, 0.46, 0.55, 0.23, 0.43, 0.41, 0.29, 0.79, 0.21, 0.64, 0.31, 0.51},
                {0.46, 0.46, 0.68, 0.00, 0.82, 0.15, 0.72, 0.31, 0.62, 0.42, 0.21, 0.51, 0.51, 0.43, 0.64},
                {0.68, 0.42, 0.46, 0.82, 0.00, 0.74, 0.23, 0.52, 0.21, 0.46, 0.82, 0.58, 0.46, 0.65, 0.23},
                {0.52, 0.43, 0.55, 0.15, 0.74, 0.00, 0.61, 0.23, 0.55, 0.31, 0.33, 0.37, 0.51, 0.29, 0.59},
                {0.72, 0.43, 0.23, 0.72, 0.23, 0.61, 0.00, 0.42, 0.23, 0.31, 0.77, 0.37, 0.51, 0.46, 0.33},
                {0.42, 0.23, 0.43, 0.31, 0.52, 0.23, 0.42, 0.00, 0.33, 0.15, 0.37, 0.33, 0.33, 0.31, 0.37},
                {0.51, 0.23, 0.41, 0.62, 0.21, 0.55, 0.23, 0.33, 0.00, 0.29, 0.62, 0.46, 0.29, 0.51, 0.11},
                {0.55, 0.31, 0.29, 0.42, 0.46, 0.31, 0.31, 0.15, 0.29, 0.00, 0.51, 0.21, 0.41, 0.23, 0.37},
                {0.29, 0.41, 0.79, 0.21, 0.82, 0.33, 0.77, 0.37, 0.62, 0.51, 0.00, 0.65, 0.42, 0.59, 0.61},
                {0.74, 0.51, 0.21, 0.51, 0.58, 0.37, 0.37, 0.33, 0.46, 0.21, 0.65, 0.00, 0.61, 0.11, 0.55},
                {0.23, 0.11, 0.64, 0.51, 0.46, 0.51, 0.51, 0.33, 0.29, 0.41, 0.42, 0.61, 0.00, 0.62, 0.23},
                {0.72, 0.52, 0.31, 0.43, 0.65, 0.29, 0.46, 0.31, 0.51, 0.23, 0.59, 0.11, 0.62, 0.00, 0.59},
                {0.46, 0.21, 0.51, 0.64, 0.23, 0.59, 0.33, 0.37, 0.11, 0.37, 0.61, 0.55, 0.23, 0.59, 0.00}
        };


        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected void createInputType7(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(3));

        double[][] simMatrixValues = {
                {0.0, 0.8, 0.7},
                {0.8, 0.0, 0.9},
                {0.7, 0.9, 0.0}
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected void createInputType8(List<Integer> products, List<ArrayList<Double>> simMatrix) {
        products.clear();
        products.addAll(createProductList(6));

        double[][] simMatrixValues = {
                {1.0, 0.8, 0.7, 0.6, 0.5, 0.4},
                {0.8, 1.0, 0.9, 0.7, 0.6, 0.5},
                {0.7, 0.9, 1.0, 0.8, 0.7, 0.6},
                {0.6, 0.7, 0.8, 1.0, 0.9, 0.7},
                {0.5, 0.6, 0.7, 0.9, 1.0, 0.8},
                {0.4, 0.5, 0.6, 0.7, 0.8, 1.0}
        };

        simMatrix.clear();
        simMatrix.addAll(createSimMatrix(simMatrixValues));
    }

    final protected List<Integer> createProductList(int size) {
        List<Integer> products = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            products.add(i);
        }
        return products;
    }

    final protected ArrayList<ArrayList<Double>> createSimMatrix(double[][] simMatrix) {
        ArrayList<ArrayList<Double>> newSimMatrix = new ArrayList<>();
        for (int i = 0; i < simMatrix.length; ++i) {
            ArrayList<Double> simRow = new ArrayList<>();
            for (int j = 0; j < simMatrix[i].length; ++j) {
                simRow.add(simMatrix[i][j]);
            }
            newSimMatrix.add(simRow);
        }
        return newSimMatrix;
    }

    final protected ArrayList<ArrayList<Double>> createMockSimMatrix(int rows, int cols) {
        ArrayList<ArrayList<Double>> simMatrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(1.0); // Placeholder value
            }
            simMatrix.add(row);
        }
        return simMatrix;
    }

    final protected void assertValidationError(List<Integer> products, ArrayList<ArrayList<Double>> simMatrix, String expectedMessage, ShelfOrganizer shelfOrganizer) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            shelfOrganizer.validateParameters(products, simMatrix);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }


}
