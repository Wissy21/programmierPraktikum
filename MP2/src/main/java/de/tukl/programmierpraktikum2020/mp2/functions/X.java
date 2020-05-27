package de.tukl.programmierpraktikum2020.mp2.functions;

public class X implements Function{

    public X() {
    }

    @Override
    public String toString() {
        return "x";
    }

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public Function derive() {
        return new Const(1);
    }

    @Override
    public Function simplify() {
        return this;
    }

}

