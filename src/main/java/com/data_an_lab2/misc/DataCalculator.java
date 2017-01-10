package com.data_an_lab2.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
    int[] frequency;
    double[] relativeFrequency;
    private int dimension;
    private int dataImprSize;
    private double avarageVal;
    private double rMsVal;
    private double rMsAvarageVal;
    private double rMsRMSVal;
    private Double medianVal;
    private double sigmaVal;
    private double coefOfAsymethriaVal;
    private double rMsCoefOfAsymethriaVal;
    private double excessMovedVal;
    private double coefExcessVal;
    private double rMsCoefExcessVal;
    private double coefContrExcessVal;
    private double rMsCoefContrExcessVal;
    private double pearsonCoefVariationVal;
    private double rMsPearsonCoefVariationVal;
    private double Up;

    private Double[] dataReley;
    private double [] frequencyReley;

    private ClassA[] classes;
    private int[] classesFrequency;
    private double [] classesRelativeFrequency;
    private double [] classesEmperFrequency;
    private double [][] classesLimits;
    private int classesSize;

    private double[] emperFrequency;

    private double aEvaulation;
    private double bEvaulation;
    private double m1Evaulation;

    private double rMsAEvaulation;
    private double rMsBEvaulation;
    private double rMsM1Evaulation;

    private double aInterval[];
    private double bInterval[];
    private double m1Interval[];

    private double alpha = 0.05;
    private boolean isReliable;
    private Double kz;
    private Double p;

    public DataCalculator(List<Double> data) {

        /**
         * вариационный ряд начало
         */


        this.data = data;
        this.dimension = data.size();
        this.dataImprSize = dimension;
        this.dataImpr = ImproveData(data);
        //frequency должен инициализироватся по ходу инициализации dataImpr
        // dataImprSize должен соответствовать размеру dataImpr
        relativeFrequencyCalc();
        this.emperFrequency = emperFreq();
        /**
         * вариационный ряд конец
         */

        /**
         * характеристики выборки начало
         */


        this.sigmaVal = sigma();
        this.excessMovedVal = excessMoved();

        //значения
        this.avarageVal = avarage(dataImpr);
        this.medianVal = median();
        this.rMsVal = rMs(dataImpr);
        this.coefOfAsymethriaVal = coefOfAsymethria();
        this.coefExcessVal = coefExcess();
        this.coefContrExcessVal = coefContrExcess();
        this.pearsonCoefVariationVal = pearsonCoefVariation();
        //их отклонения
        this.rMsAvarageVal = rMsAvarage();
        this.rMsRMSVal = rMsRMS();
        this.rMsCoefOfAsymethriaVal = rMsCoefOfAsymethria();
        this.rMsCoefExcessVal = rMsCoefExcess();
        this.rMsCoefContrExcessVal = rMsCoefContrExcess();
        this.rMsPearsonCoefVariationVal = rMsPearsonCoefVariation();

        /**
         * характеристики выборки конец
         */


        /**
         * Классы вариационного ряда начало
         */
        buildClasses();
        /**
         * Классы вариационного ряда конец
         */

        /**
         * равномерное распределение. начало
         */


        buildDataForReley();
        Ravn ravn = new Ravn(dataReley, frequency);

        this.aEvaulation = ravn.getaEvaulation();
        this.bEvaulation = ravn.getbEvaulation();
        this.m1Evaulation = ravn.getM1Evaulation();

        this.rMsAEvaulation = ravn.getrMsAEvaulation();
        this.rMsBEvaulation = ravn.getrMsBEvaulation();
        this.rMsM1Evaulation = ravn.getrMsM1Evaulation();

        this.aInterval = ravn.getaInterval();
        this.bInterval = ravn.getbInterval();
        this.m1Interval = ravn.getM1Interval();

        /**
         * равномерное распределение. конец
         */

        /**
         * Колмогоров. начало
         */


        Kolmogorov kolmogorov = new Kolmogorov(dataReley, frequencyReley);

        this.p = kolmogorov.getP();
        this.kz = kolmogorov.getKz();
        this.isReliable =  kolmogorov.isReliable();

        /**
         * Колмогоров. конец
         */
        this.Up = U();

        LOGGER.debug("calculator sucess");

    }

    private void swap(Double a, Double b){
        Double tmp = a;
        a =b;
        b = tmp;
    }

    // перестраиваем исходную выборку в вариационный ряд вида data[варианта] frequency[частота варианты]
    private Double[] ImproveData(List<Double> currData){
        int j;
        Double[] curr = currData.toArray(new Double[currData.size()]);
        frequency = new int[dimension];
        for (int i = 0; i < dimension - 1; i++){
            frequency[i] = 1;
            if (curr[i]>curr[i+1])
                swap(curr[i], curr[i+1]);
        }
        frequency[dimension-1] = 1;
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
               dataImprSize--;
           }
        }
        return curr;
    }

    // среднее арифметическое
    private Double avarage(Double[] currData){
        Double result = 0.;
        for (int i = 0; i < dataImprSize; i++){
            result += currData[i] * frequency[i];
        }
        return result/dimension;
    }

    // среднеквадратическое
    private double rMs(Double[] currData){
        double result = 0;
        double avarage = avarageVal;
        for (int i = 0; i < dataImprSize; i++){
            result += Math.pow(currData[i] - avarage, 2) * frequency[i];
        }
        return Math.sqrt(result/(dimension-1));
    }

    // среднеквадратическое отклонеие среднего арифметического
    private double rMsAvarage(){
        return rMsVal/Math.sqrt(dimension);
    }

    // среднеквадратическое отклонение среднеквадратического
    private double rMsRMS(){
        return rMsVal/Math.sqrt(2 * dimension);
    }

    // медиана
    private Double median(){
        List<Double> tmpDatList = new ArrayList<>();
        for (int i = 0; i < dataImprSize; i++){
            for (int j =0; j < frequency[i]+1; j++){
                tmpDatList.add(dataImpr[i]);
            }
        }
        Double[] tmpDat = tmpDatList.toArray(new Double[tmpDatList.size()]);
        int tmp = tmpDat.length/2;

        if (dimension % 2 == 1){
            return tmpDat[tmp];
        }else {
            return (tmpDat[tmp] + tmpDat[tmp+1])/2;
        }
    }

    // среднеквадратичное смещенное отклонение
    private double sigma(){
        double result = 0;
        for (int i = 0; i < dataImprSize; i++){
            result += Math.pow(dataImpr[i],2) * frequency[i] - Math.pow(avarageVal,2);
        }
        result = Math.sqrt(result/dimension);
        return result;
    }

    // коэффициент ассиметрии
    private double coefOfAsymethria(){
        double res = 0;
        for (int i = 0; i < dataImprSize; i++){
            res += Math.pow(dataImpr[i] - avarageVal, 3) * frequency[i];
        }
        res = res/(dimension * Math.pow(sigmaVal, 3));
        res = Math.sqrt(dimension * (dimension-1)) / (dimension-2) * res;
        return res;
    }

    // среднеквадратическое отклонение коэффициента ассиметрии
    private double rMsCoefOfAsymethria(){
        return Math.sqrt((6 * (dimension - 2)) / ((dimension + 1) * (dimension +3 )));
    }

    // смещенный коэффициент эксцесса
    private double excessMoved(){
        double result = 0;
        for (int i = 0; i < dataImprSize; i++){
            result += Math.pow(dataImpr[i] - avarageVal, 4) * frequency[i];
        }
        result /= dimension * Math.pow(sigmaVal, 4);
        return result;
    }

    // несмещенный коэффициент эксцесса
    private double coefExcess(){
        return ((Math.pow(dimension,2) -1) /
                ((dimension - 2) * (dimension - 3))) *
                (this.excessMovedVal - 3 + 6 /(dimension + 1));
    }

    // среднеквадратическое отклонение коэффициента эксцесса
    private double rMsCoefExcess(){
        double numerator = 24 * dimension * (dimension - 2) * (dimension - 3);
        double denominator = Math.pow(dimension + 1, 2) * (dimension + 3) * (dimension + 5);
        return Math.sqrt(numerator / denominator);
    }

    // коэффициент контрэксцесса
    private double coefContrExcess(){
        return 1./(Math.sqrt(Math.abs(excessMovedVal)));
    }

    // среднеквадратическое отклонение коэффициента контрэксцесса
    private double rMsCoefContrExcess(){
        return Math.sqrt(Math.abs(excessMovedVal) / (29 * dimension)) *
                Math.pow(Math.pow(Math.abs(Math.pow(excessMovedVal, 2) -1), 3), .25);
    }

    // коэффициент вариации Пирсона
    private double pearsonCoefVariation(){
        return rMsVal/avarageVal;
    }

    //  среднеквадратическое отклонение коэффициента вариации Пирсона
    private double rMsPearsonCoefVariation(){
        return pearsonCoefVariationVal *
                Math.sqrt((1 + 2 * Math.pow(pearsonCoefVariationVal, 2)) / (2 * dimension));
    }

    // квантиль Up
    private double U(){
        double c0 = 2.515517,
                c1 = 0.802853,
                c2 = 0.010328,
                d1 = 1.432788,
                d2 = 0.1892659,
                d3 = 0.001308,
                a = 0.05,
                t = Math.sqrt(-2 * Math.log(a));

        return t - (c0 + c1*t + c2*Math.pow(t, 2)) / (1 + d1*t + d2*Math.pow(t, 2) + d3*Math.pow(t, 3));
    }

    // удаление аномальных значений
    public void removeAnomalies(){
        double down = avarageVal - 3*rMsAvarageVal, up = avarageVal + 3*rMsAvarageVal;

        for (int i = 0 ; i < dataImprSize; i++){
            if (dataImpr[i] < down || dataImpr[i] > up){
                for (int k = i; k < dataImprSize-1; k++){
                    dataImpr[k] = dataImpr[k+1];
                    frequency[k] = frequency[k+1];
                }
                dataImprSize--;
            }
        }
    }

    // данные для релея
    private void buildDataForReley(){
        double tmp = 0;

        dataReley = new Double[dataImprSize];
        frequencyReley = new double[dataImprSize];
        for (int i = 0; i < dataImprSize; i++){
            tmp += frequency[i];
            dataReley[i] = dataImpr[i];
            frequencyReley[i] = tmp/dimension;
        }
    }
    /*
    private void buildDataforKolmagorov(){
        Double[] dataKolmagorov = new Double[dimension];

    }*/

    //эмпирическая частота
    private double[] emperFreq(){
        double tmp = 0.;
        double result[] = new double[dataImprSize];
        for (int i = 0; i < dataImprSize; i++){
            tmp += (double)frequency[i]/dimension;
            result[i] = tmp;
        }
        return result;
    }

    private void relativeFrequencyCalc(){
        relativeFrequency = new double[dataImprSize];
        for (int i = 0; i < dataImprSize; i++){
            relativeFrequency[i] = (double) frequency[i]/dimension;
        }
    }


    // разбивка вариационного ряда на классы
    public void buildClasses(){
        int numberClasses = 0;

        if ((this.dimension % 2) != 0) {
            numberClasses++;
        }
        if (this.dimension <= 100) {
            numberClasses += (int) (Math.sqrt(this.dimension));
        } else {
            numberClasses += (int) (Math.cbrt(this.dimension));
        }
        buildClasses(numberClasses);
    }
    public void buildClasses(int n){



        classes = null;
        classes = new ClassA[n];
        classesFrequency = new int[n];
        classesRelativeFrequency = new double[n];
        classesEmperFrequency = new double[n];
        classesLimits = new double[2][n];
        classesSize = n;

        // h - длинна каждого отрезка
        double h = (dataImpr[dataImprSize -1] - dataImpr[0])/n;

        int j = 0;
        // добавление элементов в классы
        for (int i = 0; i < n; i++){
            classesFrequency[i] = 0;
            classesRelativeFrequency[i] = 0;
            classes[i] = new ClassA(dataImprSize);

            while (dataImpr[j] <= (dataImpr[0] + (i+1) * h)){
                DataClass dataClass = new DataClass(dataImpr[j],frequency[j]);

                classes[i].addData(dataClass);
                classesFrequency[i]++;
                classesRelativeFrequency[i] += (double) frequency[j]/dimension;
                if (j < dataImprSize - 1){
                    j++;
                }else
                    break;
            }

            ///classesRelativeFrequency[i] = classesRelativeFrequency[i]/n;
        }


        // удаление пустых классов при перестройке
        for (int i = 0 ; i < n; i++){
            if (this.classes[i].getIterator() == -1){
                for (int k = i; k < n-1; k++){
                    this.classes[k] = this.classes[k+1];
                    this.classesFrequency[k] = this.classesFrequency[k+1];
                    this.classesRelativeFrequency[k] = this.classesRelativeFrequency[k+1];
                }
                classesSize --;
            }
        }

        classesEmperFrequency[0] = classesRelativeFrequency[0];
        classesLimits[0][0] = classes[0].getData(0).getData();
        classesLimits[1][0] = classes[0].getData(classes[0].getIterator()).getData();
        for (int i = 1; i < classesSize; i++){
            classesEmperFrequency[i] = classesEmperFrequency[i-1] + classesRelativeFrequency[i];
            classesLimits[0][i] = classes[i].getData(0).getData();
            classesLimits[1][i] = classes[i].getData(classes[i].getIterator()).getData();
        }
    }

    /**
     * getters and setters
     */

    public List<Double> getData() {
        return data;
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

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDataImprSize() {
        return dataImprSize;
    }

    public void setDataImprSize(int dataImprSize) {
        this.dataImprSize = dataImprSize;
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

    public double getSigmaVal() {
        return sigmaVal;
    }

    public void setSigmaVal(double sigmaVal) {
        this.sigmaVal = sigmaVal;
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

    public double getExcessMovedVal() {
        return excessMovedVal;
    }

    public void setExcessMovedVal(double excessMovedVal) {
        this.excessMovedVal = excessMovedVal;
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

    public double getUp() {
        return Up;
    }

    public void setUp(double up) {
        Up = up;
    }

    public Double[] getDataReley() {
        return dataReley;
    }

    public void setDataReley(Double[] dataReley) {
        this.dataReley = dataReley;
    }

    public double[] getFrequencyReley() {
        return frequencyReley;
    }

    public void setFrequencyReley(double[] frequencyReley) {
        this.frequencyReley = frequencyReley;
    }

    public ClassA[] getClasses() {
        return classes;
    }

    public void setClasses(ClassA[] classes) {
        this.classes = classes;
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

    public int getClassesSize() {
        return classesSize;
    }

    public void setClassesSize(int classesSize) {
        this.classesSize = classesSize;
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

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public boolean isReliable() {
        return isReliable;
    }

    public void setReliable(boolean reliable) {
        isReliable = reliable;
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
}


