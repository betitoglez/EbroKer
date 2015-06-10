// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DialDemo5 extends JFrame
{
	static class DemoPanel extends JPanel
		implements ChangeListener
	{

		DefaultValueDataset hoursDataset;
		DefaultValueDataset dataset2;
		JSlider slider1;
		JSlider slider2;

		public void stateChanged(ChangeEvent changeevent)
		{
			hoursDataset.setValue(new Integer(slider1.getValue()));
			dataset2.setValue(new Integer(slider2.getValue()));
		}

		public DemoPanel()
		{
			super(new BorderLayout());
			hoursDataset = new DefaultValueDataset(6D);
			dataset2 = new DefaultValueDataset(15D);
			DialPlot dialplot = new DialPlot();
			dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
			dialplot.setDataset(0, hoursDataset);
			dialplot.setDataset(1, dataset2);
			StandardDialFrame standarddialframe = new StandardDialFrame();
			standarddialframe.setBackgroundPaint(Color.lightGray);
			standarddialframe.setForegroundPaint(Color.darkGray);
			dialplot.setDialFrame(standarddialframe);
			DialBackground dialbackground = new DialBackground(Color.white);
			dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
			dialplot.setBackground(dialbackground);
			StandardDialScale standarddialscale = new StandardDialScale(0.0D, 12D, 90D, -360D, 10D, 4);
			standarddialscale.setFirstTickLabelVisible(false);
			standarddialscale.setMajorTickIncrement(1.0D);
			standarddialscale.setTickRadius(0.88D);
			standarddialscale.setTickLabelOffset(0.14999999999999999D);
			standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(0, standarddialscale);
			StandardDialScale standarddialscale1 = new StandardDialScale(0.0D, 60D, 90D, -360D, 10D, 4);
			standarddialscale1.setVisible(false);
			standarddialscale1.setMajorTickIncrement(5D);
			standarddialscale1.setTickRadius(0.68000000000000005D);
			standarddialscale1.setTickLabelOffset(0.14999999999999999D);
			standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(1, standarddialscale1);
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
			pointer.setRadius(0.55000000000000004D);
			dialplot.addLayer(pointer);
			dialplot.mapDatasetToScale(1, 1);
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer1 = new org.jfree.chart.plot.dial.DialPointer.Pointer(1);
			dialplot.addLayer(pointer1);
			DialCap dialcap = new DialCap();
			dialcap.setRadius(0.10000000000000001D);
			dialplot.setCap(dialcap);
			JFreeChart jfreechart = new JFreeChart(dialplot);
			jfreechart.setTitle("Dial Demo 5");
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			JPanel jpanel = new JPanel(new GridLayout(2, 2));
			jpanel.add(new JLabel("Hours:"));
			jpanel.add(new JLabel("Minutes:"));
			slider1 = new JSlider(0, 12);
			slider1.setMajorTickSpacing(2);
			slider1.setPaintTicks(true);
			slider1.setPaintLabels(true);
			slider1.addChangeListener(this);
			jpanel.add(slider1);
			jpanel.add(slider1);
			slider2 = new JSlider(0, 60);
			slider2.setValue(15);
			slider2.setMajorTickSpacing(10);
			slider2.setPaintTicks(true);
			slider2.setPaintLabels(true);
			slider2.addChangeListener(this);
			jpanel.add(slider2);
			add(chartpanel);
			add(jpanel, "South");
		}
	}


	public DialDemo5(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new DemoPanel();
	}

	public static void main(String args[])
	{
		DialDemo5 dialdemo5 = new DialDemo5("JFreeChart: DialDemo5.java");
		dialdemo5.pack();
		dialdemo5.setVisible(true);
	}
}
