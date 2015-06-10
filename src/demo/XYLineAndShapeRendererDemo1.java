// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYLineAndShapeRendererDemo1 extends ApplicationFrame
{

	public XYLineAndShapeRendererDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("XYLineAndShapeRenderer Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
		xylineandshaperenderer.setSeriesLinesVisible(0, true);
		xylineandshaperenderer.setSeriesShapesVisible(0, false);
		xylineandshaperenderer.setSeriesLinesVisible(1, false);
		xylineandshaperenderer.setSeriesShapesVisible(1, true);
		xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		xylineandshaperenderer.setDefaultEntityRadius(6);
		xyplot.setRenderer(xylineandshaperenderer);
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
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		XYLineAndShapeRendererDemo1 xylineandshaperendererdemo1 = new XYLineAndShapeRendererDemo1("JFreeChart: XYLineAndShapeRendererDemo1.java");
		xylineandshaperendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xylineandshaperendererdemo1);
		xylineandshaperendererdemo1.setVisible(true);
	}
}
