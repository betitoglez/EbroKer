// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class YIntervalChartDemo2 extends ApplicationFrame
{

	public YIntervalChartDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static void add(YIntervalSeries yintervalseries, int i, int j, int k, double d, double d1)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(i, j, k);
		yintervalseries.add(calendar.getTime().getTime(), d, d - d1, d + d1);
	}

	private static IntervalXYDataset createDataset()
	{
		YIntervalSeries yintervalseries = new YIntervalSeries("Series 1");
		add(yintervalseries, 2005, 4, 16, 1309D, 13D);
		add(yintervalseries, 2005, 9, 18, 1312D, 12D);
		add(yintervalseries, 2005, 10, 7, 1309D, 12D);
		add(yintervalseries, 2006, 0, 12, 1311D, 12D);
		add(yintervalseries, 2006, 1, 7, 1311D, 13D);
		add(yintervalseries, 2006, 3, 3, 1309D, 13D);
		add(yintervalseries, 2006, 3, 4, 1307D, 13D);
		add(yintervalseries, 2006, 3, 6, 1305D, 13D);
		add(yintervalseries, 2006, 3, 13, 1303D, 13D);
		add(yintervalseries, 2006, 3, 25, 1308D, 13D);
		add(yintervalseries, 2006, 3, 28, 1311D, 13D);
		add(yintervalseries, 2006, 4, 2, 1306D, 13D);
		add(yintervalseries, 2006, 4, 15, 1303D, 13D);
		add(yintervalseries, 2006, 4, 18, 1311D, 13D);
		add(yintervalseries, 2006, 10, 16, 1301D, 13D);
		YIntervalSeriesCollection yintervalseriescollection = new YIntervalSeriesCollection();
		yintervalseriescollection.addSeries(yintervalseries);
		return yintervalseriescollection;
	}

	private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("YIntervalChartDemo2", "Date", "Value", intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		xyplot.setDomainAxis(new DateAxis("Date"));
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(false);
		XYErrorRenderer xyerrorrenderer = new XYErrorRenderer();
		xyerrorrenderer.setBaseLinesVisible(true);
		xyerrorrenderer.setUseFillPaint(true);
		xyerrorrenderer.setBaseFillPaint(Color.white);
		xyplot.setRenderer(xyerrorrenderer);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		YIntervalChartDemo2 yintervalchartdemo2 = new YIntervalChartDemo2("JFreeChart: YIntervalChartDemo2.java");
		yintervalchartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(yintervalchartdemo2);
		yintervalchartdemo2.setVisible(true);
	}
}
