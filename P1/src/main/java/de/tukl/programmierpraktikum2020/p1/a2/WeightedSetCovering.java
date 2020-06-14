package de.tukl.programmierpraktikum2020.p1.a2;

import java.util.Set;


public class WeightedSetCovering {

    new PriorityQueue <WeightedSet<E>> queue;
    new Set<E> targetSet;
    new Set<WeightedSet<E>> familyofSets;

    public WeightedSetCovering (Set <E> targetSet , Set <WeightedSet <E>> familyOfSets , PriorityQueue <WeightedSet <E>> queue) {
        this.familyofSets=familyOfSets;
        this.queue=queue;
        this.targetSet=targetSet;
    }


}
