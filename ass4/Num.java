import java.util.LinkedList;
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
public class Num implements Expression {
    private double num;

    /**
     * Constructor.
     *
     * @param num
     *            the number
     */
    public Num(double num) {
        this.num = num;
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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return this.num;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but
     * uses an empty assignment.
     *
     * @return The result
     * @throws Exception
     *             if there is a variable that is not in the assignment
     */
    public double evaluate() throws Exception {
        try {
            return this.num;
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
        return new LinkedList<String>();
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return The expression
     */
    public String toString() {
        Double temp = num;
        return temp.toString();
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
        return this;
    }
    /**
     * sets the expressions.
     * @param lex the left expression.
     * @param rex the right expression.
     */
    public void setExpression(Expression lex, Expression rex) {
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
        return new Num(0);
    }
    /**
     * sorts the variables.
     */
    public void sortVars() {
    }
    /**
     * Returns a simplified version of the current expression.
     * @return a new expression
     */
    public Expression simplify() {
            return this;
        }
}
