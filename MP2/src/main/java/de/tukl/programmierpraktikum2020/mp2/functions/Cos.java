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
        // -sin(f) * f'
        return new Mult(new Const(-1),new Mult(new Sin(function), function.derive()));
    }

    @Override
    public Function simplify() {
        Function simpleExp = function.simplify();
        Function simple = new Cos(simpleExp);
        // cos(Konstante)
        if (simpleExp instanceof Const){
            simple = new Const(Math.cos(simpleExp.apply(0)));
        }
        return simple;
    }

}
