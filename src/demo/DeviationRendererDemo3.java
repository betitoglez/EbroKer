// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DeviationRendererDemo3 extends ApplicationFrame
{

	public DeviationRendererDemo3(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static XYDataset createDataset()
	{
		YIntervalSeries yintervalseries = new YIntervalSeries("Band A");
		YIntervalSeries yintervalseries1 = new YIntervalSeries("Band B");
		YIntervalSeries yintervalseries2 = new YIntervalSeries("Band C");
		Object obj = new Quarter(1, 2005);
		double d = 0.0D;
		for (int i = 0; i <= 12; i++)
		{
			d += (Math.random() - 0.5D) * 15D;
			yintervalseries.add(((RegularTimePeriod) (obj)).getMiddleMillisecond(), d, d + 10D, Math.max(50D, d + 30D));
			yintervalseries1.add(((RegularTimePeriod) (obj)).getMiddleMillisecond(), d, d - 10D, d + 10D);
			yintervalseries2.add(((RegularTimePeriod) (obj)).getMiddleMillisecond(), d, Math.min(-50D, d - 30D), d - 10D);
			obj = ((RegularTimePeriod) (obj)).next();
		}

		YIntervalSeriesCollection yintervalseriescollection = new YIntervalSeriesCollection();
		yintervalseriescollection.addSeries(yintervalseries);
		yintervalseriescollection.addSeries(yintervalseries1);
		yintervalseriescollection.addSeries(yintervalseries2);
		return yintervalseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("DeviationRenderer - Demo 3", "X", "Y", xydataset, PlotOrientation.VERTICAL, false, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		DeviationRenderer deviationrenderer = new DeviationRenderer(false, false);
		deviationrenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
		deviationrenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
		deviationrenderer.setSeriesStroke(1, new BasicStroke(3F, 1, 1));
		deviationrenderer.setSeriesFillPaint(0, Color.red);
		deviationrenderer.setSeriesFillPaint(1, Color.orange);
		deviationrenderer.setSeriesFillPaint(2, Color.green);
		xyplot.setRenderer(deviationrenderer);
		DateAxis dateaxis = new DateAxis("Date");
		dateaxis.setLowerMargin(0.0D);
		dateaxis.setUpperMargin(0.0D);
		xyplot.setDomainAxis(dateaxis);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setRange(-40D, 40D);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
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
		DeviationRendererDemo3 deviationrendererdemo3 = new DeviationRendererDemo3("JFreeChart : DeviationRendererDemo3.java");
		deviationrendererdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(deviationrendererdemo3);
		deviationrendererdemo3.setVisible(true);
	}
}
