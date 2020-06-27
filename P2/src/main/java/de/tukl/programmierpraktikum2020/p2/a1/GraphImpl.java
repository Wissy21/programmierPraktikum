package de.tukl.programmierpraktikum2020.p2.a1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GraphImpl<D,W> implements Graph<D,W>{
    ArrayList<Node<D,W>> nodes = new ArrayList<>();
    //Kanten werden bei den Knoten gespeichert als eingehend, bzw ausgehend
    Integer identifierCounter = 0;
    Set<Integer> nodeIds = new HashSet<>();

    @Override
    public int addNode(D data) {
        //neuer Knoten mit eindeutiger ID
        Node<D,W> node = new Node<>(identifierCounter,data);
        //ID hinzufügen
        this.nodeIds.add(identifierCounter);
        //durchlaufende IDs
        this.identifierCounter +=1;
        //Knoten wird hinten angefügt, somit an die Stelle der ID eingefügt
        this.nodes.add(node);

        return node.identifier;
    }

    @Override
    public D getData(int nodeId) throws InvalidNodeException {
        if (nodeId >= identifierCounter){
            throw new InvalidNodeException(nodeId);
        }
        return nodes.get(nodeId).data;
    }

    @Override
    public void setData(int nodeId, D data) throws InvalidNodeException {
        if (nodeId >= identifierCounter){
            throw new InvalidNodeException(nodeId);
        }
        nodes.get(nodeId).setData(data);
    }

    @Override
    public void addEdge(int fromId, int toId, W weight) throws InvalidNodeException, DuplicateEdgeException {
       //InvalidNodeException
        if (fromId >= identifierCounter){
           throw new InvalidNodeException(fromId);
       }
        if (toId >= identifierCounter){
            throw new InvalidNodeException(toId);
        }
        Node<D,W> from = nodes.get(fromId);
        Node<D,W> to = nodes.get(toId);
        Edge<D,W> newEdge= new Edge<>(from,to, weight);

        //DuplicateEdgeException
        if (to.containsIncoming(newEdge)){
            throw new DuplicateEdgeException(fromId,toId);
        }
        //ad edge
        from.newOutgoingE(newEdge);
        to.newIncomingE(newEdge);
    }

    @Override
    public W getWeight(int fromId, int toId) throws InvalidEdgeException {
        if (!nodes.isEmpty()) {
            Node<D, W> from = nodes.get(fromId);
            //durchsucht die ausgehenden Kanten vom Knoten fromID bis die Kante mit toID gefunden wurde

            for (Edge<D, W> edge : from.outgoingE) {
                if (edge.to.identifier == toId) {
                    return edge.weight;
                }
            }
        }
        throw new InvalidEdgeException(fromId,toId);
    }

    @Override
    public void setWeight(int fromId, int toId, W weight) throws InvalidEdgeException {
        boolean found = false;
        if (!nodes.isEmpty()) {
            Node<D, W> from = nodes.get(fromId);
            //durchsucht die ausgehenden Kanten vom Knoten fromID bis die Kante mit toID gefunden wurde
            for (Edge<D, W> edge : from.outgoingE) {
                if (edge.to.identifier == toId) {
                    found = true;
                    edge.setWeigth(weight);
                    break;
                }
            }
        }
        if (!found) {
            throw new InvalidEdgeException(fromId,toId);
        }
    }

    @Override
    public Set<Integer> getNodeIds() {
        return nodeIds;
    }

    @Override
    public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
        if (nodeId >= identifierCounter){
            throw new InvalidNodeException(nodeId);
        }
        Node<D,W> node = nodes.get(nodeId);
        return node.incomingSet();
    }

    @Override
    public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
        if (nodeId >= identifierCounter){
            throw new InvalidNodeException(nodeId);
        }
        Node<D,W> node = nodes.get(nodeId);
        return node.outgoingSet();
    }
}
