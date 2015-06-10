package com.alberto.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.joda.time.DateTime;

public class Chart extends ApplicationFrame implements ChartMouseListener, ChangeListener, ChartProgressListener{
	
	private JFrame frame;
	
	TimeSeries series;
	TimeSeries indicator;
	TimeSeries indicatorBis;
	TimeSeries emaseries;
	TimeSeriesCollection seriesCollection;
	JFreeChart chart;
	JSlider slider;
	Crosshair xCrosshair;
	Crosshair yCrosshair;
	Rectangle2D dataArea;
	

	public Chart() {
		super("Grafico");
		// TODO Auto-generated constructor stub
		initialize();
		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}
	
	public void stateChanged(ChangeEvent changeevent)
	{
		int i = slider.getValue();
		XYPlot xyplot = (XYPlot)chart.getPlot();
		ValueAxis valueaxis = xyplot.getDomainAxis();
		Range range = valueaxis.getRange();
		double d = valueaxis.getLowerBound() + ((double)i / 100D) * range.getLength();
		xyplot.setDomainCrosshairValue(d);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		series = new TimeSeries("Ibex35");
		indicator = new TimeSeries("MACD");
		indicatorBis = new TimeSeries("MACDBIS");
		emaseries = new TimeSeries("EMA");
		seriesCollection = new TimeSeriesCollection(series);
		seriesCollection.addSeries(emaseries);
		
		
		
		chart = createChart(seriesCollection);
		
		XYPlot xyplot = (XYPlot)chart.getPlot();
		
		xyplot.setDomainPannable(true);
		xyplot.setRangePannable(true);
		
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setDomainCrosshairLockedOnData(true);
		xyplot.setRangeCrosshairVisible(true);
	
		
		xyplot.getRenderer(0).setSeriesPaint(0, Color.BLUE);
		NumberAxis numberaxis = new NumberAxis("MACD");
		numberaxis.setAutoRangeIncludesZero(false);
		xyplot.setRangeAxis(1, numberaxis);
		TimeSeriesCollection _indicatorSeriesCollection = new TimeSeriesCollection(indicator);
		//xyplot.getRendererForDataset(_indicatorSeriesCollection).setSeriesPaint(0,Color.red);
		_indicatorSeriesCollection.addSeries(indicatorBis);
		xyplot.getRenderer(0).setSeriesPaint(1, Color.YELLOW);
		xyplot.setDataset(1, _indicatorSeriesCollection);
		xyplot.mapDatasetToRangeAxis(1, 1);
		
		
	
		ChartPanel cpanel = new ChartPanel(chart);
		cpanel.setDomainZoomable(true);
		cpanel.setRangeZoomable(false);		
		cpanel.setFillZoomRectangle(false);
		cpanel.setDisplayToolTips(true);
		cpanel.setPreferredSize(new Dimension(900, 700));
		cpanel.addChartMouseListener(this);
		
		CrosshairOverlay crosshair = new CrosshairOverlay();
		xCrosshair = new Crosshair(Double.NaN,Color.GRAY,new BasicStroke(0f));
		xCrosshair.setLabelVisible(true);
		yCrosshair = new Crosshair(Double.NaN,Color.GRAY,new BasicStroke(0f));
		yCrosshair.setLabelVisible(true);
		crosshair.addDomainCrosshair(xCrosshair);
		crosshair.addRangeCrosshair(yCrosshair);
		cpanel.addOverlay(crosshair);
		
		
	    dataArea = cpanel.getScreenDataArea();
		

		
		setContentPane(cpanel);
	}
	
	private JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Ibex 35 Analisis", "Hora", "Precio", xydataset, true, true, false);
		return jfreechart;
	}
	
	public void addPoint (DateTime dateTime , double price){
		Second _second = new Second(dateTime.toDate());
		series.add(_second, price);
		
	}
	
	
	public void addEmaPoint (DateTime dateTime , double price){
		Second _second = new Second(dateTime.toDate());
		emaseries.add(_second, price);
		
	}
	
	public void addIndicatorPoint (DateTime dateTime , double price){
		Second _second = new Second(dateTime.toDate());
		indicator.add(_second, price);
		
	}
	
	public void addIndicatorBisPoint (DateTime dateTime , double price){
		Second _second = new Second(dateTime.toDate());
		indicatorBis.add(_second, price);
		
	}
	
	public void addMarker (DateTime dateTime , Color color){
		ValueMarker valueMarker = new ValueMarker(dateTime.getMillis());
		
		valueMarker.setPaint(color);
		//valueMarker.setLabel("Original Close (02:00)");
		valueMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
		valueMarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
		
		XYPlot xyplot = (XYPlot)chart.getPlot();
		
		xyplot.addDomainMarker(valueMarker);
		
	}
	
	
	public IntervalMarker addInterval (double start , double end , Color color, String label){
		XYPlot xyplot = (XYPlot)chart.getPlot();
		IntervalMarker intervalmarker = new IntervalMarker(start, end);
		intervalmarker.setLabelOffsetType(LengthAdjustmentType.EXPAND);
		intervalmarker.setPaint(color);
		intervalmarker.setAlpha(0.1f);
		intervalmarker.setLabel(label);
		intervalmarker.setLabelFont(new Font("SansSerif", 0, 11));
		intervalmarker.setLabelPaint(color);
		intervalmarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
		intervalmarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
		xyplot.addDomainMarker(intervalmarker, Layer.BACKGROUND);
		return intervalmarker;
		
	}
	
	synchronized public void addDottedMarker (DateTime dateTime , Color color){
		ValueMarker valueMarker = new ValueMarker(dateTime.getMillis());
		
		valueMarker.setPaint(color);
		//valueMarker.setLabel("Original Close (02:00)");
		valueMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
		valueMarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
		 float dash[] = { 10.0f };
		valueMarker.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
		        BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		
		XYPlot xyplot = (XYPlot)chart.getPlot();
		
		xyplot.addDomainMarker(valueMarker);
	
	}

	@Override
	public void chartProgress(ChartProgressEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chartMouseClicked(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getTrigger().getX());
		int mouseX = arg0.getTrigger().getX();
		int mouseY = arg0.getTrigger().getY();
		
		JFreeChart _chart = arg0.getChart();
		
	    xCrosshair.setValue(mouseX);
	    yCrosshair.setValue(mouseY);
	    xCrosshair.setLabelXOffset(mouseX);
	    yCrosshair.setLabelYOffset(mouseY);
	    
	    
		
		
	}

}
