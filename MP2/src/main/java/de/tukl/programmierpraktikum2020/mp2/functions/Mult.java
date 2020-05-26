package de.tukl.programmierpraktikum2020.mp2.functions;

public class Mult implements Function {
    final public Function f1;
    final public Function f2;

    public Mult(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString() + " * " + f2.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) * f2.apply(x);
    }

    @Override
    public Function derive() {
        return null;
    }
}
