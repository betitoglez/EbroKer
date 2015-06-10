// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.util.Arrays;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.*;

public class BarChartDemo3 extends ApplicationFrame
{
	static class CustomRenderer extends BarRenderer
	{

		private Paint colors[];

		public Paint getItemPaint(int i, int j)
		{
			return colors[j % colors.length];
		}

		public CustomRenderer(Paint apaint[])
		{
			colors = apaint;
		}
	}


	public BarChartDemo3(String s)
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
				4D, 3D, -2D, 3D, 6D
			}
		};
		return DatasetUtilities.createCategoryDataset("Series ", "Category ", ad);
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 3", "Category", "Value", categorydataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setNoDataMessage("NO DATA!");
		categoryplot.setRangePannable(true);
		Paint apaint[] = {
			new Color(196, 215, 216), new Color(78, 137, 139), new Color(138, 177, 178), new Color(19, 97, 100)
		};
		CustomRenderer customrenderer = new CustomRenderer(apaint);
		customrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		customrenderer.setBaseItemLabelsVisible(true);
		ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0D);
		customrenderer.setBasePositiveItemLabelPosition(itemlabelposition);
		categoryplot.setRenderer(customrenderer);
		CategoryMarker categorymarker = new CategoryMarker("Category 3");
		categorymarker.setLabel("Special");
		categorymarker.setPaint(new Color(221, 255, 221, 128));
		categorymarker.setAlpha(0.5F);
		categorymarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
		categorymarker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
		categorymarker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
		categoryplot.addDomainMarker(categorymarker, Layer.BACKGROUND);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setLowerMargin(0.14999999999999999D);
		numberaxis.setUpperMargin(0.14999999999999999D);
		NumberAxis numberaxis1 = new NumberAxis(null);
		numberaxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis1.setLowerMargin(0.14999999999999999D);
		numberaxis1.setUpperMargin(0.14999999999999999D);
		categoryplot.setRangeAxis(1, numberaxis1);
		CategoryAxis categoryaxis = new CategoryAxis(null);
		categoryplot.setDomainAxis(1, categoryaxis);
		java.util.List list = Arrays.asList(new Integer[] {
			new Integer(0), new Integer(1)
		});
		categoryplot.mapDatasetToDomainAxes(0, list);
		categoryplot.mapDatasetToRangeAxes(0, list);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		BarChartDemo3 barchartdemo3 = new BarChartDemo3("JFreeChart: BarChartDemo3.java");
		barchartdemo3.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo3);
		barchartdemo3.setVisible(true);
	}
}
