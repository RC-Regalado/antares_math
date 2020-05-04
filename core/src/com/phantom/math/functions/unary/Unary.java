package com.phantom.math.functions.unary;

import com.phantom.math.functions.Base;
import com.phantom.math.functions.Node;

public abstract class Unary extends Base {
    protected final Node function;

    public Unary(Node function) {
        this.function = function;
    }

    public Node base(){
        return function;
    }

    @Override
    public int compareTo(Node other){
        if (getClass() != other.getClass())
            return super.compareTo(other);
        Unary o = (Unary) other;
        return function.compareTo(o.function);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((function == null) ? 0 : function.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object){
        if ( this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        Unary other = (Unary) object;

        if (function == null)
            return other.function == null;
        else
            return function.equals(other.function);
    }
}
