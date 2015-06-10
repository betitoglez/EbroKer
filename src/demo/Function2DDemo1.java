// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Function2DDemo1 extends ApplicationFrame
{
	static class X2
		implements Function2D
	{

		public double getValue(double d)
		{
			return d * d + 2D;
		}

		X2()
		{
		}
	}


	public Function2DDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Function2DDemo1 ", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		ValueAxis valueaxis = xyplot.getDomainAxis();
		valueaxis.setLowerMargin(0.0D);
		valueaxis.setUpperMargin(0.0D);
		valueaxis.setRange(-2D, 2D);
		ValueAxis valueaxis1 = xyplot.getRangeAxis();
		valueaxis1.setRange(0.0D, 5D);
		return jfreechart;
	}

	public static XYDataset createDataset()
	{
		XYDataset xydataset = DatasetUtilities.sampleFunction2D(new X2(), -40D, 40D, 400, "f(x)");
		return xydataset;
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
		Function2DDemo1 function2ddemo1 = new Function2DDemo1("JFreeChart: Function2DDemo1.java");
		function2ddemo1.pack();
		RefineryUtilities.centerFrameOnScreen(function2ddemo1);
		function2ddemo1.setVisible(true);
	}
}
