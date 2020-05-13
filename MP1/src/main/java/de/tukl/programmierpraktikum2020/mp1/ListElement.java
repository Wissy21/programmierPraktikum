package de.tukl.programmierpraktikum2020.mp1;

public class ListElement<K, V> {
    K key;
    V value;
    ListElement<K, V> next;
    //we create this Constructor inorder to initialise our Objects(elements of the list)
    public ListElement(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
