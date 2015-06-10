// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StackedXYAreaRendererDemo1 extends ApplicationFrame
{

	public StackedXYAreaRendererDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static TableXYDataset createDataset()
	{
		DefaultTableXYDataset defaulttablexydataset = new DefaultTableXYDataset();
		XYSeries xyseries = new XYSeries("Series 1", true, false);
		xyseries.add(5D, 5D);
		xyseries.add(10D, 15.5D);
		xyseries.add(15D, 9.5D);
		xyseries.add(20D, 7.5D);
		defaulttablexydataset.addSeries(xyseries);
		XYSeries xyseries1 = new XYSeries("Series 2", true, false);
		xyseries1.add(5D, 5D);
		xyseries1.add(10D, 15.5D);
		xyseries1.add(15D, 9.5D);
		xyseries1.add(20D, 3.5D);
		defaulttablexydataset.addSeries(xyseries1);
		return defaulttablexydataset;
	}

	private static JFreeChart createChart(TableXYDataset tablexydataset)
	{
		JFreeChart jfreechart = ChartFactory.createStackedXYAreaChart("StackedXYAreaRendererDemo1", "X Value", "Y Value", tablexydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		StackedXYAreaRenderer stackedxyarearenderer = new StackedXYAreaRenderer(5);
		stackedxyarearenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		xyplot.setRenderer(0, stackedxyarearenderer);
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		stackedxyarearenderer.setShapePaint(Color.yellow);
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
		StackedXYAreaRendererDemo1 stackedxyarearendererdemo1 = new StackedXYAreaRendererDemo1("StackedXYAreaRendererDemo1");
		stackedxyarearendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(stackedxyarearendererdemo1);
		stackedxyarearendererdemo1.setVisible(true);
	}
}
