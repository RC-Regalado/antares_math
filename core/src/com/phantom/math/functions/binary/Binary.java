package com.phantom.math.functions.binary;

import com.phantom.math.functions.Base;
import com.phantom.math.functions.Node;

import static com.phantom.math.handler.Handler.negative;

public abstract class Binary extends Base {
    protected final Node left;
    protected final Node right;

    public static Node make(String op, Node left, Node right){


        switch (op){
            case "*": return Product.makeProduct(left, right);
            case "+": return Sum.makeSum(left, right);
            case "**": return Exponentiation.makeExponentiation(right, left);
            case "-": return Sum.makeSum(left, negative(right));
        }

        throw new RuntimeException();
    }

    protected Binary(Node right, Node left) {
        this.right = right;
        this.left = left;

    }

    @Override
    public int compareTo(Node other) {
        if (getClass() != other.getClass())
            return super.compareTo(other);

        Binary _other = (Binary) other;

        int ret = left.compareTo(_other.left);
        if (ret != 0)
            return ret;

        return _other.right.compareTo(right);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        Binary other = (Binary) object;

        if (right == null)
            return other.right == null;
        else if (!right.equals(other.right))
            return false;

        if (left == null)
            return other.left == null;
        else
            return left.equals(other.left);
    }


}
