package main.java.ru.clevertec.check.interfaces;

import main.java.ru.clevertec.check.logic.Check;

public interface ICheckFormatter {
	public void print(Check check);
	public void printError(Exception error);
}
