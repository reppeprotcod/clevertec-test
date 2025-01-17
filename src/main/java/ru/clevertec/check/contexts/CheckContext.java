package main.java.ru.clevertec.check.contexts;

import main.java.ru.clevertec.check.logic.Check;
import main.java.ru.clevertec.check.logic.databases.DiscountDatabase;
import main.java.ru.clevertec.check.logic.databases.ProductDatabase;
import main.java.ru.clevertec.check.logic.models.DebitCardModel;

public class CheckContext {
	private Check check;
	private DebitCardModel card;
	private DiscountDatabase discountDb;
	private ProductDatabase productDb;
	
	public Check getCheck() {
		return check;
	}
	
	public void setCheck(Check check) {
		this.check = check;
	}
	
	public DebitCardModel getCard() {
		return card;
	}
	
	public void setCard(DebitCardModel card) {
		this.card = card;
	}

	public DiscountDatabase getDiscountDb() {
		return discountDb;
	}

	public void setDiscountDb(DiscountDatabase discountDb) {
		this.discountDb = discountDb;
	}

	public ProductDatabase getProductDb() {
		return productDb;
	}

	public void setProductDb(ProductDatabase productDb) {
		this.productDb = productDb;
	}
}
