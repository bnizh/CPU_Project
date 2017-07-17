package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvailableOperatorsList extends ArrayList<String> {
	private static List<String> ourInstance;

	public static AvailableOperatorsList getInstance() {
		if(ourInstance==null)
			ourInstance= new AvailableOperatorsList();
		return (AvailableOperatorsList) ourInstance;
	}

	private AvailableOperatorsList() {
		ourInstance = Collections.synchronizedList(new ArrayList<String>());
	}
}
