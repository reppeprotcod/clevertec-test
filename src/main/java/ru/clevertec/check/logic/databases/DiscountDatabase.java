package main.java.ru.clevertec.check.logic.databases;

import java.util.ArrayList;

import main.java.ru.clevertec.check.logic.interfaces.IDatabase;
import main.java.ru.clevertec.check.logic.interfaces.IDbReader;
import main.java.ru.clevertec.check.logic.interfaces.IModel;
import main.java.ru.clevertec.check.logic.models.DiscountCardModel;

public class DiscountDatabase implements IDatabase {
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
			DiscountCardModel item = new DiscountCardModel();
			item.setId(Integer.parseInt(row[0]));
			item.setNumber(row[1]);
			item.setDiscountAmount(Integer.parseInt(row[2]));
			items.add(item);
		}
	}

}
