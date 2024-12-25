package main.domain.classes;

import main.domain.classes.DTO.SimilarityMatrixDTO;
import main.domain.exceptions.ProductAlreadyExistsException;
import main.domain.exceptions.ProductNotFoundException;
import main.domain.exceptions.InvalidSimilarityValueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 La Similarity Matrix representa las similitudes entre productos mediante una matriz cuadrada, donde cada celda [i][j][i][j] indica el grado de similitud (valor entre 0 y 1) entre los productos ii y jj. 
 */

public class SimilarityMatrix {
    private HashMap<Integer, Integer> idToIndex;
    private HashMap<Integer, Integer> indexToId;
    private HashMap<Integer, ArrayList<Double>> idToSimilarities;

    /**
     * Constructor de SimilarityMatrix
     * Inicializa una matriz de similitudes
     */
    public SimilarityMatrix() {
        idToIndex = new HashMap<>();
        indexToId = new HashMap<>();
        idToSimilarities = new HashMap<>();
    }

    public SimilarityMatrix(SimilarityMatrixDTO similarityMatrixDTO) {
        this.idToIndex = similarityMatrixDTO.getIdToIndex();
        this.indexToId = similarityMatrixDTO.getIndexToId();
        this.idToSimilarities = similarityMatrixDTO.getIdToSimilarities();
    }

    // Getters

        /**
     * Obtiene una copia de la matriz de similitudes completa.
     *
     * <p>La matriz se representa como una lista de listas, donde cada fila corresponde a
     * las similitudes del producto asociado con otros productos. Se asegura de que la
     * matriz devuelta sea una copia independiente para evitar modificaciones externas.</p>
     *
     * @return Una copia de la matriz de similitudes como un {@code ArrayList<ArrayList<Double>>}.
     */
    public ArrayList<ArrayList<Double>> getMatrix() {
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        for (int i = 0; i < idToIndex.size(); ++i) {
            int id = indexToId.get(i);
            ArrayList<Double> simList = idToSimilarities.get(id);
            matrix.add(new ArrayList<>(simList));  // Crea una copia para evitar modificación externa
        }
        return matrix;
    }

    /**
     * Obtiene una copia del mapa idToIndex.
     *
     * @return Una copia de idToIndex.
     */
    public HashMap<Integer, Integer> getIdToIndex() {
        return new HashMap<>(idToIndex); // Devolvemos una copia para evitar modificaciones externas
    }

    /**
     * Obtiene una copia del mapa indexToId.
     *
     * @return Una copia de indexToId.
     */
    public HashMap<Integer, Integer> getIndexToId() {
        return new HashMap<>(indexToId); // Devolvemos una copia para evitar modificaciones externas
    }

    /**
     * Obtiene una copia del mapa idToSimilarities.
     *
     * @return Una copia de idToSimilarities.
     */
    public HashMap<Integer, ArrayList<Double>> getIdToSimilarities() {
        HashMap<Integer, ArrayList<Double>> copy = new HashMap<>();
        for (int id : idToSimilarities.keySet()) {
            copy.put(id, new ArrayList<>(idToSimilarities.get(id))); // Copia profunda
        }
        return copy;
    }

    // Setters para modificar los campos privados
    public void setIdToIndex(HashMap<Integer, Integer> idToIndex) {
        this.idToIndex = new HashMap<>(idToIndex);  // Se establece una nueva copia
    }

    public void setIndexToId(HashMap<Integer, Integer> indexToId) {
        this.indexToId = new HashMap<>(indexToId);  // Se establece una nueva copia
    }

    public void setIdToSimilarities(HashMap<Integer, ArrayList<Double>> idToSimilarities) {
        // Se crea una copia de las similitudes
        HashMap<Integer, ArrayList<Double>> copy = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Double>> entry : idToSimilarities.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        this.idToSimilarities = copy;
    }

    /**
     * Obtiene el número total de productos presentes en la matriz de similitudes.
     *
     * <p>Este número corresponde a la cantidad de productos que tienen al menos una
     * similitud registrada en la matriz.</p>
     *
     * @return El número de productos presentes en la matriz.
     */
    public int getNumProducts() {
        return idToIndex.size();
    }


