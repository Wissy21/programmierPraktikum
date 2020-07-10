package de.tukl.programmierpraktikum2020.p2.a1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class GraphTest {
    @Test
    public void exampleOneNode() throws Exception {
        Graph<String, Double> graph = new GraphImpl<>();
        assertTrue(graph.getNodeIds().isEmpty());
        graph.addNode("a");
        graph.addEdge(0,0,0.0);

        //addEdge Test
        assertThrows(InvalidNodeException.class, () -> graph.addEdge(0,1,1.0), "Ausnahme InvalidNodeException nicht geworfen");
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(0,0,1.0), "Ausnahme InvalidNodeException nicht geworfen");
        //getWeight Test
        assertEquals(0.0, graph.getWeight(0,0));
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(0,1), "Ausnahme InvalidEdgeException nicht geworfen");
        //setWeight Test
        graph.setWeight(0,0,1.0);
        assertEquals(1.0, graph.getWeight(0,0));
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(0,1,1.0));

        //outgoingNeighbours
        Set<Integer> erwartet = Set.of(0);
        assertEquals(erwartet, graph.getOutgoingNeighbors(0));
        //incoming Test
        assertEquals(erwartet, graph.getIncomingNeighbors(0));
    }

    @Test
    public void exampleThreeNodes() throws Exception {
        Graph<String, Double> graph = new GraphImpl<>();

        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");

        graph.addEdge(0,1,2.0);
        graph.addEdge(1,0,3.0);
        graph.addEdge(2,0,1.0);
        graph.addEdge(2,1,1.0);

        assertThrows(InvalidNodeException.class, () -> graph.addEdge(0,3,1.0), "Ausnahme InvalidNodeException nicht geworfen");
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(0,1,3.0),"DuplicateEdgeException nicht geworfen");

        assertEquals(2.0, graph.getWeight(0,1));
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(0,3), "Ausnahme InvalidEdgeException nicht geworfen");

        graph.setWeight(2,1,2.0);
        assertEquals(2.0, graph.getWeight(2,1));
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(0,3,1.0));

        Set<Integer> erwartetOutgoing = Set.of(1);
        assertEquals(erwartetOutgoing, graph.getOutgoingNeighbors(0));

        Set<Integer> erwartetIncoming = Set.of(1,2);
        assertEquals(erwartetIncoming, graph.getIncomingNeighbors(0));
    }
    @Test
    public void exampleFourNodes() throws Exception {
        Graph<String, Double> graph = new GraphImpl<>();

        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");

        graph.addEdge(0,1,1.0);
        graph.addEdge(0,2,1.0);
        graph.addEdge(2,1,3.0);
        graph.addEdge(2,2,3.0);
        graph.addEdge(3,2,1.0);
        graph.addEdge(3,1,1.0);


        assertThrows(InvalidNodeException.class, () -> graph.addEdge(3,4,1.0), "Ausnahme InvalidNodeException nicht geworfen");
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(2,1,0.0),"DuplicateEdgeException nicht geworfen");

        assertEquals(3.0, graph.getWeight(2,2));
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(0,4), "Ausnahme InvalidEdgeException nicht geworfen");
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(0,100), "Ausnahme InvalidEdgeException nicht geworfen");

        graph.setWeight(2,2,2.0);
        assertEquals(2.0, graph.getWeight(2,2));
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(0,10,1.0));

        Set<Integer> erwartetOutgoingC = Set.of(1,2);
        assertEquals(erwartetOutgoingC, graph.getOutgoingNeighbors(2));

        Set<Integer> erwartetIncomingC = Set.of(0,2,3);
        assertEquals(erwartetIncomingC, graph.getIncomingNeighbors(2));

    }

    @Test
    public void exampleManu1() throws GraphException {
        Graph<String, Integer> graph = new GraphImpl<>();
        assertTrue(graph.getNodeIds().isEmpty());

        assertEquals(0, graph.addNode("a"));
        assertFalse(graph.getNodeIds().isEmpty());
        assertEquals(1, graph.addNode("b"));
        assertEquals(2, graph.addNode("d"));
        assertTrue(graph.getNodeIds().containsAll(Set.of(0, 1, 2)));

        assertEquals("a", graph.getData(0));
        assertEquals("b", graph.getData(1));
        assertEquals("d", graph.getData(2));

        graph.setData(2, "c");
        assertEquals("c", graph.getData(2));

        assertThrows(InvalidNodeException.class, () ->
                graph.getData(3), "Ausnahme InvalidNodeException nicht geworfen");

        assertThrows(InvalidNodeException.class, () ->
                graph.setData(3, "e"), "Ausnahme InvalidNodeException nicht geworfen");

        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 0, 3);
        graph.addEdge(2, 0, 1);
        graph.addEdge(2, 1, 2);

        assertThrows(InvalidNodeException.class, () ->
                graph.addEdge(3, 3, 2), "Ausnahme InvalidNodeException nicht geworfen");

        assertThrows(InvalidNodeException.class, () ->
                graph.addEdge(0, 3, 2), "Ausnahme InvalidNodeException nicht geworfen");

        assertThrows(DuplicateEdgeException.class, () ->
                graph.addEdge(0, 1, 2), "Ausnahme DuplicateEdgeException nicht geworfen");

        assertThrows(DuplicateEdgeException.class, () ->
                graph.addEdge(1, 0, 3), "Ausnahme DuplicateEdgeException nicht geworfen");

        assertThrows(DuplicateEdgeException.class, () ->
                graph.addEdge(2, 0, 1), "Ausnahme DuplicateEdgeException nicht geworfen");

        assertThrows(DuplicateEdgeException.class, () ->
                graph.addEdge(2, 1, 2), "Ausnahme DuplicateEdgeException nicht geworfen");

        assertEquals(2, graph.getWeight(0, 1));
        assertEquals(3, graph.getWeight(1, 0));
        assertEquals(1, graph.getWeight(2, 0));
        assertEquals(2, graph.getWeight(2, 1));

        assertThrows(InvalidEdgeException.class, () ->
                graph.getWeight(3, 3), "Ausnahme InvalidNodeException nicht geworfen");

        graph.setWeight(2, 0, 4);
        assertEquals(4, graph.getWeight(2, 0));

        assertThrows(InvalidEdgeException.class, () ->
                graph.setWeight(0, 2, 4), "Ausnahme InvalidEdgeException nicht geworfen");

        // IncomingNeighborTest
        assertEquals(Set.of(1, 2), graph.getIncomingNeighbors(0));
        assertEquals(Set.of(0, 2), graph.getIncomingNeighbors(1));
        assertEquals(Set.of(), graph.getIncomingNeighbors(2));

        assertThrows(InvalidNodeException.class, () ->
                graph.getIncomingNeighbors(3), "Ausnahme InvalidNodeException nicht geworfen");

        // OutgoingNeighborTest
        assertEquals(Set.of(1), graph.getOutgoingNeighbors(0));
        assertEquals(Set.of(0), graph.getOutgoingNeighbors(1));
        assertEquals(Set.of(0, 1), graph.getOutgoingNeighbors(2));

        assertThrows(InvalidNodeException.class, () ->
                graph.getOutgoingNeighbors(3), "Ausnahme InvalidNodeException nicht geworfen");

    }

    @Test
    public void exampleManu2() throws Exception {

        Graph<String, Integer> graph = new GraphImpl<>();
        assertTrue(graph.getNodeIds().isEmpty());

        assertEquals(0, graph.addNode("a"));
        assertFalse(graph.getNodeIds().isEmpty());
        assertEquals(1, graph.addNode("b"));
        assertEquals(2, graph.addNode("c"));
        assertEquals(3, graph.addNode("d"));
        assertEquals(Set.of(0, 1, 2, 3), graph.getNodeIds());

        // Tests for data
        assertEquals("a", graph.getData(0));
        assertEquals("b", graph.getData(1));
        assertEquals("c", graph.getData(2));
        assertEquals("d", graph.getData(3));

        graph.addEdge(0, 1, 1); // a->b
        graph.addEdge(0, 2, 1); // a->c
        graph.addEdge(2, 1, 2); // c->b
        graph.addEdge(2, 2, 2); // c->c
        graph.addEdge(3, 1, 2); // d->b
        graph.addEdge(3, 2, 1); // d->c

        // Tests for weights
        assertEquals(1, graph.getWeight(0, 1));
        assertEquals(1, graph.getWeight(0, 2));
        assertEquals(2, graph.getWeight(2, 1));
        assertEquals(2, graph.getWeight(2, 2));
        assertEquals(2, graph.getWeight(3, 1));
        assertEquals(1, graph.getWeight(3, 2));

        // IncomingNeighborTest
        assertEquals(Set.of(), graph.getIncomingNeighbors(0));
        assertEquals(Set.of(0, 2, 3), graph.getIncomingNeighbors(1));
        assertEquals(Set.of(0, 2, 3), graph.getIncomingNeighbors(2));
        assertEquals(Set.of(), graph.getIncomingNeighbors(3));

        // OutgoingNeighborTest
        assertEquals(Set.of(1, 2), graph.getOutgoingNeighbors(0));
        assertEquals(Set.of(), graph.getOutgoingNeighbors(1));
        assertEquals(Set.of(1, 2), graph.getOutgoingNeighbors(2));
        assertEquals(Set.of(1, 2), graph.getOutgoingNeighbors(3));
    }

}
