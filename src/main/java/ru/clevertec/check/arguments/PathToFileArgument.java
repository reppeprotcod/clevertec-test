package main.java.ru.clevertec.check.arguments;

import main.java.ru.clevertec.check.contexts.CheckContext;
import main.java.ru.clevertec.check.interfaces.IArgument;
import main.java.ru.clevertec.check.logic.databases.CSVReader;

public class PathToFileArgument implements IArgument {
	private String filePath;
	
	public PathToFileArgument(String path) {
		filePath = path;
	}
	
	@Override
	public void apply(CheckContext context) throws Exception {
		CSVReader reader = new CSVReader(filePath);
		context.getProductDb().setReader(reader);
		context.getProductDb().read();
	}

	@Override
	public int getPriority() {
		return 1;
	}

}
