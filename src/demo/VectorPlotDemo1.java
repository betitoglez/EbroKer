// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.*;

public class VectorPlotDemo1 extends ApplicationFrame
{
	static class VectorToolTipGenerator extends StandardXYToolTipGenerator
	{

		public String generateToolTip(XYDataset xydataset, int i, int j)
		{
			if (xydataset instanceof VectorXYDataset)
			{
				VectorXYDataset vectorxydataset = (VectorXYDataset)xydataset;
				double d = vectorxydataset.getXValue(i, j);
				double d1 = vectorxydataset.getVectorXValue(i, j);
				double d2 = vectorxydataset.getYValue(i, j);
				double d3 = vectorxydataset.getVectorYValue(i, j);
				NumberFormat numberformat = getXFormat();
				NumberFormat numberformat1 = getYFormat();
				return numberformat.format(d) + " (" + numberformat.format(d1) + "), " + numberformat1.format(d2) + " (" + numberformat1.format(d3) + ")";
			} else
			{
				return super.generateToolTip(xydataset, i, j);
			}
		}

		public VectorToolTipGenerator()
		{
		}
	}


	public VectorPlotDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static JFreeChart createChart(VectorXYDataset vectorxydataset)
	{
		NumberAxis numberaxis = new NumberAxis("X");
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setLowerMargin(0.01D);
		numberaxis.setUpperMargin(0.01D);
		numberaxis.setAutoRangeIncludesZero(false);
		NumberAxis numberaxis1 = new NumberAxis("Y");
		numberaxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis1.setLowerMargin(0.01D);
		numberaxis1.setUpperMargin(0.01D);
		numberaxis1.setAutoRangeIncludesZero(false);
		VectorRenderer vectorrenderer = new VectorRenderer();
		vectorrenderer.setSeriesPaint(0, Color.blue);
		vectorrenderer.setBaseToolTipGenerator(new VectorToolTipGenerator());
		XYPlot xyplot = new XYPlot(vectorxydataset, numberaxis, numberaxis1, vectorrenderer);
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
		xyplot.setOutlinePaint(Color.black);
		JFreeChart jfreechart = new JFreeChart("Vector Plot Demo 1", xyplot);
		ChartUtilities.applyCurrentTheme(jfreechart);
		return jfreechart;
	}

	private static VectorXYDataset createDataset()
	{
		VectorSeries vectorseries = new VectorSeries("Series 1");
		for (double d = 0.0D; d < 20D; d++)
		{
			for (double d1 = 0.0D; d1 < 20D; d1++)
				vectorseries.add(d + 10D, d1 + 10D, Math.sin(d / 5D) / 2D, Math.cos(d1 / 5D) / 2D);

		}

		VectorSeriesCollection vectorseriescollection = new VectorSeriesCollection();
		vectorseriescollection.addSeries(vectorseries);
		return vectorseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		return new ChartPanel(createChart(createDataset()));
	}

	public static void main(String args[])
	{
		VectorPlotDemo1 vectorplotdemo1 = new VectorPlotDemo1("JFreeChart : Vector Plot Demo 1");
		vectorplotdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(vectorplotdemo1);
		vectorplotdemo1.setVisible(true);
	}
}
