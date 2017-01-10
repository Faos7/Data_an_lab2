package com.data_an_lab2.misc;

/**
 * Created by faos7 on 08.01.17.
 */
public class DataClass {


    private Double data;
    private int frequency;

    public DataClass(Double data, int frequency) {
        this.data = data;
        this.frequency = frequency;
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
}
