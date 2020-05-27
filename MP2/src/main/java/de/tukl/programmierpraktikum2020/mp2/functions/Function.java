package de.tukl.programmierpraktikum2020.mp2.functions;

public interface Function {
    String toString();
    double apply(double x);
    Function derive();
    Function simplify();
}
