// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.*;

// Referenced classes of package demo:
//			DemoPanel

public class ClusteredXYBarRendererDemo1 extends ApplicationFrame
{

	public ClusteredXYBarRendererDemo1(String s)
	{
		super(s);
		setContentPane(createDemoPanel());
	}

	private static JFreeChart createChart(String s, IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYBarChart(s, null, true, "Y", intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		ClusteredXYBarRenderer clusteredxybarrenderer = new ClusteredXYBarRenderer(0.20000000000000001D, false);
		xyplot.setRenderer(clusteredxybarrenderer);
		return jfreechart;
	}

	private static IntervalXYDataset createDataset()
	{
		TimeSeries timeseries = new TimeSeries("Series 1");
		timeseries.add(new Day(1, 1, 2003), 54.299999999999997D);
		timeseries.add(new Day(2, 1, 2003), 20.300000000000001D);
		timeseries.add(new Day(3, 1, 2003), 43.399999999999999D);
		timeseries.add(new Day(4, 1, 2003), -12D);
		TimeSeries timeseries1 = new TimeSeries("Series 2");
		timeseries1.add(new Day(1, 1, 2003), 8D);
		timeseries1.add(new Day(2, 1, 2003), 16D);
		timeseries1.add(new Day(3, 1, 2003), 21D);
		timeseries1.add(new Day(4, 1, 2003), 5D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		DemoPanel demopanel = new DemoPanel(new GridLayout(2, 2));
		demopanel.setPreferredSize(new Dimension(800, 600));
		IntervalXYDataset intervalxydataset = createDataset();
		JFreeChart jfreechart = createChart("Vertical", intervalxydataset);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
		xybarrenderer.setDrawBarOutline(false);
		xybarrenderer.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		xybarrenderer.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		xybarrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		demopanel.add(chartpanel);
		JFreeChart jfreechart1 = createChart("Vertical / Inverted Axis", intervalxydataset);
		XYPlot xyplot1 = (XYPlot)jfreechart1.getPlot();
		XYBarRenderer xybarrenderer1 = (XYBarRenderer)xyplot1.getRenderer();
		xybarrenderer1.setDrawBarOutline(false);
		xybarrenderer1.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		xybarrenderer1.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		xybarrenderer1.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
		xyplot1.getDomainAxis().setInverted(true);
		ChartPanel chartpanel1 = new ChartPanel(jfreechart1);
		demopanel.add(chartpanel1);
		JFreeChart jfreechart2 = createChart("Horizontal", intervalxydataset);
		XYPlot xyplot2 = (XYPlot)jfreechart2.getPlot();
		xyplot2.setOrientation(PlotOrientation.HORIZONTAL);
		XYBarRenderer xybarrenderer2 = (XYBarRenderer)xyplot2.getRenderer();
		xybarrenderer2.setDrawBarOutline(false);
		xybarrenderer2.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		xybarrenderer2.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		xybarrenderer2.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
		ChartPanel chartpanel2 = new ChartPanel(jfreechart2);
		demopanel.add(chartpanel2);
		JFreeChart jfreechart3 = createChart("Horizontal / Inverted Axis", intervalxydataset);
		XYPlot xyplot3 = (XYPlot)jfreechart3.getPlot();
		xyplot3.setOrientation(PlotOrientation.HORIZONTAL);
		XYBarRenderer xybarrenderer3 = (XYBarRenderer)xyplot3.getRenderer();
		xybarrenderer3.setDrawBarOutline(false);
		xybarrenderer3.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		xybarrenderer3.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		xybarrenderer3.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
		xyplot3.getDomainAxis().setInverted(true);
		ChartPanel chartpanel3 = new ChartPanel(jfreechart3);
		demopanel.add(chartpanel3);
		demopanel.addChart(jfreechart);
		demopanel.addChart(jfreechart1);
		demopanel.addChart(jfreechart2);
		demopanel.addChart(jfreechart3);
		return demopanel;
	}

	public static void main(String args[])
	{
		ClusteredXYBarRendererDemo1 clusteredxybarrendererdemo1 = new ClusteredXYBarRendererDemo1("JFreeChart: ClusteredXYBarRendererDemo1.java");
		clusteredxybarrendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(clusteredxybarrendererdemo1);
		clusteredxybarrendererdemo1.setVisible(true);
	}
}
