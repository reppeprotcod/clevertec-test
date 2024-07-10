package main.java.ru.clevertec.check.arguments;

import main.java.ru.clevertec.check.contexts.CheckContext;
import main.java.ru.clevertec.check.interfaces.IArgument;
import main.java.ru.clevertec.check.logic.Check;
import main.java.ru.clevertec.check.logic.databases.DiscountDatabase;
import main.java.ru.clevertec.check.logic.interfaces.IModel;
import main.java.ru.clevertec.check.logic.models.DiscountCardModel;

public class DiscountCardArgument implements IArgument {
	private String cardNumber;
	
	public DiscountCardArgument(String number) {
		cardNumber = number;
	}
	
	@Override
	public void apply(CheckContext context) {
		DiscountDatabase db = context.getDiscountDb();
		Check check = context.getCheck();
		for (IModel item : db.getItems()) {
			DiscountCardModel card = (DiscountCardModel) item;
			if (card.getNumber().equals(cardNumber)) {
				check.setDiscountCard(card);
				break;
			}
		}
		if (check.getDiscountCard().getNumber() == null) {
			DiscountCardModel card = new DiscountCardModel();
			card.setDiscountAmount(2);
			card.setNumber(cardNumber);
			check.setDiscountCard(card);
		}
	}

	@Override
	public int getPriority() {
		return 4;
	}

}
