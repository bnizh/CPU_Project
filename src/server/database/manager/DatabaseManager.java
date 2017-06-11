package server.database.manager;


import common.users.User;

import java.sql.Connection;

public interface DatabaseManager {

	void createUser(User user, Connection connection) throws Exception;

	User getUserById(String id, Connection connection) throws Exception;

	User getUserByIdAndPassword(String id, String password, Connection connection) throws Exception;

	void updateUser (User user, Connection connection) throws Exception;
}
