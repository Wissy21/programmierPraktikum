package de.tukl.programmierpraktikum2020.mp2.functions;

public class Div implements Function {
    final public Function f1;
    final public Function f2;

    public Div(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString() + " / " + f2.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) / f2.apply(x);
    }

    @Override
    public Function derive() {
        //f1'*f2 - f1*f2'
        Function oben = new Plus(new Mult(f1.derive(),f2),new Mult(new Const(-1),new Mult(f1,f2.derive())));
        //f2^2
        Function unten = new Mult(f2,f2);
        return new Div(oben,unten);
    }

    @Override
    public Function simplify() {
        Function simpleF1 = f1.simplify();
        Function simpleF2 = f2.simplify();
        Function simple = new Div(simpleF1, simpleF2);
        //0/f2
        if (simpleF1 instanceof Const && simpleF1.apply(0) == 0){
            simple = new Const(0);
        }
        // f1/1
        if (simpleF2 instanceof Const && simpleF2.apply(0) == 1){
            simple = simpleF1;
        }
        // f1/f2 wenn beide Konstant
        if (simpleF1 instanceof Const && simpleF2 instanceof Const){
            simple = new Const(simpleF1.apply(0)/simpleF2.apply(0));
        }
        return simple;
    }
}
