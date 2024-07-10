package main.java.ru.clevertec.check.logic.interfaces;

import java.io.IOException;

public interface IDatabase {
	public void setReader(IDbReader reader);
	public IModel[] getItems();
	public IModel getItemById(int id);
	public void read() throws IOException;
}
