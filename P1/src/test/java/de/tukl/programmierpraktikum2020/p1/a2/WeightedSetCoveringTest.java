package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.*;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;
import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueueTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeightedSetCoveringTest {
    public static List<PriorityQueue<WeightedSet<Integer>>> getPriorityQueueInstances() {
        // Wir nutzen einfach die PriorityQueue Instanzen aus der anderen Testklasse.

        List<PriorityQueue<WeightedSet<Integer>>> implementations = new LinkedList<>();

        Comparator<WeightedSet<Integer>> cw;
        cw = Comparator.comparingDouble(WeightedSet::getWeight);

        implementations.add(new ListQueue<>(cw.reversed()));
        implementations.add(new SkewHeap<>(cw.reversed()));
        implementations.add(new FibonacciHeap<>(cw.reversed()));
        return implementations;

    }

    @ParameterizedTest
    @MethodSource("getPriorityQueueInstances")
    public void weightedSetBeispiel(PriorityQueue<WeightedSet<Integer>> queue) {

        System.out.println("Teste weightedSetBeispiel mit " + queue.getClass().getSimpleName());

        Bundle bundle1 = new Bundle("bundle 1", 1.0, Set.of(1, 2));
        Bundle bundle2 = new Bundle("bundle 2", 2.0, Set.of(2, 3));
        Bundle bundle3 = new Bundle("bundle 3", 10.0, Set.of(2, 3, 5));
        Bundle bundle4 = new Bundle("bundle 4", 3.0, Set.of(4));
        Bundle bundle5 = new Bundle("bundle 5", 4.0, Set.of(1, 5));
        Bundle bundle6 = new Bundle("bundle 6", 10.0, Set.of(2, 3, 4));

        Set<WeightedSet<Integer>> familyOfSets = Set.of(bundle1, bundle2, bundle3, bundle4, bundle5, bundle6);
        Set<Integer> targetSet = Set.of(1, 2, 3, 4, 5);

        WeightedSetCovering<Integer> test = new WeightedSetCovering<>(targetSet, familyOfSets, queue);
        Set<WeightedSet<Integer>> result = test.greedyWeightedCover();
        Set<String> soll_es_sein = Set.of("Bundle bundle 5", "Bundle bundle 2", "Bundle bundle 1", "Bundle bundle 4");

        Set<Integer> erwartet = Set.of(1, 2, 3, 4, 5);
        Set<Integer> features = new HashSet<>();
        for (WeightedSet<Integer> w : result) {
            features.addAll(w.getSet());
        }
        double price = 0.0;
        for (WeightedSet<Integer> set : result) {
            Bundle bundle = (Bundle) set;
            price = price + bundle.price;
        }

        Set<String> bundles = new HashSet<>();
        for (WeightedSet<Integer> w : result) {
            bundles.add(w.toString());
        }
        assertEquals(10.0, price, "die Kosten betragen nicht 10€");
        assertEquals(erwartet, features, "es sind nicht alle Features enthalten");
        assertEquals(soll_es_sein, bundles, "die Bundels entsprechen nicht denen aus dem Bsp");
    }

}
