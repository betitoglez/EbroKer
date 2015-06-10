// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.*;
import org.jfree.ui.*;
import org.jfree.util.UnitType;

// Referenced classes of package demo:
//			DemoPanel

public class DynamicDataDemo3 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
		implements ActionListener
	{

		public static final int SUBPLOT_COUNT = 3;
		private TimeSeriesCollection datasets[];
		private double lastValue[];

		public void actionPerformed(ActionEvent actionevent)
		{
			for (int i = 0; i < 3; i++)
				if (actionevent.getActionCommand().endsWith(String.valueOf(i)))
				{
					Millisecond millisecond1 = new Millisecond();
					System.out.println("Now = " + millisecond1.toString());
					lastValue[i] = lastValue[i] * (0.90000000000000002D + 0.20000000000000001D * Math.random());
					datasets[i].getSeries(0).add(new Millisecond(), lastValue[i]);
				}

			if (actionevent.getActionCommand().equals("ADD_ALL"))
			{
				Millisecond millisecond = new Millisecond();
				System.out.println("Now = " + millisecond.toString());
				for (int j = 0; j < 3; j++)
				{
					lastValue[j] = lastValue[j] * (0.90000000000000002D + 0.20000000000000001D * Math.random());
					datasets[j].getSeries(0).add(new Millisecond(), lastValue[j]);
				}

			}
		}

		public MyDemoPanel()
		{
			super(new BorderLayout());
			lastValue = new double[3];
			CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(new DateAxis("Time"));
			datasets = new TimeSeriesCollection[3];
			for (int i = 0; i < 3; i++)
			{
				lastValue[i] = 100D;
				TimeSeries timeseries = new TimeSeries("Random " + i);
				datasets[i] = new TimeSeriesCollection(timeseries);
				NumberAxis numberaxis = new NumberAxis("Y" + i);
				numberaxis.setAutoRangeIncludesZero(false);
				XYPlot xyplot = new XYPlot(datasets[i], null, numberaxis, new StandardXYItemRenderer());
				xyplot.setBackgroundPaint(Color.lightGray);
				xyplot.setDomainGridlinePaint(Color.white);
				xyplot.setRangeGridlinePaint(Color.white);
				combineddomainxyplot.add(xyplot);
			}

			JFreeChart jfreechart = new JFreeChart("Dynamic Data Demo 3", combineddomainxyplot);
			addChart(jfreechart);
			LegendTitle legendtitle = (LegendTitle)jfreechart.getSubtitle(0);
			legendtitle.setPosition(RectangleEdge.RIGHT);
			legendtitle.setMargin(new RectangleInsets(UnitType.ABSOLUTE, 0.0D, 4D, 0.0D, 4D));
			jfreechart.setBorderPaint(Color.black);
			jfreechart.setBorderVisible(true);
			ValueAxis valueaxis = combineddomainxyplot.getDomainAxis();
			valueaxis.setAutoRange(true);
			valueaxis.setFixedAutoRange(20000D);
			ChartUtilities.applyCurrentTheme(jfreechart);
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			add(chartpanel);
			JPanel jpanel = new JPanel(new FlowLayout());
			for (int j = 0; j < 3; j++)
			{
				JButton jbutton1 = new JButton("Series " + j);
				jbutton1.setActionCommand("ADD_DATA_" + j);
				jbutton1.addActionListener(this);
				jpanel.add(jbutton1);
			}

			JButton jbutton = new JButton("ALL");
			jbutton.setActionCommand("ADD_ALL");
			jbutton.addActionListener(this);
			jpanel.add(jbutton);
			add(jpanel, "South");
			chartpanel.setPreferredSize(new Dimension(500, 470));
			chartpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}
	}


	public DynamicDataDemo3(String s)
	{
		super(s);
		setContentPane(createDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		DynamicDataDemo3 dynamicdatademo3 = new DynamicDataDemo3("JFreeChart: DynamicDataDemo3.java");
		dynamicdatademo3.pack();
		RefineryUtilities.centerFrameOnScreen(dynamicdatademo3);
		dynamicdatademo3.setVisible(true);
	}
}
