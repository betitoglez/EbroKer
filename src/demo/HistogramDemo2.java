// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.statistics.SimpleHistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class HistogramDemo2 extends ApplicationFrame
{

	public HistogramDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static IntervalXYDataset createDataset()
	{
		SimpleHistogramDataset simplehistogramdataset = new SimpleHistogramDataset("Series 1");
		SimpleHistogramBin simplehistogrambin = new SimpleHistogramBin(0.0D, 1.0D, true, false);
		SimpleHistogramBin simplehistogrambin1 = new SimpleHistogramBin(1.0D, 2D, true, false);
		SimpleHistogramBin simplehistogrambin2 = new SimpleHistogramBin(2D, 3D, true, false);
		SimpleHistogramBin simplehistogrambin3 = new SimpleHistogramBin(3D, 4D, true, true);
		simplehistogrambin.setItemCount(1);
		simplehistogrambin1.setItemCount(10);
		simplehistogrambin2.setItemCount(15);
		simplehistogrambin3.setItemCount(20);
		simplehistogramdataset.addBin(simplehistogrambin);
		simplehistogramdataset.addBin(simplehistogrambin1);
		simplehistogramdataset.addBin(simplehistogrambin2);
		simplehistogramdataset.addBin(simplehistogrambin3);
		return simplehistogramdataset;
	}

	private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createHistogram("HistogramDemo2", null, null, intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setForegroundAlpha(0.85F);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
		xybarrenderer.setDrawBarOutline(false);
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
		HistogramDemo2 histogramdemo2 = new HistogramDemo2("JFreeChart: HistogramDemo2.java");
		histogramdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(histogramdemo2);
		histogramdemo2.setVisible(true);
	}
}
