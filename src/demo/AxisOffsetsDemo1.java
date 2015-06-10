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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;

// Referenced classes of package demo:
//			DemoPanel

public class AxisOffsetsDemo1 extends ApplicationFrame
{

	public AxisOffsetsDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		String s = "S1";
		String s1 = "S2";
		String s2 = "S3";
		String s3 = "C1";
		String s4 = "C2";
		String s5 = "C3";
		String s6 = "C4";
		String s7 = "C5";
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0D, s, s3);
		defaultcategorydataset.addValue(4D, s, s4);
		defaultcategorydataset.addValue(3D, s, s5);
		defaultcategorydataset.addValue(5D, s, s6);
		defaultcategorydataset.addValue(5D, s, s7);
		defaultcategorydataset.addValue(5D, s1, s3);
		defaultcategorydataset.addValue(7D, s1, s4);
		defaultcategorydataset.addValue(6D, s1, s5);
		defaultcategorydataset.addValue(8D, s1, s6);
		defaultcategorydataset.addValue(4D, s1, s7);
		defaultcategorydataset.addValue(4D, s2, s3);
		defaultcategorydataset.addValue(3D, s2, s4);
		defaultcategorydataset.addValue(2D, s2, s5);
		defaultcategorydataset.addValue(3D, s2, s6);
		defaultcategorydataset.addValue(6D, s2, s7);
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(String s, CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart(s, "Category", "Value", categorydataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		barrenderer.setSeriesPaint(0, gradientpaint);
		barrenderer.setSeriesPaint(1, gradientpaint1);
		barrenderer.setSeriesPaint(2, gradientpaint2);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart("Axis Offsets: 0", createDataset());
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setAxisOffset(RectangleInsets.ZERO_INSETS);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMinimumDrawWidth(0);
		chartpanel.setMinimumDrawHeight(0);
		JFreeChart jfreechart1 = createChart("Axis Offsets: 5", createDataset());
		ChartPanel chartpanel1 = new ChartPanel(jfreechart1);
		chartpanel1.setMinimumDrawWidth(0);
		chartpanel1.setMinimumDrawHeight(0);
		CategoryPlot categoryplot1 = (CategoryPlot)jfreechart1.getPlot();
		categoryplot1.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
		DemoPanel demopanel = new DemoPanel(new GridLayout(2, 1));
		demopanel.add(chartpanel);
		demopanel.add(chartpanel1);
		demopanel.addChart(jfreechart);
		demopanel.addChart(jfreechart1);
		return demopanel;
	}

	public static void main(String args[])
	{
		AxisOffsetsDemo1 axisoffsetsdemo1 = new AxisOffsetsDemo1("JFreeChart: AxisOffsetsDemo1.java");
		axisoffsetsdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(axisoffsetsdemo1);
		axisoffsetsdemo1.setVisible(true);
	}
}
