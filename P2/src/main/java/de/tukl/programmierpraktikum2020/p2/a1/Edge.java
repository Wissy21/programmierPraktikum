package de.tukl.programmierpraktikum2020.p2.a1;

public class Edge<D,W> {
    Node<D,W> from;
    Node<D,W> to;
    W weight;

    public Edge(Node<D, W> from, Node<D, W> to, W weigth) {
        this.from = from;
        this.to = to;
        this.weight = weigth;
    }

    public int getIdFrom(){
        return from.identifier;
    }
    public int getIdTo(){
        return to.identifier;
    }

    public void setWeigth(W weight) {
        this.weight = weight;
    }

    public boolean equals(Edge<D,W> otherEdge){
        return (this.from.identifier == otherEdge.getIdFrom()
                && this.to.identifier == otherEdge.getIdTo());
    }
}
