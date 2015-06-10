// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.*;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;

public class DualAxisDemo2 extends ApplicationFrame
{

	public DualAxisDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static XYDataset createDataset1()
	{
		TimeSeries timeseries = new TimeSeries("Random Data 1");
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
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	private static XYDataset createDataset2()
	{
		TimeSeries timeseries = new TimeSeries("Random Data 2");
		timeseries.add(new Month(2, 2001), 429.60000000000002D);
		timeseries.add(new Month(3, 2001), 323.19999999999999D);
		timeseries.add(new Month(4, 2001), 417.19999999999999D);
		timeseries.add(new Month(5, 2001), 624.10000000000002D);
		timeseries.add(new Month(6, 2001), 422.60000000000002D);
		timeseries.add(new Month(7, 2001), 619.20000000000005D);
		timeseries.add(new Month(8, 2001), 416.5D);
		timeseries.add(new Month(9, 2001), 512.70000000000005D);
		timeseries.add(new Month(10, 2001), 501.5D);
		timeseries.add(new Month(11, 2001), 306.10000000000002D);
		timeseries.add(new Month(12, 2001), 410.30000000000001D);
		timeseries.add(new Month(1, 2002), 511.69999999999999D);
		timeseries.add(new Month(2, 2002), 611D);
		timeseries.add(new Month(3, 2002), 709.60000000000002D);
		timeseries.add(new Month(4, 2002), 613.20000000000005D);
		timeseries.add(new Month(5, 2002), 711.60000000000002D);
		timeseries.add(new Month(6, 2002), 708.79999999999995D);
		timeseries.add(new Month(7, 2002), 501.60000000000002D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	private static JFreeChart createChart()
	{
		XYDataset xydataset = createDataset1();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Dual Axis Demo 2", "Date", "Price Per Unit", xydataset, false, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		NumberAxis numberaxis = new NumberAxis("Secondary");
		numberaxis.setAutoRangeIncludesZero(false);
		xyplot.setRangeAxis(1, numberaxis);
		xyplot.setDataset(1, createDataset2());
		xyplot.mapDatasetToRangeAxis(1, 1);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
		if (xyitemrenderer instanceof XYLineAndShapeRenderer)
		{
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
			xylineandshaperenderer.setBaseShapesVisible(true);
			xylineandshaperenderer.setBaseShapesFilled(true);
		}
		XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer();
		xylineandshaperenderer1.setSeriesPaint(0, Color.black);
		xylineandshaperenderer1.setBaseShapesVisible(true);
		xylineandshaperenderer1.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
		xyplot.setRenderer(1, xylineandshaperenderer1);
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		LegendTitle legendtitle = new LegendTitle(xyitemrenderer);
		LegendTitle legendtitle1 = new LegendTitle(xylineandshaperenderer1);
		BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
		blockcontainer.add(legendtitle, RectangleEdge.LEFT);
		blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
		blockcontainer.add(new EmptyBlock(2000D, 0.0D));
		CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
		compositetitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(compositetitle);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		DualAxisDemo2 dualaxisdemo2 = new DualAxisDemo2("JFreeChart: DualAxisDemo2.java");
		dualaxisdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(dualaxisdemo2);
		dualaxisdemo2.setVisible(true);
	}
}
