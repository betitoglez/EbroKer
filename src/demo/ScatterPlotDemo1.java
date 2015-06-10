// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			SampleXYDataset2

public class ScatterPlotDemo1 extends ApplicationFrame
{

	public ScatterPlotDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createScatterPlot("Scatter Plot Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setNoDataMessage("NO DATA");
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		xyplot.setDomainZeroBaselineVisible(true);
		xyplot.setRangeZeroBaselineVisible(true);
		xyplot.setDomainGridlineStroke(new BasicStroke(0.0F));
		xyplot.setDomainMinorGridlineStroke(new BasicStroke(0.0F));
		xyplot.setDomainGridlinePaint(Color.blue);
		xyplot.setRangeGridlineStroke(new BasicStroke(0.0F));
		xyplot.setRangeMinorGridlineStroke(new BasicStroke(0.0F));
		xyplot.setRangeGridlinePaint(Color.blue);
		xyplot.setDomainMinorGridlinesVisible(true);
		xyplot.setRangeMinorGridlinesVisible(true);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setSeriesOutlinePaint(0, Color.black);
		xylineandshaperenderer.setUseOutlinePaint(true);
		NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setTickMarkInsideLength(2.0F);
		numberaxis.setTickMarkOutsideLength(2.0F);
		numberaxis.setMinorTickCount(2);
		numberaxis.setMinorTickMarksVisible(true);
		NumberAxis numberaxis1 = (NumberAxis)xyplot.getRangeAxis();
		numberaxis1.setTickMarkInsideLength(2.0F);
		numberaxis1.setTickMarkOutsideLength(2.0F);
		numberaxis1.setMinorTickCount(2);
		numberaxis1.setMinorTickMarksVisible(true);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(new SampleXYDataset2());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		ScatterPlotDemo1 scatterplotdemo1 = new ScatterPlotDemo1("JFreeChart: ScatterPlotDemo1.java");
		scatterplotdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(scatterplotdemo1);
		scatterplotdemo1.setVisible(true);
	}
}
