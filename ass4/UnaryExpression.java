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
public abstract class UnaryExpression extends BaseExpression {
    /**
     * Returns a list of the variables in the expression.
     *
     * @param mainExpression
     *            main expression
     * @return The result
     */
    public List<String> getVariables(Expression mainExpression) {
        return mainExpression.getVariables();
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
    protected abstract double evaluate(Map<String, Double> assignment)
            throws Exception;
}
