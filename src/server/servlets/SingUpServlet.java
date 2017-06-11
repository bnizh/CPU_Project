package server.servlets;
import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.database.manager.DatabaseManager;
import server.database.manager.DatabaseManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/signUp")
public class SingUpServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(req.getParameter("name"), req.getParameter("id"), req.getParameter("email"), req.getParameter("mobile"), Date.valueOf(req.getParameter("date")), req.getParameter("pass"));
		DatabaseManager manager = new DatabaseManagerImpl();
		try {
			manager.createUser(user, ConnectionPool.getInstance().getConnection());
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
