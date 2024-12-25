package main.domain.enums;

/**
 * Enumeración que define los tipos de algoritmos disponibles para resolver
 * problemas de optimización en el contexto de este dominio.
 *
 * <p>Los tipos de algoritmos incluyen:
 * <ul>
 *   <li>{@link #TWO_APPROXIMATION} - Una implementación de un algoritmo de aproximación
 *       con una garantía de rendimiento de dos veces el óptimo.</li>
 *   <li>{@link #BRUTE_FORCE} - Una implementación de fuerza bruta que explora todas las
 *       posibles soluciones para encontrar el resultado óptimo, generalmente utilizado
 *       para problemas de menor escala debido a su alto costo computacional.</li>
 * </ul>
 *
 * Esta enumeración permite seleccionar el algoritmo deseado en las clases
 * y métodos correspondientes.
 *
 */
public enum AlgorithmType {
    /**
     * Representa un algoritmo de aproximación que garantiza una solución
     * que es, como máximo, el doble del óptimo.
     */
    TWO_APPROXIMATION,

    /**
     * Representa un algoritmo de fuerza bruta que evalúa todas las
     * posibles combinaciones para encontrar la solución exacta.
     */
    BRUTE_FORCE;
}
