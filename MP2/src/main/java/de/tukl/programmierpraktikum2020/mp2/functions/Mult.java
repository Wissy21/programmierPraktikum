package de.tukl.programmierpraktikum2020.mp2.functions;

public class Mult implements Function {
    final public Function f1;
    final public Function f2;

    public Mult(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString() + " * " + f2.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) * f2.apply(x);
    }

    @Override
    public Function derive() {
        //f1'*f2 + f1*f2'
        return new Plus(new Mult(f1.derive(), f2), new Mult(f1, f2.derive()));
    }

    @Override
    public Function simplify() {
        Function simpleF1 = f1.simplify();
        Function simpleF2 = f2.simplify();
        Function simple = new Mult(simpleF1, simpleF2);

        // f1 ist Const
        if (simpleF1 instanceof Const){
            double c1 = ((Const) simpleF1).number;
            simple = helper_constant(c1,simpleF2);
        }
        //f2 ist Const
        if (simpleF2 instanceof Const){
            double c1 = ((Const) simpleF2).number;
            simple = helper_constant(c1,simpleF1);
        }
        // beide Exp -> exp addieren
        if (simpleF1 instanceof Exp && simpleF2 instanceof Exp){
            Function exp1 = ((Exp) simpleF1).exp;
            Function exp2 = ((Exp) simpleF2).exp;
            simple = new Exp(new Plus(exp1,exp2).simplify());
        }
        //beide Pow und basis X -> exp addieren
        if(simpleF1 instanceof Pow && simpleF2 instanceof Pow){
            Pow p1 = (Pow) simpleF1;
            Pow p2 = (Pow) simpleF2;
            if (p1.basis instanceof X && p2.basis instanceof X){
                simple = new Pow(p1.basis,new Plus(p1.exp,p2.exp)).simplify();
            }
        }
        // beide X -> x^2
        if (simpleF1 instanceof X && simpleF2 instanceof X){
            simple = new Pow(new X(), new Const(2));
        }
        // beide sind Mult
        if (simpleF1 instanceof Mult && simpleF2 instanceof Mult) {
            Mult m1 = (Mult) simpleF1;
            Mult m2 = (Mult) simpleF2;
            simple = helper_multiplication(m1, m2);
        }
        return simple;
    }

    private Function helper_constant(double c1, Function function){
        Function result = new Mult(new Const(c1),function);

        // Konstante ist 0
        if (c1 == 0){
            result =  new Const(0);
        }
        // Konstante ist 1
        if (c1 == 1){
            result = function;
        }
        // beide Konstant
        if (function instanceof Const){
            double c2 = ((Const) function).number;
            result = new Const(c1*c2);
        }
        // die andere Funktion ist eine Mult mit einer Konstanten -> c1 * (c2 * function) = c1*c2 * function
        if (function instanceof Mult){
            Mult m = (Mult)function;
            if (m.f1 instanceof Const){
                double c2 = ((Const) m.f1).number;
                result = new Mult(new Const(c1*c2),m.f2).simplify();
            }
            if (m.f2 instanceof Const){
                double c2 = ((Const) m.f2).number;
                result = new Mult(new Const(c1*c2),m.f1).simplify();
            }
        }
        return result;
    }
    private Function helper_multiplication(Mult m1, Mult m2){
        // beide sind Mult, davon jeweils eine Konstant und die andere X
        Function result = new Mult(m1,m2);
        //m1.f1 Konstant
        if (m1.f1 instanceof Const) {
            // m2.f1 Konstant
            if (m2.f1 instanceof Const && m1.f2 instanceof X && m2.f2 instanceof X) {
                result = new Mult(new Const(((Const) m1.f1).number * ((Const) m2.f1).number), new Pow(new X(), new Const(2))).simplify();
            }
            // m2.f2 Konstant
            if (m2.f2 instanceof Const && m1.f2 instanceof X && m2.f1 instanceof X) {
                result = new Mult(new Const(((Const) m1.f1).number * ((Const) m2.f2).number), new Pow(new X(), new Const(2))).simplify();
            }
        }
        //m1.f2 Konstant
        if (m1.f2 instanceof Const) {
            // m2.f1 Konstant
            if (m2.f1 instanceof Const && m1.f1 instanceof X && m2.f2 instanceof X) {
                result = new Mult(new Const(((Const) m1.f2).number * ((Const) m2.f1).number), new Pow(new X(), new Const(2))).simplify();
            }
            // m2.f2 Konstant
            if (m2.f2 instanceof Const && m1.f1 instanceof X && m2.f1 instanceof X) {
                result = new Mult(new Const(((Const) m1.f2).number * ((Const) m2.f2).number), new Pow(new X(), new Const(2))).simplify();
            }
        }
        return result;
    }
}
