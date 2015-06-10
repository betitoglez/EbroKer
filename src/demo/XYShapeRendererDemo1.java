// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.*;

public class XYShapeRendererDemo1 extends ApplicationFrame
{

	public XYShapeRendererDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYZDataset xyzdataset)
	{
		NumberAxis numberaxis = new NumberAxis("X");
		numberaxis.setAutoRangeIncludesZero(false);
		NumberAxis numberaxis1 = new NumberAxis("Y");
		numberaxis1.setAutoRangeIncludesZero(false);
		XYShapeRenderer xyshaperenderer = new XYShapeRenderer();
		LookupPaintScale lookuppaintscale = new LookupPaintScale(1.0D, 4D, new Color(0, 0, 255));
		lookuppaintscale.add(2D, new Color(100, 100, 255));
		lookuppaintscale.add(3D, new Color(200, 200, 255));
		xyshaperenderer.setPaintScale(lookuppaintscale);
		XYPlot xyplot = new XYPlot(xyzdataset, numberaxis, numberaxis1, xyshaperenderer);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		JFreeChart jfreechart = new JFreeChart("XYShapeRendererDemo1", xyplot);
		jfreechart.removeLegend();
		NumberAxis numberaxis2 = new NumberAxis("Score");
		numberaxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		PaintScaleLegend paintscalelegend = new PaintScaleLegend(lookuppaintscale, numberaxis2);
		paintscalelegend.setPosition(RectangleEdge.RIGHT);
		paintscalelegend.setMargin(4D, 4D, 40D, 4D);
		paintscalelegend.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		jfreechart.addSubtitle(paintscalelegend);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static XYZDataset createDataset()
	{
		DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();
		double ad[] = {
			2.1000000000000001D, 2.2999999999999998D, 2.2999999999999998D, 2.2000000000000002D, 2.2000000000000002D, 1.8D, 1.8D, 1.8999999999999999D, 2.2999999999999998D, 2.7999999999999998D
		};
		double ad1[] = {
			14.1D, 17.100000000000001D, 10D, 8.8000000000000007D, 8.6999999999999993D, 8.4000000000000004D, 5.4000000000000004D, 4.0999999999999996D, 4.0999999999999996D, 25D
		};
		double ad2[] = {
			2.3999999999999999D, 2.7000000000000002D, 1.7D, 2.2000000000000002D, 1.3D, 2.2000000000000002D, 2.1000000000000001D, 3.2000000000000002D, 1.6000000000000001D, 3.3999999999999999D
		};
		double ad3[][] = {
			ad, ad1, ad2
		};
		defaultxyzdataset.addSeries("Series 1", ad3);
		return defaultxyzdataset;
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
		XYShapeRendererDemo1 xyshaperendererdemo1 = new XYShapeRendererDemo1("JFreeChart: XYShapeRendererDemo1.java");
		xyshaperendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xyshaperendererdemo1);
		xyshaperendererdemo1.setVisible(true);
	}
}
