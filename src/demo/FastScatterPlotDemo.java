// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.awt.RenderingHints;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class FastScatterPlotDemo extends ApplicationFrame
{

	private static final int COUNT = 0x186a0;
	private float data[][];

	public FastScatterPlotDemo(String s)
	{
		super(s);
		data = new float[2][0x186a0];
		populateData();
		NumberAxis numberaxis = new NumberAxis("X");
		numberaxis.setAutoRangeIncludesZero(false);
		NumberAxis numberaxis1 = new NumberAxis("Y");
		numberaxis1.setAutoRangeIncludesZero(false);
		FastScatterPlot fastscatterplot = new FastScatterPlot(data, numberaxis, numberaxis1);
		fastscatterplot.setDomainPannable(true);
		fastscatterplot.setRangePannable(true);
		JFreeChart jfreechart = new JFreeChart("Fast Scatter Plot", fastscatterplot);
		jfreechart.addSubtitle(new TextTitle("This chart contains 100000 data points."));
		ChartUtilities.applyCurrentTheme(jfreechart);
		jfreechart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ChartPanel chartpanel = new ChartPanel(jfreechart, true);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setDomainZoomable(true);
		chartpanel.setRangeZoomable(true);
		chartpanel.setMinimumDrawHeight(10);
		chartpanel.setMaximumDrawHeight(2000);
		chartpanel.setMinimumDrawWidth(20);
		chartpanel.setMaximumDrawWidth(2000);
		chartpanel.setMouseWheelEnabled(true);
		setContentPane(chartpanel);
	}

	private void populateData()
	{
		for (int i = 0; i < data[0].length; i++)
		{
			float f = (float)i + 100000F;
			data[0][i] = f;
			data[1][i] = 100000F + (float)Math.random() * 100000F;
		}

	}

	public static void main(String args[])
	{
		FastScatterPlotDemo fastscatterplotdemo = new FastScatterPlotDemo("JFreeChart: FastScatterPlotDemo.java");
		fastscatterplotdemo.pack();
		RefineryUtilities.centerFrameOnScreen(fastscatterplotdemo);
		fastscatterplotdemo.setVisible(true);
	}
}
