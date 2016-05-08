import java.util.List;
import java.util.Map;

/**
 * assignment 4.
 *
 * @author Daniel Kasman 206274946 <fiter21@gmail.com>
 * @author Yossi Khasidashvili 320650864 <Khasidashvili@gmail.com>
 * @version 1
 * @since 17/04/2014
 */
public class Cos extends UnaryExpression implements Expression {
    private Expression mainExpression;

    /**
     * Constructor.
     *
     * @param expression
     *            The expression
     */
    public Cos(Expression expression) {
        this.mainExpression = expression;
    }

    /**
     * Constructor.
     *
     * @param var
     *            The var
     */
    public Cos(String var) {
        this.mainExpression = new Var(var);
    }

    /**
     * Constructor.
     *
     * @param num
     *            The num
     */
    public Cos(double num) {
        this.mainExpression = new Num(num);
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result. If the expression contains a variable
     * which is not in the assignment, an exception is thrown.
     *
     * @param assignment
     *            The assignment
     * @return The result
     * @throws Exception
     *             if there is a variable that is not in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return Math.cos(Math.toRadians(
                    this.mainExpression.evaluate(assignment)));
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return The result
     */
    public List<String> getVariables() {
        return mainExpression.getVariables();
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return The expression
     */
    public String toString() {
        return ("cos(" + this.mainExpression.toString() + ")");
    }

    /**
     * Returns a new expression in which all occurrences of the variable var are
     * replaced with the provided expression (Does not modify the current
     * expression).
     *
     * @param var
     *            The variable
     * @param expression
     *            The expression
     * @return The expression
     */
    public Expression assign(String var, Expression expression) {
        this.mainExpression = this.mainExpression.assign(var, expression);
        return this;
    }

    /**
     * Returns the expression tree resulting from differentiating the current
     * expression relative variable 'var'.
     *
     * @param var
     *            the variable
     * @return the differentiated expression
     */
    public Expression differentiate(String var) {
        return new Mult(new Neg(new Sin(this.mainExpression)),
                this.mainExpression.differentiate(var));
    }
    /**
     * sets the expressions.
     * @param lex the left expression.
     * @param rex the right expression.
     */
    public void setExpression(Expression lex, Expression rex) {
    }
    /**
     * sorts the variables.
     */
    public void sortVars() {
        this.mainExpression.sortVars();
    }
    /**
     * returns a simplified expression.
     * @return this expression
     */
    public Expression simplify() {
        this.sortVars();
        return this;
    }
}
