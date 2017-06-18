package server.database.manager;

import common.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserManagerImpl implements DatabaseManager {

	@Override
	public void createObject(Object object, Connection connection) throws Exception {
		String query = "INSERT INTO USERS (id, name, mobile, birthDate, email, password, isActive) VALUES ( ?,?,?,?,?,?,? )";
		User user = (User) object;
		PreparedStatement stm = connection.prepareStatement(query);
		stm.setString(1, user.getId());
		stm.setString(2, user.getName());
		stm.setString(3, user.getMobile());
		stm.setDate(4, user.getBirthDate());
		stm.setString(5, user.getEmail());
		stm.setString(6, user.getPassword());
		stm.setBoolean(7, user.isActive());
		stm.execute();
	}

	@Override
	public User getObjectById(String id, Connection connection) throws Exception {
		return null;
	}

	public User getUserByIdAndPassword(String id, String password, Connection connection) throws Exception {
		return null;
	}

	@Override
	public void updateObject(Object object, Connection connection) throws Exception {

	}

}
