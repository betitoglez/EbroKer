// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BoxAndWhiskerChartDemo1 extends ApplicationFrame
{

	public BoxAndWhiskerChartDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static BoxAndWhiskerCategoryDataset createDataset()
	{
		byte byte0 = 3;
		byte byte1 = 5;
		byte byte2 = 20;
		DefaultBoxAndWhiskerCategoryDataset defaultboxandwhiskercategorydataset = new DefaultBoxAndWhiskerCategoryDataset();
		for (int i = 0; i < byte0; i++)
		{
			for (int j = 0; j < byte1; j++)
			{
				java.util.List list = createValueList(0.0D, 20D, byte2);
				defaultboxandwhiskercategorydataset.add(list, "Series " + i, "Category " + j);
			}

		}

		return defaultboxandwhiskercategorydataset;
	}

	private static java.util.List createValueList(double d, double d1, int i)
	{
		ArrayList arraylist = new ArrayList();
		for (int j = 0; j < i; j++)
		{
			double d2 = d + Math.random() * (d1 - d);
			arraylist.add(new Double(d2));
		}

		return arraylist;
	}

	private static JFreeChart createChart(BoxAndWhiskerCategoryDataset boxandwhiskercategorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBoxAndWhiskerChart("Box and Whisker Chart Demo 1", "Category", "Value", boxandwhiskercategorydataset, true);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangePannable(true);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
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
		BoxAndWhiskerChartDemo1 boxandwhiskerchartdemo1 = new BoxAndWhiskerChartDemo1("JFreeChart: BoxAndWhiskerChartDemo1.java");
		boxandwhiskerchartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(boxandwhiskerchartdemo1);
		boxandwhiskerchartdemo1.setVisible(true);
	}
}
