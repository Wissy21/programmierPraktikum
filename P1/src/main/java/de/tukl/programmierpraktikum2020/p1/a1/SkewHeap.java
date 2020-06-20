package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.function.UnaryOperator;

public class SkewHeap<E> implements PriorityQueue<E> {

    Comparator<E> comparator;
    SkewHeapNode<E> skewHeapRoot;

    public SkewHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.skewHeapRoot = null;

    }

    @Override
    public void insert(E elem) {
        skewHeapRoot = mergeHelper(skewHeapRoot, new SkewHeapNode<>(elem));
    }


    // Sourcecode 1: https://www.geeksforgeeks.org/skew-heap/
    // Sourcecode 2: https://www.javascan.com/681/java-program-to-implement-skew-heap
    private SkewHeapNode<E> mergeHelper(SkewHeapNode<E> lSkewHeapNode, SkewHeapNode<E> rSkewHeapNode) {
        if (lSkewHeapNode == null) return rSkewHeapNode;
        if (rSkewHeapNode == null) return lSkewHeapNode;
        if (comparator.compare(lSkewHeapNode.priority, rSkewHeapNode.priority) < 0) {
            SkewHeapNode<E> tmpSkewHeapNode = rSkewHeapNode.rightChild;
            rSkewHeapNode.rightChild = mergeHelper(rSkewHeapNode.leftChild, lSkewHeapNode);
            rSkewHeapNode.leftChild = tmpSkewHeapNode;
            return rSkewHeapNode;
        } else {
            SkewHeapNode<E> tmpSkewHeapNode = lSkewHeapNode.leftChild;
            lSkewHeapNode.leftChild = mergeHelper(lSkewHeapNode.rightChild, rSkewHeapNode);
            lSkewHeapNode.rightChild = tmpSkewHeapNode;
            return lSkewHeapNode;
        }
    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        while (!otherQueue.isEmpty()) {
            this.insert(otherQueue.deleteMax());
        }
    }

    @Override
    public E deleteMax() {
        if (this.isEmpty()) return null;
        E maxPriority = skewHeapRoot.priority;
        skewHeapRoot = mergeHelper(skewHeapRoot.leftChild, skewHeapRoot.rightChild);
        return maxPriority;
    }

    @Override
    public E max() {
        if (this.isEmpty()) return null;
        return skewHeapRoot.priority;
    }

    @Override
    public boolean isEmpty() {
        return skewHeapRoot == null;
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        SkewHeapNode<E> oldSkewHeapRoot = skewHeapRoot;
        skewHeapRoot = null;// altes SkewHeap geleert
        return updateHelper(oldSkewHeapRoot, elem, updatedElem, false);
    }

    private boolean updateHelper(SkewHeapNode<E> skewHeapNode, E elem, E updatedElem, boolean updated) {
        if (skewHeapNode != null) {
            if (comparator.compare(skewHeapNode.priority, elem) == 0) {
                this.insert(updatedElem);
                updated = true;
            } else {
                this.insert(skewHeapNode.priority);
            }
            updateHelper(mergeHelper(skewHeapNode.rightChild, skewHeapNode.leftChild), elem, updatedElem, updated);
        }
        return updated;
    }

    @Override
    public void map(UnaryOperator<E> f) {
        SkewHeapNode<E> oldSkewHeapRoot = skewHeapRoot; // speichert alle Elemente des SkewHeaps
        skewHeapRoot = null; // SkewHeap ausleeren
        mapHelper(oldSkewHeapRoot, f);
    }

    private void mapHelper(SkewHeapNode<E> skewHeapNode, UnaryOperator<E> f) {
        if (skewHeapNode != null) {
            this.insert(f.apply(skewHeapNode.priority));
            mapHelper(mergeHelper(skewHeapNode.leftChild, skewHeapNode.rightChild), f);
        }
    }
}
