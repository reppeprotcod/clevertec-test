package main.java.ru.clevertec.check.interfaces;

import main.java.ru.clevertec.check.contexts.CheckContext;

public interface IArgument {
	public void apply(CheckContext context) throws Exception;
}