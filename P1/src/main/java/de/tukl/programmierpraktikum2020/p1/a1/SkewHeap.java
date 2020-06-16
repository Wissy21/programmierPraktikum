package de.tukl.programmierpraktikum2020.p1.a1;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.Comparator;

public class SkewHeap<E> implements PriorityQueue<E> {

    Comparator<E> comparator;
    public SkewHeap(Comparator<E> comparator){
        this.comparator = comparator;
    }
    class SkewNode {
        E elem;
        SkewNode left;
        SkewNode right;

        //node Konstruntor
        public SkewNode(E value){
            this.elem = value;
            this.left = null;
            this.right = null;
        }

    }
    SkewNode root;
    public SkewHeap(){
        root = null;
    }

    SkewNode mainHeap = new SkewNode(null);

    @Override
    public void insert(E elem) {
        mainHeap = mergeHelp(mainHeap, new SkewNode(elem));
    }

    public SkewNode mergeHelp(SkewNode mainHeap, SkewNode otherQueue){
        SkewNode mergedHeap = new SkewNode(null);

        if(comparator.compare(mainHeap.elem, otherQueue.elem)<0) {
            mergedHeap.elem = mainHeap.elem;
            mergedHeap.right = mainHeap.left;
            mergedHeap.left = mergeHelp(mainHeap.right, otherQueue);
            return mergedHeap;
        }else {
            mergedHeap.elem = mainHeap.elem;
            mergedHeap.right = mainHeap.left;
            mergedHeap.left = mergeHelp(mainHeap.right, otherQueue);
            return mergedHeap;
        }
    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        if (otherQueue instanceof SkewHeap.SkewNode) {
            SkewNode otherSkewQueue = (SkewHeap<E>.SkewNode) otherQueue;
            mainHeap = mergeHelp(mainHeap, otherSkewQueue);
        }
    }

    @Override
    public E deleteMax() {
        if(mainHeap == null){
            return null;
        }else if(mainHeap.left == null && mainHeap.right == null){
            E result = mainHeap.elem;
            mainHeap.elem = null;
            return result;
        }else if(mainHeap.left == null && mainHeap.right != null){
            E result = maxHelper(mainHeap.right);
            mainHeap = helperUpdate(mainHeap, result, null);
            return result;
        }else if(mainHeap.left != null && mainHeap.right == null){
            E result = maxHelper(mainHeap.left);
            mainHeap = helperUpdate(mainHeap, result, null);
            return result;
        } else {
            E maxLeft = maxHelper(mainHeap.left);
            E maxRight = maxHelper(mainHeap.right);
            if(comparator.compare(maxLeft, maxRight) < 0){
                E result = maxRight;
                mainHeap = helperUpdate(mainHeap, result, null);
                return result;
            }else {
                E result = maxLeft;
                mainHeap = helperUpdate(mainHeap, result, null);
                return result;
            }
        }
    }
    public E maxHelper(SkewNode x){
        if(x == null){
            return null;
        }else if(x.left == null && x.right == null){
            return mainHeap.elem;
        }else if(mainHeap.left == null && mainHeap.right != null){
            return maxHelper(x.right);
        }else if(mainHeap.left != null && mainHeap.right == null){
            return maxHelper(x.left);
        } else if(comparator.compare(maxHelper(x.left), maxHelper(x.right))<0){
            return maxHelper(x.right);
        }else return maxHelper(x.left);
    }
    @Override
    public E max() {
        if(mainHeap == null){
            return null;
        }else if(mainHeap.left == null && mainHeap.right == null){
            return mainHeap.elem;
        }else if(mainHeap.left == null && mainHeap.right != null){
            return maxHelper(mainHeap.right);
        }else if(mainHeap.left != null && mainHeap.right == null){
            return maxHelper(mainHeap.left);
        } else {
            E maxLeft = maxHelper(mainHeap.left);
            E maxRight = maxHelper(mainHeap.right);
            if(comparator.compare(maxLeft, maxRight) < 0){
                return maxRight;
            }else return maxLeft;
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    private SkewNode helperUpdate(SkewNode heap, E elem, E updatedElem){
        if(heap == null){
            return null;
        }else if(comparator.compare(mainHeap.elem, elem)==0){
            mainHeap.elem = updatedElem;
            return mainHeap;
        }else if(helperUpdate(heap.left, elem, updatedElem) == null){
            return helperUpdate(heap.right, elem, updatedElem);
        }else return helperUpdate(heap.left, elem, updatedElem);
    }
    @Override
    public boolean update(E elem, E updatedElem) {
        return helperUpdate(mainHeap, elem, updatedElem) != null;
    }

    public void helperMap(SkewNode heap, UnaryOperator<E> f){
        SkewNode helperHeap;
        while(mainHeap != null){
            mainHeap.elem = f.apply(mainHeap.elem);
            helperHeap = helperUpdate(mainHeap, mainHeap.elem, null);
            helperMap(helperHeap, f);
        }}

    @Override
    public void map(UnaryOperator<E> f) {
        helperMap(mainHeap, f);
    }

}

