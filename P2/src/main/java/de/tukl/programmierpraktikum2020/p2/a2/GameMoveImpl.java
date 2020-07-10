package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.*;

import java.util.*;

public class GameMoveImpl implements GameMove {
    private final Graph<Color, Integer> graph;

    public GameMoveImpl(Graph<Color, Integer> graph) {
        this.graph = graph;
    }

    @Override
    public void setColor(int nodeId, Color color) throws GraphException, ForcedColorException {
        Color currentColor = graph.getData(nodeId);
        Set<Integer> incomingNeighbors = graph.getIncomingNeighbors(nodeId);
        if (incomingNeighbors.size() == 1 && !currentColor.equals(Color.WHITE)) {
            int fromId = (int) incomingNeighbors.toArray()[0];
            if (graph.getWeight(fromId, nodeId) > 0) throw new ForcedColorException(nodeId, color);
        }
        graph.setData(nodeId, color);
        recoloring(nodeId);
    }

    @Override
    public void increaseWeight(int fromId, int toId) throws GraphException {
        int weight = graph.getWeight(fromId, toId) + 1;
        graph.setWeight(fromId, toId, weight);
        recoloring(fromId);
    }

    @Override
    public void decreaseWeight(int fromId, int toId) throws GraphException, NegativeWeightException {
        int weight = graph.getWeight(fromId, toId) - 1;
        if (weight < 0) throw new NegativeWeightException(fromId, toId);
        graph.setWeight(fromId, toId, weight);
        recoloring(fromId);
    }

    private void recoloring(int fromId) throws GraphException {
        Set<Integer> newColoredNodeIds = new HashSet<>();
        for (int toId : graph.getOutgoingNeighbors(fromId)) {
            for (Color color : Color.values()) {
                if (!color.equals(Color.WHITE)) {
                    Set<Integer> incomingNodeIds = graph.getIncomingNeighbors(toId);
                    Set<Integer> sameColorNodeIds = new HashSet<>();
                    for (int inComingNodeId : incomingNodeIds) {
                        if (graph.getData(inComingNodeId).equals(color)) {
                            sameColorNodeIds.add(inComingNodeId);
                        }
                    }
                    double weightC = calculateNeighborsWeight(sameColorNodeIds, toId);
                    double weightTotal = calculateNeighborsWeight(incomingNodeIds, toId);
                    if (weightC > weightTotal / 2) {
                        graph.setData(toId, color);
                        if (fromId != toId)
                            newColoredNodeIds.add(toId);
                        if (graph.getOutgoingNeighbors(fromId).contains(toId) &&  graph.getIncomingNeighbors(fromId).contains(toId))
                            newColoredNodeIds.remove(toId);
                    }
                }
            }
        }
        recoloring(newColoredNodeIds);
    }

    private void recoloring(Set<Integer> nodeIds) throws GraphException {
        for (int nodeId : nodeIds) recoloring(nodeId);
    }

    private double calculateNeighborsWeight(Set<Integer> nodeIds, int toId) throws GraphException {
        double totalWeight = 0;
        for (int fromId : nodeIds)
            totalWeight += graph.getWeight(fromId, toId);
        return totalWeight;
    }
}