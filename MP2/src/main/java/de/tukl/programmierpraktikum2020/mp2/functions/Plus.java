package de.tukl.programmierpraktikum2020.mp2.functions;

public class Plus implements Function{
    final public Function f1;
    final public Function f2;

    public Plus(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString() + " + " + f2.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) + f2.apply(x);
    }

    @Override
    public Function derive() {
        //f1'+f2'
        return new Plus(f1.derive(), f2.derive());
    }

    @Override
    public Function simplify() {
        Function simpleF1 = f1.simplify();
        Function simpleF2 = f2.simplify();
        Function simple = new Plus(simpleF1, simpleF2);
        //0+f2
        if (simpleF1 instanceof Const && simpleF1.apply(0) == 0){
                simple = simpleF2;
        }
        // f1+0
        if (simpleF2 instanceof Const && simpleF2.apply(0) == 0){
                simple = simpleF1;
        }
        // f1+f2 wenn beide Konstant
        if (simpleF1 instanceof Const && simpleF2 instanceof Const){
            simple = new Const(simpleF1.apply(0)+simpleF2.apply(0));
        }
        return simple;
    }
}
