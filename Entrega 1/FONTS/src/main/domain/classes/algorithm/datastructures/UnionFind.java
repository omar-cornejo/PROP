package main.domain.classes.algorithm.datastructures;

import java.util.Arrays;

/**
 * Implementación de la estructura de datos Union-Find (o Disjoint Set Union),
 * que permite gestionar conjuntos de elementos particionados en subconjuntos disjuntos.
 *
 * <p>Esta estructura es útil para operaciones de unión y búsqueda, proporcionando
 * una manera eficiente de determinar a qué conjunto pertenece un elemento y de unir
 * dos conjuntos distintos.</p>
 *
 * <p>El algoritmo utiliza la compresión de caminos (path compression) en la operación
 * {@link #find(int)} y la unión por tamaño (union by size) en la operación
 * {@link #union(int, int)}, mejorando así la eficiencia de ambas operaciones.</p>
 */
public class UnionFind {
    /** Arreglo que almacena los nodos, donde el valor negativo indica el tamaño del conjunto. */
    private final int[] nodes;

    /**
     * Constructor de UnionFind.
     *
     * <p>Inicializa una estructura Union-Find con el número especificado de elementos,
     * donde cada elemento es su propio conjunto al inicio.</p>
     *
     * @param size el número de elementos (nodos) en la estructura
     */
    public UnionFind(int size) {
        nodes = new int[size];
        Arrays.fill(nodes, -1);
    }

    /**
     * Encuentra la raíz o representante del conjunto al que pertenece el elemento dado.
     *
     * <p>Utiliza compresión de caminos para reducir la profundidad del árbol,
     * asignando la raíz directamente al nodo para futuras búsquedas.</p>
     *
     * @param n el nodo cuyo conjunto representativo se desea encontrar
     * @return el índice del representante (raíz) del conjunto al que pertenece {@code n}
     */
    public int find(int n) {
        if (nodes[n] < 0) return n;
        else {
            int father = find(nodes[n]);
            nodes[n] = father;  // Compresión de caminos
            return father;
        }
    }

    /**
     * Une los conjuntos a los que pertenecen dos nodos, si no pertenecen ya al mismo conjunto.
     *
     * <p>Utiliza unión por tamaño, es decir, el conjunto más pequeño se adjunta al
     * conjunto más grande para minimizar la profundidad del árbol resultante.</p>
     *
     * @param a el primer nodo
     * @param b el segundo nodo
     */
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (nodes[rootA] <= nodes[rootB]) {
                nodes[rootA] += nodes[rootB];
                nodes[rootB] = rootA;
            } else {
                nodes[rootB] += nodes[rootA];
                nodes[rootA] = rootB;
            }
        }
    }
}
