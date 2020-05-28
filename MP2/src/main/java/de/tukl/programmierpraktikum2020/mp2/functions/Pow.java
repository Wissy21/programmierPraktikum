package de.tukl.programmierpraktikum2020.mp2.functions;

public class Pow implements Function{
    public final Function basis;
    public final Function exp;

    public Pow(Function f1, Function f2) {
        this.basis = f1;
        this.exp = f2;
    }

    @Override
    public String toString() {
        return "(" + basis.toString() + " ^ " + exp.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.pow(basis.apply(x), exp.apply(x));
    }

    @Override
    public Function derive() {
        //Fallunterscheidung weil es einfacher ist hier zu vereinfachen, als später
        //wenn exp Const
        if (exp instanceof Const){
            double c = ((Const)exp).number;
            return new Mult(exp, new Pow(basis,new Const(c-1)));
        }
        // wenn basis Const
        else if(basis instanceof Const){
            return new Mult(this,new Log(basis));
        }

        else
        //nehme zur Hilfe, dass x^y = e^y*ln(x)
            return new Mult(this,new Mult(exp,new Log(basis)).derive());
    }

    @Override
    public Function simplify() {
        Function simpleB = basis.simplify();
        Function simpleE = exp.simplify();
        Function simple = new Pow(simpleB, simpleE);
        // wenn der exp Konstant
        if (exp instanceof Const){
            double c = ((Const)exp).number;

            // wenn exp == 0
            if (c==0){
                simple = new Const(1);
            }
            // wenn exp == 1
            if (c==1){
                simple = simpleB;
            }
        }
        //wenn beide Const
        if (simpleB instanceof Const && simpleE instanceof Const){
            simple = new Const(Math.pow(simpleB.apply(0),simpleE.apply(0)));
        }


        return simple;
    }
}
