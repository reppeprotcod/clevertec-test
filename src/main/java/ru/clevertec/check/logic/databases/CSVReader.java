package main.java.ru.clevertec.check.logic.databases;

import java.io.BufferedReader;
import java.io.FileReader;

import main.java.ru.clevertec.check.logic.interfaces.IDbReader;

public class CSVReader implements IDbReader {
	private BufferedReader csvReader;

	public CSVReader(String filePath) throws Exception {
		try {
			csvReader = new BufferedReader(new FileReader(filePath));
			csvReader.readLine();
		} catch (Exception e) {
			throw new Exception("INTERNAL SERVER ERROR");
		}
	}

	@Override
	public String[] nextRow() throws Exception {
		try {
			String row = csvReader.readLine();
			if (row != null) {
				return row.split(";");
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception("INTERNAL SERVER ERROR");
		}
	}
}
