package com.data_an_lab2.misc;

/**
 * Created by faos7 on 09.01.17.
 */
public class Ravn {

    double a  =10, b =20;

    private Double[] givenData;
    private int [] givenFrequency;

    private double aEvaulation;
    private double bEvaulation;
    private double m1Evaulation;

    private double rMsAEvaulation;
    private double rMsBEvaulation;
    private double rMsM1Evaulation;

    private double aInterval[];
    private double bInterval[];
    private double m1Interval[];





    public Ravn(Double[] givenData, int[] givenFrequency) {
        this.givenData = givenData;
        this.givenFrequency = givenFrequency;
        evaulations();
        rmsEvaulations();
        intervals();
    }

    private void intervals(){
        aInterval = new double[2];
        bInterval = new double[2];
        m1Interval = new double[2];
        aInterval[0] = aEvaulation - rMsAEvaulation;
        aInterval[1] = aEvaulation + rMsAEvaulation;
        bInterval[0] = bEvaulation - rMsBEvaulation;
        bInterval[1] = bEvaulation + rMsBEvaulation;
        m1Interval[0] = m1Evaulation - rMsM1Evaulation;
        m1Interval[1] = m1Evaulation + rMsM1Evaulation;
    }

    // среднеквадратическое отклонение параметров
    private void rmsEvaulations(){
        rMsAEvaulation = aEvaulation * Math.sqrt(2-(Math.PI / 2));
        rMsBEvaulation = bEvaulation * Math.sqrt(2-(Math.PI / 2));
        rMsM1Evaulation = m1Evaulation * Math.sqrt(2-(Math.PI / 2));
    }

    // оценки параметров a, b, m1=(a+b)/2
    private void evaulations(){
        aEvaulation = givenData[0];
        bEvaulation = givenData[givenData.length -1];

        m1Evaulation = 1./givenData.length;
        Double sum=0.;

        for (int i = 0; i < givenData.length; i++){
            for (int j = 0; j < givenFrequency[i]; j++){
                sum += givenData[i];
            }
        }

        m1Evaulation *=sum;
    }

    //функция нормального распределения
    public double F (double x){
        if (x < a){
            return 0;
        } else if (x>=b){
            return 1;
        }else {
            return (double) ((x-a)/(b-a));
        }
    }

    //плотность нормального распределения
    public double f (double x){
        if (x < a || x > b)
            return 0;
        else return (double) 1./(b - a);
    }

    /**
     * getters and setters
     */

    public Double[] getGivenData() {
        return givenData;
    }

    public void setGivenData(Double[] givenData) {
        this.givenData = givenData;
    }

    public int[] getGivenFrequency() {
        return givenFrequency;
    }

    public void setGivenFrequency(int[] givenFrequency) {
        this.givenFrequency = givenFrequency;
    }

    public double getaEvaulation() {
        return aEvaulation;
    }

    public void setaEvaulation(double aEvaulation) {
        this.aEvaulation = aEvaulation;
    }

    public double getbEvaulation() {
        return bEvaulation;
    }

    public void setbEvaulation(double bEvaulation) {
        this.bEvaulation = bEvaulation;
    }

    public double getM1Evaulation() {
        return m1Evaulation;
    }

    public void setM1Evaulation(double m1Evaulation) {
        this.m1Evaulation = m1Evaulation;
    }

    public double getrMsAEvaulation() {
        return rMsAEvaulation;
    }

    public void setrMsAEvaulation(double rMsAEvaulation) {
        this.rMsAEvaulation = rMsAEvaulation;
    }

    public double getrMsBEvaulation() {
        return rMsBEvaulation;
    }

    public void setrMsBEvaulation(double rMsBEvaulation) {
        this.rMsBEvaulation = rMsBEvaulation;
    }

    public double getrMsM1Evaulation() {
        return rMsM1Evaulation;
    }

    public void setrMsM1Evaulation(double rMsM1Evaulation) {
        this.rMsM1Evaulation = rMsM1Evaulation;
    }

    public double[] getaInterval() {
        return aInterval;
    }

    public void setaInterval(double[] aInterval) {
        this.aInterval = aInterval;
    }

    public double[] getbInterval() {
        return bInterval;
    }

    public void setbInterval(double[] bInterval) {
        this.bInterval = bInterval;
    }

    public double[] getM1Interval() {
        return m1Interval;
    }

    public void setM1Interval(double[] m1Interval) {
        this.m1Interval = m1Interval;
    }
}
