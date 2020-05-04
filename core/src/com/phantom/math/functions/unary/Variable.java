package com.phantom.math.functions.unary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Base;
import com.phantom.math.functions.Node;

import static com.phantom.math.handler.Handler.literal;

public class Variable extends Base {
    private final String fictitious;
    private  Variable(String fictitious){
        this.fictitious = fictitious;
    }

    public static Variable makeVariable(String fictitious){
        return new Variable(fictitious);
    }

    @Override
    public String toString() {
        return fictitious;
    }

    @Override
    public int compareTo(Node other) {
        if (getClass() != other.getClass())
            return super.compareTo(other);
        return 0;
    }

    @Override
    public int hashCode() {
        return 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        return getClass() == obj.getClass();
    }


    @Override
    protected Node diff2() {
        return literal(1);
    }

    @Override
    public void evaluate(Collector collector) {
        double x = collector.getValue();
        collector.setData(x);
    }
}
