package test.algorithm;

import main.domain.classes.algorithm.datastructures.UnionFind;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestUnionFind {
    @Test
    public void testFind() {
        UnionFind unionFind = new UnionFind(3);

        assertNotEquals(unionFind.find(0), unionFind.find(1));
        assertNotEquals(unionFind.find(0), unionFind.find(2));
        assertNotEquals(unionFind.find(1), unionFind.find(2));
    }

    @Test
    public void testUnion() {
        UnionFind unionFind = new UnionFind(3);

        assertNotEquals(unionFind.find(0), unionFind.find(1));
        unionFind.union(0, 1);
        assertEquals(unionFind.find(0), unionFind.find(1));
    }
}
