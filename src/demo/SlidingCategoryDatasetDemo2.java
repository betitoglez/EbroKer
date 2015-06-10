// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			DemoPanel

public class SlidingCategoryDatasetDemo2 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
		implements ChangeListener
	{

		JScrollBar scroller;
		SlidingCategoryDataset dataset;

		private static CategoryDataset createDataset()
		{
			DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
			for (int i = 0; i < 50; i++)
				defaultcategorydataset.addValue(Math.random() * 100D, "S1", "S" + i);

			return defaultcategorydataset;
		}

		private static JFreeChart createChart(CategoryDataset categorydataset)
		{
			JFreeChart jfreechart = ChartFactory.createBarChart("SlidingCategoryDatasetDemo2", "Series", "Value", categorydataset, PlotOrientation.VERTICAL, false, true, false);
			CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
			CategoryAxis categoryaxis = categoryplot.getDomainAxis();
			categoryaxis.setMaximumCategoryLabelWidthRatio(0.8F);
			categoryaxis.setLowerMargin(0.02D);
			categoryaxis.setUpperMargin(0.02D);
			NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
			numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			numberaxis.setRange(0.0D, 100D);
			BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
			barrenderer.setDrawBarOutline(false);
			GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
			barrenderer.setSeriesPaint(0, gradientpaint);
			return jfreechart;
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset.setFirstCategoryIndex(scroller.getValue());
		}

		public MyDemoPanel()
		{
			super(new BorderLayout());
			dataset = new SlidingCategoryDataset(createDataset(), 0, 10);
			JFreeChart jfreechart = createChart(dataset);
			addChart(jfreechart);
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			scroller = new JScrollBar(0, 0, 10, 0, 50);
			add(chartpanel);
			scroller.getModel().addChangeListener(this);
			JPanel jpanel = new JPanel(new BorderLayout());
			jpanel.add(scroller);
			jpanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			jpanel.setBackground(Color.white);
			add(jpanel, "South");
		}
	}


	public SlidingCategoryDatasetDemo2(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		SlidingCategoryDatasetDemo2 slidingcategorydatasetdemo2 = new SlidingCategoryDatasetDemo2("JFreeChart: SlidingCategoryDatasetDemo2.java");
		slidingcategorydatasetdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(slidingcategorydatasetdemo2);
		slidingcategorydatasetdemo2.setVisible(true);
	}
}
