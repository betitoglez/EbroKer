// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer2;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYAreaRenderer2Demo1 extends ApplicationFrame
{

	public XYAreaRenderer2Demo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static XYDataset createDataset()
	{
		XYSeries xyseries = new XYSeries("Random 1");
		xyseries.add(new Integer(1), new Double(500.19999999999999D));
		xyseries.add(new Integer(2), new Double(694.10000000000002D));
		xyseries.add(new Integer(3), new Double(-734.39999999999998D));
		xyseries.add(new Integer(4), new Double(453.19999999999999D));
		xyseries.add(new Integer(5), new Double(500.19999999999999D));
		xyseries.add(new Integer(6), new Double(300.69999999999999D));
		xyseries.add(new Integer(7), new Double(734.39999999999998D));
		xyseries.add(new Integer(8), new Double(453.19999999999999D));
		XYSeries xyseries1 = new XYSeries("Random 2");
		xyseries1.add(new Integer(1), new Double(700.20000000000005D));
		xyseries1.add(new Integer(2), new Double(534.10000000000002D));
		xyseries1.add(new Integer(3), new Double(323.39999999999998D));
		xyseries1.add(new Integer(4), new Double(125.2D));
		xyseries1.add(new Integer(5), new Double(653.20000000000005D));
		xyseries1.add(new Integer(6), new Double(432.69999999999999D));
		xyseries1.add(new Integer(7), new Double(564.39999999999998D));
		xyseries1.add(new Integer(8), new Double(322.19999999999999D));
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		xyseriescollection.setIntervalWidth(0.0D);
		return xyseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYAreaChart("XYAreaRenderer2Demo1", "Domain (X)", "Range (Y)", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setRenderer(new XYAreaRenderer2());
		xyplot.setForegroundAlpha(0.65F);
		ValueAxis valueaxis = xyplot.getDomainAxis();
		valueaxis.setTickMarkPaint(Color.black);
		valueaxis.setLowerMargin(0.0D);
		valueaxis.setUpperMargin(0.0D);
		ValueAxis valueaxis1 = xyplot.getRangeAxis();
		valueaxis1.setTickMarkPaint(Color.black);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		return new ChartPanel(createChart(createDataset()));
	}

	public static void main(String args[])
	{
		XYAreaRenderer2Demo1 xyarearenderer2demo1 = new XYAreaRenderer2Demo1("XYAreaRenderer2Demo1");
		xyarearenderer2demo1.pack();
		RefineryUtilities.centerFrameOnScreen(xyarearenderer2demo1);
		xyarearenderer2demo1.setVisible(true);
	}
}
