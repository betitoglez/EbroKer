// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartDemo5 extends ApplicationFrame
{

	public BarChartDemo5(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		String s = "Prison Population Rates";
		defaultcategorydataset.addValue(59D, s, "Norway");
		defaultcategorydataset.addValue(69D, s, "Switzerland");
		defaultcategorydataset.addValue(85D, s, "France");
		defaultcategorydataset.addValue(93D, s, "Syria");
		defaultcategorydataset.addValue(96D, s, "Germany");
		defaultcategorydataset.addValue(111D, s, "China");
		defaultcategorydataset.addValue(116D, s, "Australia");
		defaultcategorydataset.addValue(121D, s, "Egypt");
		defaultcategorydataset.addValue(129D, s, "England & Wales");
		defaultcategorydataset.addValue(157D, s, "New Zealand");
		defaultcategorydataset.addValue(205D, s, "Chile");
		defaultcategorydataset.addValue(229D, s, "Iran");
		defaultcategorydataset.addValue(359D, s, "Singapore");
		defaultcategorydataset.addValue(404D, s, "South Africa");
		defaultcategorydataset.addValue(406D, s, "Ukraine");
		defaultcategorydataset.addValue(686D, s, "USA");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Prison Population Rates - Selected Countries", "Country", "Prisoners Per 100,000 National Population", categorydataset, PlotOrientation.HORIZONTAL, false, true, false);
		jfreechart.addSubtitle(new TextTitle("Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf", new Font("Dialog", 2, 10)));
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		categoryplot.setRangePannable(true);
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setItemLabelAnchorOffset(9D);
		barrenderer.setBaseItemLabelsVisible(true);
		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0}, {1}) = {2} per 100,000", new DecimalFormat("0")));
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryMargin(0.25D);
		categoryaxis.setUpperMargin(0.02D);
		categoryaxis.setLowerMargin(0.02D);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setUpperMargin(0.10000000000000001D);
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
		BarChartDemo5 barchartdemo5 = new BarChartDemo5("JFreeChart: BarChartDemo5.java");
		barchartdemo5.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo5);
		barchartdemo5.setVisible(true);
	}
}
