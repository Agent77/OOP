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
public class Div extends BinaryExpression implements Expression {
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
    public Div(double num, Expression rightExpression) {
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
    public Div(Expression leftExpression, double num) {
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
    public Div(String var, Expression rightExpression) {
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
    public Div(Expression leftExpression, String var) {
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
    public Div(Expression leftExpression, Expression rightExpression) {
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
    public Div(String leftVar, String rightVar) {
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
    public Div(double leftNum, double rightNum) {
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
    public Div(String leftVar, double rightNum) {
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
    public Div(double leftNum, String rightVar) {
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
                    / this.rightExpression.evaluate(assignment);
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
        return "(" + this.leftExpression.toString() + " / "
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
        return new Div(new Minus(new Mult(
                this.leftExpression.differentiate(var), this.rightExpression),
                new Mult(this.leftExpression, this.rightExpression
                      .differentiate(var))), new Pow(this.rightExpression, 2));
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
    }
    /**
     * simplifies the expression.
     * @return returns a simplified expression
     * @throws Exception ex
     */
    public Expression simplify() throws Exception {
        this.sortVars();
        if (this.leftExpression.getVariables().isEmpty()) {
            // left expression is a number
            try {
                if (this.leftExpression.evaluate() == 0) {
                    return new Num(0);
                } else {
                    if (this.rightExpression.getVariables().isEmpty()) {
                        // both expressions are numbers
                        try {
                            return new Num(this.evaluate());
                        } catch (Exception ex) {
                            throw ex;
                        }
                    } else {
                        try {
                            if (this.rightExpression.simplify().
                                getVariables().isEmpty()) {
                                // means that the expression after
                                // simplification is a number and as
                                // a result we have two numbers
                                return new Num(this.leftExpression.evaluate()
                                        / this.rightExpression.
                                        simplify().evaluate());
                            }
                        } catch (Exception ex) {
                            throw ex;
                        }
                        return new Div(this.leftExpression,
                                this.rightExpression.simplify());
                    }
                }
            } catch (Exception ex) {
                throw ex;
            }
       } else {
           // the left expression is an expression
           if (this.rightExpression.getVariables().isEmpty()) {
               // right expression is a number
               if (this.rightExpression.evaluate() == 1) {
                   return this.leftExpression.simplify();
               } else {
                   try {
                       if (this.leftExpression.simplify().getVariables().
                                                               isEmpty()) {
                           // means both expressions are numbers
                           try {
                               return new Num(this.leftExpression.simplify()
                                       .evaluate() / this.rightExpression
                                       .evaluate());
                           } catch (Exception ex) {
                               throw ex;
                           }
                       }
                   } catch (Exception ex) {
                       throw ex;
                   }
                   // right expression is a number and left expression
                   // is an expression
                   return new Div(this.leftExpression.simplify(),
                           this.rightExpression.evaluate());
               }
           } else {
               //both expressions are expressions
               if (this.leftExpression.simplify().toString().
                       equals(this.rightExpression.simplify().toString())) {
                   return new Num(1);
               }
               return new Div(this.leftExpression.simplify(),
                       this.rightExpression.simplify());
           }
       }
    }
}
