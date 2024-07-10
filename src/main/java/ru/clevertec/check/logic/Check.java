package main.java.ru.clevertec.check.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.ru.clevertec.check.interfaces.ICheckFormatter;
import main.java.ru.clevertec.check.logic.models.CheckPositionModel;
import main.java.ru.clevertec.check.logic.models.DiscountCardModel;
import main.java.ru.clevertec.check.logic.models.ProductModel;

public class Check {
	private ArrayList<CheckPositionModel> positions = new ArrayList<CheckPositionModel>();
	private ArrayList<ICheckFormatter> formatters = new ArrayList<ICheckFormatter>();
	private DiscountCardModel discountCard;
	private String date;
	private String time;
	private double totalPrice = 0;
	private double totalDiscount = 0;
	private double totalWithDiscount = 0;

	public Check() {
		discountCard = new DiscountCardModel();
		discountCard.setDiscountAmount(0);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		Date currentDate = new Date();
		date = dateFormat.format(currentDate);
		time = timeFormat.format(currentDate);
	}

	public CheckPositionModel[] getPositions() {
		CheckPositionModel[] result = new CheckPositionModel[positions.size()];
		return positions.toArray(result);
	}

	public CheckPositionModel addPosition(int quantity, ProductModel product) {
		CheckPositionModel position = new CheckPositionModel();
		position.setQuantity(quantity);
		position.setProduct(product);
		positions.add(position);
		return position;
	}

	public DiscountCardModel getDiscountCard() {
		return discountCard;
	}

	public void setDiscountCard(DiscountCardModel discountCard) {
		this.discountCard = discountCard;
	}

	public void addCheckFormatter(ICheckFormatter formatter) {
		formatters.add(formatter);
	}
	
	public void removeCheckFormatter(ICheckFormatter formatter) {
		formatters.remove(formatter);
	}

	public void calculate() {
		for (CheckPositionModel position : positions) {
			ProductModel product = position.getProduct();
			double total = product.getPrice() * position.getQuantity();
			if (product.isWholesale() && position.getQuantity() >= 5) {
				position.setDiscount(total * 0.1);
			} else {
				position.setDiscount(total * discountCard.getDiscountAmount() / 100.0);
			}
			position.setTotal(total);
			totalPrice += position.getTotal();
			totalDiscount += position.getDiscount();
		}
		totalWithDiscount = totalPrice - totalDiscount;
	}

	public void print() {
		for (ICheckFormatter formatter : formatters) {
			if (formatter != null) {
				formatter.print(this);
			}
		}
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public double getTotalWithDiscount() {
		return totalWithDiscount;
	}

}
