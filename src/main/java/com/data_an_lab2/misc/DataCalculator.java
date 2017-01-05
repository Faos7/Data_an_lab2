package com.data_an_lab2.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by faos7 on 04.01.17.
 */
public class DataCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataCalculator.class);

    private static DataCalculator calculator;

    public static DataCalculator getInstance() {
        if (calculator == null) {
            throw new NullPointerException(
                    "Calculator is not instantiated yet!"
            );
        }

        return calculator;
    }


    public static void initializeInstance(List<Double> data) {
        calculator = new DataCalculator(data);
    }

    public static void reinitializeInstance() {
        calculator = new DataCalculator(
                calculator.data
        );
    }

    private final List<Double> data;
    private Double[] dataImpr;
    private int[] frequency;
    private int dimension;

    public DataCalculator(List<Double> data) {
        this.data = data;
        this.dimension = data.size();
        this.dataImpr = ImproveData(data);
        //frequency должен инициализироватся по ходу инициализации dataImpr

    }

    private void swap(Double a, Double b){
        Double tmp = a;
        a =b;
        b = tmp;
    }

    private Double[] ImproveData(List<Double> currData){
        int j;
        Double[] curr = currData.toArray(new Double[currData.size()]);
        frequency = new int[dimension];
        for (int i = 0; i < dimension - 1; i++){
            frequency[i] = 0;
            if (curr[i]>curr[i+1])
                swap(curr[i], curr[i+1]);
        }
        for (int i = 0; i < dimension; i++){
            j = i+1;
            while (j<curr.length){
                if ((curr[i] == curr[j])&&(curr[i] != -1)){
                    curr[j] = -1.;
                    frequency[i]++;
                }
                j++;
            }
        }
        for (int i = 0; i < dimension; i++){
           if (curr[i]== -1){
               for (int k = i; k < dimension-1; k++){
                   curr[k] = curr[k+1];
                   frequency[k] = frequency[k+1];
               }
               dimension--;
           }
        }
        return curr;
    }


}


