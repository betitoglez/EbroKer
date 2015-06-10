// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.*;

public class NormalDistributionDemo2 extends ApplicationFrame
{

	public NormalDistributionDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static XYDataset createDataset()
	{
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		NormalDistributionFunction2D normaldistributionfunction2d = new NormalDistributionFunction2D(0.0D, 1.0D);
		org.jfree.data.xy.XYSeries xyseries = DatasetUtilities.sampleFunction2DToSeries(normaldistributionfunction2d, -5.0999999999999996D, 5.0999999999999996D, 121, "N1");
		xyseriescollection.addSeries(xyseries);
		NormalDistributionFunction2D normaldistributionfunction2d1 = new NormalDistributionFunction2D(0.0D, Math.sqrt(0.20000000000000001D));
		org.jfree.data.xy.XYSeries xyseries1 = DatasetUtilities.sampleFunction2DToSeries(normaldistributionfunction2d1, -5.0999999999999996D, 5.0999999999999996D, 121, "N2");
		xyseriescollection.addSeries(xyseries1);
		NormalDistributionFunction2D normaldistributionfunction2d2 = new NormalDistributionFunction2D(0.0D, Math.sqrt(5D));
		org.jfree.data.xy.XYSeries xyseries2 = DatasetUtilities.sampleFunction2DToSeries(normaldistributionfunction2d2, -5.0999999999999996D, 5.0999999999999996D, 121, "N3");
		xyseriescollection.addSeries(xyseries2);
		NormalDistributionFunction2D normaldistributionfunction2d3 = new NormalDistributionFunction2D(-2D, Math.sqrt(0.5D));
		org.jfree.data.xy.XYSeries xyseries3 = DatasetUtilities.sampleFunction2DToSeries(normaldistributionfunction2d3, -5.0999999999999996D, 5.0999999999999996D, 121, "N4");
		xyseriescollection.addSeries(xyseries3);
		return xyseriescollection;
	}

	public static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Normal Distribution Demo 2", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainZeroBaselineVisible(true);
		xyplot.setRangeZeroBaselineVisible(true);
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		ValueAxis valueaxis = xyplot.getDomainAxis();
		valueaxis.setLowerMargin(0.0D);
		valueaxis.setUpperMargin(0.0D);
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setDrawSeriesLineAsPath(true);
		xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(1.5F));
		xylineandshaperenderer.setSeriesStroke(1, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {
			6F, 4F
		}, 0.0F));
		xylineandshaperenderer.setSeriesStroke(2, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {
			6F, 4F, 3F, 3F
		}, 0.0F));
		xylineandshaperenderer.setSeriesStroke(3, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {
			4F, 4F
		}, 0.0F));
		XYPointerAnnotation xypointerannotation = new XYPointerAnnotation("米 = -2.0, 考\262 = 0.5", -2D, 0.56399999999999995D, 3.9269908169872414D);
		xypointerannotation.setLabelOffset(4D);
		xypointerannotation.setTextAnchor(TextAnchor.BOTTOM_RIGHT);
		xypointerannotation.setBackgroundPaint(Color.yellow);
		xyplot.addAnnotation(xypointerannotation);
		XYPointerAnnotation xypointerannotation1 = new XYPointerAnnotation("米 = 0.0, 考\262 = 0.2", 0.22500000000000001D, 0.80000000000000004D, 0.0D);
		xypointerannotation1.setLabelOffset(4D);
		xypointerannotation1.setTextAnchor(TextAnchor.CENTER_LEFT);
		xypointerannotation1.setBackgroundPaint(new Color(0, 0, 255, 63));
		xyplot.addAnnotation(xypointerannotation1);
		XYPointerAnnotation xypointerannotation2 = new XYPointerAnnotation("米 = 0.0, 考\262 = 1.0", 0.75D, 0.29999999999999999D, 5.497787143782138D);
		xypointerannotation2.setLabelOffset(4D);
		xypointerannotation2.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
		xypointerannotation2.setBackgroundPaint(new Color(255, 0, 0, 63));
		xyplot.addAnnotation(xypointerannotation2);
		XYPointerAnnotation xypointerannotation3 = new XYPointerAnnotation("米 = 0.0, 考\262 = 5.0", 3D, 0.074999999999999997D, 4.7123889803846897D);
		xypointerannotation3.setLabelOffset(4D);
		xypointerannotation3.setTextAnchor(TextAnchor.BOTTOM_CENTER);
		xypointerannotation3.setBackgroundPaint(new Color(0, 255, 0, 63));
		xyplot.addAnnotation(xypointerannotation3);
		return jfreechart;
	}

	public static void main(String args[])
	{
		NormalDistributionDemo2 normaldistributiondemo2 = new NormalDistributionDemo2("JFreeChart: NormalDistributionDemo2.java");
		normaldistributiondemo2.pack();
		RefineryUtilities.centerFrameOnScreen(normaldistributiondemo2);
		normaldistributiondemo2.setVisible(true);
	}
}
