package com.phantom.math.handler;

public class Collector {
    public double data;
    public final double value;


    public double getValue() {
        return value;
    }

    public Collector(double value) {
        this.value = value;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
