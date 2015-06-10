package com.alberto.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCItem;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.joda.time.DateTime;

import demo.CandlestickChartDemo1;

public class OHLChart extends ApplicationFrame{
	
	private JFrame frame;
	public OHLCDataset dataset;
	public OHLCDataset dataset2;
	public JFreeChart jfreechart;
	
	public ChartPanel chartpanel;
	
	public TimeSeries adxseries;
	public TimeSeriesCollection adxSeriesCollection;
	
	public TimeSeries indicatorAseries;
	public TimeSeries indicatorBseries;
	public TimeSeries indicatorCseries;
	public TimeSeries indicatorDseries;
	public TimeSeriesCollection indicatorSeriesCollection;
	
	
	public TimeSeries macdEma;
    public TimeSeries macdIndicator;
    public TimeSeriesCollection macdCollection;
	
	OHLCSeriesCollection ohlcseriescollection;
	OHLCSeries ohlcseries;
	
	private static final Calendar calendar = Calendar.getInstance();
	

	public void addMacd (DateTime datetime, double ema , double indicator){
		Minute minute = new Minute (datetime.toDate());
		macdEma.addOrUpdate(minute, ema);
		macdIndicator.addOrUpdate(minute, indicator);
	}
	
	public void addPointIndicator (int indicator , DateTime datetime , double value){
		Minute minute = new Minute (datetime.toDate());
		switch (indicator){
			case 1:
			default:
				indicatorAseries.addOrUpdate(minute, value);
				break;
			case 2:
				indicatorBseries.addOrUpdate(minute, value);
				break;
			case 3:
				indicatorCseries.addOrUpdate(minute, value);
				break;
			case 4:
				indicatorDseries.addOrUpdate(minute, value);
				break;
		}
	}

	public OHLChart() {
		super("Grafico");
		init();
		
		CandlestickRenderer candleRenderer = new CandlestickRenderer(5D);
		
		DateAxis domainAxis = new DateAxis("Fecha");
		NumberAxis rangeAxis = new NumberAxis("Precio");
		rangeAxis.setAutoRange(true);
	    rangeAxis.setAutoRangeIncludesZero(false);
	    XYPlot plot1 = new XYPlot(ohlcseriescollection, domainAxis, rangeAxis, candleRenderer);
	    plot1.setBackgroundPaint(Color.lightGray);
	    plot1.setDomainGridlinePaint(Color.white);
	    plot1.setRangeGridlinePaint(Color.white);
	    plot1.setRangePannable(true);
	    
	    
	    indicatorAseries = new TimeSeries("Indicador A");
	    indicatorBseries = new TimeSeries("Indicador B");
	    indicatorCseries = new TimeSeries("Indicador C");
	    indicatorDseries = new TimeSeries("Indicador D");
	    
	    indicatorSeriesCollection = new TimeSeriesCollection();
	    indicatorSeriesCollection.addSeries(indicatorAseries);
	    indicatorSeriesCollection.addSeries(indicatorBseries);
	    indicatorSeriesCollection.addSeries(indicatorCseries);
	    indicatorSeriesCollection.addSeries(indicatorDseries);
	    
	    plot1.setDataset(1, indicatorSeriesCollection);
	    plot1.setRenderer(1, new StandardXYItemRenderer());
	    
	    plot1.setDomainCrosshairVisible(true);
	    plot1.setDomainCrosshairLockedOnData(true);
	    plot1.setRangeCrosshairVisible(true);
	    
	    StandardXYItemRenderer adx = new StandardXYItemRenderer();
	    XYPlot plot2 = new XYPlot(adxSeriesCollection, null, new NumberAxis("ADX"), adx);
	    plot2.setBackgroundPaint(Color.lightGray);
	    plot2.setDomainGridlinePaint(Color.white);
	    plot2.setRangeGridlinePaint(Color.white);
	    plot2.getRenderer().setSeriesOutlinePaint(0, Color.black);
	    
	    plot2.setDomainCrosshairVisible(true);
	    plot2.setDomainCrosshairLockedOnData(true);
	    plot2.setRangeCrosshairVisible(true);
	    
	    
	    macdEma = new TimeSeries("Macd Ema");
	    macdIndicator = new TimeSeries("Macd Indicator");
	    macdCollection = new TimeSeriesCollection();
	    macdCollection.addSeries(macdEma);
	    macdCollection.addSeries(macdIndicator);
	    
	    StandardXYItemRenderer macd = new StandardXYItemRenderer();
	    XYPlot plot3 = new XYPlot(macdCollection, null, new NumberAxis("MACD"), macd);
	    
	    
	    CombinedDomainXYPlot cplot = new CombinedDomainXYPlot(domainAxis);
	    cplot.add(plot1, 3);
	    cplot.add(plot2, 2);
	    cplot.add(plot3, 2);
	    
	    cplot.setGap(8.0);
	    
	    JFreeChart chart = new JFreeChart("Ibex 35 Candlestick", JFreeChart.DEFAULT_TITLE_FONT, cplot, false);

	    ChartUtilities.applyCurrentTheme(chart);
	    
	    chartpanel = new ChartPanel(chart);
		chartpanel.setMouseWheelEnabled(true);
		chartpanel.setDomainZoomable(true);
		chartpanel.setRangeZoomable(true);		
		chartpanel.setFillZoomRectangle(false);
		chartpanel.setDisplayToolTips(true);
		chartpanel.setPreferredSize(new Dimension(900,700));
		setContentPane(chartpanel);
		
		
		
		pack();
		RefineryUtilities.centerFrameOnScreen(this);
		setVisible(true);
		
	}
	
	

	public void addCandle (DateTime datetime , double open , double high , double low, double close){
		Minute minute = new Minute(datetime.toDate());
		ohlcseries.add(minute, open, high, low, close);
	}
	
	public void addAdx (DateTime dateTime, double value){
		adxseries.add(new Minute(dateTime.toDate()), value);
	}
	
	private void init ()
	{
		ohlcseries = new OHLCSeries("Precio");
		ohlcseriescollection = new OHLCSeriesCollection();
		ohlcseriescollection.addSeries(ohlcseries);
		
		adxseries = new TimeSeries("ADX");
		adxSeriesCollection = new TimeSeriesCollection();
		adxSeriesCollection.addSeries(adxseries);
		
	}
	

	
}
