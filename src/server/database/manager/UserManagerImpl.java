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
        String query = "SELECT * FROM USERS WHERE USERS.id=? ";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, id);
        ResultSet resSet = stm.executeQuery();
        if (!resSet.next()) return null;
        else {
            do {
                String id = resSet.getString("id");
                String name = resSet.getString("name");
                String mobile = resSet.getString("mobile");
                Date birthDate = resSet.getDate("birthDate");
                String email = resSet.getString("email");
                String password = resSet.getString("password");
                //boolean isActive = resSet.getBoolean("isActive");
            } while (resSet.next());
        }
        stm.close();
        User user = new User(name, id, email, mobile, birthDate, password);



        return user;
    }

    public User getUserByIdAndPassword(String id, String password, Connection connection) throws Exception {
        String query = "SELECT * FROM USERS WHERE USERS.id= ? AND USERS.password= ? ";
        PreparedStatement stm = connection.prepareStatement(query);
        stm.setString(1, id);
        stm.setString(2, password);
        ResultSet resSet = stm.executeQuery();
        if (!resSet.next()) return null;
        else {
            do {
                String id = resSet.getString("id");
                String name = resSet.getString("name");
                String mobile = resSet.getString("mobile");
                Date birthDate = resSet.getDate("birthDate");
                String email = resSet.getString("email");
                String password = resSet.getString("password");
                //boolean isActive = resSet.getBoolean("isActive");
            }
            while (resSet.next())
        }
        stm.close();
        User user = new User(name, id, email, mobile, birthDate, password);
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
