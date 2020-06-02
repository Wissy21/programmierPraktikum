package de.tukl.programmierpraktikum2020.p1.a1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    /**
     * Diese Methode wird verwendet, um Instanzen von PriorityQueue Implementierungen an Testmethoden zu übergeben.
     */
    public static List<PriorityQueue<Integer>> getPriorityQueueInstances() {
        List<PriorityQueue<Integer>> implementations = new LinkedList<>();
        // Um Compilefehler zu verhindern, sind die Instanziierungen der PriorityQueue Implementierungen auskommentiert.
        // Kommentieren Sie die Zeilen ein, sobald Sie die entsprechenden Klassen implementiert haben.
        //implementations.add(new ListQueue<>(Comparator.<Integer>naturalOrder()));
        //implementations.add(new SkewHeap<>(Comparator.<Integer>naturalOrder()));
        //implementations.add(new FibonacciHeap<>(Comparator.<Integer>naturalOrder()));
        return implementations;
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void priorityQueueBeispiel(PriorityQueue<Integer> queue) {
        System.out.println("Teste priorityQueueBeispiel mit " + queue.getClass().getSimpleName());

        // Test: eine frisch initialisierte Queue ist leer
        assertTrue(queue.isEmpty());

        // Fügen Sie hier weitere Tests ein.
        // Sie dürfen auch gerne weitere Test-Methoden erstellen, z.B. priorityQueueBeispiel2 usw.
    }
}
