package de.tukl.programmierpraktikum2020.p1.a1;

/**
 * creates a Pair with a {@code Node<E>} as value and a {@code booelan} as result
 */
public class Pair<E> {
    Node<E> value;
    boolean result;

    public Pair(Node<E> value, boolean result) {
        this.value = value;
        this.result = result;
    }

    public void update(Node<E> value,boolean result){
        this.value = value;
        this.result = result;
    }

}
