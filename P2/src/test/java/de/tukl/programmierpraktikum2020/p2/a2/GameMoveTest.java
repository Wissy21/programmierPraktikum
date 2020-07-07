package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.Graph;
import de.tukl.programmierpraktikum2020.p2.a1.GraphImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameMoveTest {
    @Test
    public void example() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        int c = graph.addNode(Color.WHITE);
        graph.addEdge(a, b,2);
        graph.addEdge(b, a,3);
        graph.addEdge(c, b,2);
        graph.addEdge(c, a,1);

        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.setColor(c, Color.RED);
        assertEquals(Color.RED, graph.getData(c));

        //Spielzug 2:
        gm.increaseWeight(c, b);

        assertEquals(Color.RED, graph.getData(c));
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(3, graph.getWeight(c, b));

        //Spielzug 3:
        gm.setColor(c, Color.RED);
        assertEquals(Color.RED, graph.getData(c));
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));

        //Spielzug 4:
        gm.setColor(c, Color.GREEN);
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.GREEN, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));


    }

    @Test
    public void example2() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        int c = graph.addNode(Color.WHITE);
        int d = graph.addNode(Color.WHITE);

        graph.addEdge(a, b,1);
        graph.addEdge(a, c, 1);
        graph.addEdge(c, b,3);
        graph.addEdge(c, c,3);
        graph.addEdge(d, b, 1);
        graph.addEdge(d, c, 1);

        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(Color.WHITE, graph.getData(d));

        //Spielzug 2:
        gm.setColor(c, Color.GREEN);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.WHITE, graph.getData(d));

        //Spielzug 3:
        gm.setColor(d, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 4:
        gm.decreaseWeight(c, b);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));
        assertEquals(2, graph.getWeight(c, b));

        //Spielzug 5:
        gm.decreaseWeight(c, b);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));
        assertEquals(1, graph.getWeight(c, b));

    }

    @Test
    public void example3() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);
        int c = graph.addNode(Color.WHITE);
        int d = graph.addNode(Color.WHITE);

        graph.addEdge(a, b,1);
        graph.addEdge(a, c, 1);
        graph.addEdge(c, b,2);
        graph.addEdge(c, c,2);
        graph.addEdge(d, b, 2);
        graph.addEdge(d, c, 1);

        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.setColor(d, Color.RED);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 2:
        gm.setColor(c, Color.GREEN);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 3;
        gm.setColor(b, Color.BLUE);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 4:
        gm.setColor(c, Color.YELLOW);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.YELLOW, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 5:
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 6:
        gm.increaseWeight(c, c);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));
        assertEquals(3, graph.getWeight(c, c));

        //Spielzug 7:
        gm.setColor(d, Color.BLUE);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.BLUE, graph.getData(d));

        //Spielzug 8:
        gm.setColor(a, Color.YELLOW);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.BLUE, graph.getData(d));

        //Spielzug 9:
        gm.setColor(d, Color.RED);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 10:
        gm.increaseWeight(c, b);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));
        assertEquals(3, graph.getWeight(c, b));

        //Spielzug 11:
        gm.setColor(b, Color.BLUE);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 12:
        gm.setColor(d, Color.YELLOW);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.YELLOW, graph.getData(d));

        //Spielzug 13:
        gm.setColor(d, Color.RED);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        //Spielzug 14:
        gm.increaseWeight(c, b);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));
        assertEquals(4, graph.getWeight(c, b));
    }

    @Test
    public void example4() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        int b = graph.addNode(Color.WHITE);

        graph.addEdge(a, b, 1);
        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));

        //Spielzug 2:
        assertThrows(ForcedColorException.class, () -> gm.setColor(b, Color.GREEN));
    }

    @Test
    public void example5() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        graph.addEdge(a, a, 1);
        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));

        //Spielzug 2:
        assertThrows(ForcedColorException.class, () -> gm.setColor(a, Color.GREEN));
    }

    @Test
    public void example6() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        graph.addEdge(a, a, 1);
        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));

        //Spielzug 2:
        gm.decreaseWeight(a, a);
        assertEquals(0, graph.getWeight(a, a));

        //Spielzug 3:
        gm.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));

        //Spielzug 4:
        gm.setColor(a, Color.GREEN);
        assertEquals(Color.GREEN, graph.getData(a));
    }

    @Test
    public void example7() throws Exception {
        Graph<Color, Integer> graph = new GraphImpl<>();
        int a = graph.addNode(Color.WHITE);
        graph.addEdge(a, a, 1);
        GameMove gm = new GameMoveImpl(graph);

        //Spielzug 1:
        gm.decreaseWeight(a, a);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(0, graph.getWeight(a, a));

        //Spielzug 2:
        assertThrows(NegativeWeightException.class, () -> gm.decreaseWeight(a, a));
    }
}
