// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class WaterfallChartDemo1 extends ApplicationFrame
{

	public WaterfallChartDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(15.76D, "Product 1", "Labour");
		defaultcategorydataset.addValue(8.6600000000000001D, "Product 1", "Administration");
		defaultcategorydataset.addValue(4.71D, "Product 1", "Marketing");
		defaultcategorydataset.addValue(3.5099999999999998D, "Product 1", "Distribution");
		defaultcategorydataset.addValue(32.640000000000001D, "Product 1", "Total Expense");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createWaterfallChart("Product Cost Breakdown", "Expense Category", "Cost Per Unit", categorydataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		ValueAxis valueaxis = categoryplot.getRangeAxis();
		DecimalFormat decimalformat = new DecimalFormat("##,###");
		decimalformat.setNegativePrefix("(");
		decimalformat.setNegativeSuffix(")");
		TickUnits tickunits = new TickUnits();
		tickunits.add(new NumberTickUnit(5D, decimalformat));
		tickunits.add(new NumberTickUnit(10D, decimalformat));
		tickunits.add(new NumberTickUnit(20D, decimalformat));
		tickunits.add(new NumberTickUnit(50D, decimalformat));
		tickunits.add(new NumberTickUnit(100D, decimalformat));
		tickunits.add(new NumberTickUnit(200D, decimalformat));
		tickunits.add(new NumberTickUnit(500D, decimalformat));
		tickunits.add(new NumberTickUnit(1000D, decimalformat));
		tickunits.add(new NumberTickUnit(2000D, decimalformat));
		tickunits.add(new NumberTickUnit(5000D, decimalformat));
		valueaxis.setStandardTickUnits(tickunits);
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		barrenderer.setBase(5D);
		DecimalFormat decimalformat1 = new DecimalFormat("$##,###.00");
		decimalformat1.setNegativePrefix("(");
		decimalformat1.setNegativeSuffix(")");
		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
		barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0}, {1}) = {2}", new DecimalFormat("$##,###.00")));
		barrenderer.setBaseItemLabelsVisible(true);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		return new ChartPanel(createChart(createDataset()));
	}

	public static void main(String args[])
	{
		WaterfallChartDemo1 waterfallchartdemo1 = new WaterfallChartDemo1("JFreeChart: WaterfallChartDemo1.java");
		waterfallchartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(waterfallchartdemo1);
		waterfallchartdemo1.setVisible(true);
	}
}
