// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.*;

// Referenced classes of package demo:
//			DemoPanel

public class XYSplineRendererDemo1 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
	{

		private XYDataset data1;

		private XYDataset createSampleData()
		{
			XYSeries xyseries = new XYSeries("Series 1");
			xyseries.add(2D, 56.270000000000003D);
			xyseries.add(3D, 41.32D);
			xyseries.add(4D, 31.449999999999999D);
			xyseries.add(5D, 30.050000000000001D);
			xyseries.add(6D, 24.690000000000001D);
			xyseries.add(7D, 19.780000000000001D);
			xyseries.add(8D, 20.940000000000001D);
			xyseries.add(9D, 16.73D);
			xyseries.add(10D, 14.210000000000001D);
			xyseries.add(11D, 12.44D);
			XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
			XYSeries xyseries1 = new XYSeries("Series 2");
			xyseries1.add(11D, 56.270000000000003D);
			xyseries1.add(10D, 41.32D);
			xyseries1.add(9D, 31.449999999999999D);
			xyseries1.add(8D, 30.050000000000001D);
			xyseries1.add(7D, 24.690000000000001D);
			xyseries1.add(6D, 19.780000000000001D);
			xyseries1.add(5D, 20.940000000000001D);
			xyseries1.add(4D, 16.73D);
			xyseries1.add(3D, 14.210000000000001D);
			xyseries1.add(2D, 12.44D);
			xyseriescollection.addSeries(xyseries1);
			return xyseriescollection;
		}

		private JTabbedPane createContent()
		{
			JTabbedPane jtabbedpane = new JTabbedPane();
			jtabbedpane.add("Splines:", createChartPanel1());
			jtabbedpane.add("Lines:", createChartPanel2());
			return jtabbedpane;
		}

		private ChartPanel createChartPanel1()
		{
			NumberAxis numberaxis = new NumberAxis("X");
			numberaxis.setAutoRangeIncludesZero(false);
			NumberAxis numberaxis1 = new NumberAxis("Y");
			numberaxis1.setAutoRangeIncludesZero(false);
			XYSplineRenderer xysplinerenderer = new XYSplineRenderer();
			XYPlot xyplot = new XYPlot(data1, numberaxis, numberaxis1, xysplinerenderer);
			xyplot.setBackgroundPaint(Color.lightGray);
			xyplot.setDomainGridlinePaint(Color.white);
			xyplot.setRangeGridlinePaint(Color.white);
			xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
			JFreeChart jfreechart = new JFreeChart("XYSplineRenderer", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
			addChart(jfreechart);
			ChartUtilities.applyCurrentTheme(jfreechart);
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			return chartpanel;
		}

		private ChartPanel createChartPanel2()
		{
			NumberAxis numberaxis = new NumberAxis("X");
			numberaxis.setAutoRangeIncludesZero(false);
			NumberAxis numberaxis1 = new NumberAxis("Y");
			numberaxis1.setAutoRangeIncludesZero(false);
			XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
			XYPlot xyplot = new XYPlot(data1, numberaxis, numberaxis1, xylineandshaperenderer);
			xyplot.setBackgroundPaint(Color.lightGray);
			xyplot.setDomainGridlinePaint(Color.white);
			xyplot.setRangeGridlinePaint(Color.white);
			xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
			JFreeChart jfreechart = new JFreeChart("XYLineAndShapeRenderer", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
			addChart(jfreechart);
			ChartUtilities.applyCurrentTheme(jfreechart);
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			return chartpanel;
		}

		public MyDemoPanel()
		{
			super(new BorderLayout());
			data1 = createSampleData();
			add(createContent());
		}
	}


	public XYSplineRendererDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		getContentPane().add(jpanel);
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		XYSplineRendererDemo1 xysplinerendererdemo1 = new XYSplineRendererDemo1("JFreeChart: XYSplineRendererDemo1.java");
		xysplinerendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(xysplinerendererdemo1);
		xysplinerendererdemo1.setVisible(true);
	}
}
