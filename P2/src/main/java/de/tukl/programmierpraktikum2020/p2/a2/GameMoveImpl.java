package de.tukl.programmierpraktikum2020.p2.a2;

import de.tukl.programmierpraktikum2020.p2.a1.*;

import java.util.*;

public class GameMoveImpl<D,W> implements GameMove {
    private final Graph<Color, Integer> spielfeld;

    public GameMoveImpl(Graph<Color, Integer> spielfeld) {
        this.spielfeld = spielfeld;
    }

    private void helper(int nodeId, Color color) throws InvalidNodeException, ForcedColorException, InvalidEdgeException {
        Set<Integer> setin = spielfeld.getIncomingNeighbors(nodeId);
        Set<Integer> setout = spielfeld.getOutgoingNeighbors(nodeId);
        int wYELLOW = 0;
        int wRED = 0;
        int wGREEN = 0;
        int wBLUE = 0;
        int wcolor =0;
        Color save = Color.WHITE;

        //Gewichtung für einzelne Farben wird berechnet
        for (Integer nodes : setin) {
            switch (spielfeld.getData(nodes)) {
                case RED:
                    wRED += spielfeld.getWeight(nodes, nodeId);
                    break;
                case BLUE:
                    wBLUE += spielfeld.getWeight(nodes, nodeId);
                    break;
                case GREEN:
                    wGREEN += spielfeld.getWeight(nodes, nodeId);
                    break;
                case YELLOW:
                    wYELLOW += spielfeld.getWeight(nodes, nodeId);
                    break;
                default:
            }
            //Gewichtung für eingegebene Farbe wird berechnet
            if (spielfeld.getData(nodes)==color) {
                    wcolor += spielfeld.getWeight(nodes, nodeId);
            }
        }
            //Farbe wird auf maximale Gewichtung gesetzt und Farbe gespeichert
            int wmax=Collections.max(Arrays.asList(wBLUE, wRED, wGREEN, wYELLOW));
            if (wmax==wBLUE) {
                spielfeld.setData(nodeId, Color.BLUE);
                save = Color.BLUE;
            }
            else if (wmax==wYELLOW) {
                spielfeld.setData(nodeId, Color.YELLOW);
                save = Color.YELLOW;
            }
            else if (wmax==wGREEN) {
                spielfeld.setData(nodeId, Color.GREEN);
                save = Color.GREEN;
            }
            else if (wmax == wRED) {
                spielfeld.setData(nodeId, Color.RED);
                save = Color.RED;
            }
            //wenn gespeicherte bzw gesetzte Farbe nicht der eingegeben Farbe entspicht: Exception
            if (save != color) {throw new ForcedColorException(nodeId, color); }

            //iterativ über folgende Knoten, wenn sich Farbe geändert hat
            for (Integer nodes : setout) {
                helper(nodes, save);
            }
        }

/*
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
        }*/




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
            //wenn Knoten gleiche Farbe, ändert sich nichts, wenn Gewichtung reduziert wird
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