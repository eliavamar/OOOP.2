package api.test;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {

    @Test
    void init() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);
    }

    @Test
    void getGraph() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);
        assertNotNull(s);
    }

    @Test
    void copy() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        dw_graph_algorithms g1 = new DWGraph_Algo();
        g.init(s);
        directed_weighted_graph h = new DWGraph_DS();
        h = g1.copy();
        h.removeEdge(2, 1);
        assertEquals(h, s);
        h.connect(1, 2, 1);
        assertEquals(h, s);
        h.connect(2, 1, 1);
        h.removeEdge(1, 2);
        assertEquals(h, s);
        h.removeNode(1);
        assertEquals(h, s);
        NodeData t = new NodeData(1);
        h.addNode(t);
        assertEquals(h, s);
    }

    @Test
    void isConnected() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);
        assertFalse(g.isConnected());
        s.connect(1, 2, 1);
        s.connect(2, 3, 1);
        s.connect(3, 4, 1);
        s.connect(4, 5, 1);
        s.connect(5, 1, 1);
        assertTrue(g.isConnected());
        s.removeEdge(3, 4);
        assertFalse(g.isConnected());
        s.connect(3, 4, -1);
        assertFalse(g.isConnected());

    }

    @Test
    void shortestPathDist() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);
        assertEquals(g.shortestPathDist(1, 5), -1);
        s.connect(1, 2, 2);
        s.connect(2, 3, 1);
        s.connect(3, 4, 1);
        s.connect(4, 5, 1);
        s.connect(5, 1, 1);
        assertEquals(g.shortestPathDist(1, 3), 3);
        assertEquals(g.shortestPathDist(5, 2), 3);


    }

    @Test
    void switchGraph() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);

    }

    @Test
    void shortestPath() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);
        LinkedList<node_data> shortPath = new LinkedList<node_data>();
        shortPath.addFirst(s.getNode(3));
        shortPath.addFirst(s.getNode(2));
        shortPath.addFirst(s.getNode(1));
        s.connect(1, 2, 0.1);
        s.connect(2, 3, 0.2);
        s.connect(1, 3, 3);
        assertEquals(shortPath, g.shortestPath(1, 3));
        assertEquals(g.shortestPath(4, 3).size(), 2);
        assertNull(g.shortestPath(4, 1));
        s.removeEdge(1, 2);
        assertEquals(g.shortestPath(1, 3).size(), 2);
    }

    @Test
    void save() {
        directed_weighted_graph s = DWGraph_DSTest.graphCreator();
        dw_graph_algorithms g = new DWGraph_Algo();
        g.init(s);

    }

    @Test
    void load() {
    }



    @Test
    void searchComponents() {
    }
}