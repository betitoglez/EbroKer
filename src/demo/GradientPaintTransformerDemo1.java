// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;

// Referenced classes of package demo:
//			DemoPanel

public class GradientPaintTransformerDemo1 extends ApplicationFrame
{

	public GradientPaintTransformerDemo1(String s)
	{
		super(s);
		setContentPane(createDemoPanel());
	}

	private static JFreeChart createChart(String s, CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart(s, null, "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setItemMargin(0.02D);
		return jfreechart;
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(7D, "Series 1", "Category 1");
		defaultcategorydataset.addValue(5D, "Series 2", "Category 1");
		return defaultcategorydataset;
	}

	public static JPanel createDemoPanel()
	{
		DemoPanel demopanel = new DemoPanel(new GridLayout(2, 2));
		demopanel.setPreferredSize(new Dimension(800, 600));
		CategoryDataset categorydataset = createDataset();
		JFreeChart jfreechart = createChart("Type: VERTICAL", categorydataset);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setBarPainter(new StandardBarPainter());
		barrenderer.setDrawBarOutline(false);
		barrenderer.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		barrenderer.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		barrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		demopanel.add(chartpanel);
		JFreeChart jfreechart1 = createChart("Type: HORIZONTAL", categorydataset);
		CategoryPlot categoryplot1 = (CategoryPlot)jfreechart1.getPlot();
		BarRenderer barrenderer1 = (BarRenderer)categoryplot1.getRenderer();
		barrenderer1.setBarPainter(new StandardBarPainter());
		barrenderer1.setDrawBarOutline(false);
		barrenderer1.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		barrenderer1.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		barrenderer1.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
		ChartPanel chartpanel1 = new ChartPanel(jfreechart1);
		demopanel.add(chartpanel1);
		JFreeChart jfreechart2 = createChart("Type: CENTER_VERTICAL", categorydataset);
		CategoryPlot categoryplot2 = (CategoryPlot)jfreechart2.getPlot();
		BarRenderer barrenderer2 = (BarRenderer)categoryplot2.getRenderer();
		barrenderer2.setBarPainter(new StandardBarPainter());
		barrenderer2.setDrawBarOutline(false);
		barrenderer2.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		barrenderer2.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		barrenderer2.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
		ChartPanel chartpanel2 = new ChartPanel(jfreechart2);
		demopanel.add(chartpanel2);
		JFreeChart jfreechart3 = createChart("Type: CENTER_HORIZONTAL", categorydataset);
		CategoryPlot categoryplot3 = (CategoryPlot)jfreechart3.getPlot();
		BarRenderer barrenderer3 = (BarRenderer)categoryplot3.getRenderer();
		barrenderer3.setBarPainter(new StandardBarPainter());
		barrenderer3.setDrawBarOutline(false);
		barrenderer3.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.yellow));
		barrenderer3.setSeriesPaint(1, new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.green));
		barrenderer3.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
		ChartPanel chartpanel3 = new ChartPanel(jfreechart3);
		demopanel.add(chartpanel3);
		demopanel.addChart(jfreechart);
		demopanel.addChart(jfreechart1);
		demopanel.addChart(jfreechart2);
		demopanel.addChart(jfreechart3);
		return demopanel;
	}

	public static void main(String args[])
	{
		GradientPaintTransformerDemo1 gradientpainttransformerdemo1 = new GradientPaintTransformerDemo1("JFreeChart: GradientPaintTransformerDemo1.java");
		gradientpainttransformerdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(gradientpainttransformerdemo1);
		gradientpainttransformerdemo1.setVisible(true);
	}
}
