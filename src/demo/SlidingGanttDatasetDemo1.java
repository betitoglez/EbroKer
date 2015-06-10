// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.gantt.*;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.*;
import org.jfree.ui.*;

public class SlidingGanttDatasetDemo1 extends ApplicationFrame
{
	static class DemoPanel extends JPanel
		implements ChangeListener
	{

		JScrollBar scroller;
		SlidingGanttCategoryDataset dataset;

		public static GanttCategoryDataset createDataset()
		{
			TaskSeries taskseries = new TaskSeries("Scheduled");
			Day day = new Day();
			Day day1 = new Day();
			for (int i = 0; i < 50; i++)
			{
				int j = (int)(Math.random() * 10D) + 1;
				for (int k = 0; k < j; k++)
					day1 = (Day)day1.next();

				taskseries.add(new Task("Task " + i, new Date(day.getMiddleMillisecond()), new Date(day1.getMiddleMillisecond())));
				day = (Day)day1.next();
				day1 = (Day)day1.next();
			}

			TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
			taskseriescollection.add(taskseries);
			return taskseriescollection;
		}

		private static JFreeChart createChart(SlidingGanttCategoryDataset slidingganttcategorydataset)
		{
			JFreeChart jfreechart = ChartFactory.createGanttChart("Gantt Chart Demo", "Task", "Date", slidingganttcategorydataset, true, true, false);
			CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
			Hour hour = new Hour(1, 14, 5, 2008);
			for (int i = 0; i < 12; i++)
			{
				IntervalMarker intervalmarker = new IntervalMarker(hour.getFirstMillisecond(), hour.getLastMillisecond(), Color.lightGray);
				categoryplot.addRangeMarker(intervalmarker, Layer.BACKGROUND);
				hour = (Hour)hour.next().next();
			}

			categoryplot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10F);
			DateAxis dateaxis = (DateAxis)categoryplot.getRangeAxis();
			dateaxis.setRange(DatasetUtilities.findRangeBounds(slidingganttcategorydataset.getUnderlyingDataset(), true));
			GanttRenderer ganttrenderer = (GanttRenderer)categoryplot.getRenderer();
			ganttrenderer.setDrawBarOutline(false);
			ganttrenderer.setShadowVisible(false);
			return jfreechart;
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset.setFirstCategoryIndex(scroller.getValue());
		}

		public DemoPanel()
		{
			super(new BorderLayout());
			dataset = new SlidingGanttCategoryDataset(createDataset(), 0, 15);
			JFreeChart jfreechart = createChart(dataset);
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			scroller = new JScrollBar(1, 0, 15, 0, 50);
			add(chartpanel);
			scroller.getModel().addChangeListener(this);
			JPanel jpanel = new JPanel(new BorderLayout());
			jpanel.add(scroller);
			jpanel.setBorder(BorderFactory.createEmptyBorder(66, 2, 2, 2));
			add(jpanel, "East");
		}
	}


	public SlidingGanttDatasetDemo1(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new DemoPanel();
	}

	public static void main(String args[])
	{
		SlidingGanttDatasetDemo1 slidingganttdatasetdemo1 = new SlidingGanttDatasetDemo1("JFreeChart: SlidingGanttDatasetDemo1.java");
		slidingganttdatasetdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(slidingganttdatasetdemo1);
		slidingganttdatasetdemo1.setVisible(true);
	}
}
