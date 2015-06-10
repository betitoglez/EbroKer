// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.io.PrintStream;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.StandardTickUnitSource;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class SmallNumberDemo extends ApplicationFrame
{

	public SmallNumberDemo(String s)
	{
		super(s);
		XYSeries xyseries = new XYSeries("Small Numbers");
		xyseries.add(1.0000000000000001E-005D, 9.9999999999999998E-017D);
		xyseries.add(5.0000000000000002E-005D, 2E-012D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Small Number Demo", "X", "Y", xyseriescollection, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
		numberaxis.setStandardTickUnits(new StandardTickUnitSource());
		NumberAxis numberaxis1 = (NumberAxis)xyplot.getRangeAxis();
		numberaxis1.setStandardTickUnits(new StandardTickUnitSource());
		numberaxis1.setAutoRangeMinimumSize(4.9406564584124654E-324D);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartpanel);
	}

	public static void main(String args[])
	{
		System.out.println("Min Double: 4.9E-324");
		SmallNumberDemo smallnumberdemo = new SmallNumberDemo("Small Number Demo");
		smallnumberdemo.pack();
		RefineryUtilities.centerFrameOnScreen(smallnumberdemo);
		smallnumberdemo.setVisible(true);
	}
}
