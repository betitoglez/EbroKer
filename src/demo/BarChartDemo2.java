// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.*;

public class BarChartDemo2 extends ApplicationFrame
{

	public BarChartDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		double ad[][] = {
			{
				1.0D, 43D, 35D, 58D, 54D, 77D, 71D, 89D
			}, {
				54D, 75D, 63D, 83D, 43D, 46D, 27D, 13D
			}, {
				41D, 33D, 22D, 34D, 62D, 32D, 42D, 34D
			}
		};
		return DatasetUtilities.createCategoryDataset("Series ", "Factor ", ad);
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 2", "Category", "Score (%)", categorydataset, PlotOrientation.HORIZONTAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setRange(0.0D, 100D);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(0, 0, 128), 0.0F, 0.0F, Color.blue);
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, new Color(0, 128, 0), 0.0F, 0.0F, Color.green);
		GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, new Color(128, 0, 0), 0.0F, 0.0F, Color.red);
		barrenderer.setSeriesPaint(0, gradientpaint);
		barrenderer.setSeriesPaint(1, gradientpaint1);
		barrenderer.setSeriesPaint(2, gradientpaint2);
		barrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
		barrenderer.setDrawBarOutline(false);
		barrenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		BarChartDemo2 barchartdemo2 = new BarChartDemo2("JFreeChart: BarChartDemo2.java");
		barchartdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo2);
		barchartdemo2.setVisible(true);
	}
}
