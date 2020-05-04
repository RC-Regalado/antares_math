package com.phantom.math.functions.unary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;

import static com.phantom.math.handler.Handler.*;

public class Inverse extends Unary{
    public static Node create(Node f) {
        if (f instanceof Inverse)
            return ((Inverse) f).function;

        if (f instanceof Literal)
            return literal(apply(constValue(f)));
        return new Inverse(f);
    }

    private Inverse(Node f) {
        super(f);
    }

    private static double apply(double x) {
        if (x == 0)
            throw new ArithmeticException("Division by 0");
        return 1 / x;
    }

    public String toString() {
        return String.format("1/(%s)", function);
    }

    @Override
    protected Node diff2() {
        Node thisD = negative(product(this, this));
        return product(thisD, function.diff());
    }

    @Override
    public void evaluate(Collector collector) {
        function.evaluate(collector);
        collector.setData(apply(collector.getData()));
    }
}
