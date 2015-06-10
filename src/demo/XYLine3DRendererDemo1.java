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
import org.jfree.chart.renderer.xy.XYLine3DRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYLine3DRendererDemo1 extends ApplicationFrame
{

	public XYLine3DRendererDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("XYLine3DRenderer Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		XYLine3DRenderer xyline3drenderer = new XYLine3DRenderer();
		xyline3drenderer.setWallPaint(Color.gray);
		xyline3drenderer.setXOffset(2D);
		xyline3drenderer.setYOffset(3D);
		xyline3drenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		xyline3drenderer.setDefaultEntityRadius(6);
		xyplot.setRenderer(xyline3drenderer);
		return jfreechart;
	}

	private static XYDataset createDataset()
	{
		XYSeries xyseries = new XYSeries("Series 1");
		xyseries.add(1.0D, 3.2999999999999998D);
		xyseries.add(2D, 4.4000000000000004D);
		xyseries.add(3D, 1.7D);
		XYSeries xyseries1 = new XYSeries("Series 2");
		xyseries1.add(1.0D, 7.2999999999999998D);
		xyseries1.add(2D, 0.0D);
		xyseries1.add(3D, 9.5999999999999996D);
		xyseries1.add(4D, 5.5999999999999996D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		return xyseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		XYLine3DRendererDemo1 xyline3drendererdemo1 = new XYLine3DRendererDemo1("JFreeChart: XYLine3DRendererDemo1.java");
		xyline3drendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xyline3drendererdemo1);
		xyline3drendererdemo1.setVisible(true);
	}
}
