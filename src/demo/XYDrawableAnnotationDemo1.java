// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;

public class XYDrawableAnnotationDemo1 extends ApplicationFrame
{

	public XYDrawableAnnotationDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("XYDrawableAnnotationDemo1", null, "$ million", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setLowerMargin(0.20000000000000001D);
		dateaxis.setUpperMargin(0.20000000000000001D);
		dateaxis.setStandardTickUnits(createStandardDateTickUnits());
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setLowerMargin(0.20000000000000001D);
		numberaxis.setUpperMargin(0.20000000000000001D);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseLinesVisible(true);
		xylineandshaperenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D));
		xylineandshaperenderer.setSeriesShape(1, new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D));
		xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(3F));
		xylineandshaperenderer.setSeriesStroke(1, new BasicStroke(3F, 1, 1, 5F, new float[] {
			10F, 5F
		}, 0.0F));
		xylineandshaperenderer.setSeriesFillPaint(0, Color.white);
		xylineandshaperenderer.setSeriesFillPaint(1, Color.white);
		xylineandshaperenderer.setUseFillPaint(true);
		xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		xylineandshaperenderer.setDefaultEntityRadius(6);
		xylineandshaperenderer.addAnnotation(new XYDrawableAnnotation((new Month(4, 2005)).getFirstMillisecond(), 600D, 180D, 100D, 3D, createPieChart()));
		xylineandshaperenderer.addAnnotation(new XYDrawableAnnotation((new Month(9, 2007)).getFirstMillisecond(), 1250D, 120D, 100D, 2D, createBarChart()));
		xyplot.setRenderer(xylineandshaperenderer);
		return jfreechart;
	}

	private static XYDataset createDataset()
	{
		TimeSeries timeseries = new TimeSeries("Division A");
		timeseries.add(new Year(2005), 1520D);
		timeseries.add(new Year(2006), 1132D);
		timeseries.add(new Year(2007), 450D);
		timeseries.add(new Year(2008), 620D);
		TimeSeries timeseries1 = new TimeSeries("Division B");
		timeseries1.add(new Year(2005), 1200D);
		timeseries1.add(new Year(2006), 1300D);
		timeseries1.add(new Year(2007), 640D);
		timeseries1.add(new Year(2008), 520D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}

	private static JFreeChart createPieChart()
	{
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Engineering", 43.200000000000003D);
		defaultpiedataset.setValue("Research", 13.199999999999999D);
		defaultpiedataset.setValue("Advertising", 20.899999999999999D);
		PiePlot pieplot = new PiePlot(defaultpiedataset);
		pieplot.setBackgroundPaint(null);
		pieplot.setOutlinePaint(null);
		pieplot.setBaseSectionOutlinePaint(Color.white);
		pieplot.setBaseSectionOutlineStroke(new BasicStroke(2.0F));
		pieplot.setLabelFont(new Font("Dialog", 0, 18));
		pieplot.setMaximumLabelWidth(0.25D);
		JFreeChart jfreechart = new JFreeChart(pieplot);
		jfreechart.setBackgroundPaint(null);
		jfreechart.removeLegend();
		jfreechart.setPadding(RectangleInsets.ZERO_INSETS);
		return jfreechart;
	}

	private static JFreeChart createBarChart()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(10D, "R1", "Q1");
		defaultcategorydataset.addValue(7D, "R1", "Q2");
		defaultcategorydataset.addValue(8D, "R1", "Q3");
		defaultcategorydataset.addValue(4D, "R1", "Q4");
		defaultcategorydataset.addValue(10.6D, "R2", "Q1");
		defaultcategorydataset.addValue(6.0999999999999996D, "R2", "Q2");
		defaultcategorydataset.addValue(8.5D, "R2", "Q3");
		defaultcategorydataset.addValue(4.2999999999999998D, "R2", "Q4");
		JFreeChart jfreechart = ChartFactory.createBarChart("Sales 2008", null, null, defaultcategorydataset, PlotOrientation.VERTICAL, false, false, false);
		jfreechart.setBackgroundPaint(null);
		jfreechart.getPlot().setBackgroundPaint(new Color(200, 200, 255, 60));
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	private static TickUnitSource createStandardDateTickUnits()
	{
		TickUnits tickunits = new TickUnits();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy");
		tickunits.add(new DateTickUnit(DateTickUnitType.YEAR, 1, DateTickUnitType.YEAR, 1, simpledateformat));
		tickunits.add(new DateTickUnit(DateTickUnitType.YEAR, 2, DateTickUnitType.YEAR, 1, simpledateformat));
		tickunits.add(new DateTickUnit(DateTickUnitType.YEAR, 5, DateTickUnitType.YEAR, 5, simpledateformat));
		return tickunits;
	}

	public static void main(String args[])
	{
		XYDrawableAnnotationDemo1 xydrawableannotationdemo1 = new XYDrawableAnnotationDemo1("JFreeChart: XYDrawableAnnotationDemo1.java");
		xydrawableannotationdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xydrawableannotationdemo1);
		xydrawableannotationdemo1.setVisible(true);
	}
}
