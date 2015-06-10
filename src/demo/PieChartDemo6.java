// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			DemoPanel

public class PieChartDemo6 extends ApplicationFrame
{

	public PieChartDemo6(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(800, 600));
		setContentPane(jpanel);
	}

	private static PieDataset createDataset()
	{
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("S1", 7D);
		defaultpiedataset.setValue("S2", null);
		defaultpiedataset.setValue("S3", 0.0D);
		defaultpiedataset.setValue("S4", 3D);
		defaultpiedataset.setValue("S5", -1D);
		return defaultpiedataset;
	}

	private static JFreeChart createChart(String s, PieDataset piedataset)
	{
		JFreeChart jfreechart = ChartFactory.createPieChart(s, piedataset, true, true, false);
		PiePlot pieplot = (PiePlot)jfreechart.getPlot();
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1}"));
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		DemoPanel demopanel = new DemoPanel(new GridLayout(2, 2));
		JFreeChart jfreechart = createChart("Pie Chart 1", createDataset());
		Font font = new Font("Dialog", 0, 12);
		jfreechart.addSubtitle(new TextTitle("Ignore nulls: false; Ignore zeros: false;", font));
		JFreeChart jfreechart1 = createChart("Pie Chart 2", createDataset());
		jfreechart1.addSubtitle(new TextTitle("Ignore nulls: true; Ignore zeros: false;", font));
		PiePlot pieplot = (PiePlot)jfreechart1.getPlot();
		pieplot.setIgnoreNullValues(true);
		pieplot.setIgnoreZeroValues(false);
		JFreeChart jfreechart2 = createChart("Pie Chart 3", createDataset());
		jfreechart2.addSubtitle(new TextTitle("Ignore nulls: false; Ignore zeros: true;", font));
		PiePlot pieplot1 = (PiePlot)jfreechart2.getPlot();
		pieplot1.setIgnoreNullValues(false);
		pieplot1.setIgnoreZeroValues(true);
		JFreeChart jfreechart3 = createChart("Pie Chart 4", createDataset());
		jfreechart3.addSubtitle(new TextTitle("Ignore nulls: true; Ignore zeros: true;", font));
		PiePlot pieplot2 = (PiePlot)jfreechart3.getPlot();
		pieplot2.setIgnoreNullValues(true);
		pieplot2.setIgnoreZeroValues(true);
		demopanel.add(new ChartPanel(jfreechart));
		demopanel.add(new ChartPanel(jfreechart1));
		demopanel.add(new ChartPanel(jfreechart2));
		demopanel.add(new ChartPanel(jfreechart3));
		demopanel.addChart(jfreechart);
		demopanel.addChart(jfreechart1);
		demopanel.addChart(jfreechart2);
		demopanel.addChart(jfreechart3);
		return demopanel;
	}

	public static void main(String args[])
	{
		PieChartDemo6 piechartdemo6 = new PieChartDemo6("JFreeChart: PieChartDemo6.java");
		piechartdemo6.pack();
		RefineryUtilities.centerFrameOnScreen(piechartdemo6);
		piechartdemo6.setVisible(true);
	}
}
