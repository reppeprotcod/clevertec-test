package main.java.ru.clevertec.check.logic.factories;

import main.java.ru.clevertec.check.logic.databases.CSVReader;
import main.java.ru.clevertec.check.logic.databases.DiscountDatabase;
import main.java.ru.clevertec.check.logic.databases.ProductDatabase;
import main.java.ru.clevertec.check.logic.interfaces.IDatabase;

public class DbFactory {
	public IDatabase create(String type) throws Exception {
		switch (type) {
		case "products":
			// CSVReader readerProducts = new CSVReader("./src/main/resources/products.csv");
			ProductDatabase productsDb = new ProductDatabase();
			// productsDb.setReader(readerProducts);
			// productsDb.read();
			return productsDb;
		case "discount":
			CSVReader readerDiscount = new CSVReader("./src/main/resources/discountCards.csv");
			DiscountDatabase discountDb = new DiscountDatabase();
			discountDb.setReader(readerDiscount);
			discountDb.read();
			return discountDb;
		default:
			throw new Exception("INTERNAL SERVER ERROR");
		}
	}
}