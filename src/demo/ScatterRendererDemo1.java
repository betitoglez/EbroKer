// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.ScatterRenderer;
import org.jfree.data.statistics.DefaultMultiValueCategoryDataset;
import org.jfree.data.statistics.MultiValueCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class ScatterRendererDemo1 extends ApplicationFrame
{

	public ScatterRendererDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static java.util.List listOfValues(double ad[])
	{
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < ad.length; i++)
			arraylist.add(new Double(ad[i]));

		return arraylist;
	}

	private static MultiValueCategoryDataset createDataset()
	{
		DefaultMultiValueCategoryDataset defaultmultivaluecategorydataset = new DefaultMultiValueCategoryDataset();
		defaultmultivaluecategorydataset.add(listOfValues(new double[] {
			1.0D, 2D, 3D
		}), "Series 1", "C1");
		defaultmultivaluecategorydataset.add(listOfValues(new double[] {
			1.2D, 2.2000000000000002D, 3.2000000000000002D
		}), "Series 1", "C2");
		defaultmultivaluecategorydataset.add(listOfValues(new double[] {
			1.3999999999999999D, 2.3999999999999999D, 3.3999999999999999D
		}), "Series 1", "C3");
		defaultmultivaluecategorydataset.add(listOfValues(new double[] {
			1.0D, 3D
		}), "Series 2", "C1");
		defaultmultivaluecategorydataset.add(listOfValues(new double[] {
			1.2D, 3.2000000000000002D
		}), "Series 2", "C2");
		defaultmultivaluecategorydataset.add(listOfValues(new double[] {
			1.3999999999999999D, 3.6000000000000001D
		}), "Series 2", "C3");
		return defaultmultivaluecategorydataset;
	}

	private static JFreeChart createChart(MultiValueCategoryDataset multivaluecategorydataset)
	{
		CategoryPlot categoryplot = new CategoryPlot(multivaluecategorydataset, new CategoryAxis("Category"), new NumberAxis("Value"), new ScatterRenderer());
		categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setRangeGridlinePaint(Color.white);
		categoryplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
		JFreeChart jfreechart = new JFreeChart("ScatterRendererDemo1", categoryplot);
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
		ScatterRendererDemo1 scatterrendererdemo1 = new ScatterRendererDemo1("JFreeChart: ScatterRendererDemo1.java");
		scatterrendererdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(scatterrendererdemo1);
		scatterrendererdemo1.setVisible(true);
	}
}
