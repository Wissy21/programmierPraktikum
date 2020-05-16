package de.tukl.programmierpraktikum2020.mp1;
import com.sun.source.tree.Tree;
import java.util.Arrays;
import java.util.Comparator;


public class TreeMap<K, V> implements Map<K, V>{

public class Tree{
    public K key;
    public V value;
    public Tree left;
    public Tree right;

    public Tree(K key, V value, Tree left, Tree right){
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
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

    /*public TreeMap getLeft() {return left;}
    public TreeMap getRight() {return right;}
    public K getKey() {return key;}
    public V getValue() {return value;}

    public void setLeft(TreeMap left) {this.left = left;}
    public void setRight(TreeMap right) {this.right = right;}
    public void setKey(K k) {this.key = k;}
    public void setValue(V v) {this.value = v;}*/

    Tree tree = new Tree(null, null, null, null);



        Comparator<K> comp;
        public TreeMap(java.util.Comparator<K> compare){
            comp = compare;
        }


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
    int i = 0;

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

    @Override

    public void keys(K[] array) {
            i=0;
        if(array == null || array.length < size()){
            throw new IllegalArgumentException("Array ist zu klein!");
        } else helper(tree, array);

    }
}
