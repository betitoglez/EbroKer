// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.annotations.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.about.ProjectInfo;

// Referenced classes of package demo:
//			DemoPanel

public class PlotOrientationDemo1 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
	{

		private XYDataset datasets[];
		private JFreeChart charts[];
		private ChartPanel panels[];

		public MyDemoPanel()
		{
			super(new GridLayout(2, 4));
			datasets = new XYDataset[PlotOrientationDemo1.CHART_COUNT];
			charts = new JFreeChart[PlotOrientationDemo1.CHART_COUNT];
			panels = new ChartPanel[PlotOrientationDemo1.CHART_COUNT];
			for (int i = 0; i < PlotOrientationDemo1.CHART_COUNT; i++)
			{
				datasets[i] = PlotOrientationDemo1.createDataset(i);
				charts[i] = PlotOrientationDemo1.createChart(i, datasets[i]);
				XYPlot xyplot1 = (XYPlot)charts[i].getPlot();
				xyplot1.setDomainPannable(true);
				xyplot1.setRangePannable(true);
				XYShapeAnnotation xyshapeannotation = new XYShapeAnnotation(new java.awt.geom.Rectangle2D.Double(-2D, -3D, 1.0D, 4D), new BasicStroke(1.0F), Color.blue, Color.yellow);
				XYLineAnnotation xylineannotation = new XYLineAnnotation(0.0D, -5D, 10D, -5D);
				XYDataImageAnnotation xydataimageannotation = new XYDataImageAnnotation(JFreeChart.INFO.getLogo(), 5D, 2D, 6D, 4D, true);
				xyplot1.addAnnotation(xyshapeannotation);
				xyplot1.addAnnotation(xylineannotation);
				xyplot1.addAnnotation(xydataimageannotation);
				xyplot1.setQuadrantPaint(0, new Color(230, 230, 255));
				xyplot1.setQuadrantPaint(1, new Color(230, 255, 230));
				xyplot1.setQuadrantPaint(2, new Color(255, 230, 230));
				xyplot1.setQuadrantPaint(3, new Color(255, 230, 255));
				addChart(charts[i]);
				panels[i] = new ChartPanel(charts[i]);
				panels[i].setMouseWheelEnabled(true);
			}

			XYPlot xyplot = (XYPlot)charts[1].getPlot();
			XYPlot xyplot2 = (XYPlot)charts[2].getPlot();
			XYPlot xyplot3 = (XYPlot)charts[3].getPlot();
			XYPlot xyplot4 = (XYPlot)charts[4].getPlot();
			XYPlot xyplot5 = (XYPlot)charts[5].getPlot();
			XYPlot xyplot6 = (XYPlot)charts[6].getPlot();
			XYPlot xyplot7 = (XYPlot)charts[7].getPlot();
			xyplot.getDomainAxis().setInverted(true);
			xyplot2.getRangeAxis().setInverted(true);
			xyplot3.getDomainAxis().setInverted(true);
			xyplot3.getRangeAxis().setInverted(true);
			xyplot5.getDomainAxis().setInverted(true);
			xyplot6.getRangeAxis().setInverted(true);
			xyplot4.getDomainAxis().setInverted(true);
			xyplot4.getRangeAxis().setInverted(true);
			xyplot4.setOrientation(PlotOrientation.HORIZONTAL);
			xyplot5.setOrientation(PlotOrientation.HORIZONTAL);
			xyplot6.setOrientation(PlotOrientation.HORIZONTAL);
			xyplot7.setOrientation(PlotOrientation.HORIZONTAL);
			add(panels[0]);
			add(panels[1]);
			add(panels[4]);
			add(panels[5]);
			add(panels[2]);
			add(panels[3]);
			add(panels[6]);
			add(panels[7]);
			setPreferredSize(new Dimension(800, 600));
		}
	}


	private static int CHART_COUNT = 8;

	public PlotOrientationDemo1(String s)
	{
		super(s);
		setContentPane(createDemoPanel());
	}

	private static XYDataset createDataset(int i)
	{
		XYSeries xyseries = new XYSeries("Series " + (i + 1));
		xyseries.add(-10D, -5D);
		xyseries.add(10D, 5D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		return xyseriescollection;
	}

	private static JFreeChart createChart(int i, XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createXYLineChart("Chart " + (i + 1), "X", "Y", xydataset, PlotOrientation.VERTICAL, false, false, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
		xylineandshaperenderer.setBaseShapesVisible(true);
		xylineandshaperenderer.setBaseShapesFilled(true);
		ValueAxis valueaxis = xyplot.getDomainAxis();
		valueaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		ValueAxis valueaxis1 = xyplot.getRangeAxis();
		valueaxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		PlotOrientationDemo1 plotorientationdemo1 = new PlotOrientationDemo1("JFreeChart: PlotOrientationDemo1.java");
		plotorientationdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(plotorientationdemo1);
		plotorientationdemo1.setVisible(true);
	}




}
