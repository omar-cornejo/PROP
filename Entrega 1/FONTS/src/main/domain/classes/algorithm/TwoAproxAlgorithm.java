package main.domain.classes.algorithm;

import main.domain.classes.algorithm.datastructures.Edge;
import main.domain.classes.algorithm.datastructures.UnionFind;

import java.util.*;

/**
 * Implementación de un algoritmo de aproximación para organizar productos en un ciclo Hamiltoniano
 * basado en un Árbol de Expansión Mínima (MST).
 *
 * <p>Esta clase extiende {@link ShelfOrganizer} y utiliza un enfoque de 2-aproximación para
 * encontrar un ciclo Hamiltoniano de alto costo, optimizando la similitud entre productos
 * adyacentes en la organización final.</p>
 */
public class TwoAproxAlgorithm extends ShelfOrganizer {

    /**
     * Comprueba la propiedad de desigualdad triangular.
     *
     * @param products  Lista de los ids de los productos
     * @param simMatrix Matriz de similitud
     * @return true si se cumple la propiedad, false si no.
     */

    public static boolean isTriangleInequalityPropertyTrue(final List<Integer> products, final ArrayList<ArrayList<Double>> simMatrix) {
        int size = simMatrix.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (i != j && i != k && j != k) {
                        if ((1 - simMatrix.get(i).get(j) - (0.001)) > (1- simMatrix.get(i).get(k)) + (1 - simMatrix.get(k).get(j))) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Organiza los productos en un ciclo que maximiza la similitud entre productos adyacentes
     * mediante una aproximación basada en MST.
     *
     * @param products   Lista de IDs de productos a organizar
     * @param simMatrix  Matriz de similitud, donde cada posición representa la
     *                   similitud entre dos productos
     * @return Una lista de IDs de productos organizados en el ciclo Hamiltoniano aproximado
     * @throws IllegalArgumentException si los parámetros de entrada son inválidos
     */
    @Override
    public List<Integer> organize(final List<Integer> products, final ArrayList<ArrayList<Double>> simMatrix) throws IllegalArgumentException {
        int productCount = products.size();

        if (productCount == 1) return new ArrayList<>(Arrays.asList(products.get(0)));

        try {
            validateParameters(products, simMatrix);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        PriorityQueue<Edge> maxPQ = createMaxPriorityQueue(simMatrix);

        List<Edge> selectedEdges = findMST(productCount, maxPQ);

        ArrayList<ArrayList<Double>> treeAdj = createTree(productCount, selectedEdges);

        List<Integer> hamiltonianCycle = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        // Inicia DFS desde el nodo de inicio para construir el ciclo Hamiltoniano
        int startNode = 0;
        dfs(startNode, treeAdj, visited, hamiltonianCycle);
        hamiltonianCycle.add(startNode);  // Cierra el ciclo volviendo al nodo inicial

        return hamiltonianCycle;
    }

    /**
     * Crea una cola de prioridad máxima que contiene todas las aristas de la matriz de similitud.
     *
     * @param simMatrix La matriz de similitud entre productos
     * @return Una cola de prioridad con las aristas ordenadas por su similitud en orden descendente
     */
    private PriorityQueue<Edge> createMaxPriorityQueue(final ArrayList<ArrayList<Double>> simMatrix) {
        // PriorityQueue<Edge> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Edge> maxPQ = new PriorityQueue<>();

        for (int i = 0; i < simMatrix.size(); i++) {
            for (int j = i + 1; j < simMatrix.size(); j++) {
                if (i != j) {
                    Edge e = new Edge(1 - simMatrix.get(i).get(j), i, j);
                    maxPQ.add(e);
                }
            }
        }

        return maxPQ;
    }

    /**
     * Encuentra el Árbol de Expansión Mínima (MST) utilizando la estructura Union-Find y las
     * aristas de la cola de prioridad máxima.
     *
     * @param size   El número de productos
     * @param maxPQ  La cola de prioridad con las aristas ordenadas
     * @return Una lista de aristas seleccionadas que conforman el MST
     */
    private List<Edge> findMST(final int size, final PriorityQueue<Edge> maxPQ) {
        UnionFind unionFind = new UnionFind(size);
        List<Edge> selectedEdges = new ArrayList<>();

        while (!maxPQ.isEmpty() && selectedEdges.size() < (size - 1)) {
            Edge e = maxPQ.poll();
            int product1 = e.getProduct1();
            int product2 = e.getProduct2();

            if (unionFind.find(product1) != unionFind.find(product2)) {
                selectedEdges.add(e);
                unionFind.union(product1, product2);
            }
        }

        return selectedEdges;
    }

    /**
     * Crea una representación de árbol en forma de matriz de adyacencia a partir de las aristas
     * seleccionadas del MST.
     *
     * @param size           El número de productos
     * @param selectedEdges  Las aristas seleccionadas que forman el MST
     * @return Una matriz de adyacencia que representa el MST
     */
    private ArrayList<ArrayList<Double>> createTree(final int size, final List<Edge> selectedEdges) {
        ArrayList<ArrayList<Double>> treeAdj = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            treeAdj.add(new ArrayList<Double>(Collections.nCopies(size, 0.0)));
        }

        // Añadir las aristas seleccionadas al árbol de adyacencia
        for (Edge e : selectedEdges) {
            int product1 = e.getProduct1();
            int product2 = e.getProduct2();
            double cost = e.getCost();

            treeAdj.get(product1).set(product2, cost);
            treeAdj.get(product2).set(product1, cost);
        }

        return treeAdj;
    }

    /**
     * Realiza una búsqueda en profundidad (DFS) en el árbol de adyacencia para construir el ciclo
     * Hamiltoniano.
     *
     * @param node               Nodo de inicio para la DFS
     * @param treeAdj            La matriz de adyacencia que representa el MST
     * @param visited            Conjunto de nodos ya visitados
     * @param hamiltonianCycle   Lista de productos en el ciclo Hamiltoniano en construcción
     */
    private void dfs(final int node, final ArrayList<ArrayList<Double>> treeAdj,
                     Set<Integer> visited, List<Integer> hamiltonianCycle) {
        visited.add(node);
        hamiltonianCycle.add(node);

        for (int i = 0; i < treeAdj.get(node).size(); i++) {
            if (i != node && treeAdj.get(node).get(i) > 0) {
                if (!visited.contains(i)) dfs(i, treeAdj, visited, hamiltonianCycle);
            }
        }
    }
}
