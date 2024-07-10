package main.java.ru.clevertec.check.formatters;

import main.java.ru.clevertec.check.interfaces.ICheckFormatter;
import main.java.ru.clevertec.check.logic.Check;
import main.java.ru.clevertec.check.logic.models.CheckPositionModel;
import main.java.ru.clevertec.check.logic.models.DiscountCardModel;
import main.java.ru.clevertec.check.logic.models.ProductModel;

public class ConsoleCheckFormatter implements ICheckFormatter {

	@Override
	public void print(Check check) {
		System.out.printf("Date: %s\nTime: %s\n", check.getDate(), check.getTime());
		for (CheckPositionModel pos : check.getPositions()) {
			ProductModel prod = pos.getProduct();
			System.out.printf("%d; %s; %.2f$; %.2f$; %.2f$\n", pos.getQuantity(), prod.getDescription(), prod.getPrice(), pos.getDiscount(), pos.getTotal());
		}
		DiscountCardModel card = check.getDiscountCard();
		if (card.getNumber() != null) {
			System.out.printf("Discount Card: %s\nDiscount Amount: %d%%\n", card.getNumber(), card.getDiscountAmount());
		}
		System.out.printf("Total Price: %.2f$\nTotal Discount: %.2f$\nTotal With Discount: %.2f$\n", check.getTotalPrice(), check.getTotalDiscount(), check.getTotalWithDiscount());
	}
	
	@Override
	public void printError(Exception error) {
		System.err.println(error.toString());
	}

}
