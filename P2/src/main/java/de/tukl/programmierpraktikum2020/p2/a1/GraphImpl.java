package de.tukl.programmierpraktikum2020.p2.a1;


import java.util.*;

public class GraphImpl<D, W> implements Graph<D, W> {

    private final Map<Integer, Node<D>> nodeMapIds = new TreeMap<>();
    private final Map<Integer, Set<Integer>> outgoingNodeIds = new TreeMap<>();
    private final Map<Integer, Set<Integer>> incomingNodeIds = new TreeMap<>();
    private final Set<Edge<W>> edgeSet = new HashSet<>();

    @Override
    public int addNode(D data) {
        int identifier = nodeMapIds.keySet().size(); // Am Anfang 0, dann 1, 2, 3 und so weiter
        nodeMapIds.put(identifier, new Node<>(identifier, data));
        outgoingNodeIds.put(identifier, new HashSet<>());
        incomingNodeIds.put(identifier, new HashSet<>());
        return identifier;
    }

    @Override
    public D getData(int nodeId) throws InvalidNodeException {
        if (!nodeMapIds.containsKey(nodeId)) throw new InvalidNodeException(nodeId);
        return nodeMapIds.get(nodeId).data;
    }

    @Override
    public void setData(int nodeId, D data) throws InvalidNodeException {
        if (!nodeMapIds.containsKey(nodeId)) throw new InvalidNodeException(nodeId);
        nodeMapIds.put(nodeId, new Node<>(nodeId, data));
    }

    @Override
    public void addEdge(int fromId, int toId, W weight) throws InvalidNodeException, DuplicateEdgeException {
        if (!nodeMapIds.containsKey(fromId)) throw new InvalidNodeException(fromId);
        if (!nodeMapIds.containsKey(toId)) throw new InvalidNodeException(toId);
        for (Edge<W> edge : edgeSet) {
            if (edge.fromId == fromId && edge.toId == toId) throw new DuplicateEdgeException(fromId, toId);
        }
        outgoingNodeIds.get(fromId).add(toId);
        incomingNodeIds.get(toId).add(fromId);
        edgeSet.add(new Edge<>(fromId, toId, weight));
    }

    @Override
    public W getWeight(int fromId, int toId) throws InvalidEdgeException {
        W weight = null;
        for (Edge<W> edge : edgeSet) {
            if (edge.fromId == fromId && edge.toId == toId) weight = edge.weight;
        }
        if (weight == null) throw new InvalidEdgeException(fromId, toId);
        return weight;
    }

    @Override
    public void setWeight(int fromId, int toId, W weight) throws InvalidEdgeException {
        //boolean edgeFound = false;
        for (Edge<W> edge : edgeSet) {
            if (edge.fromId == fromId && edge.toId == toId) {
                edge.weight = weight;
                //edgeFound = true;
                return;
            }
        }
        //if (!edgeFound) throw new InvalidEdgeException(fromId, toId);
        throw new InvalidEdgeException(fromId, toId);
    }

    @Override
    public Set<Integer> getNodeIds() {
        return nodeMapIds.keySet();
    }

    @Override
    public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
        if (!incomingNodeIds.containsKey(nodeId)) throw new InvalidNodeException(nodeId);
        return incomingNodeIds.get(nodeId);
    }

    @Override
    public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
        if (!outgoingNodeIds.containsKey(nodeId)) throw new InvalidNodeException(nodeId);
        return outgoingNodeIds.get(nodeId);
    }
}
