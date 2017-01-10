package com.data_an_lab2.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by faos7 on 08.01.17.
 */
public class ClassA {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassA.class);


    private int iterator;
    private int n;
    private DataClass[] data;

    public ClassA(int n) {

        iterator = -1;
        this.n = n;
        this.data = new DataClass[n];
    }

    public ClassA(int n, DataClass[] data) {

        this.n = n;
        this.data = data;
    }


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public DataClass[] getData() {
        return data;
    }

    public DataClass getData(int i){
        if (i > n || i < 0)
            return null;
        return data[i];
    }

    public void setData(int i, DataClass tmp){
        data[i] = tmp;
    }

    public void setData(DataClass[] data) {
        this.data = data;
    }

    public void addData(DataClass val){
        iterator++;
        this.data[iterator] = val;

    }

    public int getIterator() {
        return iterator;
    }
}
