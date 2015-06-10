// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

// Referenced classes of package demo:
//			DemoPanel

public class HideSeriesDemo1 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
		implements ActionListener
	{

		private XYItemRenderer renderer;

		private XYDataset createSampleDataset()
		{
			XYSeries xyseries = new XYSeries("Series 1");
			xyseries.add(1.0D, 3.2999999999999998D);
			xyseries.add(2D, 4.4000000000000004D);
			xyseries.add(3D, 1.7D);
			XYSeries xyseries1 = new XYSeries("Series 2");
			xyseries1.add(1.0D, 7.2999999999999998D);
			xyseries1.add(2D, 6.7999999999999998D);
			xyseries1.add(3D, 9.5999999999999996D);
			xyseries1.add(4D, 5.5999999999999996D);
			XYSeries xyseries2 = new XYSeries("Series 3");
			xyseries2.add(1.0D, 17.300000000000001D);
			xyseries2.add(2D, 16.800000000000001D);
			xyseries2.add(3D, 19.600000000000001D);
			xyseries2.add(4D, 15.6D);
			XYSeriesCollection xyseriescollection = new XYSeriesCollection();
			xyseriescollection.addSeries(xyseries);
			xyseriescollection.addSeries(xyseries1);
			xyseriescollection.addSeries(xyseries2);
			return xyseriescollection;
		}

		private JFreeChart createChart(XYDataset xydataset)
		{
			JFreeChart jfreechart = ChartFactory.createXYLineChart("Hide Series Demo 1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, true, false);
			XYPlot xyplot = (XYPlot)jfreechart.getPlot();
			renderer = xyplot.getRenderer();
			return jfreechart;
		}

		public void actionPerformed(ActionEvent actionevent)
		{
			byte byte0 = -1;
			if (actionevent.getActionCommand().equals("S1"))
				byte0 = 0;
			else
			if (actionevent.getActionCommand().equals("S2"))
				byte0 = 1;
			else
			if (actionevent.getActionCommand().equals("S3"))
				byte0 = 2;
			if (byte0 >= 0)
			{
				boolean flag = renderer.getItemVisible(byte0, 0);
				renderer.setSeriesVisible(byte0, new Boolean(!flag));
			}
		}

		public MyDemoPanel()
		{
			super(new BorderLayout());
			XYDataset xydataset = createSampleDataset();
			JFreeChart jfreechart = createChart(xydataset);
			addChart(jfreechart);
			ChartPanel chartpanel = new ChartPanel(jfreechart, true);
			JPanel jpanel = new JPanel();
			JCheckBox jcheckbox = new JCheckBox("Series 1");
			jcheckbox.setActionCommand("S1");
			jcheckbox.addActionListener(this);
			jcheckbox.setSelected(true);
			JCheckBox jcheckbox1 = new JCheckBox("Series 2");
			jcheckbox1.setActionCommand("S2");
			jcheckbox1.addActionListener(this);
			jcheckbox1.setSelected(true);
			JCheckBox jcheckbox2 = new JCheckBox("Series 3");
			jcheckbox2.setActionCommand("S3");
			jcheckbox2.addActionListener(this);
			jcheckbox2.setSelected(true);
			jpanel.add(jcheckbox);
			jpanel.add(jcheckbox1);
			jpanel.add(jcheckbox2);
			add(chartpanel);
			add(jpanel, "South");
			chartpanel.setPreferredSize(new Dimension(500, 270));
		}
	}


	public HideSeriesDemo1(String s)
	{
		super(s);
		setContentPane(new MyDemoPanel());
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		HideSeriesDemo1 hideseriesdemo1 = new HideSeriesDemo1("JFreeChart: HideSeriesDemo1.java");
		hideseriesdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(hideseriesdemo1);
		hideseriesdemo1.setVisible(true);
	}
}
