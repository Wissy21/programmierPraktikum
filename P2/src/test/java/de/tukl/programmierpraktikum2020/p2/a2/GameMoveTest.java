package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameMoveTest {
    @Test
    public void example1() throws Exception {
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
    public void example1_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;
            Color b = Color.WHITE;
            Color c = Color.WHITE;

            // Gewichte in dieser Reihenfolge von a->b; b->a; c->a; c->b
            final int[] weights = {2, 3, 1, 2};

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                if (nodeId == 1) return b;
                if (nodeId == 2) return c;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else if (nodeId == 1) b = data;
                else if (nodeId == 2) c = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                // a->b; b->a; c->a; c->b
                if (fromId == 0 && toId == 1) return weights[0];
                if (fromId == 1 && toId == 0) return weights[1];
                if (fromId == 2 && toId == 0) return weights[2];
                if (fromId == 2 && toId == 1) return weights[3];
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                // a->b; b->a; c->a; c->b
                if (fromId == 0 && toId == 1) weights[0] = weight;
                else if (fromId == 1 && toId == 0) weights[1] = weight;
                else if (fromId == 2 && toId == 0) weights[2] = weight;
                else if (fromId == 2 && toId == 1) weights[3] = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0, 1, 2);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(1, 2);
                if (nodeId == 1) return Set.of(0, 2);
                if (nodeId == 2) return Set.of();
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(1);
                if (nodeId == 1) return Set.of(0);
                if (nodeId == 2) return Set.of(0, 1);
                throw new InvalidNodeException(nodeId);
            }
        };

        int a = 0, b = 1, c = 2;

        // Test Startzustand
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(2, graph.getWeight(a, b));
        assertEquals(3, graph.getWeight(b, a));
        assertEquals(1, graph.getWeight(c, a));
        assertEquals(2, graph.getWeight(c, b));

        GameMove gameMove = new GameMoveImpl(graph);

        // Spielzug 1
        gameMove.setColor(c, Color.RED);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.RED, graph.getData(c));

        // Spielzug 2
        gameMove.increaseWeight(c, b);
        assertEquals(3, graph.getWeight(c, b));
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.RED, graph.getData(c));

        // Spielzug 3
        gameMove.setColor(c, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.RED, graph.getData(c));

        // Spielzug 4
        gameMove.setColor(c, Color.GREEN);
        assertEquals(Color.GREEN, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
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
    public void example2_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;
            Color b = Color.WHITE;
            Color c = Color.WHITE;
            Color d = Color.WHITE;

            // Gewichte in dieser Reihenfolge von a->b; a->c; c->b; c->c; d->b; d->c:
            final int[] weights = {1, 1, 3, 3, 1, 1};

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                if (nodeId == 1) return b;
                if (nodeId == 2) return c;
                if (nodeId == 3) return d;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else if (nodeId == 1) b = data;
                else if (nodeId == 2) c = data;
                else if (nodeId == 3) d = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                if (fromId == 0 && toId == 1) return weights[0];
                if (fromId == 0 && toId == 2) return weights[1];
                if (fromId == 2 && toId == 1) return weights[2];
                if (fromId == 2 && toId == 2) return weights[3];
                if (fromId == 3 && toId == 1) return weights[4];
                if (fromId == 3 && toId == 2) return weights[5];
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                if (fromId == 0 && toId == 1) this.weights[0] = weight;
                else if (fromId == 0 && toId == 2) this.weights[1] = weight;
                else if (fromId == 2 && toId == 1) this.weights[2] = weight;
                else if (fromId == 2 && toId == 2) this.weights[3] = weight;
                else if (fromId == 3 && toId == 1) this.weights[4] = weight;
                else if (fromId == 3 && toId == 2) this.weights[5] = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0, 1, 2, 3);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of();
                if (nodeId == 1) return Set.of(0, 2, 3);
                if (nodeId == 2) return Set.of(0, 2, 3);
                if (nodeId == 3) return Set.of();
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(1, 2);
                if (nodeId == 1) return Set.of();
                if (nodeId == 2) return Set.of(1, 2);
                if (nodeId == 3) return Set.of(1, 2);
                throw new InvalidNodeException(nodeId);
            }
        };

        int a = 0, b = 1, c = 2, d = 3;


        // Test Startzustand
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(Color.WHITE, graph.getData(d));

        assertEquals(1, graph.getWeight(a, b));
        assertEquals(1, graph.getWeight(a, c));
        assertEquals(3, graph.getWeight(c, b));
        assertEquals(3, graph.getWeight(c, c));
        assertEquals(1, graph.getWeight(d, b));
        assertEquals(1, graph.getWeight(d, c));

        GameMove gameMove = new GameMoveImpl(graph);

        // Spielzug 1
        gameMove.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(Color.WHITE, graph.getData(d));

        // Spielzug 2
        gameMove.setColor(c, Color.GREEN);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.WHITE, graph.getData(d));

        // Spielzug 3
        gameMove.setColor(d, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        // Spielzug 4
        gameMove.decreaseWeight(c, b);
        assertEquals(2, graph.getWeight(c, b));
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        // Spielzug 5
        gameMove.decreaseWeight(c, b);
        assertEquals(1, graph.getWeight(c, b));
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

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
    public void example3_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;
            Color b = Color.WHITE;
            Color c = Color.WHITE;
            Color d = Color.WHITE;

            // Gewichte in dieser Reihenfolge von a->b; a->c; c->b; c->c; d->b; d->c:
            final int[] weights = {1, 1, 2, 2, 2, 1};

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                if (nodeId == 1) return b;
                if (nodeId == 2) return c;
                if (nodeId == 3) return d;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else if (nodeId == 1) b = data;
                else if (nodeId == 2) c = data;
                else if (nodeId == 3) d = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                if (fromId == 0 && toId == 1) return weights[0];
                if (fromId == 0 && toId == 2) return weights[1];
                if (fromId == 2 && toId == 1) return weights[2];
                if (fromId == 2 && toId == 2) return weights[3];
                if (fromId == 3 && toId == 1) return weights[4];
                if (fromId == 3 && toId == 2) return weights[5];
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                if (fromId == 0 && toId == 1) this.weights[0] = weight;
                else if (fromId == 0 && toId == 2) this.weights[1] = weight;
                else if (fromId == 2 && toId == 1) this.weights[2] = weight;
                else if (fromId == 2 && toId == 2) this.weights[3] = weight;
                else if (fromId == 3 && toId == 1) this.weights[4] = weight;
                else if (fromId == 3 && toId == 2) this.weights[5] = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0, 1, 2, 3);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of();
                if (nodeId == 1) return Set.of(0, 2, 3);
                if (nodeId == 2) return Set.of(0, 2, 3);
                if (nodeId == 3) return Set.of();
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(1, 2);
                if (nodeId == 1) return Set.of();
                if (nodeId == 2) return Set.of(1, 2);
                if (nodeId == 3) return Set.of(1, 2);
                throw new InvalidNodeException(nodeId);
            }
        };

        int a = 0, b = 1, c = 2, d = 3;

        // Test Startzustand
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(Color.WHITE, graph.getData(d));

        assertEquals(1, graph.getWeight(a, b));
        assertEquals(1, graph.getWeight(a, c));
        assertEquals(2, graph.getWeight(c, b));
        assertEquals(2, graph.getWeight(c, c));
        assertEquals(2, graph.getWeight(d, b));
        assertEquals(1, graph.getWeight(d, c));

        GameMove gameMove = new GameMoveImpl(graph);
        gameMove.setColor(d, Color.RED);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.WHITE, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(c, Color.GREEN);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(b, Color.BLUE);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(b, Color.YELLOW);
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.YELLOW, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.increaseWeight(c, c);
        assertEquals(3, graph.getWeight(c, c));
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(d, Color.BLUE);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.BLUE, graph.getData(d));

        gameMove.setColor(a, Color.YELLOW);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.BLUE, graph.getData(d));

        gameMove.setColor(d, Color.RED);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.increaseWeight(c, b);
        assertEquals(3, graph.getWeight(c, b));
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(b, Color.BLUE);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.setColor(d, Color.YELLOW);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.YELLOW, graph.getData(d));

        gameMove.setColor(d, Color.RED);
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.BLUE, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));

        gameMove.increaseWeight(c, b);
        assertEquals(4, graph.getWeight(c, b));
        assertEquals(Color.YELLOW, graph.getData(a));
        assertEquals(Color.GREEN, graph.getData(b));
        assertEquals(Color.GREEN, graph.getData(c));
        assertEquals(Color.RED, graph.getData(d));
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
    public void example4_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;
            Color b = Color.WHITE;

            int weight = 1;

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                if (nodeId == 1) return b;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else if (nodeId == 1) b = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                if (fromId == 0 && toId == 1) return weight;
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                if (fromId == 0 && toId == 1) this.weight = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0, 1);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of();
                if (nodeId == 1) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(1);
                if (nodeId == 1) return Set.of();
                throw new InvalidNodeException(nodeId);
            }
        };

        int a = 0;
        int b = 1;

        // Test Startzustand
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(Color.WHITE, graph.getData(b));
        assertEquals(1, graph.getWeight(a, b));

        GameMove gameMove = new GameMoveImpl(graph);

        // Spielzug 1
        gameMove.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));

        // Spielzug 2
        assertThrows(ForcedColorException.class, () ->
                gameMove.setColor(b, Color.GREEN), "Ausnahme ForcedColorException nicht geworfen!"
        );
        assertEquals(Color.RED, graph.getData(a));
        assertEquals(Color.RED, graph.getData(b));
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
    public void example5_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;

            int weight = 1;

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                if (fromId == 0 && toId == 0) return weight;
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                if (fromId == 0 && toId == 0) this.weight = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }
        };


        int a = 0;
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(1, graph.getWeight(a, a));

        GameMove gameMove = new GameMoveImpl(graph);
        gameMove.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));

        assertThrows(ForcedColorException.class, () ->
                gameMove.setColor(a, Color.GREEN), "Ausnahme ForcedColorException nicht geworfen");
        assertEquals(Color.RED, graph.getData(a));
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
    public void example6_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;

            int weight = 1;

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                if (fromId == 0 && toId == 0) return weight;
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                if (fromId == 0 && toId == 0) this.weight = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }
        };

        int a = 0;

        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(1, graph.getWeight(a, a));

        GameMove gameMove = new GameMoveImpl(graph);

        //Spielzug 1
        gameMove.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));

        //Spielzug 2
        gameMove.decreaseWeight(a, a);
        assertEquals(0, graph.getWeight(a, a));

        //Spielzug 3
        gameMove.setColor(a, Color.RED);
        assertEquals(Color.RED, graph.getData(a));

        //Spielzug 4
        gameMove.setColor(a, Color.GREEN);
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

    @Test
    public void example7_2() throws Exception {
        Graph<Color, Integer> graph = new Graph<>() {
            Color a = Color.WHITE;

            int weight = 1;

            @Override
            public int addNode(Color data) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Color getData(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return a;
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public void setData(int nodeId, Color data) throws InvalidNodeException {
                if (nodeId == 0) a = data;
                else throw new InvalidNodeException(nodeId);
            }

            @Override
            public void addEdge(int fromId, int toId, Integer weight) throws InvalidNodeException, DuplicateEdgeException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Integer getWeight(int fromId, int toId) throws InvalidEdgeException {
                if (fromId == 0 && toId == 0) return weight;
                throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public void setWeight(int fromId, int toId, Integer weight) throws InvalidEdgeException {
                if (fromId == 0 && toId == 0) this.weight = weight;
                else throw new InvalidEdgeException(fromId, toId);
            }

            @Override
            public Set<Integer> getNodeIds() {
                return Set.of(0);
            }

            @Override
            public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }

            @Override
            public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
                if (nodeId == 0) return Set.of(0);
                throw new InvalidNodeException(nodeId);
            }
        };
        int a = 0;

        //initial test
        assertEquals(Color.WHITE, graph.getData(a));
        assertEquals(1, graph.getWeight(a, a));

        GameMove gameMove = new GameMoveImpl(graph);

        //Spielzug 1
        gameMove.decreaseWeight(a, a);
        assertEquals(0, graph.getWeight(a, a));

        //Spielzug 2
        assertThrows(NegativeWeightException.class, () ->
                gameMove.decreaseWeight(a, a), "Ausnahme NegativeWeightException nicht geworfen");

        assertEquals(Color.WHITE, graph.getData(a));
    }
}
