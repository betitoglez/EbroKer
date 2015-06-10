// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYSeriesDemo1 extends ApplicationFrame
{

	public XYSeriesDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("XY Series Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		NumberAxis numberaxis = new NumberAxis(null);
		xyplot.setDomainAxis(1, numberaxis);
		NumberAxis numberaxis1 = new NumberAxis(null);
		xyplot.setRangeAxis(1, numberaxis1);
		java.util.List list = Arrays.asList(new Integer[] {
			new Integer(0), new Integer(1)
		});
		xyplot.mapDatasetToDomainAxes(0, list);
		xyplot.mapDatasetToRangeAxes(0, list);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	private static XYDataset createDataset()
	{
		XYSeries xyseries = new XYSeries("Random Data");
		xyseries.add(1.0D, 500.19999999999999D);
		xyseries.add(5D, 694.10000000000002D);
		xyseries.add(4D, 100D);
		xyseries.add(12.5D, 734.39999999999998D);
		xyseries.add(17.300000000000001D, 453.19999999999999D);
		xyseries.add(21.199999999999999D, 500.19999999999999D);
		xyseries.add(21.899999999999999D, null);
		xyseries.add(25.600000000000001D, 734.39999999999998D);
		xyseries.add(30D, 453.19999999999999D);
		return new XYSeriesCollection(xyseries);
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
		XYSeriesDemo1 xyseriesdemo1 = new XYSeriesDemo1("JFreeChart: XYSeriesDemo1.java");
		xyseriesdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xyseriesdemo1);
		xyseriesdemo1.setVisible(true);
	}
}
