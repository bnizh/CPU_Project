package server.database.manager;


import common.users.User;

public interface DatabaseManager {

	void createUser(User user) throws Exception;

	User getUserById(String id) throws Exception;

	User getUserByIdAndPassword(String id, String password) throws Exception;

	void updateUser (User user) throws Exception;
}