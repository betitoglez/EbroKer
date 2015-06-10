// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.jfree.data.xy.*;
import org.jfree.ui.*;
import org.jfree.util.Rotation;

public class ThumbnailDemo1 extends ApplicationFrame
{

	public ThumbnailDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset1()
	{
		String s = "First";
		String s1 = "Second";
		String s2 = "Third";
		String s3 = "Category 1";
		String s4 = "Category 2";
		String s5 = "Category 3";
		String s6 = "Category 4";
		String s7 = "Category 5";
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

	private static JFreeChart createChart1(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 1", "Category", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
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
		barrenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.52359877559829882D));
		return jfreechart;
	}

	private static PieDataset createDataset2()
	{
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Java", new Double(43.200000000000003D));
		defaultpiedataset.setValue("Visual Basic", new Double(10D));
		defaultpiedataset.setValue("C/C++", new Double(17.5D));
		defaultpiedataset.setValue("PHP", new Double(32.5D));
		defaultpiedataset.setValue("Perl", null);
		return defaultpiedataset;
	}

	private static JFreeChart createChart2(PieDataset piedataset)
	{
		JFreeChart jfreechart = ChartFactory.createPieChart3D("Pie Chart 3D Demo 1", piedataset, true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		PiePlot3D pieplot3d = (PiePlot3D)jfreechart.getPlot();
		pieplot3d.setDarkerSides(true);
		pieplot3d.setStartAngle(290D);
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		pieplot3d.setForegroundAlpha(0.5F);
		pieplot3d.setOutlinePaint(null);
		pieplot3d.setNoDataMessage("No data to display");
		return jfreechart;
	}

	private static CategoryDataset createDataset3()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(81D, "Against all torture", "Italy");
		defaultcategorydataset.addValue(72D, "Against all torture", "Great Britain");
		defaultcategorydataset.addValue(58D, "Against all torture", "USA");
		defaultcategorydataset.addValue(48D, "Against all torture", "Israel");
		defaultcategorydataset.addValue(43D, "Against all torture", "Russia");
		defaultcategorydataset.addValue(23D, "Against all torture", "India");
		defaultcategorydataset.addValue(59D, "Against all torture", "Average (*)");
		defaultcategorydataset.addValue(5D, "-", "Italy");
		defaultcategorydataset.addValue(4D, "-", "Great Britain");
		defaultcategorydataset.addValue(6D, "-", "USA");
		defaultcategorydataset.addValue(9D, "-", "Israel");
		defaultcategorydataset.addValue(20D, "-", "Russia");
		defaultcategorydataset.addValue(45D, "-", "India");
		defaultcategorydataset.addValue(12D, "-", "Average (*)");
		defaultcategorydataset.addValue(14D, "Some degree permissible", "Italy");
		defaultcategorydataset.addValue(24D, "Some degree permissible", "Great Britain");
		defaultcategorydataset.addValue(36D, "Some degree permissible", "USA");
		defaultcategorydataset.addValue(43D, "Some degree permissible", "Israel");
		defaultcategorydataset.addValue(37D, "Some degree permissible", "Russia");
		defaultcategorydataset.addValue(32D, "Some degree permissible", "India");
		defaultcategorydataset.addValue(29D, "Some degree permissible", "Average (*)");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart3(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createStackedBarChart("Public Opinion : Torture of Prisoners", "Country", "%", categorydataset, PlotOrientation.HORIZONTAL, false, true, false);
		jfreechart.getTitle().setMargin(2D, 0.0D, 0.0D, 0.0D);
		TextTitle texttitle = new TextTitle("Source: http://news.bbc.co.uk/1/hi/world/6063386.stm", new Font("Dialog", 0, 11));
		texttitle.setPosition(RectangleEdge.BOTTOM);
		texttitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		texttitle.setMargin(0.0D, 0.0D, 4D, 4D);
		jfreechart.addSubtitle(texttitle);
		TextTitle texttitle1 = new TextTitle("(*) Across 27,000 respondents in 25 countries", new Font("Dialog", 0, 11));
		texttitle1.setPosition(RectangleEdge.BOTTOM);
		texttitle1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		texttitle1.setMargin(4D, 0.0D, 2D, 4D);
		jfreechart.addSubtitle(texttitle1);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		LegendItemCollection legenditemcollection = new LegendItemCollection();
		legenditemcollection.add(new LegendItem("Against all torture", null, null, null, new java.awt.geom.Rectangle2D.Double(-6D, -3D, 12D, 6D), Color.green));
		legenditemcollection.add(new LegendItem("Some degree permissible", null, null, null, new java.awt.geom.Rectangle2D.Double(-6D, -3D, 12D, 6D), Color.red));
		categoryplot.setFixedLegendItems(legenditemcollection);
		categoryplot.setInsets(new RectangleInsets(5D, 5D, 5D, 20D));
		LegendTitle legendtitle = new LegendTitle(categoryplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(legendtitle);
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeGridlinePaint(Color.white);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setUpperMargin(0.0D);
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		Color color = new Color(0, 0, 0, 0);
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		barrenderer.setSeriesPaint(0, gradientpaint);
		barrenderer.setSeriesPaint(1, color);
		barrenderer.setSeriesPaint(2, gradientpaint1);
		return jfreechart;
	}

	private static XYDataset createDataset4()
	{
		YIntervalSeries yintervalseries = new YIntervalSeries("Series 1");
		YIntervalSeries yintervalseries1 = new YIntervalSeries("Series 2");
		Object obj = new Week();
		double d = 100D;
		double d1 = 100D;
		for (int i = 0; i <= 52; i++)
		{
			double d2 = 0.050000000000000003D * (double)i;
			yintervalseries.add(((RegularTimePeriod) (obj)).getFirstMillisecond(), d, d - d2, d + d2);
			d = (d + Math.random()) - 0.45000000000000001D;
			double d3 = 0.070000000000000007D * (double)i;
			yintervalseries1.add(((RegularTimePeriod) (obj)).getFirstMillisecond(), d1, d1 - d3, d1 + d3);
			d1 = (d1 + Math.random()) - 0.55000000000000004D;
			obj = ((RegularTimePeriod) (obj)).next();
		}

		YIntervalSeriesCollection yintervalseriescollection = new YIntervalSeriesCollection();
		yintervalseriescollection.addSeries(yintervalseries);
		yintervalseriescollection.addSeries(yintervalseries1);
		return yintervalseriescollection;
	}

	private static JFreeChart createChart4(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Projected Values - Test", "Date", "Index Projection", xydataset, true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setInsets(new RectangleInsets(5D, 5D, 5D, 20D));
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		DeviationRenderer deviationrenderer = new DeviationRenderer(true, false);
		deviationrenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
		deviationrenderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
		deviationrenderer.setSeriesStroke(1, new BasicStroke(3F, 1, 1));
		deviationrenderer.setSeriesFillPaint(0, new Color(255, 200, 200));
		deviationrenderer.setSeriesFillPaint(1, new Color(200, 200, 255));
		xyplot.setRenderer(deviationrenderer);
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return jfreechart;
	}

	private static IntervalXYDataset createDataset5()
	{
		HistogramDataset histogramdataset = new HistogramDataset();
		double ad[] = new double[1000];
		Random random = new Random(0xbc614eL);
		for (int i = 0; i < 1000; i++)
			ad[i] = random.nextGaussian() + 5D;

		histogramdataset.addSeries("H1", ad, 100, 2D, 8D);
		ad = new double[1000];
		for (int j = 0; j < 1000; j++)
			ad[j] = random.nextGaussian() + 7D;

		histogramdataset.addSeries("H2", ad, 100, 4D, 10D);
		return histogramdataset;
	}

	private static JFreeChart createChart5(IntervalXYDataset intervalxydataset)
	{
		JFreeChart jfreechart = ChartFactory.createHistogram("Histogram Demo 1", null, null, intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
		jfreechart.setBackgroundPaint(Color.white);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		xyplot.setForegroundAlpha(0.85F);
		XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
		xybarrenderer.setDrawBarOutline(false);
		return jfreechart;
	}

	private static CategoryDataset createDataset6()
	{
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(212D, "Classes", "JDK 1.0");
		defaultcategorydataset.addValue(504D, "Classes", "JDK 1.1");
		defaultcategorydataset.addValue(1520D, "Classes", "SDK 1.2");
		defaultcategorydataset.addValue(1842D, "Classes", "SDK 1.3");
		defaultcategorydataset.addValue(2991D, "Classes", "SDK 1.4");
		return defaultcategorydataset;
	}

	private static JFreeChart createChart6(CategoryDataset categorydataset)
	{
		JFreeChart jfreechart = ChartFactory.createLineChart("Java Standard Class Library", "Release", "Class Count", categorydataset, PlotOrientation.VERTICAL, false, true, false);
		jfreechart.addSubtitle(new TextTitle("Number of Classes By Release"));
		TextTitle texttitle = new TextTitle("Source: Java In A Nutshell (4th Edition) by David Flanagan (O'Reilly)");
		texttitle.setFont(new Font("SansSerif", 0, 10));
		texttitle.setPosition(RectangleEdge.BOTTOM);
		texttitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		jfreechart.addSubtitle(texttitle);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setRangeGridlinePaint(Color.white);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setUpperMargin(0.14999999999999999D);
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer)categoryplot.getRenderer();
		lineandshaperenderer.setBaseShapesVisible(true);
		lineandshaperenderer.setDrawOutlines(true);
		lineandshaperenderer.setUseFillPaint(true);
		lineandshaperenderer.setBaseFillPaint(Color.white);
		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineandshaperenderer.setBaseItemLabelsVisible(true);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JPanel jpanel = new JPanel(new GridLayout(2, 3));
		JFreeChart jfreechart = createChart1(createDataset1());
		ChartUtilities.applyCurrentTheme(jfreechart);
		java.awt.image.BufferedImage bufferedimage = jfreechart.createBufferedImage(120, 80, 360D, 240D, null);
		ImageIcon imageicon = new ImageIcon(bufferedimage);
		jpanel.add(new JButton(imageicon));
		JFreeChart jfreechart1 = createChart2(createDataset2());
		ChartUtilities.applyCurrentTheme(jfreechart1);
		java.awt.image.BufferedImage bufferedimage1 = jfreechart1.createBufferedImage(120, 80, 360D, 240D, null);
		ImageIcon imageicon1 = new ImageIcon(bufferedimage1);
		jpanel.add(new JButton(imageicon1));
		JFreeChart jfreechart2 = createChart3(createDataset3());
		ChartUtilities.applyCurrentTheme(jfreechart2);
		java.awt.image.BufferedImage bufferedimage2 = jfreechart2.createBufferedImage(120, 80, 360D, 240D, null);
		ImageIcon imageicon2 = new ImageIcon(bufferedimage2);
		jpanel.add(new JButton(imageicon2));
		JFreeChart jfreechart3 = createChart4(createDataset4());
		ChartUtilities.applyCurrentTheme(jfreechart3);
		java.awt.image.BufferedImage bufferedimage3 = jfreechart3.createBufferedImage(120, 80, 360D, 240D, null);
		ImageIcon imageicon3 = new ImageIcon(bufferedimage3);
		jpanel.add(new JButton(imageicon3));
		JFreeChart jfreechart4 = createChart5(createDataset5());
		ChartUtilities.applyCurrentTheme(jfreechart4);
		java.awt.image.BufferedImage bufferedimage4 = jfreechart4.createBufferedImage(120, 80, 360D, 240D, null);
		ImageIcon imageicon4 = new ImageIcon(bufferedimage4);
		jpanel.add(new JButton(imageicon4));
		JFreeChart jfreechart5 = createChart6(createDataset6());
		ChartUtilities.applyCurrentTheme(jfreechart5);
		java.awt.image.BufferedImage bufferedimage5 = jfreechart5.createBufferedImage(120, 80, 360D, 240D, null);
		ImageIcon imageicon5 = new ImageIcon(bufferedimage5);
		jpanel.add(new JButton(imageicon5));
		return jpanel;
	}

	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		ThumbnailDemo1 thumbnaildemo1 = new ThumbnailDemo1("JFreeChart: ThumbnailDemo1.java");
		thumbnaildemo1.pack();
		RefineryUtilities.centerFrameOnScreen(thumbnaildemo1);
		thumbnaildemo1.setVisible(true);
	}
}
