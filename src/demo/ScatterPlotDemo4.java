// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			SampleXYDataset2

public class ScatterPlotDemo4 extends ApplicationFrame
{

	public ScatterPlotDemo4(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static JPanel createDemoPanel()
	{
		SampleXYDataset2 samplexydataset2 = new SampleXYDataset2();
		JFreeChart jfreechart = ChartFactory.createScatterPlot("Scatter Plot Demo 4", "X", "Y", samplexydataset2, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setRangeTickBandPaint(new Color(200, 200, 100, 100));
		XYDotRenderer xydotrenderer = new XYDotRenderer();
		xydotrenderer.setDotWidth(4);
		xydotrenderer.setDotHeight(4);
		xyplot.setRenderer(xydotrenderer);
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		xyplot.getRangeAxis().setInverted(true);
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		ScatterPlotDemo4 scatterplotdemo4 = new ScatterPlotDemo4("JFreeChart: ScatterPlotDemo4.java");
		scatterplotdemo4.pack();
		RefineryUtilities.centerFrameOnScreen(scatterplotdemo4);
		scatterplotdemo4.setVisible(true);
	}
}
