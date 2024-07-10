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
		CSVCheckFormatter formatter1 = new CSVCheckFormatter("result.csv");
		ConsoleCheckFormatter formatter2 = new ConsoleCheckFormatter();
		check.addCheckFormatter(formatter1);
		check.addCheckFormatter(formatter2);

		CheckContext context = new CheckContext();
		DbFactory dbFactory = new DbFactory();
		try {
			context.setDiscountDb((DiscountDatabase) dbFactory.create("discount"));
			context.setProductDb((ProductDatabase) dbFactory.create("products"));
		} catch (Exception e) {
			formatter1.printError(e);
			formatter2.printError(e);
			System.exit(1);
		}
		context.setCheck(check);

		ArgumentFactory argFactory = new ArgumentFactory();
		ArrayList<IArgument> arguments = new ArrayList<IArgument>();
		try {
			for (String arg : args) {
				arguments.add(argFactory.create(arg));
			}
		} catch (Exception e) {
			formatter1.printError(e);
			formatter2.printError(e);
			System.exit(1);
		}

		try {
			for (IArgument arg : arguments) {
				arg.apply(context);
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
			formatter1.printError(e);
			formatter2.printError(e);
		}
	}

}
