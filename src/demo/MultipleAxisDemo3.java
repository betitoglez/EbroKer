// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MultipleAxisDemo3 extends ApplicationFrame
{

	public MultipleAxisDemo3(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(600, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart()
	{
		XYDataset xydataset = createDataset("Series 1", 100D, new Minute(), 200);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Multiple Axis Demo 3", "Time of Day", "Primary Range Axis", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setOrientation(PlotOrientation.VERTICAL);
		NumberAxis numberaxis = new NumberAxis("Domain Axis 2");
		numberaxis.setAutoRangeIncludesZero(false);
		xyplot.setDomainAxis(1, numberaxis);
		xyplot.setDomainAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);
		NumberAxis numberaxis1 = new NumberAxis("Domain Axis 3");
		numberaxis.setAutoRangeIncludesZero(false);
		xyplot.setDomainAxis(2, numberaxis1);
		xyplot.setDomainAxisLocation(2, AxisLocation.BOTTOM_OR_LEFT);
		NumberAxis numberaxis2 = new NumberAxis("Range Axis 2");
		xyplot.setRangeAxis(1, numberaxis2);
		xyplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
		XYDataset xydataset1 = createDataset("Series 2", 1000D, new Minute(), 170);
		xyplot.setDataset(1, xydataset1);
		xyplot.mapDatasetToDomainAxis(1, 1);
		xyplot.mapDatasetToRangeAxis(1, 1);
		xyplot.setRenderer(1, new XYLineAndShapeRenderer(true, false));
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	private static XYDataset createDataset(String s, double d, RegularTimePeriod regulartimeperiod, int i)
	{
		TimeSeries timeseries = new TimeSeries(s);
		RegularTimePeriod regulartimeperiod1 = regulartimeperiod;
		double d1 = d;
		for (int j = 0; j < i; j++)
		{
			timeseries.add(regulartimeperiod1, d1);
			regulartimeperiod1 = regulartimeperiod1.next();
			d1 *= 1.0D + (Math.random() - 0.495D) / 10D;
		}

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		MultipleAxisDemo3 multipleaxisdemo3 = new MultipleAxisDemo3("JFreeChart: MultipleAxisDemo3.java");
		multipleaxisdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(multipleaxisdemo3);
		multipleaxisdemo3.setVisible(true);
	}
}
