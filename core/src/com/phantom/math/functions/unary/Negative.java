package com.phantom.math.functions.unary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Node;

import static com.phantom.math.handler.Handler.constValue;
import static com.phantom.math.handler.Handler.literal;

public class Negative extends Unary{
    public static Node makeNegative(Node function) {
        if (function instanceof Negative)
            return ((Negative) function).function;
        if (function instanceof Literal)
            return literal(apply(constValue(function)));

        return new Negative(function);
    }

    public Negative(Node function) {
        super(function);
    }

    private static double apply(double x) {
        return -x;
    }

    @Override
    public String toString (){
        return String.format("-(%s)", function);
    }

    @Override
    protected Node diff2() {
        return makeNegative(function.diff());
    }

    @Override
    public void evaluate(Collector collector) {
        function.evaluate(collector);
        collector.setData(apply(collector.getData()));
    }
}
