package com.data_an_lab2.misc;

/**
 * Created by faos7 on 10.01.17.
 */
public class ClassesOut {
    private String limits;
    private String frequency;
    private String relFreq;
    private String emp;

    public ClassesOut(double upLimit, double lowLimit, int frequency, double relFreq, double emp) {
        this.limits = "[" + upLimit + ", " + lowLimit + "]";
        this.frequency = frequency + "";
        this.relFreq = relFreq + "";
        this.emp = emp + "";
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRelFreq() {
        return relFreq;
    }

    public void setRelFreq(String relFreq) {
        this.relFreq = relFreq;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }
}
