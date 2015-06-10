// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.BasicStroke;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class OverlaidBarChartDemo2 extends ApplicationFrame
{

	public OverlaidBarChartDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0D, "S1", "Category 1");
		defaultcategorydataset.addValue(4D, "S1", "Category 2");
		defaultcategorydataset.addValue(3D, "S1", "Category 3");
		defaultcategorydataset.addValue(5D, "S1", "Category 4");
		defaultcategorydataset.addValue(5D, "S1", "Category 5");
		defaultcategorydataset.addValue(5D, "S2", "Category 1");
		defaultcategorydataset.addValue(7D, "S2", "Category 2");
		defaultcategorydataset.addValue(6D, "S2", "Category 3");
		defaultcategorydataset.addValue(8D, "S2", "Category 4");
		defaultcategorydataset.addValue(4D, "S2", "Category 5");
		BarRenderer barrenderer = new BarRenderer();
		barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		CategoryPlot categoryplot = new CategoryPlot();
		categoryplot.setDataset(defaultcategorydataset);
		categoryplot.setRenderer(barrenderer);
		categoryplot.setDomainAxis(new CategoryAxis("Category"));
		categoryplot.setRangeAxis(new NumberAxis("Value"));
		categoryplot.setOrientation(PlotOrientation.VERTICAL);
		categoryplot.setRangeGridlinesVisible(true);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeZeroBaselineVisible(true);
		categoryplot.setRangePannable(true);
		DefaultCategoryDataset defaultcategorydataset1 = new DefaultCategoryDataset();
		defaultcategorydataset1.addValue(6D, "Prior 1", "Category 1");
		defaultcategorydataset1.addValue(7D, "Prior 1", "Category 2");
		defaultcategorydataset1.addValue(2D, "Prior 1", "Category 3");
		defaultcategorydataset1.addValue(6D, "Prior 1", "Category 4");
		defaultcategorydataset1.addValue(6D, "Prior 1", "Category 5");
		defaultcategorydataset1.addValue(4D, "Prior 2", "Category 1");
		defaultcategorydataset1.addValue(2D, "Prior 2", "Category 2");
		defaultcategorydataset1.addValue(1.0D, "Prior 2", "Category 3");
		defaultcategorydataset1.addValue(3D, "Prior 2", "Category 4");
		defaultcategorydataset1.addValue(2D, "Prior 2", "Category 5");
		LevelRenderer levelrenderer = new LevelRenderer();
		categoryplot.setDataset(1, defaultcategorydataset1);
		categoryplot.setRenderer(1, levelrenderer);
		categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		JFreeChart jfreechart = new JFreeChart(categoryplot);
		jfreechart.setTitle("OverlaidBarChartDemo2");
		ChartUtilities.applyCurrentTheme(jfreechart);
		levelrenderer.setSeriesStroke(0, new BasicStroke(2.0F));
		levelrenderer.setSeriesStroke(1, new BasicStroke(2.0F));
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		OverlaidBarChartDemo2 overlaidbarchartdemo2 = new OverlaidBarChartDemo2("JFreeChart: OverlaidBarChartDemo2.java");
		overlaidbarchartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(overlaidbarchartdemo2);
		overlaidbarchartdemo2.setVisible(true);
	}
}
