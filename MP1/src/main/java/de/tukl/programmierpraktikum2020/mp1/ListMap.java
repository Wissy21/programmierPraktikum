package de.tukl.programmierpraktikum2020.mp1;

public class ListMap<K, V> implements Map<K, V> {
    ListElement<K, V> head;
    int size;

    public ListMap() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public V get(K key) {
        ListElement<K, V> actualElement = head;
        while (actualElement != null) {
            if (actualElement.key.equals(key)) {
                return actualElement.value;
            }
            actualElement = actualElement.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        ListElement<K, V> newElement = new ListElement<>(key, value);
        ListElement<K, V> actualElement = head;
        while (actualElement != null) {
            if (actualElement.key.equals(key)) {
                actualElement.value = value;
                //break;
                return;
            }
            actualElement = actualElement.next;
        }
        //add newElement at beginning of Linked list.
        newElement.next = head;
        head = newElement;
        size++;
    }

    @Override
    public void remove(K key) {
        ListElement<K, V> actualElement = head;
        if (actualElement != null && actualElement.key.equals(key)) {
            // Delete first element
            head = actualElement.next;
            size--;
        } else {
            while (actualElement != null && actualElement.next != null && !actualElement.key.equals(key) ) {
                if (actualElement.next.key.equals(key)) {
                    size--;
                    if (actualElement.next.next != null) {
                        actualElement.next = actualElement.next.next;
                    } else {
                        actualElement.next = null;
                        break;// since no other element exist in list
                    }
                }
                actualElement = actualElement.next;
            }
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void keys(K[] array) {
        if (array == null) throw new IllegalArgumentException("Your Array's length is null");
        else if (array.length < size) throw new IllegalArgumentException("The length of Your given Array is small");
        else {
            ListElement<K, V> actualElement = head;
            for (int i = 0; actualElement != null && i < size; i++) {
                array[i] = actualElement.key;
                actualElement = actualElement.next;
            }
        }
    }
}
