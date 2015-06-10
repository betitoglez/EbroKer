// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;

public class BarChartDemo9 extends ApplicationFrame
{
	static class CustomBarRenderer extends BarRenderer
	{

		private Paint colors[];

		public Paint getItemPaint(int i, int j)
		{
			return colors[j % colors.length];
		}

		public CustomBarRenderer(Paint apaint[])
		{
			colors = apaint;
		}
	}


	public BarChartDemo9(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(410D, "Network Traffic", "Monday");
		defaultcategorydataset.addValue(680D, "Network Traffic", "Tuesday");
		defaultcategorydataset.addValue(530D, "Network Traffic", "Wednesday");
		defaultcategorydataset.addValue(570D, "Network Traffic", "Thursday");
		defaultcategorydataset.addValue(330D, "Network Traffic", "Friday");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 9", null, "Value", categorydataset, PlotOrientation.VERTICAL, false, true, false);
		TextTitle texttitle = jfreechart.getTitle();
		texttitle.setBorder(0.0D, 0.0D, 1.0D, 0.0D);
		texttitle.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.red, 350F, 0.0F, Color.white, true));
		texttitle.setExpandToFitSpace(true);
		jfreechart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.yellow, 350F, 0.0F, Color.white, true));
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setNoDataMessage("NO DATA!");
		categoryplot.setBackgroundPaint(null);
		categoryplot.setInsets(new RectangleInsets(10D, 5D, 5D, 5D));
		categoryplot.setOutlinePaint(Color.black);
		categoryplot.setRangeGridlinePaint(Color.gray);
		categoryplot.setRangeGridlineStroke(new BasicStroke(1.0F));
		Paint apaint[] = createPaint();
		CustomBarRenderer custombarrenderer = new CustomBarRenderer(apaint);
		custombarrenderer.setBarPainter(new StandardBarPainter());
		custombarrenderer.setDrawBarOutline(true);
		custombarrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
		categoryplot.setRenderer(custombarrenderer);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setRange(0.0D, 800D);
		numberaxis.setTickMarkPaint(Color.black);
		return jfreechart;
	}

	private static Paint[] createPaint()
	{
		Paint apaint[] = new Paint[5];
		apaint[0] = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, Color.white);
		apaint[1] = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, Color.white);
		apaint[2] = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, Color.white);
		apaint[3] = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, Color.white);
		apaint[4] = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, Color.white);
		return apaint;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		BarChartDemo9 barchartdemo9 = new BarChartDemo9("JFreeChart: BarChartDemo9.java");
		barchartdemo9.pack();
		RefineryUtilities.centerFrameOnScreen(barchartdemo9);
		barchartdemo9.setVisible(true);
	}
}
