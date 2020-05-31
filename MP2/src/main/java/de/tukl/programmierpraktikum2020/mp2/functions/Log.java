package de.tukl.programmierpraktikum2020.mp2.functions;

public class Log implements Function {
    public final Function function;

    public Log(Function function) {
        this.function = function;
    }


    @Override
    public String toString() {
        return "log("+function.toString()+")";
    }


    @Override
    public double apply(double x) {
        return Math.log(function.apply(x));
    }

    @Override
    public Function derive() {
        return new Div(function.derive(), function);
    }

    @Override
    public Function simplify() {
        Function simpleF = function.simplify();
        Function simple = new Log(simpleF);
        // Log(Konstante)
        if (simpleF instanceof Const && ((Const) simpleF).number == 1.0) {
            simple = new Const(0.0);
        }
        return simple;
    }
}
