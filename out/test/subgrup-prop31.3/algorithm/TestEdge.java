package test.algorithm;

import main.domain.classes.algorithm.datastructures.Edge;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEdge {

    @Test
    public void testGetProduct1() {
        Edge edge = new Edge(10.5, 1, 2);
        assertEquals(1, edge.getProduct1());
    }

    @Test
    public void testGetProduct2() {
        Edge edge = new Edge(10.5, 1, 2);
        assertEquals(2, edge.getProduct2());
    }

    @Test
    public void testGetCost() {
        Edge edge = new Edge(10.5, 1, 2);
        assertEquals(10.5, edge.getCost(), 0.0001);
    }

    @Test
    public void testCompareToWithEqualCosts() {
        Edge edge = new Edge(10.5, 1, 2);
        Edge edge2 = new Edge(10.5, 3, 4);

        assertEquals(0, edge.compareTo(edge2));
    }

    @Test
    public void testCompareToWithLesserCosts() {
        Edge edge = new Edge(10.0, 1, 2);
        Edge edge2 = new Edge(10.5, 3, 4);

        assertTrue(edge.compareTo(edge2) < 0);
    }

    @Test
    public void testCompareToWithGreaterCosts() {
        Edge edge = new Edge(10.5, 1, 2);
        Edge edge2 = new Edge(10.0, 3, 4);

        assertTrue(edge.compareTo(edge2) > 0);
    }

    @Test
    public void testToString() {
        Edge edge = new Edge(10.5, 1, 2);
        String expectedString = "Edge [cost=10.5, product1=1, product2=2]";
        assertEquals(expectedString, edge.toString());
    }
}
