package com.alberto.indicators;

import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jfree.chart.plot.IntervalMarker;
import org.joda.time.DateTime;

import com.alberto.Main;
import com.alberto.TradingDay;
import com.alberto.chart.Chart;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Tick;

public class TestNoChartRecursive {

    public static HashMap<String, String> cache;

    static {
        TestNoChartRecursive.cache = new HashMap<String, String>();
    }

    public int period = 0;
    public int emaShort = 15;
    public int emaMedium = 40;
    public int emaLong = 90;
    public int macdShort = 12;
    public int macdLarge = 26;
    public int macdEma = 9;
    public int adxperiod = 14;
    public int rsiValue = 14;

    public int adxMode = 0;
    public double totalIntradayStopLoss = 80;

    public boolean lastTradeEnabled = true;
    public int lastTradeMinutes = 15;

    public int adxOrder = 27;

    public int priceInclination = 5;
    public int emaInclination = 10;

    public int zoneCheck = 2;

    public int stopGain = 25;

    public boolean stopLossTransactionEnabled = false;
    public double stopLossTransactionPrice = 40;

    // Chart chart;
    PriceCollection prices;

    EMA ema5;
    EMA ema10;
    EMA emaLarge;

    MACD macd;

    BASIC basic;

    ADX adx;
    ADX shortAdx;

    int status = 100;
    int macdStatus = 100;
    int current = 0;

    int zone = 0;

    boolean open = false;
    int mode = 0;
    IntervalMarker marker;

    double enterPrice;
    double exitPrice;
    double profitLoss = 0;

    double transactionPrice = 0;
    double transactionMin = 0;
    double transactionMax = 0;

    double adxHigh = -1;
    double adxLow = -1;

    DateTime lastTrade;

    public TestNoChartRecursive() {
        // TODO Auto-generated constructor stub
        // chart = new Chart();
        
    }
    
    public void createIndicators(){
    	prices = new PriceCollection();

        ema5 = new EMA(prices, emaShort);
        ema10 = new EMA(prices, emaMedium);
        emaLarge = new EMA(prices, emaLong);
        macd = new MACD(prices, macdShort, macdLarge, macdEma);
        basic = new BASIC(prices);
        adx = new ADX(adxperiod);
        shortAdx = new ADX(40);
    }

    public void status(DateTime datetime) {
        zone = 0;

        if (status == 100) {
            status = ema5.get() > ema10.get() ? -1 : 1;
        } else {
            if (ema5.get() > ema10.get()) {
                if (status == 1)
                    status = -1;
                zone--;
            } else if (ema5.get() < ema10.get()) {
                if (status == -1)
                    status = 1;
                zone++;
            }
        }

        if (macdStatus == 100) {
            macdStatus = macd.get() > macd.getIndicator() ? 1 : -1;
        } else {
            if (macd.get() > macd.getIndicator()) {
                if (macdStatus == -1)
                    macdStatus = 1;
                zone++;
            } else if (macd.get() < macd.getIndicator()) {
                if (macdStatus == 1)
                    macdStatus = -1;
                zone--;
            }
        }

    }

    private void checkEnter(DateTime datetime) {
        if (profitLoss < totalIntradayStopLoss * -1
                || (datetime.plusHours(2).getHourOfDay() == 10 && datetime
                        .plusHours(2).getMinuteOfHour() < 45)
                || (datetime.plusHours(2).getHourOfDay() < 10)
                || datetime.plusHours(2).getHourOfDay() == 17
                && datetime.plusHours(2).getMinuteOfHour() >= 20
                || (lastTrade != null && lastTrade.plusMinutes(30).isAfter(
                        datetime))) {
            return;
        }
        if (zone >= zoneCheck) {
            if (adx.getADX() < adxOrder
                    || basic.getInclination(priceInclination) > 0
                    || ema10.getInclination(emaInclination) >= 0
                    || ema5.getInclination(emaInclination) >= 0
                    || emaLarge.getInclination(emaInclination) >= 0
                    || macd.getInclination(emaInclination) >= 0) {
                return;
            }

            mode = -1;

        } else if (zone <= zoneCheck * -1) {
            if (adx.getADX() < adxOrder
                    || basic.getInclination(priceInclination) < 0
                    || ema10.getInclination(emaInclination) <= 0
                    || ema5.getInclination(emaInclination) <= 0
                    || emaLarge.getInclination(emaInclination) <= 0
                    || macd.getInclination(emaInclination) <= 0) {
                return;
            }

            mode = 1;

        }

        if (Math.abs(zone) >= zoneCheck) {
            open = true;

            enterPrice = prices.getLast();
            lastTrade = datetime;
            transactionPrice = 0;
            transactionMax = 0;
            transactionMin = 0;
        }
    }

