package server.servlets;

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

public class LogInServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection;
		try {
			String id = req.getParameter("id");
			String pass = req.getParameter("pass");
			String sessionId = req.getSession().getId();
			connection = ConnectionHashMap.getInstance().get(sessionId);

			if (connection == null) {
				connection = ConnectionPool.getInstance().getConnection();
				ConnectionHashMap.getInstance().put(sessionId, connection);
			}
			UserManagerImpl manager = new UserManagerImpl();
			User user = manager.getUserByIdAndPassword(id, pass, connection);
			req.getSession().setAttribute("user", user);
			PrintWriter out = resp.getWriter();
			out.write(Boolean.toString(user.isActive()));
			out.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			PrintWriter out = resp.getWriter();
			out.write("error");
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
