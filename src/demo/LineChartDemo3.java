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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LineChartDemo3 extends ApplicationFrame
{

	public LineChartDemo3(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	private static XYDataset createDataset()
	{
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		for (int i = 0; i < 10; i++)
		{
			XYSeries xyseries = new XYSeries("S" + i);
			for (int j = 0; j < 10; j++)
				xyseries.add(j, Math.random() * 100D);

			xyseriescollection.addSeries(xyseries);
		}

		return xyseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Line Chart Demo 3", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		xyplot.setDomainZeroBaselineVisible(true);
		xyplot.setRangeZeroBaselineVisible(true);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);
		xylineandshaperenderer.setDrawOutlines(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}

	public static void main(String args[])
	{
		LineChartDemo3 linechartdemo3 = new LineChartDemo3("JFreeChart: LineChartDemo3.java");
		linechartdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(linechartdemo3);
		linechartdemo3.setVisible(true);
	}
}
