package main.java.ru.clevertec.check.logic.databases;

import java.util.ArrayList;

import main.java.ru.clevertec.check.logic.interfaces.IDatabase;
import main.java.ru.clevertec.check.logic.interfaces.IDbReader;
import main.java.ru.clevertec.check.logic.interfaces.IModel;
import main.java.ru.clevertec.check.logic.models.ProductModel;

public class ProductDatabase implements IDatabase {
	private IDbReader reader;
	private ArrayList<IModel> items = new ArrayList<IModel>();
	
	@Override
	public void setReader(IDbReader reader) {
		this.reader = reader;
	}

	@Override
	public IModel[] getItems() {
		IModel[] result = new IModel[items.size()];
		return items.toArray(result);
	}

	@Override
	public IModel getItemById(int id) {
		return items.stream().dropWhile((IModel m) -> m.getId() != id).toList().getFirst();
	}

	@Override
	public void read() throws Exception {
		items.clear();
		String[] row;
		while((row = reader.nextRow()) != null) {
			ProductModel item = new ProductModel();
			item.setId(Integer.parseInt(row[0]));
			item.setDescription(row[1]);
			item.setPrice(Double.parseDouble(row[2]));
			item.setQuantity(Integer.parseInt(row[3]));
			item.setWholesale(Boolean.parseBoolean(row[4]));
			items.add(item);
		}
	}
	
}
