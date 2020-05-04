package com.phantom.math.handler;

import com.phantom.math.functions.Node;
import com.phantom.math.functions.binary.Exponentiation;
import com.phantom.math.functions.binary.Product;
import com.phantom.math.functions.binary.Sum;
import com.phantom.math.functions.unary.*;
import com.phantom.math.functions.unary.trigonometrics.Cos;
import com.phantom.math.functions.unary.trigonometrics.Sin;
import com.phantom.math.functions.unary.trigonometrics.Tan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comparator{
    private static final Map<Class<? extends Node>, Integer> classOrder;

    static {
        List<Class<? extends Node>> list = new ArrayList<>();
        list.add(Literal.class);
        list.add(Variable.class);
        list.add(Exponentiation.class);
        list.add(NaturalLog.class);
        list.add(Inverse.class);
        list.add(Product.class);
        list.add(Negative.class);
        list.add(Sum.class);
        list.add(Sin.class);
        list.add(Cos.class);
        list.add(Tan.class);

        classOrder = new HashMap<>();
        for (int i = 0; i < list.size(); i++)
            classOrder.put(list.get(i), i);
    }

    public static int funcCompare(Node a, Node b) {
        if (a.getClass() == b.getClass())
            throw new RuntimeException("Should be calling other method");
        int ao = classOrder.get(a.getClass());
        int bo = classOrder.get(b.getClass());
        return Integer.compare(ao, bo);
    }
}
