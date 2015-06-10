// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYBarChartDemo6 extends ApplicationFrame
{

	public XYBarChartDemo6(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYBarChart("XYBarChartDemo6", "X", false, "Y", intervalxydataset, PlotOrientation.HORIZONTAL, false, false, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
		xybarrenderer.setUseYInterval(true);
		xyplot.setRenderer(xybarrenderer);
		return jfreechart;
	}

	private static IntervalXYDataset createDataset()
	{
		DefaultIntervalXYDataset defaultintervalxydataset = new DefaultIntervalXYDataset();
		double ad[] = {
			1.0D, 2D, 3D, 4D
		};
		double ad1[] = {
			0.90000000000000002D, 1.8D, 2.7000000000000002D, 3.6000000000000001D
		};
		double ad2[] = {
			1.1000000000000001D, 2.2000000000000002D, 3.2999999999999998D, 4.4000000000000004D
		};
		double ad3[] = {
			1.0D, 2D, 3D, 4D
		};
		double ad4[] = {
			0.90000000000000002D, 1.8D, 2.7000000000000002D, 3.6000000000000001D
		};
		double ad5[] = {
			1.1000000000000001D, 2.2000000000000002D, 3.2999999999999998D, 4.4000000000000004D
		};
		double ad6[][] = {
			ad, ad1, ad2, ad3, ad4, ad5
		};
		defaultintervalxydataset.addSeries("Series 1", ad6);
		return defaultintervalxydataset;
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
		XYBarChartDemo6 xybarchartdemo6 = new XYBarChartDemo6("JFreeChart : XYBarChartDemo6");
		xybarchartdemo6.pack();
		RefineryUtilities.centerFrameOnScreen(xybarchartdemo6);
		xybarchartdemo6.setVisible(true);
	}
}
