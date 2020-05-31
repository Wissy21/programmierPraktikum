// Generated from C:/Users/Manuela K/team21/MP2/src/main/antlr\Function.g4 by ANTLR 4.8
package de.tukl.programmierpraktikum2020.mp2.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FunctionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FunctionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(FunctionParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trigExp}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigExp(FunctionParser.TrigExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpExpr(FunctionParser.ExpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sgnValExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSgnValExpr(FunctionParser.SgnValExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(FunctionParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(FunctionParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link FunctionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(FunctionParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constVar}
	 * labeled alternative in {@link FunctionParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstVar(FunctionParser.ConstVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idVar}
	 * labeled alternative in {@link FunctionParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdVar(FunctionParser.IdVarContext ctx);
}