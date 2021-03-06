package de.tukl.programmierpraktikum2020.p1.a1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    /**
     * Diese Methode wird verwendet, um Instanzen von PriorityQueue Implementierungen an Testmethoden zu übergeben.
     */
    public static List<PriorityQueue<Integer>> getPriorityQueueInstances() {

        List<PriorityQueue<Integer>> implementations = new LinkedList<>();
        // Um Compilefehler zu verhindern, sind die Instanziierungen der PriorityQueue Implementierungen auskommentiert.
        // Kommentieren Sie die Zeilen ein, sobald Sie die entsprechenden Klassen implementiert haben.
        implementations.add(new ListQueue<>(Comparator.<Integer>naturalOrder()));
        implementations.add(new SkewHeap<>(Comparator.<Integer>naturalOrder()));
        implementations.add(new FibonacciHeap<>(Comparator.<Integer>naturalOrder()));
        return implementations;
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueInitialization(PriorityQueue<Integer> queue) {

        for (PriorityQueue<Integer> currentQueue : getPriorityQueueInstances()) {
            queue = currentQueue;
            System.out.println("Teste priorityQueueInitialization mit " + queue.getClass().getSimpleName());


            // Test: eine frisch initialisierte Queue ist leer
            assertTrue(queue.isEmpty());
            assertNull(queue.max());
            assertNull(queue.deleteMax());


        }
    }


    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueMergeListQueueTest(PriorityQueue<Integer> queue) {

        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());
        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());

        // Test merge method with empty list queue
        PriorityQueue<Integer> otherQueue;
        //same type
        if (queue instanceof ListQueue) otherQueue = new ListQueue<>(Comparator.<Integer>naturalOrder());
        else if (queue instanceof SkewHeap) otherQueue = new SkewHeap<>(Comparator.<Integer>naturalOrder());
        else otherQueue = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());

        queue.merge(otherQueue);
        assertNull(queue.max());

        // Test merge method
        for (int i = 35; i < 85; i++) {
            otherQueue.insert(i);
        }
        queue.merge(otherQueue);
        assertEquals(84, queue.max());

        for (int i = 0; i < 50; i++) {
            queue.insert(i);
        }

        // Test merge method
        for (int i = 35; i < 85; i++) {
            otherQueue.insert(i);
        }
        queue.merge(otherQueue);
        assertEquals(84, queue.max());

        // Test map method
        UnaryOperator<Integer> f = t -> 2 * t;
        queue.map(f);
        assertEquals(168, queue.max());

        // other type
        PriorityQueue<Integer> otherTypeQueue;
        if (queue instanceof ListQueue) otherTypeQueue = new SkewHeap<>(Comparator.<Integer>naturalOrder());
        else if (queue instanceof SkewHeap) otherTypeQueue = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());
        else otherTypeQueue = new ListQueue<>(Comparator.<Integer>naturalOrder());

        for (int i = 166; i <= 170 ; i++) {
            otherTypeQueue.insert(i);
        }
        queue.merge(otherTypeQueue);
        assertEquals(170, queue.max());
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel3(PriorityQueue<Integer> queue) {

        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());

        queue.insert(34);
        assertFalse(queue.isEmpty());
        assertEquals(34, queue.max());
        assertEquals(34, queue.deleteMax());
        assertTrue(queue.isEmpty());
        for (int i = 0; i < 50; i++) {
            queue.insert(i);
        }
        assertEquals(49, queue.max());
        assertEquals(49, queue.deleteMax());
        assertEquals(48, queue.max());

        PriorityQueue<Integer> otherQueue;
        if (queue instanceof ListQueue) otherQueue = new ListQueue<>(Comparator.<Integer>naturalOrder());
        else if (queue instanceof SkewHeap) otherQueue = new SkewHeap<>(Comparator.<Integer>naturalOrder());
        else otherQueue = new FibonacciHeap<>(Comparator.<Integer>naturalOrder());

        assertTrue(otherQueue.isEmpty());
        queue.merge(otherQueue);
        assertTrue(otherQueue.isEmpty());
        assertNotEquals(101, queue.max());
        assertEquals(48, queue.max());
        assertEquals(48, queue.deleteMax());
        assertEquals(47, queue.max());
        for (int i = 48; i < 100; i++) {
            otherQueue.insert(i);
        }
        assertFalse(otherQueue.isEmpty());
        queue.merge(otherQueue);
        assertTrue(otherQueue.isEmpty());
        assertEquals(99, queue.max());
        assertEquals(99, queue.deleteMax());
        assertEquals(98, queue.max());
        queue.map(t -> t * 2);
        assertNotEquals(98, queue.max());
        assertEquals(196, queue.max());
        assertFalse(queue.isEmpty());
        while (!queue.isEmpty()) assertEquals(queue.max(), queue.deleteMax());
        assertTrue(queue.isEmpty());

        // Check if update for the class FibonacciHeap is working well
        queue.insert(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.max());
        queue.insert(8);
        queue.insert(5);
        assertEquals(8, queue.max());
        assertTrue(queue.update(8, 3));
        assertNotEquals(8, queue.max());
        // aktualisiere ein Element, das nicht existiert
        assertFalse(queue.update(-1, new Random().nextInt()));
        assertEquals(5, queue.max());
        assertEquals(5, queue.deleteMax());
        assertEquals(3, queue.deleteMax());
        assertEquals(1, queue.deleteMax());
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueMapNegativeFunktion(PriorityQueue<Integer> queue) {

        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());
        assertFalse(queue.update(0, 0));

        for (int i = 1; i < 50; i++) {
            queue.insert(i);
        }
        assertEquals(49, queue.max());
        queue.map(t -> -1 * t);
        assertEquals(-1, queue.max());
        for (int i = 1; i < 50; i++) {
            assertEquals(queue.max(), queue.deleteMax());
        }
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueMap(PriorityQueue<Integer> queue) {

        System.out.println("Teste priorityQueueBeispielMap mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());

        for (int i = 1; i < 150; i++) {
            queue.insert(i);
        }
        assertEquals(149,queue.max());

        for (int i = 1; i < 100; i++) {
            assertEquals(queue.max(), queue.deleteMax());
        }
        assertEquals(50,queue.max());

        queue.map(t -> t * 2);

        assertEquals(100,queue.deleteMax());
        assertEquals(98,queue.max());

        queue.map((t -> t-2));
        assertEquals(96,queue.max());
    }
}
