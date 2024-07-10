package main.java.ru.clevertec.check.arguments;

import main.java.ru.clevertec.check.contexts.CheckContext;
import main.java.ru.clevertec.check.interfaces.IArgument;
import main.java.ru.clevertec.check.logic.models.DebitCardModel;

public class DebitCardArgument implements IArgument {
	private double cardBalance;
	
	public DebitCardArgument(double balance) {
		cardBalance = balance;
	}

	@Override
	public void apply(CheckContext context) {
		DebitCardModel card = new DebitCardModel();
		card.setBalance(cardBalance);
		context.setCard(card);
	}

	@Override
	public int getPriority() {
		return 3;
	}

}
