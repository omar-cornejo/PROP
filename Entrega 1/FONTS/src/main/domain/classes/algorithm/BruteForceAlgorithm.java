package main.domain.classes.algorithm;

import java.util.*;

/**
 * Implementación del algoritmo de fuerza bruta para organizar productos en un ciclo
 * Hamiltoniano que maximiza el costo total de similitud entre productos adyacentes.
 *
 * <p>Esta clase extiende {@link ShelfOrganizer} y utiliza un enfoque de búsqueda exhaustiva
 * (fuerza bruta) para encontrar el ciclo Hamiltoniano con el costo máximo. Debido a la complejidad
 * factorial del algoritmo, su uso es adecuado solo para problemas con un número pequeño de productos.</p>
 */
public class BruteForceAlgorithm extends ShelfOrganizer {

    /**
     * Organiza los productos en un ciclo que maximiza la similitud total entre
     * productos adyacentes usando un algoritmo de fuerza bruta.
     *
     * @param products   Lista de IDs de productos a organizar
     * @param simMatrix  Matriz de similitud, donde cada posición representa la
     *                   similitud entre dos productos
     * @return Una lista de IDs de productos organizados en el orden que maximiza la
     *         similitud total entre productos consecutivos en el ciclo
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

        double[] maxCost = {0.0};
        List<Integer> currentHamiltonianCycle = new ArrayList<>();
        List<Integer> maxHamiltonianCycle = new ArrayList<>();
        boolean[] visited = new boolean[productCount];
        brute_force(0, productCount, currentHamiltonianCycle, visited, simMatrix, maxCost, maxHamiltonianCycle);

        return maxHamiltonianCycle;
    }

    /**
     * Función auxiliar recursiva que implementa el algoritmo de fuerza bruta
     * para encontrar el ciclo Hamiltoniano con el costo máximo.
     *
     * <p>Explora todas las posibles permutaciones de productos y calcula el costo
     * de cada ciclo para determinar el ciclo con el mayor valor de similitud total.</p>
     *
     * @param currentIdx              Índice actual en la construcción del ciclo
     * @param totalSize               Número total de productos
     * @param currentHamiltonianCycle Lista de productos en el ciclo actual en construcción
     * @param visited                 Arreglo de booleanos para rastrear los productos visitados
     * @param simMatrix               Matriz de similitud entre productos
     * @param maxCost                 Arreglo que contiene el costo máximo encontrado hasta ahora
     * @param maxHamiltonianCycle     Lista de productos que representa el ciclo con el costo máximo
     */
    private void brute_force(final int currentIdx, final int totalSize, List<Integer> currentHamiltonianCycle,
                             boolean[] visited, final ArrayList<ArrayList<Double>> simMatrix,
                             double[] maxCost, List<Integer> maxHamiltonianCycle) {

        if (currentIdx == totalSize) {
            currentHamiltonianCycle.add(currentHamiltonianCycle.get(0));
            double actCost = calculateCost(currentHamiltonianCycle, simMatrix);
            if (actCost > maxCost[0]) {
                maxCost[0] = actCost;
                maxHamiltonianCycle.clear();
                maxHamiltonianCycle.addAll(currentHamiltonianCycle);
            }
            currentHamiltonianCycle.remove(currentHamiltonianCycle.size() - 1);
        }
        else {
            for (int i = 0; i < visited.length; ++i) {
                if (!visited[i]) {
                    visited[i] = true;
                    currentHamiltonianCycle.add(i);
                    brute_force(currentIdx + 1, totalSize, currentHamiltonianCycle, visited, simMatrix, maxCost, maxHamiltonianCycle);
                    visited[i] = false;
                    currentHamiltonianCycle.remove(currentHamiltonianCycle.size() - 1);
                }
            }
        }
    }
}
