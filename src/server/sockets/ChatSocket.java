package server.sockets;

import utils.SessionIdHashMap;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint("/Chat")
public class ChatSocket {
	private Map< Session,String> sessionHashMap = SessionIdHashMap.getInstance();
	private Session curSess;
	private static Set<Session> conns = java.util.Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(Session session) throws IOException {
		this.curSess = session;
		conns.add(curSess);
		session.getBasicRemote()
				.sendText("");
	}

	@OnMessage
	public void onMessage(String message) {
		if(!sessionHashMap.containsKey(curSess)){
			sessionHashMap.put(curSess,message);
		}else{
			try {
				String userName = message.substring(0,message.indexOf("$"));
				for(Session ses : conns) {
					if (sessionHashMap.containsKey(ses)&&sessionHashMap.get(ses).equals(userName) ) {
						ses.getBasicRemote().sendText(message.substring(message.indexOf("$") + 1));
					}
				}
			} catch (IOException e) {
				System.out.println("Error while sending message message");
			}
		}

	}

	@OnClose
	public void onClose(Session session,
	                    CloseReason reason) {
		sessionHashMap.remove(session);
	}

	@OnError
	public void onError(Session session,
	                    Throwable throwable) {

	}
}