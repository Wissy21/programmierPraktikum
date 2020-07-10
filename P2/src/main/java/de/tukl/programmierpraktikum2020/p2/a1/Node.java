package de.tukl.programmierpraktikum2020.p2.a1;

public class Node<D> {
    int identifier;
    D data;

    public Node(int identifier, D data) {
        this.identifier = identifier;
        this.data = data;
    }
}
