package server.services.mailservice.confirmation;

import common.users.User;
import server.database.manager.DatabaseManager;
import server.database.manager.UserManagerImpl;
import server.services.mailservice.SendMail;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;

public class MailConfirmationProducer {
	private ConcurrentHashMap<String, String> uuidStorage;
	private static MailConfirmationProducer producer;

	private MailConfirmationProducer () {
		uuidStorage = new ConcurrentHashMap<>();
	}

	public static MailConfirmationProducer getInstance () {
		if (producer == null) {
			producer = new MailConfirmationProducer();
		}
		return producer;
	}

	public boolean sendConfirmationMail (User user) {
		Sender sender = new Sender(user);
		Thread thread = new Thread(sender);
		thread.start();
		return false;
	}

	private String generateUUID(String userId) {
		String UUID = java.util.UUID.randomUUID().toString();
		uuidStorage.put(userId, UUID);
		return UUID;
	}

	public boolean activateUser(User user, String activationCode, Connection connection) {
		boolean isCorrect = verifyUUID(user.getId(), activationCode);

		if (isCorrect) {
			UserManagerImpl manager = new UserManagerImpl();
			user.setActive(true);
			try {
				manager.updateObject(user, connection);
				return true;
			} catch (Exception ignore) {}
		}

		return false;
	}

	private boolean verifyUUID (String userID, String UUID) {
		String savedUUID = uuidStorage.get(userID);
		return savedUUID != null && savedUUID.equals(UUID);
	}

	public void removeUUID (String userID) {
		uuidStorage.remove(userID);
	}
	private class Sender implements Runnable {
		private User user;
		public Sender (User user) {
			this.user = user;
		}
		@Override
		public void run() {
			String UUID = generateUUID(user.getId());
			try {
				SendMail.sendEmail(user.getEmail(), UUID);
			} catch (MessagingException ignore) {}
		}
	}
}
