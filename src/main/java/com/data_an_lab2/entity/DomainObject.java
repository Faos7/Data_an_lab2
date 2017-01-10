package com.data_an_lab2.entity;

import com.data_an_lab2.misc.ClassesOut;
import com.data_an_lab2.misc.DataCalculator;
import com.data_an_lab2.misc.VarOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by faos7 on 09.01.17.
 */
@Entity
@Table(name = "domain")
public class DomainObject  implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainObject.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private  Long id;

    @Column(name = "data")
    private Double[] data;

    @Column(name = "data_impr")
    private Double[] dataImpr;

    @Column(name = "frequency")
    int[] frequency;

    @Column(name = "relative_freq")
    double[] relativeFrequency;

    @Column
    private double avarageVal;

    @Column
    private double rMsVal;

    @Column
    private double rMsAvarageVal;

    @Column
    private double rMsRMSVal;

    @Column
    private double medianVal;

    @Column
    private double coefOfAsymethriaVal;

    @Column
    private double rMsCoefOfAsymethriaVal;

    @Column
    private double coefExcessVal;

    @Column
    private double rMsCoefExcessVal;

    @Column
    private double coefContrExcessVal;

    @Column
    private double rMsCoefContrExcessVal;

    @Column
    private double pearsonCoefVariationVal;

    @Column
    private double rMsPearsonCoefVariationVal;

    @Column
    private int[] classesFrequency;

    @Column
    private double [] classesRelativeFrequency;

    @Column
    private double [] classesEmperFrequency;

    @Column
    private double [][] classesLimits;

    @Column
    private double[] emperFrequency;

    @Column
    private double aEvaulation;

    @Column
    private double bEvaulation;

    @Column
    private double m1Evaulation;

    @Column
    private double rMsAEvaulation;

    @Column
    private double rMsBEvaulation;

    @Column
    private double rMsM1Evaulation;

    @Column
    private String aInterval;

    @Column
    private String bInterval;

    @Column
    private String m1Interval;

    @Column
    private double alpha;

    @Column
    private String isReliable;

    @Column
    private Double kz;

    @Column
    private Double p;

    @Column
    private String avarageIntrval;

    @Column
    private String rMsMedian;

    @Column
    private String medianInterval;

    @Column
    private String rMsInterval;

    @Column
    private String coefOfAsymethrioaInterval;

    @Column
    private String coefExcessInterval;

    @Column
    private String coefContrExcessInterval;

    @Column
    private String pearsonInterval;

    @Column
    private ClassesOut[] classesOuts;

    @Column
    private VarOut[] varOuts;

    public DomainObject(DataCalculator calculator) {



        this.data = calculator.getData().toArray(new Double[calculator.getData().size()]);
        this.dataImpr = calculator.getDataImpr();
        this.frequency = calculator.getFrequency();
        this.relativeFrequency = calculator.getRelativeFrequency();
        this.emperFrequency = calculator.getEmperFrequency();

        this.varOuts = new VarOut[calculator.getDataImprSize()];
        for (int i = 0; i < calculator.getDataImprSize(); i++){
            varOuts[i] = new VarOut(i+1, this.getDataImpr()[i], this.getFrequency()[i],
                    this.getRelativeFrequency()[i], this.getEmperFrequency()[i]);
        }

        this.classesOuts = new ClassesOut[calculator.getClasses().length];
        for (int i = 0; i < calculator.getClasses().length; i++){
            classesOuts[i] = new ClassesOut(calculator.getClassesLimits()[0][i],
                    calculator.getClassesLimits()[1][i], calculator.getClassesFrequency()[i],
                    calculator.getClassesRelativeFrequency()[i],
                    calculator.getClassesEmperFrequency()[i]
                    );
        }
        /*
        this.classesFrequency = calculator.getClassesFrequency();
        this.classesRelativeFrequency = calculator.getClassesRelativeFrequency();
        this.classesEmperFrequency = calculator.getClassesEmperFrequency();
        this.classesLimits = calculator.getClassesLimits();
         */
        this.avarageVal = calculator.getAvarageVal();
        this.rMsAvarageVal = calculator.getrMsAvarageVal();
        this.avarageIntrval = "[" + (avarageVal-rMsAvarageVal) + ", " +  (avarageVal+rMsAvarageVal) + "]";
        this.medianVal = calculator.getMedianVal();
        this.rMsMedian = "-";
        this.medianInterval = "-";
        this.rMsVal = calculator.getrMsVal();
        this.rMsRMSVal = calculator.getrMsRMSVal();
        this.rMsInterval = "[" + (rMsVal-rMsRMSVal) + ", " + (rMsVal+rMsRMSVal) + "]";
        this.coefOfAsymethriaVal = calculator.getCoefOfAsymethriaVal();
        this.rMsCoefOfAsymethriaVal = calculator.getrMsCoefOfAsymethriaVal();
        this.coefOfAsymethrioaInterval = "[" + (coefOfAsymethriaVal-rMsCoefOfAsymethriaVal) + ", " + (coefOfAsymethriaVal+rMsCoefOfAsymethriaVal) + "]";
        this.coefExcessVal= calculator.getCoefExcessVal();
        this.rMsCoefExcessVal = calculator.getrMsCoefExcessVal();
        this.coefExcessInterval = "[" + (coefExcessVal-rMsCoefExcessVal) + ", " + (coefExcessVal + rMsCoefExcessVal) + "]";
        this.coefContrExcessVal = calculator.getCoefContrExcessVal();
        this.rMsCoefContrExcessVal = calculator.getrMsCoefContrExcessVal();
        this.coefContrExcessInterval = "[" + (coefContrExcessVal-rMsCoefContrExcessVal) + ", " + (coefContrExcessVal+rMsCoefContrExcessVal) +"]";
        this.pearsonCoefVariationVal = calculator.getPearsonCoefVariationVal();
        this.rMsPearsonCoefVariationVal = calculator.getrMsPearsonCoefVariationVal();
        this.pearsonInterval = "[" + (pearsonCoefVariationVal-rMsPearsonCoefVariationVal) + ", " + (pearsonCoefVariationVal+rMsPearsonCoefVariationVal) + "]";


        this.aEvaulation = calculator.getaEvaulation();
        this.bEvaulation = calculator.getbEvaulation();
        this.m1Evaulation = calculator.getM1Evaulation();
        this.rMsAEvaulation = calculator.getrMsAEvaulation();
        this.rMsBEvaulation = calculator.getrMsBEvaulation();
        this.rMsM1Evaulation = calculator.getrMsM1Evaulation();

        this.aInterval = "[" + calculator.getaInterval()[0] + ", " + calculator.getaInterval()[1] + "]";
        this.bInterval = "[" + calculator.getbInterval()[0] + ", " + calculator.getbInterval()[1] + "]";
        this.m1Interval = "[" + calculator.getM1Interval()[0] + ", " + calculator.getM1Interval()[1] + "]";

        this.kz = calculator.getKz();
        this.p = calculator.getP();
        this.alpha = 0.05;
        if (calculator.isReliable()){
            this.isReliable = "Достоверное";
        }else {
            this.isReliable = "Недостоверное";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }

    public Double[] getDataImpr() {
        return dataImpr;
    }

    public void setDataImpr(Double[] dataImpr) {
        this.dataImpr = dataImpr;
    }

    public int[] getFrequency() {
        return frequency;
    }

    public void setFrequency(int[] frequency) {
        this.frequency = frequency;
    }

    public double[] getRelativeFrequency() {
        return relativeFrequency;
    }

    public void setRelativeFrequency(double[] relativeFrequency) {
        this.relativeFrequency = relativeFrequency;
    }

    public double getAvarageVal() {
        return avarageVal;
    }

    public void setAvarageVal(double avarageVal) {
        this.avarageVal = avarageVal;
    }

    public double getrMsVal() {
        return rMsVal;
    }

    public void setrMsVal(double rMsVal) {
        this.rMsVal = rMsVal;
    }

    public double getrMsAvarageVal() {
        return rMsAvarageVal;
    }

    public void setrMsAvarageVal(double rMsAvarageVal) {
        this.rMsAvarageVal = rMsAvarageVal;
    }

    public double getrMsRMSVal() {
        return rMsRMSVal;
    }

    public void setrMsRMSVal(double rMsRMSVal) {
        this.rMsRMSVal = rMsRMSVal;
    }

    public double getMedianVal() {
        return medianVal;
    }

    public void setMedianVal(double medianVal) {
        this.medianVal = medianVal;
    }

    public double getCoefOfAsymethriaVal() {
        return coefOfAsymethriaVal;
    }

    public void setCoefOfAsymethriaVal(double coefOfAsymethriaVal) {
        this.coefOfAsymethriaVal = coefOfAsymethriaVal;
    }

    public double getrMsCoefOfAsymethriaVal() {
        return rMsCoefOfAsymethriaVal;
    }

    public void setrMsCoefOfAsymethriaVal(double rMsCoefOfAsymethriaVal) {
        this.rMsCoefOfAsymethriaVal = rMsCoefOfAsymethriaVal;
    }

    public double getCoefExcessVal() {
        return coefExcessVal;
    }

    public void setCoefExcessVal(double coefExcessVal) {
        this.coefExcessVal = coefExcessVal;
    }

    public double getrMsCoefExcessVal() {
        return rMsCoefExcessVal;
    }

    public void setrMsCoefExcessVal(double rMsCoefExcessVal) {
        this.rMsCoefExcessVal = rMsCoefExcessVal;
    }

    public double getCoefContrExcessVal() {
        return coefContrExcessVal;
    }

    public void setCoefContrExcessVal(double coefContrExcessVal) {
        this.coefContrExcessVal = coefContrExcessVal;
    }

    public double getrMsCoefContrExcessVal() {
        return rMsCoefContrExcessVal;
    }

    public void setrMsCoefContrExcessVal(double rMsCoefContrExcessVal) {
        this.rMsCoefContrExcessVal = rMsCoefContrExcessVal;
    }

    public double getPearsonCoefVariationVal() {
        return pearsonCoefVariationVal;
    }

    public void setPearsonCoefVariationVal(double pearsonCoefVariationVal) {
        this.pearsonCoefVariationVal = pearsonCoefVariationVal;
    }

    public double getrMsPearsonCoefVariationVal() {
        return rMsPearsonCoefVariationVal;
    }

    public void setrMsPearsonCoefVariationVal(double rMsPearsonCoefVariationVal) {
        this.rMsPearsonCoefVariationVal = rMsPearsonCoefVariationVal;
    }

    public int[] getClassesFrequency() {
        return classesFrequency;
    }

    public void setClassesFrequency(int[] classesFrequency) {
        this.classesFrequency = classesFrequency;
    }

    public double[] getClassesRelativeFrequency() {
        return classesRelativeFrequency;
    }

    public void setClassesRelativeFrequency(double[] classesRelativeFrequency) {
        this.classesRelativeFrequency = classesRelativeFrequency;
    }

    public double[] getClassesEmperFrequency() {
        return classesEmperFrequency;
    }

    public void setClassesEmperFrequency(double[] classesEmperFrequency) {
        this.classesEmperFrequency = classesEmperFrequency;
    }

    public double[][] getClassesLimits() {
        return classesLimits;
    }

    public void setClassesLimits(double[][] classesLimits) {
        this.classesLimits = classesLimits;
    }

    public double[] getEmperFrequency() {
        return emperFrequency;
    }

    public void setEmperFrequency(double[] emperFrequency) {
        this.emperFrequency = emperFrequency;
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

    public String getaInterval() {
        return aInterval;
    }

    public void setaInterval(String aInterval) {
        this.aInterval = aInterval;
    }

    public String getbInterval() {
        return bInterval;
    }

    public void setbInterval(String bInterval) {
        this.bInterval = bInterval;
    }

    public String getM1Interval() {
        return m1Interval;
    }

    public void setM1Interval(String m1Interval) {
        this.m1Interval = m1Interval;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public String getIsReliable() {
        return isReliable;
    }

    public void setIsReliable(String isReliable) {
        this.isReliable = isReliable;
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

    public String getAvarageIntrval() {
        return avarageIntrval;
    }

    public void setAvarageIntrval(String avarageIntrval) {
        this.avarageIntrval = avarageIntrval;
    }

    public String getrMsMedian() {
        return rMsMedian;
    }

    public void setrMsMedian(String rMsMedian) {
        this.rMsMedian = rMsMedian;
    }

    public String getMedianInterval() {
        return medianInterval;
    }

    public void setMedianInterval(String medianInterval) {
        this.medianInterval = medianInterval;
    }

    public String getrMsInterval() {
        return rMsInterval;
    }

    public void setrMsInterval(String rMsInterval) {
        this.rMsInterval = rMsInterval;
    }

    public String getCoefOfAsymethrioaInterval() {
        return coefOfAsymethrioaInterval;
    }

    public void setCoefOfAsymethrioaInterval(String coefOfAsymethrioaInterval) {
        this.coefOfAsymethrioaInterval = coefOfAsymethrioaInterval;
    }

    public String getCoefExcessInterval() {
        return coefExcessInterval;
    }

    public void setCoefExcessInterval(String coefExcessInterval) {
        this.coefExcessInterval = coefExcessInterval;
    }

    public String getCoefContrExcessInterval() {
        return coefContrExcessInterval;
    }

    public void setCoefContrExcessInterval(String coefContrExcessInterval) {
        this.coefContrExcessInterval = coefContrExcessInterval;
    }

    public String getPearsonInterval() {
        return pearsonInterval;
    }

    public void setPearsonInterval(String pearsonInterval) {
        this.pearsonInterval = pearsonInterval;
    }

    public ClassesOut[] getClassesOuts() {
        return classesOuts;
    }

    public void setClassesOuts(ClassesOut[] classesOuts) {
        this.classesOuts = classesOuts;
    }

    public VarOut[] getVarOuts() {
        return varOuts;
    }

    public void setVarOuts(VarOut[] varOuts) {
        this.varOuts = varOuts;
    }
}
