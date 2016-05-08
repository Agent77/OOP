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
public interface Expression {
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
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but
     * uses an empty assignment.
     *
     * @return The result
     * @throws Exception
     *             if there is a variable that is not in the assignment
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return The result
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return The expression
     */
    String toString();

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
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from differentiating the current
     * expression relative variable 'var'.
     *
     * @param var
     *            the variable
     * @return the differentiated expression
     */
    Expression differentiate(String var);
    /**
     * sets the expression.
     * @param lex left expression
     * @param rex right expression
     */
    void setExpression(Expression lex, Expression rex);
    /**
     * Returns a simplified version of the current expression.
     * @return a new expression
     * @throws Exception ex
     */
    Expression simplify() throws Exception;
    /**
     * the function sorts the variables in a lexicographic way.
     */
    void sortVars();
}