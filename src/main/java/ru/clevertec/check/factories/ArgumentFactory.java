package main.java.ru.clevertec.check.factories;

import java.util.regex.Pattern;

import main.java.ru.clevertec.check.arguments.AddProductArgument;
import main.java.ru.clevertec.check.arguments.DebitCardArgument;
import main.java.ru.clevertec.check.arguments.DiscountCardArgument;
import main.java.ru.clevertec.check.arguments.PathToFileArgument;
import main.java.ru.clevertec.check.arguments.SaveToFileArgument;
import main.java.ru.clevertec.check.interfaces.IArgument;

public class ArgumentFactory {
	public IArgument create(String arg) throws Exception {
		if (Pattern.matches("^discountCard=\\d{4}$", arg)) {
			String number = arg.replaceFirst("discountCard=", "");
			return new DiscountCardArgument(number);
		} else if (arg.startsWith("balanceDebitCard=")) {
			String balance = arg.replaceFirst("balanceDebitCard=", "");
			return new DebitCardArgument(Double.parseDouble(balance));
		} else if (Pattern.matches("^\\d+-\\d+$", arg)) {
			String[] pos = arg.split("-");
			int productId = Integer.parseInt(pos[0]);
			int productQuantity = Integer.parseInt(pos[1]);
			return new AddProductArgument(productId, productQuantity);
		} else if (arg.startsWith("pathToFile=")) {
			String path = arg.replaceFirst("pathToFile=", "");
			return new PathToFileArgument(path);
		} else if (arg.startsWith("saveToFile=")) {
			String path = arg.replaceFirst("saveToFile=", "");
			return new SaveToFileArgument(path);
		}
		throw new Exception("BAD REQUEST");
	}
}
