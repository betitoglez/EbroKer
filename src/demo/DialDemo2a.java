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
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DialDemo2a extends JFrame
{
	static class DemoPanelB extends JPanel
		implements ChangeListener
	{

		DefaultValueDataset dataset1;
		DefaultValueDataset dataset2;
		JSlider slider1;
		JSlider slider2;

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset1.setValue(new Integer(slider1.getValue()));
			dataset2.setValue(new Integer(slider2.getValue()));
		}

		public DemoPanelB()
		{
			super(new BorderLayout());
			dataset1 = new DefaultValueDataset(10D);
			dataset2 = new DefaultValueDataset(50D);
			DialPlot dialplot = new DialPlot();
			dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
			dialplot.setDataset(0, dataset1);
			dialplot.setDataset(1, dataset2);
			StandardDialFrame standarddialframe = new StandardDialFrame();
			standarddialframe.setBackgroundPaint(Color.lightGray);
			standarddialframe.setForegroundPaint(Color.darkGray);
			dialplot.setDialFrame(standarddialframe);
			GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
			DialBackground dialbackground = new DialBackground(gradientpaint);
			dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
			dialplot.setBackground(dialbackground);
			DialTextAnnotation dialtextannotation = new DialTextAnnotation("Temperature");
			dialtextannotation.setFont(new Font("Dialog", 1, 14));
			dialtextannotation.setRadius(0.69999999999999996D);
			dialplot.addLayer(dialtextannotation);
			DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
			dialvalueindicator.setFont(new Font("Dialog", 0, 10));
			dialvalueindicator.setOutlinePaint(Color.darkGray);
			dialvalueindicator.setRadius(0.59999999999999998D);
			dialvalueindicator.setAngle(-103D);
			dialplot.addLayer(dialvalueindicator);
			DialValueIndicator dialvalueindicator1 = new DialValueIndicator(1);
			dialvalueindicator1.setFont(new Font("Dialog", 0, 10));
			dialvalueindicator1.setOutlinePaint(Color.red);
			dialvalueindicator1.setRadius(0.59999999999999998D);
			dialvalueindicator1.setAngle(-77D);
			dialplot.addLayer(dialvalueindicator1);
			StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10D, 4);
			standarddialscale.setTickRadius(0.88D);
			standarddialscale.setTickLabelOffset(0.14999999999999999D);
			standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(0, standarddialscale);
			StandardDialScale standarddialscale1 = new StandardDialScale(0.0D, 100D, -120D, -300D, 10D, 4);
			standarddialscale1.setTickRadius(0.5D);
			standarddialscale1.setTickLabelOffset(0.14999999999999999D);
			standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
			standarddialscale1.setMajorTickPaint(Color.red);
			standarddialscale1.setMinorTickPaint(Color.red);
			dialplot.addScale(1, standarddialscale1);
			dialplot.mapDatasetToScale(1, 1);
			StandardDialRange standarddialrange = new StandardDialRange(90D, 100D, Color.blue);
			standarddialrange.setScaleIndex(1);
			standarddialrange.setInnerRadius(0.58999999999999997D);
			standarddialrange.setOuterRadius(0.58999999999999997D);
			dialplot.addLayer(standarddialrange);
			org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
			pin.setRadius(0.55000000000000004D);
			dialplot.addPointer(pin);
			org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
			dialplot.addPointer(pointer);
			DialCap dialcap = new DialCap();
			dialcap.setRadius(0.10000000000000001D);
			dialplot.setCap(dialcap);
			JFreeChart jfreechart = new JFreeChart(dialplot);
			jfreechart.setTitle("Dial Demo 2");
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			JPanel jpanel = new JPanel(new GridLayout(1, 2));
			slider1 = new JSlider(-40, 60);
			slider1.setMajorTickSpacing(20);
			slider1.setPaintTicks(false);
			slider1.setPaintLabels(true);
			slider1.addChangeListener(this);
			jpanel.add(slider1);
			jpanel.add(slider1);
			slider2 = new JSlider(0, 100);
			slider2.setMajorTickSpacing(20);
			slider2.setPaintTicks(false);
			slider2.setPaintLabels(true);
			slider2.addChangeListener(this);
			jpanel.add(slider2);
			add(chartpanel);
			add(jpanel, "South");
		}
	}

	static class DemoPanelA extends JPanel
		implements ChangeListener
	{

		JSlider slider;
		DefaultValueDataset dataset;

		public static JFreeChart createStandardDialChart(String s, String s1, ValueDataset valuedataset, double d, double d1, double d2, int i)
		{
			DialPlot dialplot = new DialPlot();
			dialplot.setDataset(valuedataset);
			dialplot.setDialFrame(new StandardDialFrame());
			dialplot.setBackground(new DialBackground());
			DialTextAnnotation dialtextannotation = new DialTextAnnotation(s1);
			dialtextannotation.setFont(new Font("Dialog", 1, 14));
			dialtextannotation.setRadius(0.69999999999999996D);
			dialplot.addLayer(dialtextannotation);
			DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
			dialplot.addLayer(dialvalueindicator);
			StandardDialScale standarddialscale = new StandardDialScale(d, d1, -120D, -300D, 10D, 4);
			standarddialscale.setMajorTickIncrement(d2);
			standarddialscale.setMinorTickCount(i);
			standarddialscale.setTickRadius(0.88D);
			standarddialscale.setTickLabelOffset(0.14999999999999999D);
			standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
			dialplot.addScale(0, standarddialscale);
			dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
			DialCap dialcap = new DialCap();
			dialplot.setCap(dialcap);
			return new JFreeChart(s, dialplot);
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset.setValue(new Integer(slider.getValue()));
		}

		public DemoPanelA()
		{
			super(new BorderLayout());
			dataset = new DefaultValueDataset(10D);
			JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
			DialPlot dialplot = (DialPlot)jfreechart.getPlot();
			StandardDialRange standarddialrange = new StandardDialRange(40D, 60D, Color.red);
			standarddialrange.setInnerRadius(0.52000000000000002D);
			standarddialrange.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange);
			StandardDialRange standarddialrange1 = new StandardDialRange(10D, 40D, Color.orange);
			standarddialrange1.setInnerRadius(0.52000000000000002D);
			standarddialrange1.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange1);
			StandardDialRange standarddialrange2 = new StandardDialRange(-40D, 10D, Color.green);
			standarddialrange2.setInnerRadius(0.52000000000000002D);
			standarddialrange2.setOuterRadius(0.55000000000000004D);
			dialplot.addLayer(standarddialrange2);
			GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
			DialBackground dialbackground = new DialBackground(gradientpaint);
			dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
			dialplot.setBackground(dialbackground);
			dialplot.removePointer(0);
			dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pointer());
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(400, 400));
			slider = new JSlider(-40, 60);
			slider.setMajorTickSpacing(10);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
			add(chartpanel);
			add(slider, "South");
		}
	}


	public DialDemo2a(String s)
	{
		super(s);
		setDefaultCloseOperation(3);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		JPanel jpanel = new JPanel(new GridLayout(1, 2));
		jpanel.add(new DemoPanelA());
		jpanel.add(new DemoPanelB());
		return jpanel;
	}

	public static void main(String args[])
	{
		DialDemo2a dialdemo2a = new DialDemo2a("JFreeChart: DialDemo2a.java");
		dialdemo2a.pack();
		dialdemo2a.setVisible(true);
	}
}
