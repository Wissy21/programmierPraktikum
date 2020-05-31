package de.tukl.programmierpraktikum2020.mp2;

import de.tukl.programmierpraktikum2020.mp2.antlr.FunctionBaseVisitor;
import de.tukl.programmierpraktikum2020.mp2.antlr.FunctionParser;
import de.tukl.programmierpraktikum2020.mp2.functions.*;

public class Parser extends FunctionBaseVisitor<Function> {
    @Override
    public Function visitConstVar (FunctionParser.ConstVarContext ctx) {
        return new Const(Double.parseDouble(ctx.getText()));
    }

    @Override
    public Function visitParExpr (FunctionParser.ParExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Function visitTrigExp (FunctionParser.TrigExpContext ctx) {
        if (ctx.op.getType() == FunctionParser.COS) return new Cos(visit(ctx.expr()));
        else return new Sin(visit(ctx.expr()));
    }

    @Override
    public Function visitExpExpr (FunctionParser.ExpExprContext ctx) {
        if (ctx.op.getType() == FunctionParser.EXP) return new Exp(visit(ctx.expr()));
        else return new Log(visit(ctx.expr()));
    }

    @Override
    public Function visitSgnValExpr (FunctionParser.SgnValExprContext ctx) {
        if (ctx.sgn != null && ctx.sgn.getType() == FunctionParser.MINUS) {
            return new Mult(new Const(-1.0), super.visitSgnValExpr(ctx));
        }
        return super.visitSgnValExpr(ctx); // positive
    }

    @Override
    public Function visitAddExpr (FunctionParser.AddExprContext ctx) {
        Function lFunction = visit(ctx.lexpr);
        Function rFunction = visit(ctx.rexpr);
        if (ctx.op.getType() == FunctionParser.PLUS) return new Plus(lFunction, rFunction);
        else return new Plus(lFunction, new Mult(new Const(-1.0), rFunction)); // f - g = f + (-1) * g
    }

    @Override
    public Function visitPowExpr (FunctionParser.PowExprContext ctx) {
        Function lFunction = visit(ctx.lexpr);
        Function rFunction = visit(ctx.rexpr);
        return new Pow(lFunction, rFunction);
    }

    @Override
    public Function visitMultExpr (FunctionParser.MultExprContext ctx) {
        Function lFunction = visit(ctx.lexpr);
        Function rFunction = visit(ctx.rexpr);
        if (ctx.op.getType() == FunctionParser.MULT) return new Mult(lFunction, rFunction);
        else return new Div(lFunction, rFunction);
    }

    @Override
    public Function visitIdVar (FunctionParser.IdVarContext ctx) {
        return new X();
    }

    //public Function visitAbsExpr(FunctionParser.AbsExprContext ctx) {
    //    return new Abs(visit(ctx.expr()));
    // }
}
