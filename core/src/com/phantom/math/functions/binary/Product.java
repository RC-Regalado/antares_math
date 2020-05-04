package com.phantom.math.functions.binary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;
import com.phantom.math.functions.unary.Literal;
import com.phantom.math.functions.unary.Negative;
import com.phantom.math.functions.unary.Variable;

import java.util.*;

import static com.phantom.math.handler.Handler.*;

public class Product extends Binary{
    public static Node makeProduct(Node right, Node left){
        if (right.equals(ZERO) || left.equals(ZERO))
            return ZERO;
        if (right.equals(ONE))
            return left;
        if (left.equals(ONE))
            return right;

        if (right instanceof Literal && left instanceof  Literal)
            return literal(apply(constValue(right), constValue(left)));

        return new Product(right, left);
    }
    public static Node makeProduct(List<Node> functionList) {
        if (functionList.size() == 1) {
            return functionList.get(0);
        }

        List<Node> functions = new ArrayList<>(functionList);
        Map<Node, List<Node>> expMap = new HashMap<>();
        double constant = 1.0;
        for (int i = 0; i < functions.size(); i++) {
            Node f = functions.remove(i--);
            if (f instanceof Product) {
                Product f2 = (Product) f;
                functions.add(f2.right);
                functions.add(f2.left);
            } else if (f instanceof Literal) {
                constant = apply(constant, constValue(f));
            } else if (f instanceof Negative) {
                constant = -constant;
                functions.add(negative(f));
            } else {
                Node base = f;
                Node exp = ONE;
                if (base instanceof Exponentiation) {
                    Exponentiation f2 = (Exponentiation) base;
                    base = f2.base();
                    exp = f2.exponent();
                }
                List<Node> exps = expMap.get(base);
                if (exps == null)
                    exps = new ArrayList<>();
                exps.add(exp);
                expMap.put(base, exps);
            }
        }

        Map<Node, Node> expMap2 = new HashMap<>();

        for (Node base : expMap.keySet()) {
            List<Node> exps = expMap.get(base);
            Node totalExp = sum(exps);
            expMap2.put(base, totalExp);
        }
        for (Node base : expMap2.keySet()) {
            functions.add(power(base, expMap2.get(base)));
        }

        functions.add(literal(constant));

        Collections.sort(functions);

        Node res = ONE;
        for (Node f : functions)
            res = makeProduct(res, f);

        return res;
    }
    protected Product(Node right, Node left) {
        super(right, left);
    }
    private static double apply(double a, double b) {
        return a * b;
    }

    @Override
    public String toString() {
        String fs = needsParens(right) ? "(" + right + ")" : right.toString();
        String gs = needsParens(left) ? "(" + left + ")" : left.toString();
        return String.format(left instanceof Variable ? "%s%s" : "%s*%s", fs, gs);
    }

    private boolean needsParens(Node f) {
        return f instanceof Sum || f instanceof Product;
    }

    @Override
    protected Node diff2() {
        Node rightD = right.diff();
        Node leftD = left.diff();

        return sum(product(rightD, left), product(leftD, right));
    }

    @Override
    public void evaluate(Collector collector) {
        right.evaluate(collector);
        double a = collector.getData();
        left.evaluate(collector);
        double b = collector.getData();

        collector.setData(apply(a, b));
    }


}
