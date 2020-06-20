package de.tukl.programmierpraktikum2020.p1.a2;

import de.tukl.programmierpraktikum2020.p1.a1.PriorityQueue;

import java.util.Set;
import java.util.function.UnaryOperator;

/*
Gedanke: priorityqueue ist von max nach min sortiert, um kleinste gewichtung am anfang: mit kehrwert mapen
danach jeweils den geringsten(höchsten) entfernen und set subtrahieren
die subtrahierten sets speichern und mit targetsets vergleichen. wenn vollständig dann abbrechen und ausgeben
 */

public class WeightedSetCovering<E> {

    PriorityQueue<WeightedSet<E>> queue;
    Set<E> targetSet;
    Set<WeightedSet<E>> familyOfSets;
    private UnaryOperator kehrwert= new kehrwert();

    private class kehrwert implements UnaryOperator<Float> {
        @Override
        public Float apply(Float e) {
            float v = (float) 1 / e;
            return v;
        }
    }

    public WeightedSetCovering(Set<E> targetSet, Set<WeightedSet<E>> familyOfSets, PriorityQueue<WeightedSet<E>> queue) {
        this.familyOfSets = familyOfSets;
        this.queue = queue;
        this.targetSet = targetSet;
    }

    public Set<WeightedSet<E>> greedyWeightedCover() {
        for (WeightedSet E : familyOfSets) queue.insert(E);
        queue.map(kehrwert);


        WeightedSet<E> min=queue.deleteMax();
        for (WeightedSet E : familyOfSets) {
            queue.update(E, E.subtractWeightedSet(min));
        }


    }
}