package main.domain.classes.DTO;

import main.domain.classes.SimilarityMatrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Data Transfer Object (DTO) for the {@code SimilarityMatrix} class.
 * Contains all necessary fields for transferring similarity matrix data securely.
 */
public class SimilarityMatrixDTO implements Serializable {

    private Map<Integer, Integer> idToIndex; // Mapping from product IDs to matrix indices
    private Map<Integer, Integer> indexToId; // Mapping from matrix indices to product IDs
    private Map<Integer, ArrayList<Double>> idToSimilarities; // Similarities mapped by product ID

    /**
     * Default constructor for serialization/deserialization.
     */
    public SimilarityMatrixDTO() {
    }

    public SimilarityMatrixDTO(Map<Integer, Integer> idToIndex, Map<Integer, Integer> indexToId, Map<Integer, ArrayList<Double>> idToSimilarities) {
        this.idToIndex = idToIndex;
        this.indexToId = indexToId;
        this.idToSimilarities = idToSimilarities;
    }

    // ==========================
    // Getters and Setters
    // ==========================

    /**
     * Gets the mapping from product IDs to matrix indices.
     *
     * @return The mapping of product IDs to indices.
     */
    public HashMap<Integer, Integer> getIdToIndex() {
        return new HashMap<>(idToIndex);
    }

    /**
     * Sets the mapping from product IDs to matrix indices.
     *
     * @param idToIndex The mapping of product IDs to indices.
     */
    public void setIdToIndex(Map<Integer, Integer> idToIndex) {
        this.idToIndex = idToIndex != null ? new HashMap<>(idToIndex) : new HashMap<>();
    }

    /**
     * Gets the mapping from matrix indices to product IDs.
     *
     * @return The mapping of indices to product IDs.
     */
    public HashMap<Integer, Integer> getIndexToId() {
        return new HashMap<>(indexToId);
    }

    /**
     * Sets the mapping from matrix indices to product IDs.
     *
     * @param indexToId The mapping of indices to product IDs.
     */
    public void setIndexToId(Map<Integer, Integer> indexToId) {
        this.indexToId = indexToId != null ? new HashMap<>(indexToId) : new HashMap<>();
    }

    /**
     * Gets the mapping of product IDs to their similarity lists.
     *
     * @return The mapping of product IDs to similarity lists.
     */
    public HashMap<Integer, ArrayList<Double>> getIdToSimilarities() {
        return new HashMap<>(idToSimilarities);
    }

    /**
     * Sets the mapping of product IDs to their similarity lists.
     *
     * @param idToSimilarities The mapping of product IDs to similarity lists.
     */
    public void setIdToSimilarities(Map<Integer, ArrayList<Double>> idToSimilarities) {
        this.idToSimilarities = idToSimilarities != null ? new HashMap<>(idToSimilarities) : new HashMap<>();
    }

    // ==========================
    // toString
    // ==========================

    /**
     * Represents the {@code SimilarityMatrixDTO} object as a string.
     *
     * @return A string representation of the {@code SimilarityMatrixDTO}.
     */
    @Override
    public String toString() {
        return "SimilarityMatrixDTO{" +
                "idToIndex=" + idToIndex +
                ", indexToId=" + indexToId +
                ", idToSimilarities=" + idToSimilarities +
                '}';
    }
}
