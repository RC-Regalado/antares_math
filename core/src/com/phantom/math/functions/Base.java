package com.phantom.math.functions;

import com.phantom.math.handler.Comparator;

public abstract class Base implements Node {
    @Override
    public int compareTo(Node other){
        if (this.getClass() == other.getClass()) {
            String str = String.format(
                    "Should be calling other method; this is \"%s\" of class %s and other is \"%s\" of class %s", this,
                    this.getClass().toString(), other, other.getClass().toString());
            throw new RuntimeException(str);
        }
        return Comparator.funcCompare(this, other);
    }

    protected abstract Node diff2();
    protected Node derivative = null;

    @Override
    public Node diff() {
        if (derivative == null)
            derivative = diff2();

        return derivative;
    }
}
