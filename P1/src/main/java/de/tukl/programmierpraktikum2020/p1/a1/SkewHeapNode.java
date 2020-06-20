package de.tukl.programmierpraktikum2020.p1.a1;

public class SkewHeapNode<E> {
    E priority;
    SkewHeapNode<E> leftChild;
    SkewHeapNode<E> rightChild;

    public SkewHeapNode(E priority) {
        this.priority = priority;
        this.leftChild = null;
        this.rightChild = null;
    }
}
