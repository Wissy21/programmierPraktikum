package de.tukl.programmierpraktikum2020.mp2.functions;

public class Pow implements Function {
    public final Function basis;
    public final Function exp;

    public Pow(Function f1, Function f2) {
        this.basis = f1;
        this.exp = f2;
    }

    @Override
    public String toString() {
        return "("+basis.toString()+" ^ "+exp.toString()+")";
    }

    @Override
    public double apply(double x) {
        return Math.pow(basis.apply(x), exp.apply(x));
    }


    @Override
    public Function derive() {
        Function power = new Plus(exp, new Const(-1));
        Function deriveBasis = basis.derive();
        Function op1 = new Mult(exp, deriveBasis);
        Function op2 = new Pow(basis, power);
        return new Mult(op1, op2);
    }

    @Override
    public Function simplify() {
        Function simpleB = basis.simplify();
        Function simpleE = exp.simplify();
        Function simple = new Pow(simpleB, simpleE);
        // wenn der exp Konstant
        if (exp instanceof Const) {
            double c = ((Const) exp).number;

            // wenn exp == 0
            if (c == 0.0) {
                simple = new Const(1);
            }
            // wenn exp == 1
            if (c == 1.0) {
                simple = simpleB;
            }
        }
        //wenn beide Const
        if (simpleB instanceof Const && simpleE instanceof Const) {
            simple = new Const(Math.pow(((Const) simpleB).number, ((Const) simpleE).number));
        }
        return simple;
    }
}
