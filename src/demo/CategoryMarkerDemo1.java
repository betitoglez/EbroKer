// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;

import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.*;

public class CategoryMarkerDemo1 extends ApplicationFrame
{

	public CategoryMarkerDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(21D, "Series 1", "Category 1");
		defaultcategorydataset.addValue(50D, "Series 1", "Category 2");
		defaultcategorydataset.addValue(152D, "Series 1", "Category 3");
		defaultcategorydataset.addValue(184D, "Series 1", "Category 4");
		defaultcategorydataset.addValue(299D, "Series 1", "Category 5");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createLineChart("Category Marker Demo 1", "Category", "Count", categorydataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer)categoryplot.getRenderer();
		lineandshaperenderer.setSeriesShapesVisible(0, true);
		lineandshaperenderer.setDrawOutlines(true);
		lineandshaperenderer.setUseFillPaint(true);
		lineandshaperenderer.setBaseFillPaint(Color.white);
		ValueMarker valuemarker1 = new ValueMarker(184D);
		valuemarker1.setLabelOffsetType(LengthAdjustmentType.EXPAND);
		valuemarker1.setPaint(Color.red);
		valuemarker1.setLabel("Target Price");
		valuemarker1.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
		valuemarker1.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
		
		
		CategoryMarker categorymarker = new CategoryMarker("Category 4", Color.blue, new BasicStroke(1.0F));
		categorymarker.setDrawAsLine(true);
		categorymarker.setLabel("Marker Label");
		categorymarker.setLabelFont(new Font("Dialog", 0, 11));
		categorymarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
		categorymarker.setLabelOffset(new RectangleInsets(2D, 5D, 2D, 5D));
		categoryplot.addDomainMarker(categorymarker, Layer.BACKGROUND);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		CategoryMarkerDemo1 categorymarkerdemo1 = new CategoryMarkerDemo1("JFreeChart: CategoryMarkerDemo1.java");
		categorymarkerdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(categorymarkerdemo1);
		categorymarkerdemo1.setVisible(true);
	}
}
