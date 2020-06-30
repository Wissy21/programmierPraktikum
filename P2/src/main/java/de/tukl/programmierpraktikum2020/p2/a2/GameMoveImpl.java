package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameMoveImpl<D,W> implements GameMove {
    private final Graph<Color, Integer> spielfeld;

    public GameMoveImpl(Graph<Color, Integer> spielfeld) {
        this.spielfeld = spielfeld;
    }

    private void helper(int nodeId, Color color) throws InvalidNodeException, ForcedColorException {
        Set<Integer> setin = spielfeld.getIncomingNeighbors(nodeId);
        Set<Integer> setout = spielfeld.getOutgoingNeighbors(nodeId);
        int wold = 0;
        int wnew = 0;

        try {
            spielfeld.getIncomingNeighbors(nodeId);
            for (Integer nodes : setin) {
                if (spielfeld.getData(nodeId) == spielfeld.getData(nodes)) {
                    wold += spielfeld.getWeight(nodes, nodeId);
                } else if (spielfeld.getData(nodes) == color) {
                    wnew += spielfeld.getWeight(nodes, nodeId);
                }
            }
            if (wold <= wnew) {
                spielfeld.setData(nodeId, color);
                for (Integer nodes : setout) {
                    helper(nodes, color);
                }
            } else throw new ForcedColorException(nodeId, color);

        } catch (InvalidNodeException | InvalidEdgeException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void setColor(int nodeId, Color color) throws GraphException, ForcedColorException {
        if (spielfeld.getData(nodeId) == Color.WHITE) {
            spielfeld.setData(nodeId, color);
        } else if (spielfeld.getData(nodeId) == color) {
            spielfeld.setData(nodeId, color);
        } else helper(nodeId, color);
    }

    @Override
    public void increaseWeight(int fromId, int toId) throws GraphException {
        Set<Integer> set = spielfeld.getIncomingNeighbors(toId);
        int wtotal = 0;
        int w = 0;

        spielfeld.setWeight(fromId, toId, spielfeld.getWeight(fromId, toId) + 1);

        //Summe der Gewichte wtotal berechnen
        for (Integer nodes : set) {
            wtotal += spielfeld.getWeight(nodes, toId);
        }
        //wenn Knoten gleiche Farbe, ändert sich nichts, wenn Gewichtung erhöht wird
        if (spielfeld.getData(fromId) != spielfeld.getData(toId)) {
            for (Integer nodes : set) {
                //wenn Knoten gleiche Farbe wie fromId wird w summiert
                if (spielfeld.getData(fromId) == spielfeld.getData(nodes)) {
                    w += spielfeld.getWeight(nodes, toId);
                }
            }
        }
        //Berechnung
        if (w > (wtotal / 2)) {
            try {
                setColor(toId, spielfeld.getData(fromId));

            } catch (ForcedColorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void decreaseWeight(int fromId, int toId) throws GraphException, NegativeWeightException {
        Set<Integer> set = spielfeld.getIncomingNeighbors(toId);
        int wtotal = 0;
        int w = 0;


        if (spielfeld.getWeight(fromId, toId) <= 0) throw new NegativeWeightException(fromId, toId);
        else {
            spielfeld.setWeight(fromId, toId, spielfeld.getWeight(fromId, toId) - 1);


            //Summe der Gewichte wtotal berechnen
            for (Integer nodes : set) {
                wtotal += spielfeld.getWeight(nodes, toId);
            }
            //wenn Knoten gleiche Farbe, ändert sich nichts, wenn Gewichtung erhöht wird
            if (spielfeld.getData(fromId) != spielfeld.getData(toId)) {
                for (Integer nodes : set) {
                    //wenn Knoten gleiche Farbe wie fromId wird w summiert
                    if (spielfeld.getData(fromId) == spielfeld.getData(nodes)) {
                        w += spielfeld.getWeight(nodes, toId);
                    }
                }
            }
            //Berechnung
            if (w > (wtotal / 2)) {
                try {
                    setColor(toId, spielfeld.getData(fromId));

                } catch (ForcedColorException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}