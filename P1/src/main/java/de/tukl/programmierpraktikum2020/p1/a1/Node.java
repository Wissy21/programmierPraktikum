package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.ArrayList;

/**
 * creates a node with a {@code E} value as head;
 * {@code int out_degree};
 * {@code boolean token};
 * {@code ArrayList<Node<E>> children} as a list of his children;
 * {@code Node<E> parrent} as his parrent node;
 */
public class Node<E> {
    E head;
    int out_degree;
    boolean token;
    ArrayList<Node<E>> children;
    Node<E> parrent;


    public Node(E head) {
        this.head = head;
        this.out_degree = 0;
        this.token = false;
        this.children = new ArrayList<>();
        this.parrent = null;
    }

    /**
     * adds child;
     * increases out_degree;
     * updates child.parrent
     */
    public void add_child(Node<E> child){
        children.add(child);
        out_degree = out_degree +1;
        child.parrent = this;
    }
}
