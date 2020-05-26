package de.tukl.programmierpraktikum2020.mp2.functions;

public class Exp implements Function{

    final public Function exp;

    public Exp(Function exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "exp(" + exp.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.exp(exp.apply(x));
    }

    @Override
    public Function derive() {
        return null;
    }

}
