// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.text.NumberFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

public class MultiplePieChartDemo2 extends ApplicationFrame
{

	public MultiplePieChartDemo2(String s)
	{
		super(s);
		CategoryDataset categorydataset = createDataset();
		JFreeChart jfreechart = createChart(categorydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
		chartpanel.setPreferredSize(new Dimension(600, 380));
		setContentPane(chartpanel);
	}

	private static CategoryDataset createDataset()
	{
		double ad[][] = {
			{
				3D, 4D, 3D, 5D
			}, {
				5D, 7D, 6D, 8D
			}, {
				5D, 7D, 3D, 8D
			}, {
				1.0D, 2D, 3D, 4D
			}, {
				2D, 3D, 2D, 3D
			}
		};
		CategoryDataset categorydataset = DatasetUtilities.createCategoryDataset("Region ", "Sales/Q", ad);
		return categorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createMultiplePieChart("Multiple Pie Chart", categorydataset, TableOrder.BY_COLUMN, true, true, false);
		MultiplePiePlot multiplepieplot = (MultiplePiePlot)jfreechart.getPlot();
		multiplepieplot.setBackgroundPaint(Color.white);
		multiplepieplot.setOutlineStroke(new BasicStroke(1.0F));
		JFreeChart jfreechart1 = multiplepieplot.getPieChart();
		PiePlot pieplot = (PiePlot)jfreechart1.getPlot();
		pieplot.setBackgroundPaint(null);
		pieplot.setOutlineStroke(null);
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));
		pieplot.setMaximumLabelWidth(0.20000000000000001D);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		MultiplePieChartDemo2 multiplepiechartdemo2 = new MultiplePieChartDemo2("JFreeChart: MultiplePieChartDemo2.java");
		multiplepiechartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(multiplepiechartdemo2);
		multiplepiechartdemo2.setVisible(true);
	}
}
