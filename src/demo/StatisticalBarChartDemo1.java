// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.ui.*;

public class StatisticalBarChartDemo1 extends ApplicationFrame
{

	public StatisticalBarChartDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		DefaultStatisticalCategoryDataset defaultstatisticalcategorydataset = new DefaultStatisticalCategoryDataset();
		defaultstatisticalcategorydataset.add(10D, 2.3999999999999999D, "Row 1", "Column 1");
		defaultstatisticalcategorydataset.add(15D, 4.4000000000000004D, "Row 1", "Column 2");
		defaultstatisticalcategorydataset.add(13D, 2.1000000000000001D, "Row 1", "Column 3");
		defaultstatisticalcategorydataset.add(7D, 1.3D, "Row 1", "Column 4");
		defaultstatisticalcategorydataset.add(22D, 2.3999999999999999D, "Row 2", "Column 1");
		defaultstatisticalcategorydataset.add(18D, 4.4000000000000004D, "Row 2", "Column 2");
		defaultstatisticalcategorydataset.add(28D, 2.1000000000000001D, "Row 2", "Column 3");
		defaultstatisticalcategorydataset.add(17D, 1.3D, "Row 2", "Column 4");
		return defaultstatisticalcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createLineChart("Statistical Bar Chart Demo 1", "Type", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(false);
		StatisticalBarRenderer statisticalbarrenderer = new StatisticalBarRenderer();
		statisticalbarrenderer.setDrawBarOutline(false);
		statisticalbarrenderer.setErrorIndicatorPaint(Color.black);
		statisticalbarrenderer.setIncludeBaseInRange(false);
		categoryplot.setRenderer(statisticalbarrenderer);
		ChartUtilities.applyCurrentTheme(jfreechart);
		statisticalbarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		statisticalbarrenderer.setBaseItemLabelsVisible(true);
		statisticalbarrenderer.setBaseItemLabelPaint(Color.yellow);
		statisticalbarrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.BOTTOM_CENTER));
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		statisticalbarrenderer.setSeriesPaint(0, gradientpaint);
		statisticalbarrenderer.setSeriesPaint(1, gradientpaint1);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		StatisticalBarChartDemo1 statisticalbarchartdemo1 = new StatisticalBarChartDemo1("Statistical Bar Chart Demo");
		statisticalbarchartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(statisticalbarchartdemo1);
		statisticalbarchartdemo1.setVisible(true);
	}
}
