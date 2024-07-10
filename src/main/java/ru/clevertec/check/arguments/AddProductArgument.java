package main.java.ru.clevertec.check.arguments;

import main.java.ru.clevertec.check.contexts.CheckContext;
import main.java.ru.clevertec.check.interfaces.IArgument;
import main.java.ru.clevertec.check.logic.Check;
import main.java.ru.clevertec.check.logic.databases.ProductDatabase;
import main.java.ru.clevertec.check.logic.interfaces.IModel;
import main.java.ru.clevertec.check.logic.models.CheckPositionModel;
import main.java.ru.clevertec.check.logic.models.ProductModel;

public class AddProductArgument implements IArgument {
	private int id;
	private int quantity;
	
	public AddProductArgument(int id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	@Override
	public void apply(CheckContext context) throws Exception {
		ProductDatabase db = context.getProductDb();
		Check check = context.getCheck();
		
		ProductModel product = null;
		for (IModel item : db.getItems()) {
			ProductModel p = (ProductModel) item;
			if (p.getId() == id) {
				product = p;
				break;
			}
		}
		
		if (product == null) {
			throw new Exception("BAD REQUEST");
		}
		
		CheckPositionModel position = null;
		for (CheckPositionModel pos : check.getPositions()) {
			if (pos.getProduct().getId() == id) {
				pos.setQuantity(pos.getQuantity() + quantity);
				position = pos;
				break;
			}
		}
		
		if (position == null) {
			position = check.addPosition(quantity, product);
		}
		
		if (position.getQuantity() > product.getQuantity()) {
			throw new Exception("BAD REQUEST");
		}
	}

}
