package main.java.ru.clevertec.check.logic.interfaces;

import java.io.IOException;

public interface IDbReader {
	public String[] nextRow() throws IOException;
}
