package com.alberto;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.FastDateParser;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.slf4j.LoggerFactory;

import com.alberto.chart.Chart;

import eu.verdelhan.ta4j.AnalysisCriterion;
import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Indicator;
import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.Trade;
import eu.verdelhan.ta4j.TradingRecord;
import eu.verdelhan.ta4j.analysis.CashFlow;
import eu.verdelhan.ta4j.analysis.criteria.AverageProfitableTradesCriterion;
import eu.verdelhan.ta4j.analysis.criteria.RewardRiskRatioCriterion;
import eu.verdelhan.ta4j.analysis.criteria.TotalProfitCriterion;
import eu.verdelhan.ta4j.analysis.criteria.VersusBuyAndHoldCriterion;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.EMAIndicator;
import eu.verdelhan.ta4j.indicators.trackers.MACDIndicator;
import eu.verdelhan.ta4j.indicators.trackers.RSIIndicator;
import eu.verdelhan.ta4j.trading.rules.CrossedDownIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.StopLossRule;

public class Main {

	public void run(String doc, String filename) {
		try {

			CSVParser oParser = CSVParser.parse(doc,
					CSVFormat.DEFAULT.withDelimiter(';'));
			TradingDay tradingDay = new TradingDay(filename);

			for (CSVRecord csvRecord : oParser) {
				try {
					FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").parse(
							csvRecord.get(1));

					DateTime datetime = DateTime.parse(csvRecord.get(1));
					Decimal price = Decimal.valueOf(csvRecord.get(0)
							.replaceAll("\\.", "").replaceAll(",", "."));
					Tick tick = new Tick(
							datetime,
							price,
							Decimal.valueOf(csvRecord.get(4)
									.replaceAll("\\.", "").replaceAll(",", ".")),
							Decimal.valueOf(csvRecord.get(6)
									.replaceAll("\\.", "").replaceAll(",", ".")),
							Decimal.valueOf(csvRecord.get(2)
									.replaceAll("\\.", "").replaceAll(",", ".")),
							Decimal.valueOf(0));

					tradingDay.addTick(tick);
					tradingDay.trade();

				} catch (ParseException e) {
					// TODO Auto-generated catch block

				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			
			tradingDay.closePositions();
			
			File output = new File("resources/outputs/out_"+filename);
			FileUtils
					.writeStringToFile(output, tradingDay.getProfits());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Falla");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		String doc = "";
		Main main = new Main();
		File folder = new File("resources/inputs");

		String[] files = folder.list(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				// TODO Auto-generated method stub
				if (FilenameUtils.isExtension(arg1, "csv")) {
					return true;
				}
				return false;
			}
		});

		for (String file : files) {
			System.out.println(file);

			try {
				doc = FileUtils.readFileToString(new File("resources/inputs/"
						+ file));
				main.run(doc,file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
