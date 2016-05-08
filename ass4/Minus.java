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
public class Minus extends BinaryExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     * Constructor.
     *
     * @param num
     *            left num
     * @param rightExpression
     *            right expression
     */
    public Minus(double num, Expression rightExpression) {
        this.leftExpression = new Num(num);
        this.rightExpression = rightExpression;
    }

    /**
     * Constructor.
     *
     * @param leftExpression
     *            left expression
     * @param num
     *            right num
     */
    public Minus(Expression leftExpression, double num) {
        this.leftExpression = leftExpression;
        this.rightExpression = new Num(num);
    }

    /**
     * Constructor.
     *
     * @param var
     *            left var
     * @param rightExpression
     *            right expression
     */
    public Minus(String var, Expression rightExpression) {
        this.leftExpression = new Var(var);
        this.rightExpression = rightExpression;
    }

    /**
     * Constructor.
     *
     * @param leftExpression
     *            left expression
     * @param var
     *            right var
     */
    public Minus(Expression leftExpression, String var) {
        this.leftExpression = leftExpression;
        this.rightExpression = new Var(var);
    }

    /**
     * Constructor.
     *
     * @param leftExpression
     *            left expression
     * @param rightExpression
     *            right expression
     */
    public Minus(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * Constructor.
     *
     * @param leftVar
     *            left var
     * @param rightVar
     *            right var
     */
    public Minus(String leftVar, String rightVar) {
        this.leftExpression = new Var(leftVar);
        this.rightExpression = new Var(rightVar);
    }

    /**
     * Constructor.
     *
     * @param leftNum
     *            left num
     * @param rightNum
     *            right num
     */
    public Minus(double leftNum, double rightNum) {
        this.leftExpression = new Num(leftNum);
        this.rightExpression = new Num(rightNum);
    }

    /**
     * Constructor.
     *
     * @param leftVar
     *            left var
     * @param rightNum
     *            right num
     */
    public Minus(String leftVar, double rightNum) {
        this.leftExpression = new Var(leftVar);
        this.rightExpression = new Num(rightNum);
    }

    /**
     * Constructor.
     *
     * @param leftNum
     *            left num
     * @param rightVar
     *            right var
     */
    public Minus(double leftNum, String rightVar) {
        this.leftExpression = new Num(leftNum);
        this.rightExpression = new Var(rightVar);
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
            return this.leftExpression.evaluate(assignment)
                    - this.rightExpression.evaluate(assignment);
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
        return super.getVariables(this.leftExpression, this.rightExpression);
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return The expression
     */
    public String toString() {
        return "(" + this.leftExpression.toString() + " - "
                + this.rightExpression + ")";
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
        this.leftExpression = this.leftExpression.assign(var, expression);
        this.rightExpression = this.rightExpression.assign(var, expression);
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
        return new Minus(this.leftExpression.differentiate(var),
                this.rightExpression.differentiate(var));
    }
    /**
     * sets the expression.
     * @param lex new left expression
     * @param rex new right expression
     */
    public void setExpression(Expression lex, Expression rex) {
        this.leftExpression = lex;
        this.rightExpression = rex;
    }
    /**
     * this function sorts the variables in a lexicographic way.
     */
    public void sortVars() {
        if (this.leftExpression.getVariables().size() > 1) {
            this.leftExpression.sortVars();
            if (this.rightExpression.getVariables().size() > 1) {
                this.rightExpression.sortVars();
            } else {
                if (this.rightExpression.getVariables().size() == 1) {
                    // means the right expression has 1 value
                    if (this.leftExpression.getVariables().get(0)
                         .toString().toCharArray()[0]
                          > this.rightExpression.getVariables().
                          get(0).toString().toCharArray()[0]) {
                        this.setExpression(this.rightExpression,
                                this.leftExpression);
                    }
                } else {
                    this.rightExpression.sortVars();
                }
            }
        } else {
            if (this.leftExpression.getVariables().size() == 1) {
                // means there is only one variable in the left expression
                if (this.rightExpression.getVariables().size() > 1) {
                    this.rightExpression.sortVars();
                    } else {
                    if (this.rightExpression.getVariables().size() == 1) {
                        // means both expressions have one value
                        if (this.toString().charAt(1)
                                > this.toString().charAt(5)) {
                            this.setExpression(this.rightExpression,
                                    this.leftExpression);
                        }
                    }
                }
            }
        }
        if (this.leftExpression.getVariables().size() != 0
                && this.rightExpression.getVariables().size() != 0) {
            if (this.leftExpression.getVariables().get(0)
                    .toString().toCharArray()[0]
                     > this.rightExpression.getVariables().
                     get(0).toString().toCharArray()[0]) {
                   this.setExpression(this.rightExpression,
                           this.leftExpression);
               }
        }
    }
    /**
     * simplifies the expression.
     * @return a simplified expression
     * @exception Exception ex
     */
    public Expression simplify() throws Exception {
        this.sortVars();
        if (this.leftExpression.getVariables().isEmpty()) {
            // means left expression is a number
            try {
                if (this.leftExpression.evaluate() == 0) {
                    return this.rightExpression.simplify();
                } else {
                    if (this.rightExpression.getVariables().isEmpty()) {
                        // right expression is a number
                        try {
                            if (this.rightExpression.evaluate()
                               - this.leftExpression.evaluate() == 0) {
                                return new Num(0);
                            } else {
                                return new Num(this.rightExpression.evaluate()
                                        - this.leftExpression.evaluate());
                            }
                        } catch (Exception ex) {
                            throw ex;
                        }
                    } else {
                        if (this.rightExpression.simplify().getVariables()
                                                            .isEmpty()) {
                            // both expressions are numbers
                            return new Num(this.leftExpression.evaluate()
                                    - this.rightExpression.simplify()
                                    .evaluate());
                        }
                        return new Minus(this.leftExpression,
                                this.rightExpression.evaluate());
                    }
                }
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            // left expression is an expression
            if (this.rightExpression.getVariables().isEmpty()) {
                // means right expression is a number
                try {
                    if (this.rightExpression.evaluate() == 0) {
                        return this.leftExpression.simplify();
                    } else {
                        if (this.leftExpression.simplify().getVariables()
                                .isEmpty()) {
                            // both expressions are numbers
                            return new Num(this.leftExpression.
                                            simplify().evaluate()
                                            - this.rightExpression.evaluate());
                        }
                        return new Minus(this.leftExpression.simplify(),
                                        this.rightExpression);
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            } else {
                // both expressions are expressions
                if (this.leftExpression.toString().equals(
                        this.rightExpression.toString())) {
                    return new Num(0);
                }
                if (this.leftExpression.simplify().toString().equals(
                        this.rightExpression.simplify().toString())) {
                    return new Num(0);
                }
                if (this.leftExpression.equals(this.leftExpression.simplify())
                        && this.rightExpression.equals(
                        this.rightExpression.simplify())) {
                    return new Minus(this.leftExpression.simplify(),
                            this.rightExpression.simplify());
                }
                return new Minus(this.leftExpression.simplify(),
                        this.rightExpression.simplify()).simplify();
            }
        }
    }
}
