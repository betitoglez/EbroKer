// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ThermometerDemo2 extends ApplicationFrame
{

	public ThermometerDemo2(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		setContentPane(jpanel);
	}

	private static JFreeChart createChart()
	{
		DefaultValueDataset defaultvaluedataset = new DefaultValueDataset(37.200000000000003D);
		ThermometerPlot thermometerplot = new ThermometerPlot(defaultvaluedataset);
		JFreeChart jfreechart = new JFreeChart("ThermometerDemo2", thermometerplot);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart();
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		ThermometerDemo2 thermometerdemo2 = new ThermometerDemo2("JFreeChart: ThermometerDemo2.java");
		thermometerdemo2.pack();
		RefineryUtilities.centerFrameOnScreen(thermometerdemo2);
		thermometerdemo2.setVisible(true);
	}
}
