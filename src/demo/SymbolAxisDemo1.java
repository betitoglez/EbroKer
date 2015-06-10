// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class SymbolAxisDemo1 extends ApplicationFrame
{

	public SymbolAxisDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 300));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		SymbolAxis symbolaxis = new SymbolAxis("Domain", new String[] {
			"A", "B", "C", "DDDDDDDDDDDDDDDDDDD"
		});
		SymbolAxis symbolaxis1 = new SymbolAxis("Range", new String[] {
			"V", "X", "Y", "Z"
		});
		symbolaxis1.setUpperMargin(0.0D);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
		XYPlot xyplot = new XYPlot(xydataset, symbolaxis, symbolaxis1, xylineandshaperenderer);
		JFreeChart jfreechart = new JFreeChart("SymbolAxis Demo 1", xyplot);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	private static XYDataset createDataset()
	{
		XYSeries xyseries = new XYSeries("Series 1");
		xyseries.add(0.0D, 3D);
		xyseries.add(1.0D, 3D);
		xyseries.add(2D, 0.0D);
		xyseries.add(3D, 1.0D);
		XYSeries xyseries1 = new XYSeries("Series 2");
		xyseries1.add(0.0D, 1.0D);
		xyseries1.add(1.0D, 2D);
		xyseries1.add(2D, 1.0D);
		xyseries1.add(3D, 3D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		return xyseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		return new ChartPanel(createChart(createDataset()));
	}

	public static void main(String args[])
	{
		SymbolAxisDemo1 symbolaxisdemo1 = new SymbolAxisDemo1("JFreeChart: SymbolAxisDemo1.java");
		symbolaxisdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(symbolaxisdemo1);
		symbolaxisdemo1.setVisible(true);
	}
}
