package de.tukl.programmierpraktikum2020.p1.a1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        //implementations.add(new SkewHeap<>(Comparator.<Integer>naturalOrder()));
        //implementations.add(new FibonacciHeap<>(Comparator.<Integer>naturalOrder()));
        return implementations;
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel(PriorityQueue<Integer> queue) {

        for (PriorityQueue<Integer> currentQueue : getPriorityQueueInstances()) {
            queue = currentQueue;
            System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());


            // Test: eine frisch initialisierte Queue ist leer
            assertTrue(queue.isEmpty());
            assertNull(queue.max());
            assertNull(queue.deleteMax());

            // Fügen Sie hier weitere Tests ein.
            // Sie dürfen auch gerne weitere Test-Methoden erstellen, z.B. priorityQueueBeispiel2 usw.
            for (int i = 0; i < 50; i++) {
                queue.insert(i);
            }
            assertFalse(queue.isEmpty());
            assertEquals(49, queue.max());
            assertEquals(49, queue.deleteMax());
            assertEquals(48, queue.max());
            assertTrue(queue.update(queue.max(), -6));
            assertEquals(47, queue.max());
            assertFalse(queue.update(105, 110));
            assertEquals(47, queue.max());
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
    }
}
