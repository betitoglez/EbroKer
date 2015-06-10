// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class HighLowChartDemo3 extends ApplicationFrame
{

	public HighLowChartDemo3(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static OHLCDataset createDataset1()
	{
		OHLCSeries ohlcseries = new OHLCSeries("Series 1");
		ohlcseries.add(new Day(24, 9, 2007), 50.5D, 53.200000000000003D, 49.799999999999997D, 50.100000000000001D);
		ohlcseries.add(new Day(25, 9, 2007), 50.200000000000003D, 51.200000000000003D, 47.799999999999997D, 48.100000000000001D);
		ohlcseries.add(new Day(26, 9, 2007), 48D, 49.200000000000003D, 45.299999999999997D, 47.399999999999999D);
		ohlcseries.add(new Day(27, 9, 2007), 47.5D, 48.299999999999997D, 46.799999999999997D, 46.799999999999997D);
		ohlcseries.add(new Day(28, 9, 2007), 46.600000000000001D, 47D, 45.100000000000001D, 46D);
		ohlcseries.add(new Day(1, 10, 2007), 46.600000000000001D, 47D, 45.100000000000001D, 46D);
		ohlcseries.add(new Day(2, 10, 2007), 47.5D, 48.299999999999997D, 46.799999999999997D, 46.799999999999997D);
		ohlcseries.add(new Day(3, 10, 2007), 48D, 49.200000000000003D, 45.299999999999997D, 47.399999999999999D);
		ohlcseries.add(new Day(4, 10, 2007), 50.200000000000003D, 51.200000000000003D, 47.799999999999997D, 48.100000000000001D);
		ohlcseries.add(new Day(5, 10, 2007), 50.5D, 53.200000000000003D, 49.799999999999997D, 50.100000000000001D);
		OHLCSeriesCollection ohlcseriescollection = new OHLCSeriesCollection();
		ohlcseriescollection.addSeries(ohlcseries);
		return ohlcseriescollection;
	}

	public static OHLCDataset createDataset2()
	{
		OHLCSeries ohlcseries = new OHLCSeries("Series 2");
		ohlcseries.add(new Day(24, 9, 2007), 5.5D, 6.2000000000000002D, 4.7999999999999998D, 5.9000000000000004D);
		ohlcseries.add(new Day(25, 9, 2007), 6D, 6.9000000000000004D, 6D, 6.7000000000000002D);
		ohlcseries.add(new Day(26, 9, 2007), 6.7999999999999998D, 7.5D, 6.4000000000000004D, 7.0999999999999996D);
		ohlcseries.add(new Day(27, 9, 2007), 7.2000000000000002D, 8.1999999999999993D, 7D, 7.9000000000000004D);
		ohlcseries.add(new Day(28, 9, 2007), 7.7999999999999998D, 8.5D, 7.7000000000000002D, 8.1999999999999993D);
		ohlcseries.add(new Day(1, 10, 2007), 8.1999999999999993D, 8.5D, 7.7000000000000002D, 7.7999999999999998D);
		ohlcseries.add(new Day(2, 10, 2007), 7.9000000000000004D, 8.1999999999999993D, 7D, 7.2000000000000002D);
		ohlcseries.add(new Day(3, 10, 2007), 7.0999999999999996D, 7.5D, 6.4000000000000004D, 6.7999999999999998D);
		ohlcseries.add(new Day(4, 10, 2007), 6D, 6.9000000000000004D, 6D, 6.7000000000000002D);
		ohlcseries.add(new Day(5, 10, 2007), 5.5D, 6.2000000000000002D, 4.7999999999999998D, 5.9000000000000004D);
		OHLCSeriesCollection ohlcseriescollection = new OHLCSeriesCollection();
		ohlcseriescollection.addSeries(ohlcseries);
		return ohlcseriescollection;
	}

	private static JFreeChart createChart(OHLCDataset ohlcdataset)
	{
		JFreeChart jfreechart = ChartFactory.createHighLowChart("OHLC Demo 3", "Time", "Price", ohlcdataset, true);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		HighLowRenderer highlowrenderer = (HighLowRenderer)xyplot.getRenderer();
		highlowrenderer.setBaseStroke(new BasicStroke(2.0F));
		highlowrenderer.setSeriesPaint(0, Color.blue);
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		NumberAxis numberaxis1 = new NumberAxis("Price 2");
		numberaxis1.setAutoRangeIncludesZero(false);
		xyplot.setRangeAxis(1, numberaxis1);
		xyplot.setDataset(1, createDataset2());
		xyplot.setRenderer(1, new CandlestickRenderer(10D));
		xyplot.mapDatasetToRangeAxis(1, 1);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset1());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		HighLowChartDemo3 highlowchartdemo3 = new HighLowChartDemo3("JFreeChart: HighLowChartDemo3.java");
		highlowchartdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(highlowchartdemo3);
		highlowchartdemo3.setVisible(true);
	}
}
