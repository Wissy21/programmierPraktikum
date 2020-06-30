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
}
