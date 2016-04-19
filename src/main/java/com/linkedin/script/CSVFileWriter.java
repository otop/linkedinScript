package com.linkedin.script;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CSVFileWriter {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ", 	";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "companyName,linkedInPage";

	public static void writeCSVFile(String fileName, Map<String, String> url) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			Iterator iterator = url.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry pair = (Map.Entry) iterator.next();
				fileWriter.append(String.valueOf(pair.getKey()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(pair.getValue()));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();

			}

		}
	}
}
