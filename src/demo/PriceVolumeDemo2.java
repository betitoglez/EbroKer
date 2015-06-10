// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.time.*;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PriceVolumeDemo2 extends ApplicationFrame
{

	public PriceVolumeDemo2(String s)
	{
		super(s);
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart()
	{
		OHLCDataset ohlcdataset = createPriceDataset();
		String s = "Sun Microsystems (SUNW)";
		JFreeChart jfreechart = ChartFactory.createHighLowChart(s, "Date", "Price", ohlcdataset, true);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setLowerMargin(0.01D);
		dateaxis.setUpperMargin(0.01D);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setLowerMargin(0.59999999999999998D);
		numberaxis.setAutoRangeIncludesZero(false);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
		NumberAxis numberaxis1 = new NumberAxis("Volume");
		numberaxis1.setUpperMargin(1.0D);
		xyplot.setRangeAxis(1, numberaxis1);
		xyplot.setDataset(1, createVolumeDataset());
		xyplot.setRangeAxis(1, numberaxis1);
		xyplot.mapDatasetToRangeAxis(1, 1);
		XYBarRenderer xybarrenderer = new XYBarRenderer();
		xybarrenderer.setDrawBarOutline(false);
		xybarrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
		xyplot.setRenderer(1, xybarrenderer);
		ChartUtilities.applyCurrentTheme(jfreechart);
		xybarrenderer.setShadowVisible(false);
		xybarrenderer.setBarPainter(new StandardXYBarPainter());
		return jfreechart;
	}

	private static OHLCDataset createPriceDataset()
	{
		OHLCSeries ohlcseries = new OHLCSeries("SUNW");
		ohlcseries.add(new Day(12, 4, 2007), 5.9000000000000004D, 5.96D, 5.8700000000000001D, 5.9000000000000004D);
		ohlcseries.add(new Day(13, 4, 2007), 5.8899999999999997D, 5.9000000000000004D, 5.7800000000000002D, 5.7999999999999998D);
		ohlcseries.add(new Day(16, 4, 2007), 5.8099999999999996D, 5.8700000000000001D, 5.79D, 5.8600000000000003D);
		ohlcseries.add(new Day(17, 4, 2007), 5.8700000000000001D, 5.9500000000000002D, 5.8200000000000003D, 5.9500000000000002D);
		ohlcseries.add(new Day(18, 4, 2007), 5.8899999999999997D, 5.9500000000000002D, 5.8700000000000001D, 5.9400000000000004D);
		ohlcseries.add(new Day(19, 4, 2007), 5.8700000000000001D, 5.96D, 5.8600000000000003D, 5.8899999999999997D);
		ohlcseries.add(new Day(20, 4, 2007), 5.9400000000000004D, 5.9500000000000002D, 5.8700000000000001D, 5.9299999999999997D);
		ohlcseries.add(new Day(23, 4, 2007), 5.9299999999999997D, 5.9500000000000002D, 5.8899999999999997D, 5.9199999999999999D);
		ohlcseries.add(new Day(24, 4, 2007), 5.9299999999999997D, 5.9699999999999998D, 5.8799999999999999D, 5.9400000000000004D);
		ohlcseries.add(new Day(25, 4, 2007), 5.5800000000000001D, 5.5800000000000001D, 5.1699999999999999D, 5.2699999999999996D);
		ohlcseries.add(new Day(26, 4, 2007), 5.25D, 5.2999999999999998D, 5.2000000000000002D, 5.25D);
		ohlcseries.add(new Day(27, 4, 2007), 5.2300000000000004D, 5.2800000000000002D, 5.1900000000000004D, 5.2599999999999998D);
		ohlcseries.add(new Day(30, 4, 2007), 5.25D, 5.2599999999999998D, 5.2000000000000002D, 5.2199999999999998D);
		ohlcseries.add(new Day(1, 5, 2007), 5.2300000000000004D, 5.2400000000000002D, 4.9900000000000002D, 5.0899999999999999D);
		ohlcseries.add(new Day(2, 5, 2007), 5.0899999999999999D, 5.1699999999999999D, 5.0800000000000001D, 5.1500000000000004D);
		ohlcseries.add(new Day(3, 5, 2007), 5.1399999999999997D, 5.2000000000000002D, 5.1100000000000003D, 5.1799999999999997D);
		ohlcseries.add(new Day(4, 5, 2007), 5.21D, 5.2999999999999998D, 5.2000000000000002D, 5.2400000000000002D);
		ohlcseries.add(new Day(7, 5, 2007), 5.2199999999999998D, 5.2800000000000002D, 5.21D, 5.2199999999999998D);
		ohlcseries.add(new Day(8, 5, 2007), 5.2400000000000002D, 5.2699999999999996D, 5.21D, 5.2199999999999998D);
		ohlcseries.add(new Day(9, 5, 2007), 5.21D, 5.2199999999999998D, 5.1500000000000004D, 5.2000000000000002D);
		ohlcseries.add(new Day(10, 5, 2007), 5.1600000000000001D, 5.1900000000000004D, 5.1299999999999999D, 5.1299999999999999D);
		ohlcseries.add(new Day(11, 5, 2007), 5.1399999999999997D, 5.1799999999999997D, 5.1200000000000001D, 5.1500000000000004D);
		ohlcseries.add(new Day(14, 5, 2007), 5.1600000000000001D, 5.29D, 5.1600000000000001D, 5.2199999999999998D);
		ohlcseries.add(new Day(15, 5, 2007), 5.2199999999999998D, 5.2300000000000004D, 5.1299999999999999D, 5.1399999999999997D);
		ohlcseries.add(new Day(16, 5, 2007), 5.1399999999999997D, 5.1600000000000001D, 5.0700000000000003D, 5.1200000000000001D);
		ohlcseries.add(new Day(17, 5, 2007), 5.3499999999999996D, 5.4299999999999997D, 5.2999999999999998D, 5.2999999999999998D);
		ohlcseries.add(new Day(18, 5, 2007), 5.3499999999999996D, 5.3499999999999996D, 5.2599999999999998D, 5.29D);
		ohlcseries.add(new Day(21, 5, 2007), 5.29D, 5.3899999999999997D, 5.2400000000000002D, 5.3899999999999997D);
		ohlcseries.add(new Day(22, 5, 2007), 5.3899999999999997D, 5.4199999999999999D, 5.3399999999999999D, 5.3799999999999999D);
		ohlcseries.add(new Day(23, 5, 2007), 5.3700000000000001D, 5.4299999999999997D, 5.3600000000000003D, 5.3799999999999999D);
		ohlcseries.add(new Day(24, 5, 2007), 5.3600000000000003D, 5.3700000000000001D, 5.1500000000000004D, 5.1500000000000004D);
		ohlcseries.add(new Day(25, 5, 2007), 5.1799999999999997D, 5.21D, 5.1500000000000004D, 5.1600000000000001D);
		ohlcseries.add(new Day(29, 5, 2007), 5.1600000000000001D, 5.1699999999999999D, 5D, 5.0599999999999996D);
		ohlcseries.add(new Day(30, 5, 2007), 5.0099999999999998D, 5.1500000000000004D, 4.96D, 5.1200000000000001D);
		ohlcseries.add(new Day(31, 5, 2007), 5.1600000000000001D, 5.1900000000000004D, 5.0700000000000003D, 5.0999999999999996D);
		ohlcseries.add(new Day(1, 6, 2007), 5.1200000000000001D, 5.2000000000000002D, 5.1200000000000001D, 5.1799999999999997D);
		ohlcseries.add(new Day(4, 6, 2007), 5.1500000000000004D, 5.2400000000000002D, 5.0700000000000003D, 5.1100000000000003D);
		ohlcseries.add(new Day(5, 6, 2007), 5.0800000000000001D, 5.0800000000000001D, 4.9699999999999998D, 5.0700000000000003D);
		ohlcseries.add(new Day(6, 6, 2007), 5.0300000000000002D, 5.0499999999999998D, 4.9900000000000002D, 5.0199999999999996D);
		ohlcseries.add(new Day(7, 6, 2007), 5D, 5.0499999999999998D, 4.9699999999999998D, 4.9699999999999998D);
		ohlcseries.add(new Day(8, 6, 2007), 4.9800000000000004D, 5.04D, 4.9500000000000002D, 5.04D);
		ohlcseries.add(new Day(11, 6, 2007), 5.0499999999999998D, 5.0599999999999996D, 4.9500000000000002D, 4.96D);
		ohlcseries.add(new Day(12, 6, 2007), 4.9500000000000002D, 5.0099999999999998D, 4.9199999999999999D, 4.9199999999999999D);
		ohlcseries.add(new Day(13, 6, 2007), 4.9500000000000002D, 4.9900000000000002D, 4.8300000000000001D, 4.9900000000000002D);
		ohlcseries.add(new Day(14, 6, 2007), 5.0499999999999998D, 5.0999999999999996D, 5.0199999999999996D, 5.0800000000000001D);
		ohlcseries.add(new Day(15, 6, 2007), 5.1299999999999999D, 5.1299999999999999D, 5.0300000000000002D, 5.0499999999999998D);
		ohlcseries.add(new Day(18, 6, 2007), 5.0599999999999996D, 5.0700000000000003D, 5.0099999999999998D, 5.0499999999999998D);
		ohlcseries.add(new Day(19, 6, 2007), 5.0300000000000002D, 5.1600000000000001D, 5.0300000000000002D, 5.0999999999999996D);
		ohlcseries.add(new Day(20, 6, 2007), 5.1399999999999997D, 5.1500000000000004D, 5.0499999999999998D, 5.0499999999999998D);
		ohlcseries.add(new Day(21, 6, 2007), 5.0700000000000003D, 5.1799999999999997D, 5.0599999999999996D, 5.1299999999999999D);
		ohlcseries.add(new Day(22, 6, 2007), 5.1100000000000003D, 5.1399999999999997D, 5.0800000000000001D, 5.0800000000000001D);
		ohlcseries.add(new Day(25, 6, 2007), 5.0800000000000001D, 5.0999999999999996D, 4.9900000000000002D, 5.0199999999999996D);
		ohlcseries.add(new Day(26, 6, 2007), 5.04D, 5.0999999999999996D, 4.9900000000000002D, 5.0099999999999998D);
		ohlcseries.add(new Day(27, 6, 2007), 5D, 5.0899999999999999D, 4.9900000000000002D, 5.0700000000000003D);
		ohlcseries.add(new Day(28, 6, 2007), 5.0800000000000001D, 5.1900000000000004D, 5.0700000000000003D, 5.1600000000000001D);
		ohlcseries.add(new Day(29, 6, 2007), 5.1900000000000004D, 5.3300000000000001D, 5.1600000000000001D, 5.2599999999999998D);
		ohlcseries.add(new Day(2, 7, 2007), 5.1299999999999999D, 5.3300000000000001D, 5.1200000000000001D, 5.1900000000000004D);
		ohlcseries.add(new Day(3, 7, 2007), 5.2000000000000002D, 5.2400000000000002D, 5.1699999999999999D, 5.2199999999999998D);
		ohlcseries.add(new Day(5, 7, 2007), 5.2800000000000002D, 5.3499999999999996D, 5.2400000000000002D, 5.3300000000000001D);
		ohlcseries.add(new Day(6, 7, 2007), 5.3600000000000003D, 5.4900000000000002D, 5.3399999999999999D, 5.3799999999999999D);
		ohlcseries.add(new Day(9, 7, 2007), 5.4000000000000004D, 5.4400000000000004D, 5.3099999999999996D, 5.3300000000000001D);
		ohlcseries.add(new Day(10, 7, 2007), 5.29D, 5.4100000000000001D, 5.2800000000000002D, 5.3200000000000003D);
		ohlcseries.add(new Day(11, 7, 2007), 5.29D, 5.3799999999999999D, 5.29D, 5.3799999999999999D);
		ohlcseries.add(new Day(12, 7, 2007), 5.3799999999999999D, 5.4299999999999997D, 5.3300000000000001D, 5.4299999999999997D);
		ohlcseries.add(new Day(13, 7, 2007), 5.4199999999999999D, 5.4299999999999997D, 5.3399999999999999D, 5.3700000000000001D);
		ohlcseries.add(new Day(16, 7, 2007), 5.3300000000000001D, 5.3799999999999999D, 5.2999999999999998D, 5.3399999999999999D);
		OHLCSeriesCollection ohlcseriescollection = new OHLCSeriesCollection();
		ohlcseriescollection.addSeries(ohlcseries);
		return ohlcseriescollection;
	}

	private static IntervalXYDataset createVolumeDataset()
	{
		TimeSeries timeseries = new TimeSeries("Volume");
		timeseries.add(new Day(12, 4, 2007), 49646800D);
		timeseries.add(new Day(13, 4, 2007), 67319300D);
		timeseries.add(new Day(16, 4, 2007), 56840200D);
		timeseries.add(new Day(17, 4, 2007), 40752300D);
		timeseries.add(new Day(18, 4, 2007), 42533800D);
		timeseries.add(new Day(19, 4, 2007), 34232100D);
		timeseries.add(new Day(20, 4, 2007), 32437600D);
		timeseries.add(new Day(23, 4, 2007), 28755900D);
		timeseries.add(new Day(24, 4, 2007), 74503300D);
		timeseries.add(new Day(25, 4, 2007), 322214000D);
		timeseries.add(new Day(26, 4, 2007), 96141700D);
		timeseries.add(new Day(27, 4, 2007), 62326200D);
		timeseries.add(new Day(30, 4, 2007), 52334400D);
		timeseries.add(new Day(1, 5, 2007), 133096100D);
		timeseries.add(new Day(2, 5, 2007), 93874500D);
		timeseries.add(new Day(3, 5, 2007), 101011400D);
		timeseries.add(new Day(4, 5, 2007), 56629700D);
		timeseries.add(new Day(7, 5, 2007), 43302200D);
		timeseries.add(new Day(8, 5, 2007), 51456500D);
		timeseries.add(new Day(9, 5, 2007), 48230900D);
		timeseries.add(new Day(10, 5, 2007), 65536000D);
		timeseries.add(new Day(11, 5, 2007), 46469700D);
		timeseries.add(new Day(14, 5, 2007), 118580000D);
		timeseries.add(new Day(15, 5, 2007), 50774100D);
		timeseries.add(new Day(16, 5, 2007), 44590200D);
		timeseries.add(new Day(17, 5, 2007), 125482500D);
		timeseries.add(new Day(18, 5, 2007), 49987500D);
		timeseries.add(new Day(21, 5, 2007), 48387400D);
		timeseries.add(new Day(22, 5, 2007), 67992600D);
		timeseries.add(new Day(23, 5, 2007), 45629200D);
		timeseries.add(new Day(24, 5, 2007), 123288600D);
		timeseries.add(new Day(25, 5, 2007), 47345300D);
		timeseries.add(new Day(29, 5, 2007), 90454000D);
		timeseries.add(new Day(30, 5, 2007), 73049200D);
		timeseries.add(new Day(31, 5, 2007), 60395400D);
		timeseries.add(new Day(1, 6, 2007), 48792300D);
		timeseries.add(new Day(4, 6, 2007), 109972800D);
		timeseries.add(new Day(5, 6, 2007), 198201200D);
		timeseries.add(new Day(6, 6, 2007), 121415700D);
		timeseries.add(new Day(7, 6, 2007), 56637400D);
		timeseries.add(new Day(8, 6, 2007), 64116600D);
		timeseries.add(new Day(11, 6, 2007), 60274800D);
		timeseries.add(new Day(12, 6, 2007), 93451300D);
		timeseries.add(new Day(13, 6, 2007), 103309000D);
		timeseries.add(new Day(14, 6, 2007), 103135400D);
		timeseries.add(new Day(15, 6, 2007), 104415900D);
		timeseries.add(new Day(18, 6, 2007), 51506900D);
		timeseries.add(new Day(19, 6, 2007), 74592100D);
		timeseries.add(new Day(20, 6, 2007), 69027600D);
		timeseries.add(new Day(21, 6, 2007), 97212500D);
		timeseries.add(new Day(22, 6, 2007), 51612000D);
		timeseries.add(new Day(25, 6, 2007), 63845400D);
		timeseries.add(new Day(26, 6, 2007), 84818400D);
		timeseries.add(new Day(27, 6, 2007), 73512900D);
		timeseries.add(new Day(28, 6, 2007), 75275100D);
		timeseries.add(new Day(29, 6, 2007), 104203900D);
		timeseries.add(new Day(2, 7, 2007), 66540400D);
		timeseries.add(new Day(3, 7, 2007), 28520800D);
		timeseries.add(new Day(5, 7, 2007), 47176300D);
		timeseries.add(new Day(6, 7, 2007), 70084800D);
		timeseries.add(new Day(9, 7, 2007), 91630800D);
		timeseries.add(new Day(10, 7, 2007), 114071700D);
		timeseries.add(new Day(11, 7, 2007), 34217900D);
		timeseries.add(new Day(12, 7, 2007), 30298000D);
		timeseries.add(new Day(13, 7, 2007), 40423500D);
		timeseries.add(new Day(16, 7, 2007), 39238000D);
		return new TimeSeriesCollection(timeseries);
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		PriceVolumeDemo2 pricevolumedemo2 = new PriceVolumeDemo2("JFreeChart: PriceVolumeDemo2.java");
		pricevolumedemo2.pack();
		RefineryUtilities.centerFrameOnScreen(pricevolumedemo2);
		pricevolumedemo2.setVisible(true);
	}
}
