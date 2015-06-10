// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.gantt.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYTaskDatasetDemo2 extends ApplicationFrame
{

	public XYTaskDatasetDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static XYPlot createSubplot1(XYDataset xydataset)
	{
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
		xylineandshaperenderer.setUseFillPaint(true);
		xylineandshaperenderer.setBaseFillPaint(Color.white);
		xylineandshaperenderer.setBaseShape(new java.awt.geom.Ellipse2D.Double(-4D, -4D, 8D, 8D));
		xylineandshaperenderer.setAutoPopulateSeriesShape(false);
		NumberAxis numberaxis = new NumberAxis("Y");
		numberaxis.setLowerMargin(0.10000000000000001D);
		numberaxis.setUpperMargin(0.10000000000000001D);
		XYPlot xyplot = new XYPlot(xydataset, new DateAxis("Time"), numberaxis, xylineandshaperenderer);
		return xyplot;
	}

	private static XYPlot createSubplot2(IntervalXYDataset intervalxydataset)
	{
		DateAxis dateaxis = new DateAxis("Date/Time");
		SymbolAxis symbolaxis = new SymbolAxis("Resources", new String[] {
			"Team A", "Team B", "Team C", "Team D", "Team E"
		});
		symbolaxis.setGridBandsVisible(false);
		XYBarRenderer xybarrenderer = new XYBarRenderer();
		xybarrenderer.setUseYInterval(true);
		XYPlot xyplot = new XYPlot(intervalxydataset, dateaxis, symbolaxis, xybarrenderer);
		return xyplot;
	}

	private static JFreeChart createChart()
	{
		CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(new DateAxis("Date/Time"));
		combineddomainxyplot.setDomainPannable(true);
		combineddomainxyplot.add(createSubplot1(createDataset1()));
		combineddomainxyplot.add(createSubplot2(createDataset2()));
		JFreeChart jfreechart = new JFreeChart("XYTaskDatasetDemo2", combineddomainxyplot);
		jfreechart.setBackgroundPaint(Color.white);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		return new ChartPanel(createChart());
	}

	private static XYDataset createDataset1()
	{
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		TimeSeries timeseries = new TimeSeries("Time Series 1");
		timeseries.add(new Hour(0, new Day()), 20214.5D);
		timeseries.add(new Hour(4, new Day()), 73346.5D);
		timeseries.add(new Hour(8, new Day()), 54643.599999999999D);
		timeseries.add(new Hour(12, new Day()), 92683.800000000003D);
		timeseries.add(new Hour(16, new Day()), 110235.39999999999D);
		timeseries.add(new Hour(20, new Day()), 120742.5D);
		timeseries.add(new Hour(24, new Day()), 90654.5D);
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	private static IntervalXYDataset createDataset2()
	{
		XYTaskDataset xytaskdataset = new XYTaskDataset(createTasks());
		xytaskdataset.setTransposed(true);
		xytaskdataset.setSeriesWidth(0.59999999999999998D);
		return xytaskdataset;
	}

	private static TaskSeriesCollection createTasks()
	{
		TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
		TaskSeries taskseries = new TaskSeries("Team A");
		taskseries.add(new Task("T1a", new Hour(11, new Day())));
		taskseries.add(new Task("T1b", new Hour(14, new Day())));
		taskseries.add(new Task("T1c", new Hour(16, new Day())));
		TaskSeries taskseries1 = new TaskSeries("Team B");
		taskseries1.add(new Task("T2a", new Hour(13, new Day())));
		taskseries1.add(new Task("T2b", new Hour(19, new Day())));
		taskseries1.add(new Task("T2c", new Hour(21, new Day())));
		TaskSeries taskseries2 = new TaskSeries("Team C");
		taskseries2.add(new Task("T3a", new Hour(13, new Day())));
		taskseries2.add(new Task("T3b", new Hour(19, new Day())));
		taskseries2.add(new Task("T3c", new Hour(21, new Day())));
		TaskSeries taskseries3 = new TaskSeries("Team D");
		taskseries3.add(new Task("T4a", new Day()));
		TaskSeries taskseries4 = new TaskSeries("Team E");
		taskseries4.add(new Task("T5a", new Day()));
		taskseriescollection.add(taskseries);
		taskseriescollection.add(taskseries1);
		taskseriescollection.add(taskseries2);
		taskseriescollection.add(taskseries3);
		taskseriescollection.add(taskseries4);
		return taskseriescollection;
	}

	public static void main(String args[])
	{
		XYTaskDatasetDemo2 xytaskdatasetdemo2 = new XYTaskDatasetDemo2("JFreeChart : XYTaskDatasetDemo2.java");
		xytaskdatasetdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(xytaskdatasetdemo2);
		xytaskdatasetdemo2.setVisible(true);
	}
}
