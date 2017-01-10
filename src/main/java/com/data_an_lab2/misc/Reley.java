package com.data_an_lab2.misc;

/**
 * Created by faos7 on 08.01.17.
 */
public class Reley {

    private Double[] givenData;
    private double [] givenFrequency;
    private double sigma;
    private Double [] dataT;
    private double [] dataZ;

    public Reley(Double[] givenData, double[] givenFrequency) {
        this.givenData = givenData;
        this.givenFrequency = givenFrequency;
        buildData();
    }

    private double z (double x){
        return Math.log(Math.log( 1 / (1 - x)));
    }

    private double t (double x){
        return Math.log(x);
    }

    private void buildData(){
        for (int i = 0; i < givenData.length; i++){
            dataT[i] = t(givenData[i]);
            dataZ[i] = z(givenFrequency[i]);
        }
    }

}
