package main.java.ru.clevertec.check.formatters;

import java.io.FileWriter;

import main.java.ru.clevertec.check.interfaces.ICheckFormatter;
import main.java.ru.clevertec.check.logic.Check;
import main.java.ru.clevertec.check.logic.models.CheckPositionModel;
import main.java.ru.clevertec.check.logic.models.DiscountCardModel;
import main.java.ru.clevertec.check.logic.models.ProductModel;

public class CSVCheckFormatter implements ICheckFormatter {
	private String filePath;

	public CSVCheckFormatter(String path) {
		filePath = path;
	}

	@Override
	public void print(Check check) {
		try {
			FileWriter writer = new FileWriter(filePath);

			writer.append("Date;Time\n");
			writer.append(String.format("%s;%s\n\n", check.getDate(), check.getTime()));

			writer.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
			for (CheckPositionModel pos : check.getPositions()) {
				ProductModel prod = pos.getProduct();
				writer.append(String.format("%d;%s;%.2f$;%.2f$;%.2f$\n", pos.getQuantity(), prod.getDescription(),
						prod.getPrice(), pos.getDiscount(), pos.getTotal()));
			}
			writer.append('\n');

			DiscountCardModel card = check.getDiscountCard();
			if (card.getNumber() != null) {
				writer.append("DISCOUNT CARD;DISCOUNT PERCENTAGE\n");
				writer.append(String.format("%s;%d%%\n", card.getNumber(), card.getDiscountAmount()));
				writer.append('\n');
			}

			writer.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
			writer.append(String.format("%.2f$;%.2f$;%.2f$", check.getTotalPrice(), check.getTotalDiscount(),
					check.getTotalWithDiscount()));

			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	@Override
	public void printError(Exception error) {
		try {
			FileWriter writer = new FileWriter(filePath);

			writer.append("ERROR\n");
			writer.append(error.getMessage());

			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

}
