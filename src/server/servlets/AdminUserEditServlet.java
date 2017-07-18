package server.servlets;

import com.google.gson.Gson;
import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.database.manager.UserManagerImpl;
import utils.ConnectionHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

/**
 * Created by andro on 7/18/17.
 */
@WebServlet("/AdminUserEditServlet")
public class AdminUserEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setCharacterEncoding("utf-8");
        Connection connection;
        try {
            String id = req.getParameter("id");
            String sessionId = req.getSession().getId();
            connection = ConnectionHashMap.getInstance().get(sessionId);

            if (connection == null) {
                connection = ConnectionPool.getInstance().getConnection();
                ConnectionHashMap.getInstance().put(sessionId, connection);
            }
            UserManagerImpl manager = new UserManagerImpl();
            List<User> users = manager.getUserById(id, connection);
            req.getSession().setAttribute("user", users);
            //response.setCharacterEncoding("utf8");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();

            Gson gson = new Gson();
            String obj = gson.toJson(users);
            out.print(obj);
            System.out.println(obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            PrintWriter out = resp.getWriter();
            out.write("error");
            out.close();
        }*/
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
