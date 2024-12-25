package main.domain.exceptions;

/**
 * Excepción lanzada cuando no se cumple la propiedad de la desigualdad triangular
 * en el grafo representado por la matriz de similitudes proporcionada.
 *
 * <p>Esta excepción se produce al invocar el método {@code organize} de la clase
 * {@code TwoApproxAlgorithm}, indicando que los valores en la matriz de similitudes
 * no satisfacen la condición de desigualdad triangular, lo cual es un requisito
 * para la correcta ejecución del algoritmo.</p>
 *
 * <p>La desigualdad triangular establece que, para cualquier conjunto de tres nodos
 * en un grafo ponderado, la distancia directa entre dos nodos cualesquiera
 * debe ser menor o igual a la suma de las distancias entre estos nodos
 * y un nodo intermedio. Si esta propiedad no se cumple, el grafo no puede
 * procesarse adecuadamente en el contexto del algoritmo de aproximación
 * de dos pasos.</p>
 */
public class TriangleInequalityViolationException extends Exception {

    /**
     * Crea una nueva instancia de {@code TriangleInequalityViolationException} con
     * un mensaje detallado que describe la violación de la desigualdad triangular.
     *
     * @param message Detalle sobre la violación de la desigualdad triangular.
     */
    public TriangleInequalityViolationException(String message) {
        super(message);
    }
}
