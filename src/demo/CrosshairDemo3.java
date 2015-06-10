// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			DemoPanel

public class CrosshairDemo3 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
		implements ChangeListener
	{

		private JFreeChart chart;
		private JSlider slider;

		private JFreeChart createChart(XYDataset xydataset)
		{
			JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", xydataset, true, true, false);
			XYPlot xyplot = (XYPlot)jfreechart.getPlot();
			xyplot.setDomainCrosshairVisible(true);
			xyplot.setDomainCrosshairLockedOnData(false);
			xyplot.setRangeCrosshairVisible(false);
			org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
			if (xyitemrenderer instanceof XYLineAndShapeRenderer)
			{
				XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
				xylineandshaperenderer.setBaseShapesVisible(true);
				xylineandshaperenderer.setBaseShapesFilled(true);
			}
			DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
			dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
			return jfreechart;
		}

		private XYDataset createDataset()
		{
			TimeSeries timeseries = new TimeSeries("L&G European Index Trust");
			timeseries.add(new Month(2, 2001), 181.80000000000001D);
			timeseries.add(new Month(3, 2001), 167.30000000000001D);
			timeseries.add(new Month(4, 2001), 153.80000000000001D);
			timeseries.add(new Month(5, 2001), 167.59999999999999D);
			timeseries.add(new Month(6, 2001), 158.80000000000001D);
			timeseries.add(new Month(7, 2001), 148.30000000000001D);
			timeseries.add(new Month(8, 2001), 153.90000000000001D);
			timeseries.add(new Month(9, 2001), 142.69999999999999D);
			timeseries.add(new Month(10, 2001), 123.2D);
			timeseries.add(new Month(11, 2001), 131.80000000000001D);
			timeseries.add(new Month(12, 2001), 139.59999999999999D);
			timeseries.add(new Month(1, 2002), 142.90000000000001D);
			timeseries.add(new Month(2, 2002), 138.69999999999999D);
			timeseries.add(new Month(3, 2002), 137.30000000000001D);
			timeseries.add(new Month(4, 2002), 143.90000000000001D);
			timeseries.add(new Month(5, 2002), 139.80000000000001D);
			timeseries.add(new Month(6, 2002), 137D);
			timeseries.add(new Month(7, 2002), 132.80000000000001D);
			TimeSeries timeseries1 = new TimeSeries("L&G UK Index Trust");
			timeseries1.add(new Month(2, 2001), 129.59999999999999D);
			timeseries1.add(new Month(3, 2001), 123.2D);
			timeseries1.add(new Month(4, 2001), 117.2D);
			timeseries1.add(new Month(5, 2001), 124.09999999999999D);
			timeseries1.add(new Month(6, 2001), 122.59999999999999D);
			timeseries1.add(new Month(7, 2001), 119.2D);
			timeseries1.add(new Month(8, 2001), 116.5D);
			timeseries1.add(new Month(9, 2001), 112.7D);
			timeseries1.add(new Month(10, 2001), 101.5D);
			timeseries1.add(new Month(11, 2001), 106.09999999999999D);
			timeseries1.add(new Month(12, 2001), 110.3D);
			timeseries1.add(new Month(1, 2002), 111.7D);
			timeseries1.add(new Month(2, 2002), 111D);
			timeseries1.add(new Month(3, 2002), 109.59999999999999D);
			timeseries1.add(new Month(4, 2002), 113.2D);
			timeseries1.add(new Month(5, 2002), 111.59999999999999D);
			timeseries1.add(new Month(6, 2002), 108.8D);
			timeseries1.add(new Month(7, 2002), 101.59999999999999D);
			TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
			timeseriescollection.addSeries(timeseries);
			timeseriescollection.addSeries(timeseries1);
			return timeseriescollection;
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			int i = slider.getValue();
			XYPlot xyplot = (XYPlot)chart.getPlot();
			ValueAxis valueaxis = xyplot.getDomainAxis();
			Range range = valueaxis.getRange();
			double d = valueaxis.getLowerBound() + ((double)i / 100D) * range.getLength();
			xyplot.setDomainCrosshairValue(d);
		}

		public MyDemoPanel()
		{
			super(new BorderLayout());
			XYDataset xydataset = createDataset();
			chart = createChart(xydataset);
			addChart(chart);
			ChartPanel chartpanel = new ChartPanel(chart);
			chartpanel.setPreferredSize(new Dimension(500, 270));
			chartpanel.setMouseZoomable(true);
			JPanel jpanel = new JPanel(new BorderLayout());
			slider = new JSlider(0, 100, 50);
			slider.addChangeListener(this);
			jpanel.add(slider);
			add(chartpanel);
			add(jpanel, "South");
		}
	}


	public CrosshairDemo3(String s)
	{
		super(s);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		CrosshairDemo3 crosshairdemo3 = new CrosshairDemo3("JFreeChart: CrosshairDemo3.java");
		crosshairdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(crosshairdemo3);
		crosshairdemo3.setVisible(true);
	}
}
