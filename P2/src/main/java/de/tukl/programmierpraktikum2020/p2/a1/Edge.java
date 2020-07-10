package de.tukl.programmierpraktikum2020.p2.a1;

public class Edge<W> {
    int fromId;
    int toId;
    W weight;

    public Edge(int fromId, int toId, W weight) {
        this.fromId = fromId;
        this.toId = toId;
        this.weight = weight;
    }
}
