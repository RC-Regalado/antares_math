package com.phantom.math.functions.unary.trigonometrics;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;
import com.phantom.math.functions.unary.Literal;
import com.phantom.math.functions.unary.Unary;

import static com.phantom.math.handler.Handler.*;

public class Sin extends Unary {
    public static Node create(Node f) {
        if (f instanceof Literal)
            return literal(apply(constValue(f)));
        return new Sin(f);
    }

    private Sin(Node f) {
        super(f);
    }

    private static double apply(double x) {
        return Math.sin(x);
    }
    @Override
    public String toString() {
        return String.format("sin(%s)", function);
    }

    @Override
    protected Node diff2() {
        return product(cos(function), function.diff());
    }

    @Override
    public void evaluate(Collector collector) {
        double x = collector.getValue();
        collector.setData(apply(x));
    }
}
