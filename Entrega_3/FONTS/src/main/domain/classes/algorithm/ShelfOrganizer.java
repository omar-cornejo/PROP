package main.domain.classes.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta `ShelfOrganizer` que define la estructura básica para organizar
 * productos en un orden específico basado en una matriz de similitud.
 *
 * <p>Las clases que extienden `ShelfOrganizer` deben implementar el método
 * {@link #organize(List, ArrayList)}, que define la lógica para organizar
 * los productos en base a sus similitudes.</p>
 */
public abstract class ShelfOrganizer {

    /**
     * Organiza los productos en un orden específico basado en la matriz de similitud
     * proporcionada. Este método debe ser implementado por las clases que extienden
     * `ShelfOrganizer`.
     *
     * @param products  Lista de IDs de los productos a organizar
     * @param simMatrix Matriz de similitud, donde cada posición representa la
     *                  similitud entre dos productos
     * @return Una lista de IDs de productos en el orden organizado
     * @throws IllegalArgumentException si los parámetros de entrada son inválidos
     */
    public abstract List<Integer> organize(final List<Integer> products, final ArrayList<ArrayList<Double>> simMatrix) throws IllegalArgumentException;

    /**
     * Valida los parámetros de entrada para asegurar que la lista de productos y
     * la matriz de similitud cumplen con las condiciones necesarias para el
     * proceso de organización.
     *
     * <p>Este método verifica que la lista de productos no sea nula ni vacía,
     * que la matriz de similitud no sea nula, y que sus dimensiones sean
     * consistentes con el número de productos.</p>
     *
     * @param products  Lista de IDs de los productos
     * @param simMatrix Matriz de similitud entre los productos
     * @throws IllegalArgumentException si los productos o la matriz de similitud no son válidos
     */
    public final void validateParameters(final List<Integer> products, final ArrayList<ArrayList<Double>> simMatrix) throws IllegalArgumentException {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no puede ser nula ni estar vacía..");
        }

        if (simMatrix == null) {
            throw new IllegalArgumentException("La matriz de similitud no puede ser nula..");
        }

        int productCount = products.size();
        if (simMatrix.size() != productCount) {
            throw new IllegalArgumentException("Las dimensiones de la matriz de similitud no coinciden con el número de productos.");
        }

        for (ArrayList<Double> row : simMatrix) {
            if (row == null || row.size() != productCount) {
                throw new IllegalArgumentException("Cada fila de la matriz de similitud debe coincidir con el número de productos.");
            }
        }
    }

    /**
     * Calcula el costo total de un ciclo Hamiltoniano en base a la matriz de similitud
     * entre productos.
     *
     * <p>Este método recorre el ciclo dado y suma los valores de similitud entre
     * productos adyacentes para calcular el costo total del ciclo.</p>
     *
     * @param hamiltonianCycle Lista de IDs de productos que representan el ciclo Hamiltoniano
     * @param simMatrix        Matriz de similitud entre productos
     * @return El costo total del ciclo Hamiltoniano
     */
    final public double calculateCost(final List<Integer> hamiltonianCycle, final ArrayList<ArrayList<Double>> simMatrix) {
        double cost = 0.0;
        for (int i = 1; i < hamiltonianCycle.size(); i++) {
            cost += simMatrix.get(hamiltonianCycle.get(i - 1)).get(hamiltonianCycle.get(i));
        }

        return cost;
    }
}
