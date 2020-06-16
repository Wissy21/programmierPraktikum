package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class ListQueue<E> implements PriorityQueue<E> {
    LinkedList<E> queue = new LinkedList<>();
    Comparator<E> comparator;

    public ListQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void insert(E elem) {
        queue.addLast(elem);
        queue.sort(comparator.reversed());
    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        while (!otherQueue.isEmpty()) {
            insert(otherQueue.deleteMax());
        }
    }

    @Override
    public E deleteMax() {
        if (!queue.isEmpty()) {
            return queue.removeFirst();
        }
        return null;
    }

    @Override
    public E max() {
        if (!queue.isEmpty()) {
            return queue.getFirst();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean update(E elem, E updatedElem) {
        if (queue.contains(elem)) {
            queue.remove(elem);
            this.insert(updatedElem);
            return true;
        }
        return false;
    }

    @Override
    public void map(UnaryOperator<E> f) {
        LinkedList<E> tmpQueue = queue;
        queue = new LinkedList<>();
        for (E elem : tmpQueue) this.insert(f.apply(elem));

        /*ListQueue<E> oldPriorityQueue = this;
        System.out.println(oldPriorityQueue.max());
        this.queue = new LinkedList<>();
        while (!oldPriorityQueue.isEmpty()) {
            System.out.println("MAP");
            this.insert(f.apply(oldPriorityQueue.deleteMax()));
        }*/
    }
}
