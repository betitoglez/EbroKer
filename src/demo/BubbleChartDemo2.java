// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.*;

// Referenced classes of package demo:
//			SampleXYZDataset2

public class BubbleChartDemo2 extends ApplicationFrame
{

	public BubbleChartDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYZDataset xyzdataset)
	{
		JFreeChart jfreechart = ChartFactory.createBubbleChart("Bubble Chart Demo 2", "X", "Y", xyzdataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setRenderer(new XYBubbleRenderer(0));
		xyplot.setForegroundAlpha(0.65F);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.blue);
		xyitemrenderer.setBaseItemLabelGenerator(new BubbleXYItemLabelGenerator());
		xyitemrenderer.setBaseToolTipGenerator(new StandardXYZToolTipGenerator());
		xyitemrenderer.setBaseItemLabelsVisible(true);
		xyitemrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
		NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
		numberaxis.setRange(0.0D, 10D);
		NumberAxis numberaxis1 = (NumberAxis)xyplot.getRangeAxis();
		numberaxis1.setRange(0.0D, 10D);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(new SampleXYZDataset2());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		BubbleChartDemo2 bubblechartdemo2 = new BubbleChartDemo2("JFreeChart: BubbleChartDemo2.java");
		bubblechartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(bubblechartdemo2);
		bubblechartdemo2.setVisible(true);
	}
}
