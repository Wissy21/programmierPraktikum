package de.tukl.programmierpraktikum2020.mp2.functions;

public class Const implements Function{
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
        return number;
    }

    @Override
    //hängt von den anderen Klassen ab, also erst nur apply und to String bei allen Klassen, danach derive
    public Function derive() {
        return new Const(0);
    }
}