    /**
     * Añade un nuevo producto con el ID especificado.
     *
     * @param id ID del nuevo producto
     * @throws ProductAlreadyExistsException si el producto con el ID ya existe
     */
    public void addProduct(final int id) {
        if (idToIndex.containsKey(id)) {
            throw new ProductAlreadyExistsException(id);
        }

        int currentSize = idToIndex.size();
        indexToId.put(currentSize, id);
        idToIndex.put(id, currentSize);

        ArrayList<Double> defaultSimilarities = new ArrayList<>();
        for (int i = 0; i < currentSize; ++i) {
            defaultSimilarities.add(0.0);
        }
        idToSimilarities.put(id, defaultSimilarities);

        for (ArrayList<Double> simList : idToSimilarities.values()) {
            simList.add(0.0);
        }
    }

    /**
     * Establece la similitud entre dos productos identificados por sus índices.
     *
     * @param id1        ID del primer producto
     * @param id2        ID del segundo producto
     * @param similarity Valor de similitud entre 0.0 y 1.0
     * @throws ProductNotFoundException si uno de los IDs no existe
     * @throws InvalidSimilarityValueException si el valor de similitud es inválido
     */
    public void setSimilarity(final int id1, final int id2, final double similarity) {
        if (!idToIndex.containsKey(id1)) {
            throw new ProductNotFoundException(id1);
        }
        if (!idToIndex.containsKey(id2)) {
            throw new ProductNotFoundException(id2);
        }
        if (similarity < 0.0 || similarity > 1.0) {
            throw new InvalidSimilarityValueException(similarity);
        }

        int idx1 = idToIndex.get(id1);
        int idx2 = idToIndex.get(id2);

        idToSimilarities.get(id1).set(idx2, similarity);
        idToSimilarities.get(id2).set(idx1, similarity);
    }

    /**
     * Obtiene la similitud entre dos productos identificados por sus índices.
     *
     * @param id1 ID del primer producto
     * @param id2 ID del segundo producto
     * @return La similitud entre los dos productos
     * @throws ProductNotFoundException si uno de los IDs no existe
     */
    public double getSimilarity(int id1, int id2) {
        if (!idToIndex.containsKey(id1)) {
            throw new ProductNotFoundException(id1);
        }
        if (!idToIndex.containsKey(id2)) {
            throw new ProductNotFoundException(id2);
        }

        int idx1 = idToIndex.get(id1);
        int idx2 = idToIndex.get(id2);

        return idToSimilarities.get(id1).get(idx2);
    }

    /**
     * Elimina un producto de la matriz de similitud
     *
     * @param id ID del producto a eliminar
     * @throws ProductNotFoundException si el ID no existe
     */
    public void removeProduct(int id) {
        if (!idToIndex.containsKey(id)) {
            throw new ProductNotFoundException(id);
        }

        System.out.println("Se borra:" + id);

        int originalSize = idToIndex.size();
        int idx = idToIndex.get(id);

        // Remueve el ID del producto de los mapas
        idToIndex.remove(id);
        indexToId.remove(idx);
        idToSimilarities.remove(id);

        // Remueve la columna correspondiente de cada lista de similitud
        for (ArrayList<Double> simList : idToSimilarities.values()) {
            simList.remove(idx);
        }

        // Actualiza los índices de los productos restantes
        for (int i = idx + 1; i < originalSize; ++i) {
            int currentId = indexToId.get(i);
            indexToId.remove(i);
            indexToId.put(i - 1, currentId);
            idToIndex.put(currentId, i - 1);
        }
    }

    /**
     * Converts a list of product indices to their corresponding product IDs.
     *
     * This method takes a list of product indices and maps each index to its
     * associated product ID using the `indexToId` mapping. The resulting list
     * contains the product IDs in the same order as the input indices.
     *
     * @param idxProducts a list of integers representing the indices of products.
     * @return a list of integers representing the IDs of the products corresponding to the input indices.
     *
     * @throws IndexOutOfBoundsException if any index in the input list is out of bounds
     *                                   for the `indexToId` mapping.
     * @throws NullPointerException if `idxProducts` is null or if any value in
     *                              `idxProducts` maps to a null value in `indexToId`.
     */
    public List<Integer> listIdxToId(List<Integer> idxProducts) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < idxProducts.size(); ++i) {
            ids.add(indexToId.get(idxProducts.get(i)));
        }
        return ids;
    }

}