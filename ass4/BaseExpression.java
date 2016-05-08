import java.util.Map;
import java.util.TreeMap;

/**
 * assignment 4.
 *
 * @author Daniel Kasman 206274946 <fiter21@gmail.com>
 * @author Yossi Khasidashvili 320650864 <Khasidashvili@gmail.com>
 * @version 1
 * @since 17/04/2014
 */
public abstract class BaseExpression {
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
            return evaluate(new TreeMap<String, Double>());
        } catch (Exception ex) {
            throw ex;
        }
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
    protected abstract double evaluate(Map<String, Double> assignment)
            throws Exception;
}