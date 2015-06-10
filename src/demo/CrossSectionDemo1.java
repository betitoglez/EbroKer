// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.*;
import org.jfree.chart.annotations.XYDataImageAnnotation;
import org.jfree.chart.axis.*;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.GrayPaintScale;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.*;

// Referenced classes of package demo:
//			DemoPanel

public class CrossSectionDemo1 extends ApplicationFrame
{
	static class MyDemoPanel extends DemoPanel
		implements ChangeListener, ChartChangeListener
	{

		private HeatMapDataset dataset;
		private JFreeChart mainChart;
		private JFreeChart subchart1;
		private JFreeChart subchart2;
		private JSlider slider1;
		private JSlider slider2;
		private Crosshair crosshair1;
		private Crosshair crosshair2;
		private Range lastXRange;
		private Range lastYRange;

		public JPanel createMainPanel()
		{
			mainChart = createChart(new XYSeriesCollection());
			mainChart.addChangeListener(this);
			ChartPanel chartpanel = new ChartPanel(mainChart);
			chartpanel.setFillZoomRectangle(true);
			chartpanel.setMouseWheelEnabled(true);
			return chartpanel;
		}

		public void stateChanged(ChangeEvent changeevent)
		{
			if (changeevent.getSource() == slider1)
			{
				crosshair2.setValue(slider1.getValue());
				int i = slider1.getValue() - slider1.getMinimum();
				XYDataset xydataset = HeatMapUtilities.extractColumnFromHeatMapDataset(dataset, i, "Y1");
				subchart2.getXYPlot().setDataset(xydataset);
			} else
			if (changeevent.getSource() == slider2)
			{
				crosshair1.setValue(slider2.getValue());
				int j = slider2.getValue() - slider2.getMinimum();
				XYDataset xydataset1 = HeatMapUtilities.extractRowFromHeatMapDataset(dataset, j, "Y2");
				subchart1.getXYPlot().setDataset(xydataset1);
			}
		}

		public void chartChanged(ChartChangeEvent chartchangeevent)
		{
			XYPlot xyplot = (XYPlot)mainChart.getPlot();
			if (!xyplot.getDomainAxis().getRange().equals(lastXRange))
			{
				lastXRange = xyplot.getDomainAxis().getRange();
				XYPlot xyplot1 = (XYPlot)subchart2.getPlot();
				xyplot1.getDomainAxis().setRange(lastXRange);
			}
			if (!xyplot.getRangeAxis().getRange().equals(lastYRange))
			{
				lastYRange = xyplot.getRangeAxis().getRange();
				XYPlot xyplot2 = (XYPlot)subchart1.getPlot();
				xyplot2.getDomainAxis().setRange(lastYRange);
			}
		}

		private JFreeChart createChart(XYDataset xydataset)
		{
			JFreeChart jfreechart = ChartFactory.createScatterPlot("CrossSectionDemo1", "X", "Y", xydataset, PlotOrientation.VERTICAL, true, false, false);
			dataset = CrossSectionDemo1.createMapDataset();
			GrayPaintScale graypaintscale = new GrayPaintScale(-1D, 1.0D, 128);
			java.awt.image.BufferedImage bufferedimage = HeatMapUtilities.createHeatMapImage(dataset, graypaintscale);
			XYDataImageAnnotation xydataimageannotation = new XYDataImageAnnotation(bufferedimage, -250.5D, -250.5D, 501D, 501D, true);
			XYPlot xyplot = (XYPlot)jfreechart.getPlot();
			xyplot.setDomainPannable(true);
			xyplot.setRangePannable(true);
			xyplot.getRenderer().addAnnotation(xydataimageannotation, Layer.BACKGROUND);
			NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
			numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			numberaxis.setLowerMargin(0.0D);
			numberaxis.setUpperMargin(0.0D);
			NumberAxis numberaxis1 = (NumberAxis)xyplot.getRangeAxis();
			numberaxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			numberaxis1.setLowerMargin(0.0D);
			numberaxis1.setUpperMargin(0.0D);
			return jfreechart;
		}

		public MyDemoPanel()
		{
			super(new BorderLayout());
			ChartPanel chartpanel = (ChartPanel)createMainPanel();
			chartpanel.setPreferredSize(new Dimension(500, 270));
			CrosshairOverlay crosshairoverlay = new CrosshairOverlay();
			crosshair1 = new Crosshair(0.0D);
			crosshair1.setPaint(Color.red);
			crosshair2 = new Crosshair(0.0D);
			crosshair2.setPaint(Color.blue);
			crosshairoverlay.addDomainCrosshair(crosshair1);
			crosshairoverlay.addRangeCrosshair(crosshair2);
			chartpanel.addOverlay(crosshairoverlay);
			crosshair1.setLabelVisible(true);
			crosshair1.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
			crosshair1.setLabelBackgroundPaint(new Color(255, 255, 0, 100));
			crosshair2.setLabelVisible(true);
			crosshair2.setLabelBackgroundPaint(new Color(255, 255, 0, 100));
			add(chartpanel);
			JPanel jpanel = new JPanel(new BorderLayout());
			XYSeriesCollection xyseriescollection = new XYSeriesCollection();
			subchart1 = ChartFactory.createXYLineChart("Cross-section A", "Y", "Z", xyseriescollection, PlotOrientation.HORIZONTAL, false, false, false);
			XYPlot xyplot = (XYPlot)subchart1.getPlot();
			xyplot.getDomainAxis().setLowerMargin(0.0D);
			xyplot.getDomainAxis().setUpperMargin(0.0D);
			xyplot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
			ChartPanel chartpanel1 = new ChartPanel(subchart1);
			chartpanel1.setMinimumDrawWidth(0);
			chartpanel1.setMinimumDrawHeight(0);
			chartpanel1.setPreferredSize(new Dimension(200, 150));
			slider1 = new JSlider(-250, 250, 0);
			slider1.addChangeListener(this);
			slider1.setOrientation(1);
			jpanel.add(chartpanel1);
			jpanel.add(slider1, "West");
			JPanel jpanel1 = new JPanel(new BorderLayout());
			XYSeriesCollection xyseriescollection1 = new XYSeriesCollection();
			subchart2 = ChartFactory.createXYLineChart("Cross-section B", "X", "Z", xyseriescollection1, PlotOrientation.VERTICAL, false, false, false);
			XYPlot xyplot1 = (XYPlot)subchart2.getPlot();
			xyplot1.getDomainAxis().setLowerMargin(0.0D);
			xyplot1.getDomainAxis().setUpperMargin(0.0D);
			xyplot1.getRenderer().setSeriesPaint(0, Color.blue);
			ChartPanel chartpanel2 = new ChartPanel(subchart2);
			chartpanel2.setMinimumDrawWidth(0);
			chartpanel2.setMinimumDrawHeight(0);
			chartpanel2.setPreferredSize(new Dimension(200, 150));
			JPanel jpanel2 = new JPanel();
			jpanel2.setPreferredSize(new Dimension(200, 10));
			jpanel1.add(jpanel2, "East");
			slider2 = new JSlider(-250, 250, 0);
			slider2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
			slider2.addChangeListener(this);
			jpanel1.add(chartpanel2);
			jpanel1.add(slider2, "North");
			add(jpanel, "East");
			add(jpanel1, "South");
			mainChart.setNotify(true);
		}
	}


	public CrossSectionDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		setContentPane(jpanel);
	}

	private static HeatMapDataset createMapDataset()
	{
		DefaultHeatMapDataset defaultheatmapdataset = new DefaultHeatMapDataset(501, 501, -250D, 250D, -250D, 250D);
		for (int i = 0; i < 501; i++)
		{
			for (int j = 0; j < 501; j++)
				defaultheatmapdataset.setZValue(i, j, Math.sin(Math.sqrt(i * j) / 10D));

		}

		return defaultheatmapdataset;
	}

	public static JPanel createDemoPanel()
	{
		return new MyDemoPanel();
	}

	public static void main(String args[])
	{
		CrossSectionDemo1 crosssectiondemo1 = new CrossSectionDemo1("JFreeChart: CrossSectionDemo1");
		crosssectiondemo1.pack();
		RefineryUtilities.centerFrameOnScreen(crosssectiondemo1);
		crosssectiondemo1.setVisible(true);
	}

}
