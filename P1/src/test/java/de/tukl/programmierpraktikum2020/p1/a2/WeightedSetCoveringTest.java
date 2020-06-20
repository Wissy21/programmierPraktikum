package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.*;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeightedSetCoveringTest {
    public static List<PriorityQueue<Integer>> getPriorityQueueInstances() {
        // Wir nutzen einfach die PriorityQueue Instanzen aus der anderen Testklasse.
        return PriorityQueueTest.getPriorityQueueInstances();
    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void weightedSetBeispiel(PriorityQueue<WeightedSet<Integer>> queue) {
        System.out.println("Teste weightedSetBeispiel mit " + queue.getClass().getSimpleName());

        //fail("Test wurde noch nicht implementiert");
        Comparator<WeightedSet<Integer>> cw;
        cw = Comparator.comparing(WeightedSet::getWeight);
        if (queue instanceof ListQueue) {
            System.out.println("It is a ListQueue");
            queue = new ListQueue<>(cw.reversed());
        } else if (queue instanceof SkewHeap) {
            queue = new SkewHeap<>(cw.reversed());
            System.out.println("It is a SkewHeap");
        } else {
            queue = new FibonacciHeap<>(cw.reversed());
            System.out.println("It is a FibonacciHeap");
        }
        assertTrue(queue.isEmpty());

        //or
        //Set<Integer> s1 = new HashSet<>();
        //s1.add(1); //..........
        //Bundle bundle1 = new Bundle("Bundle 1", 1, s1);

        //or--------------------------------------------------------------------------------
        Bundle bundle1 = new Bundle("Bundle 1", 1, new HashSet<>(Arrays.asList(1, 2)));
        Bundle bundle2 = new Bundle("Bundle 2", 2, new HashSet<>(Arrays.asList(1, 3)));
        Bundle bundle3 = new Bundle("Bundle 3", 10, new HashSet<>(Arrays.asList(2, 3, 5)));
        Bundle bundle4 = new Bundle("Bundle 4", 3, new HashSet<>(Collections.singletonList(4)));
        Bundle bundle5 = new Bundle("Bundle 5", 4, new HashSet<>(Arrays.asList(1, 5)));
        Bundle bundle6 = new Bundle("Bundle 6", 10, new HashSet<>(Arrays.asList(2, 3, 4)));

        Set<Integer> targetSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<WeightedSet<Integer>> familyOfSets = new HashSet<>(Arrays.asList(bundle1, bundle2, bundle3, bundle4, bundle5, bundle6));
        WeightedSetCovering<Integer> weightedSetCovering = new WeightedSetCovering<>(targetSet, familyOfSets, queue);
        Set<WeightedSet<Integer>> greedySolution = weightedSetCovering.greedyWeightedCover();

        double totalCost = 0;
        for (WeightedSet<Integer> weightedSet : greedySolution) {
            totalCost += weightedSet.getWeight() * weightedSet.getSet().size();
        }
        assertEquals(10, totalCost);
        //prüfe ob den greedy alle bundle enthält
        assertTrue(greedySolution.contains(bundle1));
        assertTrue(greedySolution.contains(bundle2));
        assertFalse(greedySolution.contains(bundle3));
        assertTrue(greedySolution.contains(bundle4));
        assertTrue(greedySolution.contains(bundle5));
        assertFalse(greedySolution.contains(bundle6));
        assertFalse(queue.isEmpty());
    }
}
