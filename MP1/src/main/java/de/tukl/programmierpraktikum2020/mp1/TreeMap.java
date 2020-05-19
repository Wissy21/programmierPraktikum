package de.tukl.programmierpraktikum2020.mp1;
import com.sun.source.tree.Tree;
import java.util.Arrays;
import java.util.Comparator;


public class TreeMap<K, V> implements Map<K, V>{

//Klasse Tree erstellt einen Konoten, der Schlüssel, Wert und zwei weitere Teilbäume speichert.
public class Tree{
    public K key;
    public V value;
    public Tree left;
    public Tree right;

//Kontruktor Tree
    public Tree(K key, V value, Tree left, Tree right){
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

//alle helper untersuchen ein Baum der Klasse Tree.

//helperGet gibt den zum Schlussel gespeicherten Wert zurück.
    public V helperGet(K key){
        if(this.key != null) {
            if (comp.compare(key, this.key) == 0) {
                return this.value;
            } else if (comp.compare(key, this.key) < 0 && this.left != null) {
                return this.left.helperGet(key);
            } else if (comp.compare(key, this.key) > 0 && this.right != null) {
                return this.right.helperGet(key);
            } else return null;
        } else return null;
    }

//helperPut speichert für der Wert key dern Wert value
    public void helperPut(K key, V value) {
        if(this.key != null) {
            if (comp.compare(key, this.key) == 0) {
                this.value = value;
            } else if (comp.compare(key, this.key) < 0) {
                if (this.left != null) {
                    this.left.helperPut(key, value);
                } else this.left = new Tree(key, value, null, null);
            } else if (comp.compare(key, this.key) > 0) {
                if (this.right != null) {
                    this.right.helperPut(key, value);
                } else this.right = new Tree(key, value, null, null);
            }
        }else {
            this.value = value;
            this.key = key;
        }

    }
//helperSize untersucht die Anzahl der Einträge
    public int helperSize() {
        if(this.key == null){
            return 0;
        }else if(this.left== null && this.right == null){
            return 1;
        } else if(this.left != null && this.right == null){
            return 1 + this.left.helperSize();
        } else if(this.left == null && this.right != null){
            return 1 + this.right.helperSize();
        }else return 1 + this.left.helperSize() + this.right.helperSize();
    }
}


//hier wird ein leerer Baum der Klasse Tree implementiert
    Tree tree = new Tree(null, null, null, null);


//Erstellen des Comparators
        Comparator<K> comp;
        public TreeMap(java.util.Comparator<K> compare){
            comp = compare;
        }

//ab hier werden die Map Methoden überschrieben

    //Um get, put und size zu implementiern wird auf die, in der inneren Klasse Tree
    // implementierten Methoden zugegriffen.
    @Override
    public V get(K key) {
       return tree.helperGet(key);
    }

    @Override
    public void put(K key, V value) {
            tree.helperPut(key, value);

    }

    @Override
    public void remove(K key) {
        throw new UnsupportedOperationException();

    }

    @Override
    public int size() {
         return tree.helperSize();

    }

    // Um die Methode keys zu implementieren wird eine helper Methode erstellt.
    //i ist eine Variable, die für die Hilfsmethode halper benutzt wird.

        int i = 0;

    //helper durchläuft den Array und gleichzeitig den Baum
    // und fügt jeden Schlussel aus dem Baum an die ensprechende Stelle im Array.
        public void helper(Tree tree, K[] array){
            if (tree.key == null){
                return;
            }else{
                array[i] = tree.key;
                i++;
                if (tree.left != null){
                    helper(tree.left, array);
                }
                if(tree.right != null) {
                    helper(tree.right, array);
                }
            }
        }

    //keys überprüft zuerst ob array zu klein ist.
    // Wenn es nicht der Fall ist, wird array an gie helper Funktion weitergegeben.
    @Override
    public void keys(K[] array) {
            i=0;
        if(array == null || array.length < size()){
            throw new IllegalArgumentException("Array ist zu klein!");
        } else helper(tree, array);

    }
}
