// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.CompassPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CompassDemo1 extends ApplicationFrame
{

	public CompassDemo1(String s)
	{
		super(s);
		ChartPanel chartpanel = (ChartPanel)createDemoPanel();
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setEnforceFileExtensions(false);
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart(ValueDataset valuedataset)
	{
		CompassPlot compassplot = new CompassPlot(valuedataset);
		compassplot.setSeriesNeedle(7);
		compassplot.setSeriesPaint(0, Color.black);
		compassplot.setSeriesOutlinePaint(0, Color.black);
		compassplot.setRosePaint(Color.red);
		compassplot.setRoseHighlightPaint(Color.gray);
		compassplot.setRoseCenterPaint(Color.white);
		compassplot.setDrawBorder(false);
		JFreeChart jfreechart = new JFreeChart(compassplot);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(new DefaultValueDataset(new Double(45D)));
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		CompassDemo1 compassdemo1 = new CompassDemo1("JFreeChart: CompassDemo1.java");
		compassdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(compassdemo1);
		compassdemo1.setVisible(true);
	}
}
