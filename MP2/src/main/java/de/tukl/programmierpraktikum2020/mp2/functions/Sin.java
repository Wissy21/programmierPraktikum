package de.tukl.programmierpraktikum2020.mp2.functions;

public class Sin implements Function {
    final public Function function;

    public Sin(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "sin(" + function.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.sin(function.apply(x));
    }

    @Override
    public Function derive() {
        return null;
    }
}
