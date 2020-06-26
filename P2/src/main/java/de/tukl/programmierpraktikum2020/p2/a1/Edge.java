package de.tukl.programmierpraktikum2020.p2.a1;

public class Edge<D,W> {
    Node<D,W> from;
    Node<D,W> to;
    W weigth;

    public Edge(Node<D, W> from, Node<D, W> to, W weigth) {
        this.from = from;
        this.to = to;
        this.weigth = weigth;
    }

    public int getIdFrom(){
        return from.identifier;
    }
    public int getIdTo(){
        return to.identifier;
    }

    public void setWeigth(W weigth) {
        this.weigth = weigth;
    }

    public boolean equals(Edge<D,W> otherEdge){
        return this.from.identifier == otherEdge.getIdFrom()
                && this.to.identifier == otherEdge.getIdTo();
    }
}
