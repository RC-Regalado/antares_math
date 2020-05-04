package com.phantom.math.functions.unary;

import com.phantom.math.handler.Collector;
import com.phantom.math.functions.Base;
import com.phantom.math.functions.Node;

import java.util.Objects;

public class Literal extends Base {

    public static Literal makeLiteral(double x) {
        return new Literal(x);
    }

    private final double value;

    public double getValue() {
        return value;
    }

    public Literal(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node other) {
        if (getClass() != other.getClass())
            return super.compareTo(other);
        Literal o = (Literal) other;
        return Double.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        String res = String.format("%.2f", value);
        double distFromInt = Math.abs(value - Math.rint(value));
        if (distFromInt < 0.0000001) {
            long l = Math.round(value);
            res = String.format("%d", l);
        }
        if (value < 0)
            res = "(" + res + ")";
        return res;
    }
    @Override
    public void evaluate(Collector collector) {
        collector.setData(value);
    }

    @Override
    protected Node diff2() {
        return makeLiteral(0);
    }

    @Override
    public Node diff() {
        return diff2();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Literal literal = (Literal) object;
        return Double.compare(literal.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
