package com.data_an_lab2.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by faos7 on 09.01.17.
 */
public class Kolmogorov {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassA.class);

    private double a  =10, b =20;
    private double alpha = 0.05;
    private boolean isReliable;

    private Double[] trustInterval;

    private Double[] data;
    private double [] frequency;
    private Double[] Fk;
    private Double[] Fk1;
    private Double Nstatistics;
    private Double z;
    private Double kz;
    private Double p;

    public Kolmogorov(Double[] data, double[] frequency) {

        this.data = data;
        this.frequency = frequency;
        DnPlus();
        DnMinus();
        this.Nstatistics = DNstat();
        zStat();
        Kz();
        reliable();

    }
/*
    private void interval(){
        trustInterval = new Double[2];
        trustInterval[1] =
    }
*/
    private void reliable(){
        p = 1 - kz;
        if (p < alpha)
            isReliable = false;
        else
            isReliable = true;
    }

    private void Kz(){
        kz = 1.;
        Double sum = 0.;
        double f1, f2;
        for (int i = 1; i < 6; i ++){
            f1 = i*i - 0.5*(1-Math.pow(-1, i));
            f2 = 5*i*i + 22 - 7.5*(1-Math.pow(-1,i));
            sum += Math.pow(-1, i) * Math.exp(-2*i*i*z*z)/* *
                    (1- ((2*i*i*z)/(3*Math.sqrt(data.length))) -
                    (1/(18*data.length)) * ((f1 - 4*(f1+3)) *i*i*z*z + 8*Math.pow(i,4)*Math.pow(z,4)) +
                    (i*i*z/(27*Math.sqrt(data.length))) *
                    ((f2*f2/5) - ((4*(f2 + 45)*i*i*z*z)/15) + 8*Math.pow(i,4)*Math.pow(z,4)))*/
                    /* + O(Math.pow(z, 13)/Math.pow(data/length, 2))*/;
        }
        LOGGER.debug(sum + "");
        kz += 2 * sum;
    }

    //вычисляем статистику z
    public void zStat(){
        z = (double) Math.sqrt(data.length) * Nstatistics;
    }

    //вычисляем Dn (статистика)
    public Double DNstat(){
        Double tmp1 = Fk[0];
        Double tmp2 = Fk1[0];

        for (int i = 0; i< data.length; i ++){
            if (Fk[i] > tmp1)
                tmp1 = Fk[i];
        }
        for (int i = 0; i < Fk1.length; i++){
            if (Fk1[i] > tmp2)
                tmp2 = Fk1[i];

        }
        if (tmp1 > tmp2)
            return tmp1;
        else return tmp2;
    }

    public void DnMinus(){
        Fk1 = new Double[data.length +1];
        Fk1[0] = 0.;
        Fk1[1] = frequency[1];
        for (int i = 2; i < data.length; i ++){
            Fk1[i] = Math.abs(data[i-1] - frequency[i]);
        }
        Fk1[data.length] = Math.abs(1 - data[data.length-1]);

    }

    public void DnPlus(){
        Fk = new Double[data.length];
        for (int i = 0; i < data.length; i++){
            Fk[i] = Math.abs(data[i] - frequency[i]);
        }
    }

    //плотность нормального распределения
    public double F (double x){
        if (x < a){
            return 0;
        } else if (x>=b){
            return 1;
        }else {
            return (double) ((x-a)/(b-a));
        }
    }

/**
 * getters and setters
 */

    public Double[] getTrustInterval() {
        return trustInterval;
    }

    public void setTrustInterval(Double[] trustInterval) {
        this.trustInterval = trustInterval;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }

    public double[] getFrequency() {
        return frequency;
    }

    public void setFrequency(double[] frequency) {
        this.frequency = frequency;
    }

    public Double[] getFk() {
        return Fk;
    }

    public void setFk(Double[] fk) {
        Fk = fk;
    }

    public Double[] getFk1() {
        return Fk1;
    }

    public void setFk1(Double[] fk1) {
        Fk1 = fk1;
    }

    public Double getNstatistics() {
        return Nstatistics;
    }

    public void setNstatistics(Double nstatistics) {
        Nstatistics = nstatistics;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double getKz() {
        return kz;
    }

    public void setKz(Double kz) {
        this.kz = kz;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public boolean isReliable() {
        return isReliable;
    }
}
