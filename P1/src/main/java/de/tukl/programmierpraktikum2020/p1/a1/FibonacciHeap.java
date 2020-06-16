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
    ArrayList<Node<E>>[] degrees = new ArrayList[100];
    ArrayList<Node<E>> forest;
    Node<E> max;


    public FibonacciHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    @Override
    public void insert(E elem) {
        // inserts elem as an new tree
        Node<E> n = new Node<>(elem);
        //update forest
        forest.add(n);
        // update degrees
        degrees[0].add(n);
        //update max
        if (comparator.compare(n.head, max.head) > 0) {
            max = n;
        }
    }

    @Override
    public void merge(PriorityQueue<E> otherQueue) {
        // ich nehme an, dass der Comparater gleich ist
        //wenn beides Fibonacci Heaps, einfach die Wurzellisten aneinander hängen
        if (otherQueue instanceof FibonacciHeap){
            FibonacciHeap<E> otherFibonacciQueue = (FibonacciHeap<E>) otherQueue;
            for (Node<E> node:otherFibonacciQueue.forest) {
                this.forest.add(node);
                this.degrees[node.out_degree].add(node);
            }
            //max = maximum of both max
            if(comparator.compare(this.max.head,otherFibonacciQueue.max.head) < 0)
                this.max = otherFibonacciQueue.max;
        }
        else {
            while (!otherQueue.isEmpty()) {
                insert(otherQueue.deleteMax());
            }
        }
    }


    @Override
    public E deleteMax() {
        //delete max
        Node<E> result = max.copy();
        ArrayList<Node<E>> children = result.children;
        this.max = find_new_max();
        degrees[result.out_degree].remove(result);
        forest.remove(result);

        //all children become new trees
        for (Node<E> child: children){
            node_to_new_tree(child);
        }
        clear_up();

        return result.head;
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
            E oldValue = node.head;
            Node<E> parrent = node.parrent;

            //update elem
            node.head = elem;

            //node becomes new tree
            node_to_new_tree(node);

            //as long as the parrent has a token: make the parrent a new tree
            while (parrent.token){
                Node<E> newChild = parrent;
                parrent = newChild.parrent;
                node_to_new_tree(newChild);
            }
            // if a parrent without a token is found (and it's not a root), parrent gets a token and it stops
            if (parrent.parrent !=null){
                parrent.token =true;
            }
            //decrease elem
            if (comparator.compare(oldValue,elem) > 0){
                //children become new trees
                ArrayList<Node<E>> children = node.children;
                for (Node<E> child: children){
                    node_to_new_tree(child);
                }
                //update max
                if (comparator.compare(oldValue,max.head) == 0){
                    this.max = find_new_max();
                }
            }// increase elem
            else if(comparator.compare(max.head,node.head) < 0){
                this.max = node;
            }
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
        Node <E> result = null;
        for (Node<E> node : forest) {
            if (result == null || comparator.compare(result.head,node.head) < 0){
                result = node;
            }
        }
        return result;
    }

    /** merges two nodes with the same out_degree until no out_degree appears more than once */
    public void clear_up() {
        int i = 0;
        while (i < degrees.length){
            // one out_degree more than once
            if (degrees[i].size() > 1){
                Node<E> root1 = degrees[i].get(0);
                Node<E> root2 = degrees[i].get(1);

                //merge roots
                if (comparator.compare(root1.head, root2.head) > 0){
                    //root2 becomes child of root1
                    forest.remove(root2);
                    root1.add_child(root2);
                } else {
                    //root1 becomes child of root2
                    forest.remove(root1);
                    root2.add_child(root1);
                }
                degrees[i].remove(0);
                degrees[i].remove(1);
                // degrees to small
                if (root1.out_degree >= degrees.length){
                    ArrayList<Node<E>>[] temp = new ArrayList[degrees.length];
                    System.arraycopy(degrees, 0, temp, 0, degrees.length);
                    for (int j = 0; j < temp.length; j++) {
                        this.degrees = new ArrayList[2 * temp.length];
                        degrees[j] = temp[j];
                    }
                }
                degrees[root1.out_degree].add(root1);
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
        //node as new root
        node.token =false;
        node.parrent =null;
        //add child
        //update forest
        forest.add(node);
        //update degrees
        degrees[node.out_degree].add(node);
        //update max unnecessary

        //update parrent
        Node<E> parrent = node.parrent;
        parrent.out_degree -= 1;
        parrent.children.remove(node);
        degrees[parrent.out_degree].add(parrent);
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
}
