package server.servlets;

import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.database.manager.DatabaseManager;
import server.database.manager.UserManagerImpl;
import server.services.mailservice.confirmation.MailConfirmationProducer;
import utils.ConnectionHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


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
			user.setActive(false);
			UserManagerImpl manager = new UserManagerImpl();
			String sessionId = req.getSession().getId();
			connection = ConnectionHashMap.getInstance().get(sessionId);

			if (connection == null) {
				connection = ConnectionPool.getInstance().getConnection();
				ConnectionHashMap.getInstance().put(sessionId, connection);
			}
			manager.createObject(user, connection);
			PrintWriter out = resp.getWriter();
			out.write("success");
			out.close();
			MailConfirmationProducer.getInstance().sendConfirmationMail(user);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			PrintWriter out = resp.getWriter();
			out.write("error");
			out.close();
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ignored) {}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
