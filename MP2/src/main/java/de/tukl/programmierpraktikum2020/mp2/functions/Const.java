package de.tukl.programmierpraktikum2020.mp2.functions;

public class Const implements Function {
    final double number;

    public Const(double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public double apply(double x) {
        return number; //number
    }

    @Override
    public Function derive() {
        return new Const(0.0);
    }

    @Override
    public Function simplify() {
        return this;
    }
}
