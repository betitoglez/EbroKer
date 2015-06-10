// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CompareToPreviousYearDemo extends ApplicationFrame
{

	public CompareToPreviousYearDemo(String s)
	{
		super(s);
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = (ChartPanel)createDemoPanel();
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setMouseZoomable(true, true);
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart()
	{
		XYDataset xydataset = createDataset2006();
		XYDataset xydataset1 = createDataset2007();
		DateAxis dateaxis = new DateAxis("Date");
		Month month = new Month(1, 2007);
		Month month1 = new Month(12, 2007);
		dateaxis.setRange(month.getFirstMillisecond(), month1.getLastMillisecond());
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM"));
		dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
		xylineandshaperenderer.setUseFillPaint(true);
		xylineandshaperenderer.setBaseFillPaint(Color.white);
		xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{1}: {2}", new SimpleDateFormat("MMM-yyyy"), new DecimalFormat("0.00")));
		XYPlot xyplot = new XYPlot(xydataset1, dateaxis, new NumberAxis("Sales"), xylineandshaperenderer);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		DateAxis dateaxis1 = new DateAxis();
		dateaxis1.setVisible(false);
		xyplot.setDomainAxis(1, dateaxis1);
		xyplot.setDataset(1, xydataset);
		xyplot.mapDatasetToDomainAxis(1, 1);
		XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer();
		xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
		xylineandshaperenderer1.setUseFillPaint(true);
		xylineandshaperenderer1.setBaseFillPaint(Color.white);
		xylineandshaperenderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{1}: {2}", new SimpleDateFormat("MMM-yyyy"), new DecimalFormat("0.00")));
		xyplot.setRenderer(1, xylineandshaperenderer1);
		JFreeChart jfreechart = new JFreeChart("Sales Comparison Chart", xyplot);
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		DateAxis dateaxis2 = (DateAxis)xyplot.getDomainAxis();
		dateaxis2.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	private static XYDataset createDataset2006()
	{
		TimeSeries timeseries = new TimeSeries("Sales 2006");
		timeseries.add(new Month(1, 2006), 100D);
		timeseries.add(new Month(2, 2006), 102.3D);
		timeseries.add(new Month(3, 2006), 105.7D);
		timeseries.add(new Month(4, 2006), 104.2D);
		timeseries.add(new Month(5, 2006), 114.7D);
		timeseries.add(new Month(6, 2006), 121.7D);
		timeseries.add(new Month(7, 2006), 155.59999999999999D);
		timeseries.add(new Month(8, 2006), 143.19999999999999D);
		timeseries.add(new Month(9, 2006), 131.90000000000001D);
		timeseries.add(new Month(10, 2006), 120D);
		timeseries.add(new Month(11, 2006), 109.90000000000001D);
		timeseries.add(new Month(12, 2006), 99.599999999999994D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.setXPosition(TimePeriodAnchor.MIDDLE);
		return timeseriescollection;
	}

	private static XYDataset createDataset2007()
	{
		TimeSeries timeseries = new TimeSeries("Sales 2007");
		timeseries.add(new Month(1, 2007), 163.90000000000001D);
		timeseries.add(new Month(2, 2007), 163.80000000000001D);
		timeseries.add(new Month(3, 2007), 162D);
		timeseries.add(new Month(4, 2007), 167.09999999999999D);
		timeseries.add(new Month(5, 2007), 170D);
		timeseries.add(new Month(6, 2007), 175.69999999999999D);
		timeseries.add(new Month(7, 2007), 171.90000000000001D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.setXPosition(TimePeriodAnchor.MIDDLE);
		return timeseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		CompareToPreviousYearDemo comparetopreviousyeardemo = new CompareToPreviousYearDemo("JFreeChart: CompareToPreviousYearDemo.java");
		comparetopreviousyeardemo.pack();
		RefineryUtilities.centerFrameOnScreen(comparetopreviousyeardemo);
		comparetopreviousyeardemo.setVisible(true);
	}
}
