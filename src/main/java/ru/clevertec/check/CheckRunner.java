package main.java.ru.clevertec.check;

import java.util.ArrayList;

import main.java.ru.clevertec.check.contexts.CheckContext;
import main.java.ru.clevertec.check.factories.ArgumentFactory;
import main.java.ru.clevertec.check.formatters.CSVCheckFormatter;
import main.java.ru.clevertec.check.formatters.ConsoleCheckFormatter;
import main.java.ru.clevertec.check.interfaces.IArgument;
import main.java.ru.clevertec.check.logic.Check;
import main.java.ru.clevertec.check.logic.databases.DiscountDatabase;
import main.java.ru.clevertec.check.logic.databases.ProductDatabase;
import main.java.ru.clevertec.check.logic.factories.DbFactory;

public class CheckRunner {

	public static void main(String[] args) {
		Check check = new Check();
		ConsoleCheckFormatter formatter = new ConsoleCheckFormatter();
		check.addCheckFormatter(formatter);

		CheckContext context = new CheckContext();
		DbFactory dbFactory = new DbFactory();
		Exception databaseException = null;
		try {
			context.setDiscountDb((DiscountDatabase) dbFactory.create("discount"));
			context.setProductDb((ProductDatabase) dbFactory.create("products"));
		} catch (Exception e) {
			databaseException = e;
		}
		context.setCheck(check);
		context.setFormatter(new CSVCheckFormatter("result.csv"));

		ArgumentFactory argFactory = new ArgumentFactory();
		ArrayList<IArgument> arguments = new ArrayList<IArgument>();
		Exception argumentsException = null;
		try {
			for (String arg : args) {
				arguments.add(argFactory.create(arg));
			}
		} catch (Exception e) {
			argumentsException = e;
		}
		arguments.sort((a1, a2) -> a1.getPriority() - a2.getPriority());

		try {
			for (IArgument arg : arguments) {
				arg.apply(context);
			}
			if (argumentsException != null) {
				throw argumentsException;
			}
			if (databaseException != null) {
				throw databaseException;
			}
			if(check.getPositions().length == 0) {
				throw new Exception("BAD REQUEST");
			}
			check.calculate();
			if (context.getCard() == null) {
				throw new Exception("BAD REQUEST");
			}
			if (context.getCard().getBalance() < check.getTotalWithDiscount()) {
				throw new Exception("NOT ENOUGH MONEY");
			}
			check.print();
		} catch (Exception e) {
			formatter.printError(e);
			context.getFormatter().printError(e);
		}
	}

}
