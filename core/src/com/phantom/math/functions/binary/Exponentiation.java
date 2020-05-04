package com.phantom.math.functions.binary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;
import com.phantom.math.functions.unary.Literal;
import com.phantom.math.functions.unary.trigonometrics.Cos;
import com.phantom.math.functions.unary.trigonometrics.Sin;
import com.phantom.math.functions.unary.trigonometrics.Tan;

import static com.phantom.math.handler.Handler.*;

public class Exponentiation extends Binary {
    public static Node makeExponentiation(Node base, Node exp) {
        if (base instanceof Product) {
            Product f = (Product) base;
            return product(power(f.left, exp), power(f.right, exp));
        }

        if (base instanceof Exponentiation) {
            Exponentiation f = (Exponentiation) base;
            return power(f.base(), product(f.exponent(), exp));
        }

        if (base.equals(ZERO))
            return ZERO;
        if (base.equals(ONE))
            return ONE;
        if (exp.equals(ZERO))
            return ONE;
        if (exp.equals(ONE))
            return base;
        if (base instanceof Literal && exp instanceof Literal)
            return literal(apply(constValue(base), constValue(exp)));

        return new Exponentiation(base, exp);
    }

    private static double apply(double a, double b) {
        return Math.pow(a, b);
    }

    private Exponentiation(Node base, Node exponent) {
        super(base, exponent);
    }

    protected Node base() {
        return left;
    }

    protected Node exponent() {
        return right;
    }

    @Override
    public String toString() {
        Node base = left;
        Node exp = right;
        if (exp instanceof Literal) {
            double exp2 = constValue(exp);
            if (exp2 < 0) {
                String after = exp.equals(NEGATIVE_ONE) ? "" : "^" + negative(exp).toString();
                if (base instanceof Sin) {
                    Node sub = ((Sin) base).base();
                    return String.format("csc(%s)%s", sub, after);
                } else if (base instanceof Cos) {
                    Node sub = ((Cos) base).base();
                    return String.format("sec(%s)%s", sub, after);
                } else if (base instanceof Tan) {
                    Node sub = ((Tan) base).base();
                    return String.format("cot(%s)%s", sub, after);
                }
                return String.format("1/(%s)%s", base, after);
            }
        }

        String fs = needsParens(base()) ? "(" + base() + ")" : base().toString();
        String gs = needsParens(exponent()) ? "(" + exponent() + ")" : exponent().toString();
        return String.format("%s^%s", fs, gs);
    }

    private boolean needsParens(Node f) {
        return f instanceof Sum || f instanceof Product;
    }

    @Override
    protected Node diff2() {
        Node pt1 = product(this, left.diff(), ln(right));
        Node pt2 = product(this, left, right.diff(), inverse(right));

        return sum(pt1, pt2);
    }

    @Override
    public void evaluate(Collector collector) {
        base().evaluate(collector);
        double base = collector.getData();
        exponent().evaluate(collector);
        double exponent = collector.getData();

        collector.setData(apply(base, exponent));
    }
}
