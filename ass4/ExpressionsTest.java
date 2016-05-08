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
public class ExpressionsTest {
    /**
     * main.
     * @param args string
     * @throws Exception ex
     */
    public static void main(String[] args) throws Exception {
        Expression ex1 = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))) , new Pow("e", "x"));
        System.out.println(ex1.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        System.out.println(ex1.evaluate(assignment));
        System.out.println(ex1.differentiate("x").toString());
        System.out.println(ex1.differentiate("x").
                evaluate(assignment));
        System.out.println(ex1.differentiate("x").simplify().toString());
    }
}