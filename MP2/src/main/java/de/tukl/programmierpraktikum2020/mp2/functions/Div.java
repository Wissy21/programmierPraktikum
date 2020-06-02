package de.tukl.programmierpraktikum2020.mp2.functions;

import java.util.Collections;

public class Div implements Function {
    final public Function f1;
    final public Function f2;

    public Div(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "("+f1.toString()+" / "+f2.toString()+")";
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) / f2.apply(x);
    }

    @Override
    public Function derive() {
        //f1'*f2 - f1*f2'
        Function oben = new Plus(new Mult(f1.derive(), f2), new Mult(new Const(-1.0), new Mult(f1, f2.derive())));
        //f2^2
        Function unten = new Mult(f2, f2);
        return new Div(oben, unten);
    }

    @Override
    public Function simplify() {
        Function simpleF1 = f1.simplify();
        Function simpleF2 = f2.simplify();
        Function simple = new Div(simpleF1, simpleF2);

        //0/f2
        if (simpleF1 instanceof Const && simpleF1.apply(0.0) == 0.0) {
            simple = new Const(0.0);
        }
        // f1/1
        if (simpleF2 instanceof Const && simpleF2.apply(0.0) == 1.0) {
            simple = simpleF1;
        }
        //beide Konstant
        if (simpleF1 instanceof Const && simpleF2 instanceof Const) {
            simple = new Const(simpleF1.apply(0.0) / simpleF2.apply(0.0));
        }

        // beide X
        if (simpleF1 instanceof X && simpleF2 instanceof X) {
            simple = new Const(1.0);
        }
        // beide Potenz von X
        if (simpleF1 instanceof Pow && simpleF2 instanceof Pow) {
            Pow p1 = (Pow) f1;
            Pow p2 = (Pow) f2;
            if (p1.basis instanceof X && p2.basis instanceof X && p1.exp instanceof Const && p2.exp instanceof Const) {
                double exp1 = ((Const) p1.exp).number;
                double exp2 = ((Const) p2.exp).number;
                //exponenten gleich -> wegkürzen
                if (exp1 == exp2) {
                    simple = new Const(1.0);
                } else {
                    simple = new Pow(new X(), new Const(exp1-exp2));
                }
            }
        }
        // beide Mult
        if (simpleF1 instanceof Mult && simpleF2 instanceof Mult) {
            Mult m1 = (Mult) simpleF1;
            Mult m2 = (Mult) simpleF2;
            simple = helper_multiplication(m1, m2);
        }
        return simple;
    }

    private Function helper_multiplication(Mult m1, Mult m2) {
        // beide sind Mult, davon jeweils eine Konstant und die andere X
        Function result = new Plus(m1, m2);
        //m1.f1 Konstant
        if (m1.f1 instanceof Const) {
            // m2.f1 Konstant
            if (m2.f1 instanceof Const) {
                //davon jeweils eine Konstant und die andere X
                if (m1.f2 instanceof X && m2.f2 instanceof X) {
                    result = new Mult(new Const(((Const) m1.f1).number / ((Const) m2.f1).number), new X()).simplify();
                }
                //davon jeweils eine Konstant und die andere eine Potenz von X
                if (m1.f2 instanceof Pow && m2.f2 instanceof Pow) {
                    result = helper_xPow_mult(m1.f2, m2.f2, (Const) m1.f1, (Const) m2.f1);
                }
            }
            // m2.f2 Konstant
            if (m2.f2 instanceof Const) {
                //die andere X
                if (m1.f2 instanceof X && m2.f1 instanceof X) {
                    result = new Mult(new Const(((Const) m1.f1).number / ((Const) m2.f2).number), new X()).simplify();
                }
                //die andere eine Potenz von X
                if (m1.f2 instanceof Pow && m2.f1 instanceof Pow) {
                    result = helper_xPow_mult(m1.f2, m2.f1, (Const) m1.f1, (Const) m2.f2);
                }
            }
        }
        //m1.f2 Konstant
        if (m1.f2 instanceof Const) {
            // m2.f1 Konstant
            if (m2.f1 instanceof Const) {
                //die andere von X
                if (m1.f1 instanceof X && m2.f2 instanceof X) {
                    result = new Mult(new Const(((Const) m1.f2).number / ((Const) m2.f1).number), new X()).simplify();
                }
                //die andere eine Potenz von X
                if (m1.f1 instanceof Pow && m2.f2 instanceof Pow) {
                    result = helper_xPow_mult(m1.f2, m2.f2, (Const) m1.f2, (Const) m2.f1);
                }
            }
            // m2.f2 Konstant
            if (m2.f2 instanceof Const) {
                //die andere von X
                if (m1.f1 instanceof X && m2.f1 instanceof X) {
                    result = new Mult(new Const(((Const) m1.f2).number / ((Const) m2.f2).number), new X()).simplify();
                }
                //die andere eine Potenz von X
                if (m1.f1 instanceof Pow && m2.f1 instanceof Pow) {
                    result = helper_xPow_mult(m1.f2, m2.f1, (Const) m1.f2, (Const) m2.f2);
                }
            }
        }
        return result;
    }

    Function helper_xPow_mult(Function f1, Function f2, Const c1, Const c2) {
        Function result = new Plus(new Mult(c1, f1), new Mult(c2, f2));
        Pow p1 = (Pow) f1;
        Pow p2 = (Pow) f2;
        if (p1.basis instanceof X && p2.basis instanceof X && p1.exp instanceof Const && p2.exp instanceof Const) {
            double exp1 = ((Const) p1.exp).number;
            double exp2 = ((Const) p2.exp).number;
            //exponenten gleich -> wegkürzen
            if (exp1 == exp2) {
                result = new Const(c1.number / c2.number);
            } else {
                result = new Mult(new Const(c1.number / c2.number), new Pow(new X(), new Const(exp1-exp2))).simplify();
            }
        }
        return result;
    }

}
