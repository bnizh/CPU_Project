package server.database.manager;

import common.users.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        String query = "SELECT * FROM USERS WHERE USERS.id=? ";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, id);
        ResultSet resSet = stm.executeQuery();
        User user = new User();
        if (!resSet.next()) return null;
        else {
            do {
                user.setId(resSet.getString("id"));
                user.setName(resSet.getString("name"));
                user.setMobile(resSet.getString("mobile"));
                user.setBirthDate(resSet.getDate("birthDate"));
                user.setEmail(resSet.getString("email"));
                user.setPassword(resSet.getString("password"));
                user.setActive(resSet.getBoolean("isActive"));
            } while (resSet.next());
        }
        stm.close();
        return user;
    }

    public User getUserByIdAndPassword(String id, String password, Connection connection) throws Exception {
        String query = "SELECT * FROM USERS WHERE USERS.id= ? AND USERS.password= ? ";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, id);
        stm.setString(2, password);
        ResultSet resSet = stm.executeQuery();
        User user = new User();
        if (!resSet.next()) return null;
        else {
            do {
                user.setId(resSet.getString("id"));
                user.setName(resSet.getString("name"));
                user.setMobile(resSet.getString("mobile"));
                user.setBirthDate(resSet.getDate("birthDate"));
                user.setEmail(resSet.getString("email"));
                user.setPassword(resSet.getString("password"));
                user.setActive(resSet.getBoolean("isActive"));
            } while (resSet.next());
        }
        stm.close();
        return user;
    }


    @Override
    public void updateObject(Object object, Connection connection) throws Exception {
        String query = "UPDATE USERS SET  name=?, mobile=?, birthDate=?, email=?, password=?, isActive=? WHERE USERS.id= ?";
        User user = (User) object;
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, user.getName());
        stm.setString(2, user.getMobile());
        stm.setDate(3, user.getBirthDate());
        stm.setString(4, user.getEmail());
        stm.setString(5, user.getPassword());
        stm.setBoolean(6, user.isActive());
        stm.setString(7, user.getId());
        stm.executeUpdate();
        stm.close();

    }

}
