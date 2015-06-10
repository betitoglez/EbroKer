// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			DemoPanel

public class PieChartDemo5 extends ApplicationFrame
{

	public PieChartDemo5(String s)
	{
		super(s);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		DemoPanel demopanel = new DemoPanel(new GridLayout(2, 2));
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Section 1", 23.300000000000001D);
		defaultpiedataset.setValue("Section 2", 56.5D);
		defaultpiedataset.setValue("Section 3", 43.299999999999997D);
		defaultpiedataset.setValue("Section 4", 11.1D);
		JFreeChart jfreechart = ChartFactory.createPieChart("Chart 1", defaultpiedataset, false, false, false);
		jfreechart.addSubtitle(new TextTitle("setCircular(true);", new Font("Dialog", 0, 12)));
		PiePlot pieplot = (PiePlot)jfreechart.getPlot();
		pieplot.setCircular(true);
		pieplot.setInteriorGap(0.040000000000000001D);
		pieplot.setMaximumLabelWidth(0.20000000000000001D);
		JFreeChart jfreechart1 = ChartFactory.createPieChart("Chart 2", defaultpiedataset, false, false, false);
		jfreechart1.addSubtitle(new TextTitle("setCircular(false);", new Font("Dialog", 0, 12)));
		PiePlot pieplot1 = (PiePlot)jfreechart1.getPlot();
		pieplot1.setCircular(false);
		pieplot1.setInteriorGap(0.040000000000000001D);
		pieplot1.setMaximumLabelWidth(0.20000000000000001D);
		JFreeChart jfreechart2 = ChartFactory.createPieChart3D("Chart 3", defaultpiedataset, false, false, false);
		jfreechart2.addSubtitle(new TextTitle("setCircular(true);", new Font("Dialog", 0, 12)));
		PiePlot3D pieplot3d = (PiePlot3D)jfreechart2.getPlot();
		pieplot3d.setForegroundAlpha(0.6F);
		pieplot3d.setCircular(true);
		pieplot3d.setInteriorGap(0.040000000000000001D);
		pieplot3d.setMaximumLabelWidth(0.20000000000000001D);
		JFreeChart jfreechart3 = ChartFactory.createPieChart3D("Chart 4", defaultpiedataset, false, false, false);
		jfreechart3.addSubtitle(new TextTitle("setCircular(false);", new Font("Dialog", 0, 12)));
		PiePlot3D pieplot3d1 = (PiePlot3D)jfreechart3.getPlot();
		pieplot3d1.setForegroundAlpha(0.6F);
		pieplot3d1.setCircular(false);
		pieplot3d1.setInteriorGap(0.040000000000000001D);
		pieplot3d1.setMaximumLabelWidth(0.20000000000000001D);
		demopanel.add(new ChartPanel(jfreechart));
		demopanel.add(new ChartPanel(jfreechart1));
		demopanel.add(new ChartPanel(jfreechart2));
		demopanel.add(new ChartPanel(jfreechart3));
		demopanel.addChart(jfreechart);
		demopanel.addChart(jfreechart1);
		demopanel.addChart(jfreechart2);
		demopanel.addChart(jfreechart3);
		demopanel.setPreferredSize(new Dimension(800, 600));
		return demopanel;
	}

	public static void main(String args[])
	{
		PieChartDemo5 piechartdemo5 = new PieChartDemo5("JFreeChart: PieChartDemo5.java");
		piechartdemo5.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo5);
		piechartdemo5.setVisible(true);
	}
}
