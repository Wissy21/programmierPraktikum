package de.tukl.programmierpraktikum2020.mp2.functions;

public class Exp implements Function {

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
        //exp(f) * f'
        return new Mult(new Exp(exp), exp.derive()).simplify();
    }

    @Override
    public Function simplify() {
        Function simpleExp = exp.simplify();
        Function simple = new Exp(simpleExp);
        // Hochzahl Konstant
        if (simpleExp instanceof Const){
            simple = new Const(Math.exp(simpleExp.apply(0)));
        }
        return simple;
    }

}
