package server.database.manager;


import common.users.User;

import java.sql.Connection;

public interface DatabaseManager {

	void createObject(Object object, Connection connection) throws Exception;

	User getObjectById(String id, Connection connection) throws Exception;

	void updateObject (Object object, Connection connection) throws Exception;
}
