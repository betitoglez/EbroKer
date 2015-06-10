// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;

public class BarChartDemo11 extends ApplicationFrame
{

	public BarChartDemo11(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(23192D, "S1", "GNU General Public Licence");
		defaultcategorydataset.addValue(3157D, "S1", "GNU Lesser General Public Licence");
		defaultcategorydataset.addValue(1506D, "S1", "BSD Licence (Original)");
		defaultcategorydataset.addValue(1283D, "S1", "BSD Licence (Revised)");
		defaultcategorydataset.addValue(738D, "S1", "MIT/X Consortium Licence");
		defaultcategorydataset.addValue(630D, "S1", "Artistic Licence");
		defaultcategorydataset.addValue(585D, "S1", "Public Domain");
		defaultcategorydataset.addValue(349D, "S1", "Apache Licence 2.0");
		defaultcategorydataset.addValue(317D, "S1", "Apache Licence");
		defaultcategorydataset.addValue(309D, "S1", "Mozilla Public Licence");
		defaultcategorydataset.addValue(918D, "S1", "Other");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Open Source Projects By Licence", "Licence", "Project Count", categorydataset, PlotOrientation.HORIZONTAL, false, true, false);
		TextTitle texttitle = new TextTitle("Source: Freshmeat (http://www.freshmeat.net/)", new Font("Dialog", 0, 10));
		texttitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(texttitle);
		ChartUtilities.applyCurrentTheme(jfreechart);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.getDomainAxis().setMaximumCategoryLabelWidthRatio(0.8F);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		StandardCategoryToolTipGenerator standardcategorytooltipgenerator = new StandardCategoryToolTipGenerator("{1}: {2} projects", new DecimalFormat("0"));
		barrenderer.setBaseToolTipGenerator(standardcategorytooltipgenerator);
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
		barrenderer.setSeriesPaint(0, gradientpaint);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		BarChartDemo11 barchartdemo11 = new BarChartDemo11("JFreeChart: BarChartDemo11.java");
		barchartdemo11.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo11);
		barchartdemo11.setVisible(true);
	}
}
