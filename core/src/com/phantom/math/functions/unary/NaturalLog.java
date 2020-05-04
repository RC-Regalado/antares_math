package com.phantom.math.functions.unary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;

import static com.phantom.math.handler.Handler.*;

public class NaturalLog extends Unary{

    public static Node makeLn(Node f) {
        if (f instanceof Literal)
            return literal(apply(constValue(f)));
        return new NaturalLog(f);
    }

    private static double apply(double x) {
        if (x <= 0.0)
            throw new ArithmeticException("You can't take ln of x <= 0: " + x);
        return Math.log(x);
    }

    private NaturalLog(Node f) {
        super(f);
    }

    @Override
    public String toString() {
        return String.format("ln(%s)", function);
    }

    @Override
    protected Node diff2() {
        return quotient(function.diff(), function);
    }

    @Override
    public void evaluate(Collector collector) {
        function.evaluate(collector);
        collector.setData(apply(collector.getData()));
    }

}
