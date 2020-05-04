package com.phantom.math.functions;

import com.phantom.math.handler.Collector;

public interface Node extends Comparable<Node> {
    @Override
    int compareTo(Node otherNode);
    @Override
    boolean equals(Object other);
    @Override
    int hashCode();

    void evaluate(Collector collector);
    Node diff();
}
