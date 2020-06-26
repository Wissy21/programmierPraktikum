package de.tukl.programmierpraktikum2020.p2.a1;

import java.util.HashSet;
import java.util.Set;


public class Node<D,W> {
    int identifier;
    D data;
    Set<Edge<D,W>> outgoingE;
    Set<Edge<D,W>> incomingE;


    public Node(int identifier, D data) {
        this.identifier = identifier;
        this.data = data;
        this.incomingE = new HashSet<>();
        this.outgoingE = new HashSet<>();
    }

    public void setData(D data) {
        this.data = data;
    }

    /**
     * adds the edge to the set of outgoing edges
     */
    public void newOutgoingE(Edge<D,W> edge){
        this.outgoingE.add(edge);
    }

    /**
     * adds the edge to the set of incoming edges
     */
    public void newIncomingE(Edge<D,W> edge){
        this.incomingE.add(edge);
    }


    public boolean containsIncoming(Edge<D,W> otherEdge){
        boolean result = false;
            for (Edge<D,W> edge: incomingE) {
                if (edge.equals(otherEdge)){
                    result = true;
                    break;
                }
            }
        return result;
    }

    /**
     * @return a Set with Integers of the incoming neighbor nodes
     */
    public Set<Integer> incomingSet(){
        Set<Integer> result = new HashSet<>();
        for (Edge<D,W> edge: incomingE) {
            result.add(edge.from.identifier);
        }
        return result;
    }

    /**
     * @return a Set with Integers of the outgoing neighbor nodes
     */
    public Set<Integer> outgoingSet(){
        Set<Integer> result = new HashSet<>();
        for (Edge<D,W> edge: outgoingE) {
            result.add(edge.to.identifier);
        }
        return result;
    }
}
