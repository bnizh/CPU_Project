package utils;
import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

public class SessionIdHashMap extends ConcurrentHashMap<Session,String> {
	private static SessionIdHashMap ourInstance;

	public static SessionIdHashMap getInstance() {
		if(ourInstance==null)
			ourInstance= new SessionIdHashMap();
		return ourInstance;
	}

	private SessionIdHashMap() {
		super();
	}
}
