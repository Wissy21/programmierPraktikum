package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;


public class WeightedSetCovering<E> {

    PriorityQueue<WeightedSet<E>> queue;
    Set<E> targetSet;
    Set<WeightedSet<E>> familyOfSets;


    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets, PriorityQueue<WeightedSet<E>> queue) {
        this.familyOfSets = familyOfSets;
        this.queue = queue;
        this.targetSet = targetSet;
    }

    public Set<WeightedSet<E>> greedyWeightedCover() {
        Set<E> features = new HashSet<>();
        Set<WeightedSet<E>> result = new HashSet<>();
        for (WeightedSet<E> E : familyOfSets) queue.insert(E);


        while(!features.containsAll(targetSet) || features.isEmpty()) {
            WeightedSet<E> min = queue.deleteMax();
            features.addAll(min.getSet());
            result.add(min);

            for (WeightedSet<E> E : familyOfSets) {
                queue.update(E, E.subtractWeightedSet(min));
            }
        }
        return result;

    }
}