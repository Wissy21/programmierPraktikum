package de.tukl.programmierpraktikum2020.p1.a1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.UnaryOperator;

public class FibonacciHeap<E> implements PriorityQueue<E>{


    /**
     * {@code 0} if {@code x == y};
     * {@code <0} if {@code x < y}; and
     * {@code >0} if {@code x > y}
     */
    Comparator<E> comparator;
    ArrayList<Node<E>>[] degrees;
    ArrayList<Node<E>> forest;
    Node<E> max;


    public FibonacciHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.forest = new ArrayList<>();
        this.degrees  = new ArrayList[100];
        this.max = new Node<E>(null);
    }


    @Override
    public void insert(E elem) {
        // inserts elem as an new tree
        Node<E> n = new Node<>(elem);
        //update forest
        forest.add(n);
        // update degrees
        addDegrees(n);
        //update max
        max = max(max,n);
    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        // ich nehme an, dass der Comparater gleich ist
        //wenn beides Fibonacci Heaps, einfach die Wurzellisten aneinander hängen
        if (otherQueue instanceof FibonacciHeap){
            FibonacciHeap<E> otherFibonacciQueue = (FibonacciHeap<E>) otherQueue;
            for (Node<E> node:otherFibonacciQueue.forest) {
                this.forest.add(node);
                addDegrees(node);
            }
            //max = maximum of both max
            max = max(max,otherFibonacciQueue.max);
        }
        else {
            while (!otherQueue.isEmpty()) {
                insert(otherQueue.deleteMax());
            }
        }
    }


    @Override
    public E deleteMax() {
        E result = null;
        if (max.head != null) {
            //delete max
            result = max.head;
            ArrayList<Node<E>> children = new ArrayList<>();
            children.addAll(max.children);

            degrees[max.out_degree].remove(max);
            forest.remove(max);

            this.max = find_new_max();

            //all children become new trees
            for (Node<E> child : children) {
                node_to_new_tree(child);
            }
            clear_up();
        }
        return result;
    }

    @Override
    public E max() {
        return max.head;
    }

    @Override
    public boolean isEmpty() {
        return forest.isEmpty();
    }


    @Override
    public boolean update(E elem, E updatedElem) {
        Pair<E> pair = find(elem,forest);

        //if elem found
        if (pair.result){
            Node<E> node = pair.value;
            Node<E> parrent = node.parrent;


            //update elem
            node.head = updatedElem;

            //node is not a root
            if(node.parrent != null){
                //node becomes new tree
                node_to_new_tree(node);

                //as long as the parrent has a token and is no root: make the parrent a new tree
                parrent_to_new_tree(parrent);
            }
            //decrease elem
            if (comparator.compare(elem,updatedElem) > 0){
                //children become new trees
                ArrayList<Node<E>> children = new ArrayList<>();
                children.addAll(node.children);
                for (Node<E> child: children){
                    node_to_new_tree(child);
                }
                //update max

                if (comparator.compare(updatedElem,max.head) == 0){
                    this.max = new Node<E>(null);
                    this.max = find_new_max();
                }
            }// increase elem
            else
                max = max(max,node);

        }
        return pair.result;
    }



    @Override
    public void map(UnaryOperator<E> f) {
        ArrayList<E> elems = new ArrayList<>();
        toArray(elems, forest);
        for (E elem: elems) {
            update(elem,f.apply(elem));
        }
    }

    /**
     * finds the new max in the forrest
     * @return {@code Node<E>} with max head
     */
    public Node<E> find_new_max(){
        Node <E> result = new Node<E>(null);
        for (Node<E> node : forest) {
                result = max(result,node);

        }
        return result;
    }

    /** merges two nodes with the same out_degree until no out_degree appears more than once */
    public void clear_up() {
        int i = 0;
        while (i < degrees.length){
            // one out_degree more than once
            if (degrees[i] != null && degrees[i].size() > 1){
                Node<E> root1 = degrees[i].get(0);
                Node<E> root2 = degrees[i].get(1);

                //merge roots
                if (comparator.compare(root1.head, root2.head) > 0){
                    //root2 becomes child of root1
                    forest.remove(root2);
                    root1.add_child(root2);
                    addDegrees(root1);
                } else {
                    //root1 becomes child of root2
                    forest.remove(root1);
                    root2.add_child(root1);
                    addDegrees(root1);
                }
                degrees[i].remove(0);
                // degrees to small


                // i = i;

            } else i++;
        }
    }


    /**
     * removes node from his parrent, creates a new tree with this node,
     * keeps parrent;
     * updates: forest, degrees
     * @param node {@code Node<E>} to remove
     */
    public void node_to_new_tree(Node<E> node){
        if(node.parrent != null ) {
            Node<E> parrent = node.parrent;
            //node as new root
            node.token = false;
            node.parrent = null;
            //add child
            //update forest
            forest.add(node);
            //update degrees
            addDegrees(node);
            //update max unnecessary

            //update parrent
            parrent.out_degree -= 1;
            parrent.children.remove(node);
            addDegrees(parrent);
        }
    }

    /**
     * if node has a token: make the node a new tree; repeat;
     * else set token (if not a root)
     */
    public void parrent_to_new_tree(Node<E> node){
        if (node.token){
            node_to_new_tree(node);
            parrent_to_new_tree(node.parrent);
        }else if(node.parrent!=null){
            node.token = true;
        }
    }
    /**
     *
     * @param elem {@code E} to find
     * @param trees {@code ArrayList<Node<E>>} to search in
     * @return {@code Pair<E>} with value {@code Node<E>} as the found node with {@code node.head == elem};
     *          and result {@code boolean} if elem was found
     */
    public Pair<E> find(E elem, ArrayList<Node<E>> trees) {
        Pair<E> pair = new Pair<>(null,false);
        for (Node<E> node:trees) {
            if(comparator.compare(node.head,elem) == 0){
                pair.update(node,true);
                break;
            }
            else if (comparator.compare(node.head,elem) > 0){
                pair = find(elem,node.children);
                if (pair.result){
                    break;
                }
            }
        }
        return pair;
    }

    /**
     * adds the values of all nodes and their children to elems
     * @param elems is the result with all elems
     * @param nodes are the nodes to add
     */
    public void toArray(ArrayList<E> elems, ArrayList<Node<E>> nodes) {
        for (Node<E> node : nodes) {
            //adds the value of the node
            elems.add(node.head);
            //adds the values of all children
            toArray(elems,node.children);

        }
    }

    /**
     * adds node to degree with position == out_degree
     * increase degrees if necessary
     */
    public void addDegrees(Node<E> node){
        int i = node.out_degree;
        if (i >= degrees.length){
            ArrayList<Node<E>>[] temp = new ArrayList[degrees.length];
            System.arraycopy(degrees, 0, temp, 0, degrees.length);
            for (int j = 0; j < temp.length; j++) {
                this.degrees = new ArrayList[2 * temp.length];
                degrees[j] = temp[j];
            }
        }
        if (degrees[i] == null){
            ArrayList<Node<E>> newDegree = new ArrayList<>();
            newDegree.add(node);
            degrees[i] = newDegree;
        } else degrees[i].add(node);
    }
    public Node<E> max(Node<E> n1, Node<E> n2){
        Node<E> result = n1;
        if (n1.head == null){
            result = n2;

        } else
        if(n2.head != null && comparator.compare(n1.head,n2.head) < 0)
            result = n2;
        return result;
    }
}
