package com.phantom.math.handler;

import com.phantom.math.functions.Node;
import com.phantom.math.functions.binary.Exponentiation;
import com.phantom.math.functions.binary.Product;
import com.phantom.math.functions.binary.Sum;
import com.phantom.math.functions.unary.Literal;
import com.phantom.math.functions.unary.NaturalLog;
import com.phantom.math.functions.unary.Negative;
import com.phantom.math.functions.unary.Variable;
import com.phantom.math.functions.unary.trigonometrics.Cos;
import com.phantom.math.functions.unary.trigonometrics.Sin;
import com.phantom.math.functions.unary.trigonometrics.Tan;

import java.util.Arrays;
import java.util.List;

public class Handler {
    public static final Node E = literal(Math.E);
    public static final Node PI = literal(Math.PI);
    public static final Node ZERO = literal(0.0);
    public static final Node ONE = literal(1.0);
    public static final Node NEGATIVE_ONE = literal(-1.0);

    public static Node literal(double x) {
        return Literal.makeLiteral(x);
    }

    public static double constValue(Node f) {
        Literal f2 = (Literal) f;
        return f2.getValue();
    }

    public static Node sum(Node... nodes) {
        return Sum.makeSum(Arrays.asList(nodes));
    }

    public static Node sum(List<Node> nodes) {
        return Sum.makeSum(nodes);
    }

    public static Node difference(Node f, Node g) {
        return sum(f, negative(g));
    }

    public static Node negative(Node f) {
        return Negative.makeNegative(f);
    }

    public static Node product(Node... nodes) {
        return Product.makeProduct(Arrays.asList(nodes));
    }

    public static Node quotient(Node f, Node g) {
        return product(f, inverse(g));
    }

    public static Node inverse(Node f) {
        return Exponentiation.makeExponentiation(f, NEGATIVE_ONE);
    }

    public static Node power(Node base, Node exp) {
        return Exponentiation.makeExponentiation(base, exp);
    }

    public static Node power(Node base, double exp) {
        return power(base, literal(exp));
    }

    public static Node ln(Node f) {
        return NaturalLog.makeLn(f);
    }

    public static Node var(String fictitious) {
        return Variable.makeVariable(fictitious);
    }

    public static Node sin(Node f) {
        return Sin.create(f);
    }

    public static Node cos(Node f) {
        return Cos.create(f);
    }

    public static Node tan(Node f) {
        return Tan.create(f);
    }

    public static Node sec(Node f) {
        return inverse(cos(f));
    }

    public static Node csc(Node f) {
        return inverse(sin(f));
    }
}
