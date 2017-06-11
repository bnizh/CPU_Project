package server.database.manager;

import common.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManagerImpl implements DatabaseManager {

	@Override
	public void createUser(User user, Connection connection) throws Exception {
		String query = "INSERT INTO USERS (id,name,mobile,birthDate,email,password) VALUES ('"+user.getId()+"','"+user.getName()+"','"+user.getMobile()+"','"+user.getBirthDate()+","+user.getEmail()+"','"+user.getPassword()+"')";
		helper(query,connection);
	}

	@Override
	public User getUserById(String id, Connection connection) throws Exception {
		return null;
	}

	@Override
	public User getUserByIdAndPassword(String id, String password, Connection connection) throws Exception {
		return null;
	}

	@Override
	public void updateUser(User user, Connection connection) throws Exception {

	}

	private void helper(String query, Connection connection) throws SQLException {
		PreparedStatement stm = connection.prepareStatement(query);
		stm.execute();
	}
}
