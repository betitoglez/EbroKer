// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.MinMaxCategoryRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MinMaxCategoryPlotDemo1 extends ApplicationFrame
{

	public MinMaxCategoryPlotDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0D, "First", "C1");
		defaultcategorydataset.addValue(4D, "First", "C2");
		defaultcategorydataset.addValue(3D, "First", "C3");
		defaultcategorydataset.addValue(5D, "First", "C4");
		defaultcategorydataset.addValue(5D, "First", "C5");
		defaultcategorydataset.addValue(7D, "First", "C6");
		defaultcategorydataset.addValue(7D, "First", "C7");
		defaultcategorydataset.addValue(8D, "First", "C8");
		defaultcategorydataset.addValue(5D, "Second", "C1");
		defaultcategorydataset.addValue(7D, "Second", "C2");
		defaultcategorydataset.addValue(6D, "Second", "C3");
		defaultcategorydataset.addValue(8D, "Second", "C4");
		defaultcategorydataset.addValue(4D, "Second", "C5");
		defaultcategorydataset.addValue(4D, "Second", "C6");
		defaultcategorydataset.addValue(2D, "Second", "C7");
		defaultcategorydataset.addValue(1.0D, "Second", "C8");
		defaultcategorydataset.addValue(4D, "Third", "C1");
		defaultcategorydataset.addValue(3D, "Third", "C2");
		defaultcategorydataset.addValue(2D, "Third", "C3");
		defaultcategorydataset.addValue(3D, "Third", "C4");
		defaultcategorydataset.addValue(6D, "Third", "C5");
		defaultcategorydataset.addValue(3D, "Third", "C6");
		defaultcategorydataset.addValue(4D, "Third", "C7");
		defaultcategorydataset.addValue(3D, "Third", "C8");
		return defaultcategorydataset;
	}

	public static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Min/Max Category Plot", "Category", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setRangePannable(true);
		MinMaxCategoryRenderer minmaxcategoryrenderer = new MinMaxCategoryRenderer();
		minmaxcategoryrenderer.setDrawLines(false);
		categoryplot.setRenderer(minmaxcategoryrenderer);
		ChartUtilities.applyCurrentTheme(jfreechart);
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
		MinMaxCategoryPlotDemo1 minmaxcategoryplotdemo1 = new MinMaxCategoryPlotDemo1("JFreeChart: MinMaxCategoryPlotDemo1.java");
		minmaxcategoryplotdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(minmaxcategoryplotdemo1);
		minmaxcategoryplotdemo1.setVisible(true);
	}
}
