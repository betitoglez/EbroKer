// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DualAxisDemo6 extends ApplicationFrame
{

	public DualAxisDemo6(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static CategoryDataset createDataset1()
	{
		String s = "Series 1A";
		String s1 = "Series 1B";
		String s2 = "Category 1";
		String s3 = "Category 2";
		String s4 = "Category 3";
		String s5 = "Category 4";
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0D, s, s2);
		defaultcategorydataset.addValue(4D, s, s3);
		defaultcategorydataset.addValue(3D, s, s4);
		defaultcategorydataset.addValue(5D, s, s5);
		defaultcategorydataset.addValue(3D, s1, s2);
		defaultcategorydataset.addValue(6D, s1, s3);
		defaultcategorydataset.addValue(1.0D, s1, s4);
		defaultcategorydataset.addValue(5D, s1, s5);
		return defaultcategorydataset;
	}

	private static CategoryDataset createDataset2()
	{
		String s = "Dummy 2";
		String s1 = "Series 2";
		String s2 = "Category 1";
		String s3 = "Category 2";
		String s4 = "Category 3";
		String s5 = "Category 4";
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(null, s, s2);
		defaultcategorydataset.addValue(null, s, s3);
		defaultcategorydataset.addValue(null, s, s4);
		defaultcategorydataset.addValue(null, s, s5);
		defaultcategorydataset.addValue(75D, s1, s2);
		defaultcategorydataset.addValue(87D, s1, s3);
		defaultcategorydataset.addValue(96D, s1, s4);
		defaultcategorydataset.addValue(68D, s1, s5);
		return defaultcategorydataset;
	}

	private static JFreeChart createChart(CategoryDataset categorydataset, CategoryDataset categorydataset1)
	{
		CategoryAxis categoryaxis = new CategoryAxis("Category");
		NumberAxis numberaxis = new NumberAxis("Value");
		GroupedStackedBarRenderer groupedstackedbarrenderer = new GroupedStackedBarRenderer();
		KeyToGroupMap keytogroupmap = new KeyToGroupMap("G1");
		keytogroupmap.mapKeyToGroup("Series 1A", "G1");
		keytogroupmap.mapKeyToGroup("Series 1B", "G1");
		keytogroupmap.mapKeyToGroup("NOTHING", "G2");
		groupedstackedbarrenderer.setSeriesToGroupMap(keytogroupmap);
		CategoryPlot categoryplot = new CategoryPlot(categorydataset, categoryaxis, numberaxis, groupedstackedbarrenderer) {

			public LegendItemCollection getLegendItems()
			{
				LegendItemCollection legenditemcollection = new LegendItemCollection();
				legenditemcollection.addAll(getRenderer().getLegendItems());
				CategoryDataset categorydataset2 = getDataset(1);
				if (categorydataset2 != null)
				{
					CategoryItemRenderer categoryitemrenderer = getRenderer(1);
					if (categoryitemrenderer != null)
					{
						org.jfree.chart.LegendItem legenditem = categoryitemrenderer.getLegendItem(1, 1);
						legenditemcollection.add(legenditem);
					}
				}
				return legenditemcollection;
			}

		};
		JFreeChart jfreechart = new JFreeChart("Dual Axis Bar Chart", categoryplot);
		categoryplot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		categoryplot.setDataset(1, categorydataset1);
		categoryplot.mapDatasetToRangeAxis(1, 1);
		NumberAxis numberaxis1 = new NumberAxis("Secondary");
		categoryplot.setRangeAxis(1, numberaxis1);
		categoryplot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
		BarRenderer barrenderer = new BarRenderer();
		categoryplot.setRenderer(1, barrenderer);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset1(), createDataset2());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		DualAxisDemo6 dualaxisdemo6 = new DualAxisDemo6("JFreeChart: DualAxisDemo6.java");
		dualaxisdemo6.pack();
		RefineryUtilities.centerFrameOnScreen(dualaxisdemo6);
		dualaxisdemo6.setVisible(true);
	}
}
