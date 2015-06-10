// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GridBandDemo1 extends ApplicationFrame
{

	public GridBandDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createScatterPlot("Grid Band Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, false, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setNoDataMessage("NO DATA");
		xyplot.setRangeZeroBaselineVisible(true);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		xyplot.setDomainTickBandPaint(new Color(0, 100, 0, 50));
		xyplot.setRangeTickBandPaint(new Color(0, 100, 0, 50));
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		XYSeries xyseries = new XYSeries("Random Data");
		for (int i = 0; i < 100; i++)
			xyseries.add(Math.random() + 1.0D, Math.random() + 1.0D);

		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		JFreeChart jfreechart = createChart(xyseriescollection);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		GridBandDemo1 gridbanddemo1 = new GridBandDemo1("JFreeChart: GridBandDemo1.java");
		gridbanddemo1.pack();
		RefineryUtilities.centerFrameOnScreen(gridbanddemo1);
		gridbanddemo1.setVisible(true);
	}
}