    public void checkExit(DateTime datetime) {
        if (mode == 1) {
            transactionPrice = prices.getLast() - enterPrice;
        } else {
            transactionPrice = enterPrice - prices.getLast();
        }

        if (transactionPrice > transactionMax)
            transactionMax = transactionPrice;

        if (transactionPrice < transactionMin)
            transactionMin = transactionPrice;

        boolean endOrder = true;
        if (profitLoss > -80
                && ((zone >= 0 && mode == -1) || (zone <= 0 && mode == 1))
                && (((datetime.plusHours(2).getHourOfDay() < 17) || (datetime
                        .plusHours(2).getHourOfDay() == 17 && datetime
                        .plusHours(2).getMinuteOfHour() < 25)))
                && ((mode == -1 && emaLarge.getInclination(emaInclination) < 0.1) || (mode == 1 && emaLarge
                        .getInclination(emaInclination) > -0.1))) {
            endOrder = false;
        }

        if (mode == 1) {
            if ((macd.getInclination(emaInclination) < 0 || ema10
                    .getInclination(emaInclination) < 0)
                    && transactionPrice >= stopGain) {
                endOrder = true;
            }

        } else {
            if ((macd.getInclination(emaInclination) > 0 || ema10
                    .getInclination(emaInclination) > 0)
                    && transactionPrice >= stopGain) {
                endOrder = true;
            }

        }

        if (endOrder) {
            exitPrice = prices.getLast();

            if (mode == 1) {
                profitLoss += exitPrice - enterPrice;
            } else {
                profitLoss += enterPrice - exitPrice;
            }
            profitLoss -= 3;

            open = false;
            mode = 0;

        }
    }

    public void check(DateTime datetime) {

        if (!open) {
            checkEnter(datetime);
        } else {
            checkExit(datetime);
        }
    }

