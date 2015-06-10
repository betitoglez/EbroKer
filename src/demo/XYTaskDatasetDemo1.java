// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.gantt.*;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYTaskDatasetDemo1 extends ApplicationFrame
{

	public XYTaskDatasetDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYBarChart("XYTaskDatasetDemo1", "Resource", false, "Timing", intervalxydataset, PlotOrientation.HORIZONTAL, true, false, false);
		jfreechart.setBackgroundPaint(Color.white);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setRangePannable(true);
		SymbolAxis symbolaxis = new SymbolAxis("Series", new String[] {
			"Team A", "Team B", "Team C", "Team D"
		});
		symbolaxis.setGridBandsVisible(false);
		xyplot.setDomainAxis(symbolaxis);
		XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
		xybarrenderer.setUseYInterval(true);
		xyplot.setRangeAxis(new DateAxis("Timing"));
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	private static IntervalXYDataset createDataset()
	{
		return new XYTaskDataset(createTasks());
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
		taskseriescollection.add(taskseries);
		taskseriescollection.add(taskseries1);
		taskseriescollection.add(taskseries2);
		taskseriescollection.add(taskseries3);
		return taskseriescollection;
	}

	public static void main(String args[])
	{
		XYTaskDatasetDemo1 xytaskdatasetdemo1 = new XYTaskDatasetDemo1("JFreeChart : XYTaskDatasetDemo1.java");
		xytaskdatasetdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xytaskdatasetdemo1);
		xytaskdatasetdemo1.setVisible(true);
	}
}
