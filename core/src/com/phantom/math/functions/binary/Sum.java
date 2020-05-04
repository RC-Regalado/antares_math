package com.phantom.math.functions.binary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;
import com.phantom.math.functions.unary.Literal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.phantom.math.handler.Handler.*;

public class Sum extends Binary{
    public static Node makeSum(List<Node> functionsList) {
        if (functionsList.size() == 1)
            return functionsList.get(0);

        List<Node> functions = new ArrayList<>(functionsList);
        double constant = 0.0;

        for (int i = 0; i < functions.size(); i++) {
            Node f = functions.get(i);

            if (f.equals(ZERO)) {
                functions.remove(i--);
            } else if (f instanceof Sum) {
                functions.remove(i--);
                Sum f2 = (Sum) f;
                functions.add(f2.right);
                functions.add(f2.left);
            } else if (f instanceof Literal) {
                functions.remove(i--);
                constant = apply(constant, constValue(f));
            }
        }

        functions.add(literal(constant));
        Collections.sort(functions);

        Node res = ZERO;
        for (Node f : functions)
            res = makeSum(res, f);

        return res;
    }

    public static Node makeSum(Node right, Node left) {
        if (right.equals(ZERO))
            return left;
        if (left.equals(ZERO))
            return right;

        return new Sum(left, right);
    }

    protected Sum(Node right, Node left) {
        super(right, left);
    }

    private static double apply(double a, double b) {
        return a + b;
    }

    @Override
    public String toString() {
        String fs = needsParens(right) ? "(" + right + ")" : right.toString();
        String gs = needsParens(left) ? "(" + left + ")" : left.toString();
        return String.format("%s+%s", fs, gs);
    }

    private boolean needsParens(Node f) {
        return f instanceof Sum || f instanceof Product;
    }

    @Override
    protected Node diff2() {
        return sum(right.diff(), left.diff());
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
