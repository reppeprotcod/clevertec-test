package main.java.ru.clevertec.check.logic.models;

import main.java.ru.clevertec.check.logic.interfaces.IModel;

public class ProductModel implements IModel {
	private int id;
	private String description;
	private double price;
	private int quantity;
	private boolean wholesale;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isWholesale() {
		return wholesale;
	}

	public void setWholesale(boolean wholesale) {
		this.wholesale = wholesale;
	}

}
