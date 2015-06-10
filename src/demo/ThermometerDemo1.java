// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.*;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

// Referenced classes of package demo:
//			DemoPanel

public class ThermometerDemo1 extends ApplicationFrame
{
	static class ContentPanel extends DemoPanel
		implements ChangeListener
	{

		JSlider slider;
		DefaultValueDataset dataset;

		private static JFreeChart createChart(ValueDataset valuedataset)
		{
			ThermometerPlot thermometerplot = new ThermometerPlot(valuedataset);
			JFreeChart jfreechart = new JFreeChart("Thermometer Demo 1", JFreeChart.DEFAULT_TITLE_FONT, thermometerplot, true);
			thermometerplot.setInsets(new RectangleInsets(5D, 5D, 5D, 5D));
			thermometerplot.setPadding(new RectangleInsets(10D, 10D, 10D, 10D));
			thermometerplot.setThermometerStroke(new BasicStroke(2.0F));
			thermometerplot.setThermometerPaint(Color.lightGray);
			thermometerplot.setUnits(1);
			thermometerplot.setGap(3);
			thermometerplot.setRange(0.0D, 200D);
			thermometerplot.setSubrange(0, 0.0D, 85D);
			thermometerplot.setSubrangePaint(0, Color.red);
			thermometerplot.setSubrange(1, 85D, 125D);
			thermometerplot.setSubrangePaint(1, Color.green);
			thermometerplot.setSubrange(2, 125D, 200D);
			thermometerplot.setSubrangePaint(2, Color.red);
			ChartUtilities.applyCurrentTheme(jfreechart);
			return jfreechart;
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			dataset.setValue(new Integer(slider.getValue()));
		}

		public ContentPanel()
		{
			super(new BorderLayout());
			slider = new JSlider(0, 200, 100);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.setMajorTickSpacing(25);
			slider.addChangeListener(this);
			add(slider, "South");
			dataset = new DefaultValueDataset(slider.getValue());
			JFreeChart jfreechart = createChart(dataset);
			addChart(jfreechart);
			add(new ChartPanel(jfreechart));
		}
	}


	public ThermometerDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		setContentPane(jpanel);
	}

	public static JPanel createDemoPanel()
	{
		return new ContentPanel();
	}

	public static void main(String args[])
	{
		ThermometerDemo1 thermometerdemo1 = new ThermometerDemo1("Thermometer Demo 1");
		thermometerdemo1.pack();
		thermometerdemo1.setVisible(true);
	}
}
