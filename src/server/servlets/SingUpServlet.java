package server.servlets;

import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.database.manager.DatabaseManager;
import server.database.manager.DatabaseManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


@WebServlet("/signUp")
public class SingUpServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date(formatter.parse(req.getParameter("date")).getTime());
			User user = new User();
			user.setId(req.getParameter("id"));
			user.setBirthDate(date);
			user.setMobile(req.getParameter("mobile"));
			user.setName(req.getParameter("name"));
			user.setPassword(req.getParameter("pass"));
			user.setEmail(req.getParameter("email"));
			DatabaseManager manager = new DatabaseManagerImpl();
			HttpSession session = req.getSession(false);
			if (session == null)
				session = req.getSession(true);
			if (session.isNew()) {
				connection = ConnectionPool.getInstance().getConnection();
			} else {
				connection = (Connection) session.getAttribute("Connection");
			}
			manager.createUser(user, connection);
			session.setAttribute("Connection", connection);
			session.setAttribute("User", user);
			session.setAttribute("test", "test");
			resp.addCookie(new Cookie("sessionId", session.getId()));
			resp.sendRedirect("index.html");
		} catch (Exception e) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Can't close connection :" + connection + "error :" + e1.getMessage());
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
