package main.java.ru.clevertec.check.arguments;

import main.java.ru.clevertec.check.contexts.CheckContext;
import main.java.ru.clevertec.check.formatters.CSVCheckFormatter;
import main.java.ru.clevertec.check.interfaces.IArgument;

public class SaveToFileArgument implements IArgument {
	private String filePath;

	public SaveToFileArgument(String path) {
		filePath = path;
	}

	@Override
	public void apply(CheckContext context) throws Exception {
		context.setFormatter(new CSVCheckFormatter(filePath));
	}

	@Override
	public int getPriority() {
		return 0;
	}

}
