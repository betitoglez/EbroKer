package com.alberto;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Test {

	private JFrame frame;
	
	private  void chart () {
		final TimeSeries series = new TimeSeries( "Random Data" );
		
		Second second = new Second();
		
		for (int i=0;i<5000;i++){
			
			series.add(second, 100 + Math.random()*100*i);
			second = (Second) second.next();
		}
		
		TimeSeriesCollection timeSeries = new TimeSeriesCollection(series);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Prueba", "Hora", "Precio", timeSeries);
		JFreeChart chart2 = ChartFactory.createTimeSeriesChart("Prueba 2", "Hora", "Precio", timeSeries);
		ChartPanel panel = new ChartPanel(chart);
		ChartPanel panel2 = new ChartPanel(chart2);
		
	    panel2.setFillZoomRectangle(true);
	    panel2.setRangeZoomable(true);
		frame.add(panel,0);
		frame.add(panel2,1);
		
		//frame.setContentPane(panel);
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.chart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
