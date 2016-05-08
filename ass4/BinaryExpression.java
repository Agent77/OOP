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
public abstract class BinaryExpression extends BaseExpression {
    /**
     * Returns a list of the variables in the expression.
     *
     * @param leftExpression
     *            left expression
     * @param rightExpression
     *            right expression
     * @return The result
     */
    public List<String> getVariables(Expression leftExpression,
            Expression rightExpression) {
        List<String> leftList = leftExpression.getVariables();
        List<String> rightList = rightExpression.getVariables();
        if (leftList != null || rightList != null) {
            List<String> lst = new LinkedList<String>();
            if (leftList != null) {
                lst.addAll(leftList);
            }
            if (rightList != null) {
                lst.addAll(rightList);
            }
            return lst;
        }
        return null;
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
