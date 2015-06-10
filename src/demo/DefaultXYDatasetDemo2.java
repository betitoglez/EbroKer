// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DefaultXYDatasetDemo2 extends ApplicationFrame
{

	public DefaultXYDatasetDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createScatterPlot("DefaultXYDatasetDemo2", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, false, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		return jfreechart;
	}

	private static XYDataset createDataset()
	{
		DefaultXYDataset defaultxydataset = new DefaultXYDataset();
		double ad[] = new double[1000];
		double ad1[] = new double[1000];
		for (int i = 0; i < 1000; i++)
		{
			ad[i] = Math.random() + 1.0D;
			ad1[i] = Math.random() + 1.0D;
		}

		double ad2[][] = {
			ad, ad1
		};
		defaultxydataset.addSeries("Series 1", ad2);
		return defaultxydataset;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		DefaultXYDatasetDemo2 defaultxydatasetdemo2 = new DefaultXYDatasetDemo2("JFreeChart: DefaultXYDatasetDemo2.java");
		defaultxydatasetdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(defaultxydatasetdemo2);
		defaultxydatasetdemo2.setVisible(true);
	}
}
