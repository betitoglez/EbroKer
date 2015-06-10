// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XIntervalSeriesCollectionDemo1 extends ApplicationFrame
{

	public XIntervalSeriesCollectionDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static IntervalXYDataset createDataset()
	{
		XIntervalSeriesCollection xintervalseriescollection = new XIntervalSeriesCollection();
		XIntervalSeries xintervalseries = new XIntervalSeries("S1");
		xintervalseries.add(5D, 2D, 7.5D, 12.300000000000001D);
		xintervalseries.add(10D, 8D, 11D, 20D);
		xintervalseriescollection.addSeries(xintervalseries);
		return xintervalseriescollection;
	}

	private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		DateAxis dateaxis = new DateAxis("Date");
		NumberAxis numberaxis = new NumberAxis("Y");
		XYBarRenderer xybarrenderer = new XYBarRenderer();
		xybarrenderer.setUseYInterval(false);
		XYPlot xyplot = new XYPlot(intervalxydataset, dateaxis, numberaxis, xybarrenderer);
		JFreeChart jfreechart = new JFreeChart(xyplot);
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setDomainGridlinesVisible(true);
		xyplot.setRangeGridlinePaint(Color.white);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		XIntervalSeriesCollectionDemo1 xintervalseriescollectiondemo1 = new XIntervalSeriesCollectionDemo1("Demo 1");
		xintervalseriescollectiondemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xintervalseriescollectiondemo1);
		xintervalseriescollectiondemo1.setVisible(true);
	}
}
