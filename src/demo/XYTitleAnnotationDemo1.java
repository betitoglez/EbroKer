// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;

public class XYTitleAnnotationDemo1 extends ApplicationFrame
{

	public XYTitleAnnotationDemo1(String s)
	{
		super(s);
		ChartPanel chartpanel = (ChartPanel)createDemoPanel();
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setMouseZoomable(true);
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", xydataset, false, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		LegendTitle legendtitle = new LegendTitle(xyplot);
		legendtitle.setItemFont(new Font("Dialog", 0, 9));
		legendtitle.setBackgroundPaint(new Color(200, 200, 255, 100));
		legendtitle.setFrame(new BlockBorder(Color.white));
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		XYTitleAnnotation xytitleannotation = new XYTitleAnnotation(0.97999999999999998D, 0.02D, legendtitle, RectangleAnchor.BOTTOM_RIGHT);
		xytitleannotation.setMaxWidth(0.47999999999999998D);
		xyplot.addAnnotation(xytitleannotation);
		org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		if (xyitemrenderer instanceof XYLineAndShapeRenderer)
		{
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
			xylineandshaperenderer.setBaseShapesVisible(true);
			xylineandshaperenderer.setBaseShapesFilled(true);
		}
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		ValueAxis valueaxis = xyplot.getRangeAxis();
		valueaxis.setLowerMargin(0.34999999999999998D);
		return jfreechart;
	}

	private static XYDataset createDataset()
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

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		XYTitleAnnotationDemo1 xytitleannotationdemo1 = new XYTitleAnnotationDemo1("XYTitleAnnotationDemo1");
		xytitleannotationdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xytitleannotationdemo1);
		xytitleannotationdemo1.setVisible(true);
	}
}
