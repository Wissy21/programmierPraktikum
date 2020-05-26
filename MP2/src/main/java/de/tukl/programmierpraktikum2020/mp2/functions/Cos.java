package de.tukl.programmierpraktikum2020.mp2.functions;

public class Cos implements Function{
    final public Function function;

    public Cos(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "cos(" + function.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.cos(function.apply(x));
    }

    @Override
    public Function derive() {
        return null;
    }
}
