package main.domain.classes.algorithm.datastructures;

/**
 * Representa una arista en un grafo ponderado, utilizada para modelar la relación
 * entre dos productos con un costo asociado.
 *
 * <p>Una arista conecta dos nodos (productos) y tiene un peso (coste), lo cual
 * indica la similitud entre estos.</p>
 *
 * <p>Esta clase implementa {@link Comparable}, lo que permite ordenar las aristas
 * por su costo de manera ascendente.</p>
 */
public class Edge implements Comparable<Edge> {
    /** El costo de la arista, que representa la similitud entre dos productos. */
    private final double cost;

    /** Identificador del primer producto conectado por esta arista. */
    private final int product1;

    /** Identificador del segundo producto conectado por esta arista. */
    private final int product2;

    /**
     * Crea una nueva instancia de {@code Edge} con el costo especificado y los
     * identificadores de los dos productos conectados por esta arista.
     *
     * @param cost el costo de la arista
     * @param product1 el identificador del primer producto
     * @param product2 el identificador del segundo producto
     */
    public Edge(double cost, int product1, int product2) {
        this.cost = cost;
        this.product1 = product1;
        this.product2 = product2;
    }

    /**
     * Devuelve el identificador del primer producto.
     *
     * @return el identificador del primer producto
     */
    public int getProduct1() {
        return product1;
    }

    /**
     * Devuelve el identificador del segundo producto.
     *
     * @return el identificador del segundo producto
     */
    public int getProduct2() {
        return product2;
    }

    /**
     * Devuelve el costo de esta arista.
     *
     * @return el costo de la arista
     */
    public double getCost() {
        return cost;
    }

    /**
     * Compara esta arista con otra arista en función de su costo.
     *
     * <p>Este método permite ordenar aristas de manera ascendente por costo,
     * siendo útil en algoritmos que requieren procesamiento de aristas en
     * orden de menor a mayor peso.</p>
     *
     * @param other la otra arista a comparar
     * @return un valor negativo si esta arista tiene un costo menor, cero si
     *         los costos son iguales, y un valor positivo si esta arista
     *         tiene un costo mayor que {@code other}
     */
    @Override
    public int compareTo(final Edge other) {
        return Double.compare(cost, other.cost);
    }

    /**
     * Devuelve una representación en formato String
     * de esta arista, incluyendo el costo
     * y los identificadores de los productos conectados.
     *
     * @return una representación en cadena de esta arista
     */
    @Override
    public String toString() {
        return "Edge [coste=" + cost + ", product1=" + product1 + ", product2=" + product2 + "]";
    }
}
