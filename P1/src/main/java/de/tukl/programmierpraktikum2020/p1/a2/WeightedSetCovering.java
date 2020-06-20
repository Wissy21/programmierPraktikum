package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.HashSet;
import java.util.Set;


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
        Set<WeightedSet<E>> finalSet = new HashSet<>();
        //implement greedy Algo
        for (WeightedSet<E> weightedSet : familyOfSets) {
            queue.insert(weightedSet);
        }
        Set<E> currentTargetSet = new HashSet<>();
        while (!currentTargetSet.containsAll(targetSet)) {
            //1,2/2,4,5
            WeightedSet<E> minimalWeightedSet = queue.deleteMax();
            // null pointer exception
            if (minimalWeightedSet != null) {
                finalSet.add(minimalWeightedSet);
                //finalSet.forEach(weightedSet -> { currentTargetSet.addAll(weightedSet.getSet());
                // currentTargetSet.addAll(weightedSet.getSet());
                for (WeightedSet<E> weightedSet : finalSet) {
                    currentTargetSet.addAll(weightedSet.getSet());
                }
                //});
            }
        }
        return finalSet;
    }
}