    private void run(String doc, String filename) {
        try {

            CSVParser oParser = CSVParser.parse(doc,
                    CSVFormat.DEFAULT.withDelimiter(';'));
            int i = 0;
            for (CSVRecord csvRecord : oParser) {
                i++;
                try {

                    Decimal price = Decimal.valueOf(csvRecord.get(0)
                            .replaceAll("\\.", "").replaceAll(",", "."));

                    if (i % 12 != 0) {
                        adxLow = (adxLow < 0 || price.toDouble() < adxLow) ? price
                                .toDouble() : adxLow;
                        adxHigh = (adxHigh < 0 || price.toDouble() > adxHigh) ? price
                                .toDouble() : adxHigh;
                        continue;
                    }

                    // adx.update(adxHigh, adxLow, price.toDouble());
                    adx.update(price.toDouble(), price.toDouble(),
                            price.toDouble());
                    shortAdx.update(price.toDouble(), price.toDouble(),
                            price.toDouble());
                    adxLow = -1;
                    adxHigh = -1;

                    FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").parse(
                            csvRecord.get(1));

                    DateTime datetime = DateTime.parse(csvRecord.get(1))
                            .minusHours(2);

                    prices.add(price.toDouble());
                    // chart.addPoint(datetime, price.toDouble());

                    current++;
                    // if (i > 150){
                    status(datetime);
                    check(datetime);
                    // }

                } catch (ParseException e) {
                    // TODO Auto-generated catch block

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }

            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Falla");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String doc = "";
        File folder = new File("resources/inputs");

        String[] files = folder.list(new FilenameFilter() {

            @Override
            public boolean accept(File arg0, String arg1) {
                // TODO Auto-generated method stub
                if (FilenameUtils.isExtension(arg1, "csv")) {
                    return true;
                }
                return false;
            }
        });

        int[] emaShort = { 5, 10 ,15 };
        int[] emaMedium = {  5 ,15, 40 };
        int[] emaLarge = { 50, 60, 90 };
        int[] macdShort = { 6, 12, 20 ,30 };
        int[] macdLarge = { 14, 20, 26 , 40 };
        int[] macdEma = { 9, 12, 18 };
        int[] adxPeriod = {  8 , 14 , 20 };
        int[] adxValue = { 20 , 27 , 40 };
        int[] adxMode = { 0 };
        int[] zoneCheck = { 2 };

        int current = 0;
        int negative = 0;
        int positive = 0;
        int totalChecks = emaShort.length * emaMedium.length * emaLarge.length
                * macdShort.length * macdLarge.length * macdEma.length
                * adxPeriod.length * adxValue.length * adxMode.length
                * zoneCheck.length;

        JFrame frame = new JFrame("Percentage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        Container contentPane = frame.getContentPane();

        JLabel label2 = new JLabel("0 / " + totalChecks);
        label2.setLocation(100, 10);
        contentPane.add(label2);

        frame.setVisible(true);

        double totalProfitLoss = 0;
        String filename = DateTime.now().toString("dd_MM_YYYY") + "file.csv";
        TestNoChartRecursive
                .writetofile(
                        filename,
                        "id;total; _emaShort  ; _emaMedium  ; _emaLarge  ; _macdShort  ; _macdLarge  ; _macdEma  ; _adxPeriod  ; _adxValue  ; _adxMode  ; _zoneCheck \r\n");

        DecimalFormat df = new DecimalFormat("#.##");

        for (int _emaShort : emaShort) {
            for (int _emaMedium : emaMedium) {
                for (int _emaLarge : emaLarge) {
                    for (int _macdShort : macdShort) {
                        for (int _macdLarge : macdLarge) {
                            for (int _macdEma : macdEma) {
                                for (int _adxPeriod : adxPeriod) {
                                    for (int _adxValue : adxValue) {
                                        for (int _adxMode : adxMode) {
                                            for (int _zoneCheck : zoneCheck) {
                                                totalProfitLoss = 0;                                        
                                                for (String file : files) {

                                                    try {

                                                        if (TestNoChartRecursive.cache
                                                                .containsKey(file)) {
                                                            doc = TestNoChartRecursive.cache
                                                                    .get(file);
                                                        } else {
                                                            doc = FileUtils
                                                                    .readFileToString(new File(
                                                                            "resources/inputs/"
                                                                                    + file));
                                                            TestNoChartRecursive.cache
                                                                    .put(file,
                                                                            doc);
                                                        }
                                                        
                                                        TestNoChartRecursive eBroker = new TestNoChartRecursive();
                                                        eBroker.emaShort = _emaShort;
                                                        eBroker.emaMedium = _emaMedium;
                                                        eBroker.emaLong = _emaLarge;
                                                        eBroker.macdShort = _macdShort;
                                                        eBroker.macdLarge = _macdLarge;
                                                        eBroker.macdEma = _macdEma;
                                                        eBroker.adxperiod = _adxPeriod;
                                                        eBroker.adxOrder = _adxValue;
                                                        eBroker.adxMode = _adxMode;
                                                        eBroker.zoneCheck = _zoneCheck;
                                                        eBroker.createIndicators();
                                                        eBroker.run(doc, file);
                                                        totalProfitLoss += eBroker.profitLoss;
                                                    } catch (IOException e) {
                                                        // TODO Auto-generated
                                                        // catch block
                                                        e.printStackTrace();
                                                    }
                                                }
                                                current++;
                                                label2.setText(current + " / "
                                                        + totalChecks);
                                               
                                                if (totalProfitLoss > 300) {
                                                    TestNoChartRecursive
                                                            .writetofile(
                                                                    filename,
                                                                    df.format(totalProfitLoss) + ";" + _emaShort  + ";" + _emaMedium  + ";" + _emaLarge  + ";" + _macdShort  + ";" + _macdLarge  + ";" + _macdEma  + ";" + _adxPeriod  + ";" + _adxValue  + ";" + _adxMode  + ";" + _zoneCheck +"\r\n");
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public static void writetofile(String filename, String line) {
        try {
            FileUtils
                    .writeStringToFile(
                            FileUtils.getFile("resources/logs/" + filename),
                            line, true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}