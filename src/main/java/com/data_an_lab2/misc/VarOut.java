package com.data_an_lab2.misc;

/**
 * Created by faos7 on 10.01.17.
 */
public class VarOut {
    private int num;
    private Double data;
    private int frequency;
    private double relFreq;
    private double emp;

    public VarOut(int num, Double data, int frequency, double relFreq, double emp) {
        this.num = num;
        this.data = data;
        this.frequency = frequency;
        this.relFreq = relFreq;
        this.emp = emp;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getRelFreq() {
        return relFreq;
    }

    public void setRelFreq(double relFreq) {
        this.relFreq = relFreq;
    }

    public double getEmp() {
        return emp;
    }

    public void setEmp(double emp) {
        this.emp = emp;
    }
}

