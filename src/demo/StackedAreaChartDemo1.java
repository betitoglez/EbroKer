// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StackedAreaChartDemo1 extends ApplicationFrame
{

	public StackedAreaChartDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0D, "S1", "C1");
		defaultcategorydataset.addValue(2D, "S1", "C2");
		defaultcategorydataset.addValue(3D, "S1", "C3");
		defaultcategorydataset.addValue(4D, "S1", "C4");
		defaultcategorydataset.addValue(5D, "S1", "C5");
		defaultcategorydataset.addValue(6D, "S1", "C6");
		defaultcategorydataset.addValue(7D, "S1", "C7");
		defaultcategorydataset.addValue(8D, "S1", "C8");
		defaultcategorydataset.addValue(6D, "S2", "C1");
		defaultcategorydataset.addValue(3D, "S2", "C2");
		defaultcategorydataset.addValue(4D, "S2", "C3");
		defaultcategorydataset.addValue(3D, "S2", "C4");
		defaultcategorydataset.addValue(9D, "S2", "C5");
		defaultcategorydataset.addValue(7D, "S2", "C6");
		defaultcategorydataset.addValue(2D, "S2", "C7");
		defaultcategorydataset.addValue(3D, "S2", "C8");
		defaultcategorydataset.addValue(1.0D, "S3", "C1");
		defaultcategorydataset.addValue(7D, "S3", "C2");
		defaultcategorydataset.addValue(6D, "S3", "C3");
		defaultcategorydataset.addValue(7D, "S3", "C4");
		defaultcategorydataset.addValue(4D, "S3", "C5");
		defaultcategorydataset.addValue(5D, "S3", "C6");
		defaultcategorydataset.addValue(3D, "S3", "C7");
		defaultcategorydataset.addValue(1.0D, "S3", "C8");
		return defaultcategorydataset;
	}

	public static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createStackedAreaChart("Stacked Area Chart", "Category", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setForegroundAlpha(0.85F);
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setLowerMargin(0.0D);
		categoryaxis.setUpperMargin(0.0D);
		categoryaxis.setCategoryMargin(0.0D);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
		categoryitemrenderer.setBaseItemLabelsVisible(true);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		StackedAreaChartDemo1 stackedareachartdemo1 = new StackedAreaChartDemo1("JFreeChart: StackedAreaChartDemo1.java");
		stackedareachartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(stackedareachartdemo1);
		stackedareachartdemo1.setVisible(true);
	}
}
