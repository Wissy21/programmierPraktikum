package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.Set;


public class WeightedSetCovering<E> {

    PriorityQueue<WeightedSet<E>> queue;
    Set<E> targetSet;
    Set<WeightedSet<E>> familyofSets;

    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets, PriorityQueue<WeightedSet<E>> queue) {
        this.familyofSets = familyOfSets;
        this.queue = queue;
        this.targetSet = targetSet;
    }
}
