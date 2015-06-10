// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.YIntervalRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class YIntervalChartDemo1 extends ApplicationFrame
{

	public YIntervalChartDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createScatterPlot("Y Interval Chart Demo 1", "X", "Y", intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRenderer(new YIntervalRenderer());
		return jfreechart;
	}

	private static IntervalXYDataset createDataset()
	{
		double d = 100D;
		YIntervalSeries yintervalseries = new YIntervalSeries("Series 1");
		for (int i = 0; i < 100; i++)
		{
			d += Math.random() - 0.48999999999999999D;
			yintervalseries.add(i, d, d - 3D, d + 3D);
		}

		YIntervalSeriesCollection yintervalseriescollection = new YIntervalSeriesCollection();
		yintervalseriescollection.addSeries(yintervalseries);
		return yintervalseriescollection;
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
		YIntervalChartDemo1 yintervalchartdemo1 = new YIntervalChartDemo1("JFreeChart: YIntervalChartDemo1.java");
		yintervalchartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(yintervalchartdemo1);
		yintervalchartdemo1.setVisible(true);
	}
}
