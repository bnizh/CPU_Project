package server.servlets;

import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.services.mailservice.confirmation.MailConfirmationProducer;
import utils.ConnectionHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(asyncSupported = true, name = "UserActivation", urlPatterns = {"/userActivation"})
public class UserActivationServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String sessionId = req.getSession().getId();
			Connection connection = ConnectionHashMap.getInstance().get(sessionId);

			if (connection == null) {
				connection = ConnectionPool.getInstance().getConnection();
				ConnectionHashMap.getInstance().put(sessionId, connection);
			}

			User user = (User) req.getSession().getAttribute("user");
			String activationCode = (String) req.getAttribute("code");
			boolean activated = MailConfirmationProducer.getInstance().activateUser(user, activationCode, connection);

		} catch (Exception ignore) {}
		System.out.println("test");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}