// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.io.PrintStream;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesDemo5 extends ApplicationFrame
{

	public TimeSeriesDemo5(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static XYDataset createDataset()
	{
		TimeSeries timeseries = new TimeSeries("Random Data");
		Day day = new Day(1, 1, 1990);
		double d = 100D;
		for (int i = 0; i < 0x186a0; i++)
			try
			{
				d = (d + Math.random()) - 0.5D;
				timeseries.add(day, new Double(d));
				day = (Day)day.next();
			}
			catch (SeriesException seriesexception)
			{
				System.err.println("Error adding to series");
			}

		return new TimeSeriesCollection(timeseries);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Test", "Day", "Value", xydataset, false, false, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		SamplingXYLineRenderer samplingxylinerenderer = new SamplingXYLineRenderer();
		xyplot.setRenderer(samplingxylinerenderer);
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
		String s = "???????????";
		TimeSeriesDemo5 timeseriesdemo5 = new TimeSeriesDemo5(s);
		timeseriesdemo5.pack();
		RefineryUtilities.positionFrameRandomly(timeseriesdemo5);
		timeseriesdemo5.setVisible(true);
	}
}
