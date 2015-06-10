// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.data.general.DefaultPieDataset;

public class First
{

	public First()
	{
	}

	public static void main(String args[])
	{
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Category 1", 43.200000000000003D);
		defaultpiedataset.setValue("Category 2", 27.899999999999999D);
		defaultpiedataset.setValue("Category 3", 79.5D);
		org.jfree.chart.JFreeChart jfreechart = ChartFactory.createPieChart("Sample Pie Chart", defaultpiedataset, true, true, false);
		ChartFrame chartframe = new ChartFrame("JFreeChart: First.java", jfreechart);
		chartframe.pack();
		chartframe.setVisible(true);
	}
}
