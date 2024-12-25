package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// Importa todas las clases de prueba individuales
import test.TestProduct;
import test.TestShelf;
import test.TestSimilarityMatrix;
import test.TestUser;
import test.algorithm.TestBruteForceAlgorithm;
import test.algorithm.TestEdge;
import test.algorithm.TestShelfOrganizer;
import test.algorithm.TestTwoAproxAlgorithm;
import test.algorithm.TestUnionFind;

@RunWith(Suite.class)
@SuiteClasses({
        TestProduct.class,
        TestShelf.class,
        TestSimilarityMatrix.class,
        TestUser.class,
        TestBruteForceAlgorithm.class,
        TestEdge.class,
        TestShelfOrganizer.class,
        TestTwoAproxAlgorithm.class,
        TestUnionFind.class
})
public class TestMaster {
    // Esta clase no necesita ningún código adicional
    // Simplemente agrupa todas las pruebas para ejecutarlas juntas
}
