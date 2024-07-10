package main.java.ru.clevertec.check.logic.databases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.java.ru.clevertec.check.logic.interfaces.IDbReader;

public class CSVReader implements IDbReader {
	private BufferedReader csvReader;

	public CSVReader(String filePath) throws Exception {
		csvReader = new BufferedReader(new FileReader(filePath));
		csvReader.readLine();
	}

	@Override
	public String[] nextRow() throws IOException {
		String row = csvReader.readLine();
		if(row != null) return row.split(";");
		else return null; 
	}
}
