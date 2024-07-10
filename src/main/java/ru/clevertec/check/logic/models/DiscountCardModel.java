package main.java.ru.clevertec.check.logic.models;

import main.java.ru.clevertec.check.logic.interfaces.IModel;

public class DiscountCardModel implements IModel {
	private int id;
	private String number;
	private int discountAmount;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

}
