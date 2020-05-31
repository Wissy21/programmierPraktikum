// Generated from C:/Users/Manuela K/team21/MP2/src/main/antlr\Function.g4 by ANTLR 4.8
package de.tukl.programmierpraktikum2020.mp2.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FunctionParser}.
 */
public interface FunctionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(FunctionParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(FunctionParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trigExp}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrigExp(FunctionParser.TrigExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trigExp}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrigExp(FunctionParser.TrigExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpExpr(FunctionParser.ExpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpExpr(FunctionParser.ExpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sgnValExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSgnValExpr(FunctionParser.SgnValExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sgnValExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSgnValExpr(FunctionParser.SgnValExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(FunctionParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(FunctionParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(FunctionParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(FunctionParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(FunctionParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(FunctionParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constVar}
	 * labeled alternative in {@link FunctionParser#var}.
	 * @param ctx the parse tree
	 */
	void enterConstVar(FunctionParser.ConstVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constVar}
	 * labeled alternative in {@link FunctionParser#var}.
	 * @param ctx the parse tree
	 */
	void exitConstVar(FunctionParser.ConstVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idVar}
	 * labeled alternative in {@link FunctionParser#var}.
	 * @param ctx the parse tree
	 */
	void enterIdVar(FunctionParser.IdVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idVar}
	 * labeled alternative in {@link FunctionParser#var}.
	 * @param ctx the parse tree
	 */
	void exitIdVar(FunctionParser.IdVarContext ctx);
}