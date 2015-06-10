package com.alberto;

import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;

import com.alberto.indicators.TestFile;

import eu.verdelhan.ta4j.Decimal;

public class Parser {

	public Parser() {
		// TODO Auto-generated constructor stub
	}

	public void run(String doc, String filename) {
		try {

			CSVParser oParser = CSVParser.parse(doc,
					CSVFormat.DEFAULT.withDelimiter(';'));

			String _built = "[";
			int i = 0;

			for (CSVRecord csvRecord : oParser) {
				try {
				
					if (i % 12 == 0) {
						

						FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss")
								.parse(csvRecord.get(1));

						DateTime datetime = DateTime.parse(csvRecord.get(1));
						Decimal price = Decimal.valueOf(csvRecord.get(0)
								.replaceAll("\\.", "").replaceAll(",", "."));

						if (i != 0) {
							_built += ",";
						}
						
						_built += "[" + datetime.toDate().getTime() + " , "
								+ price.toDouble() + "]";

					}
					
					i++;

				} catch (ParseException e) {
					// TODO Auto-generated catch block

				} catch (Exception e) {
					e.printStackTrace();
					break;
				}

			}
			_built += "]";

			File output = new File("resources/parsed/" + filename);
			FileUtils.writeStringToFile(output, _built);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Falla");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser = new Parser();

		String doc = "";
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
				parser.run(doc, file + ".json");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
