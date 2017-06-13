package utils;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionHashMap extends ConcurrentHashMap<String, Connection> {
	private static ConnectionHashMap instance ;

	public static ConnectionHashMap getInstance() {
		if(instance==null)
			instance= new ConnectionHashMap();
		return instance;
	}

	private ConnectionHashMap() {
		super();
	}

}
