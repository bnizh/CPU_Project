package server.servlets;

import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.services.mailservice.confirmation.MailConfirmationProducer;
import utils.ConnectionHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

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
			String action = req.getParameter("action");
			String redirectURL = "";
			User user = (User) req.getSession().getAttribute("user");
			if (action.equals("verify")) {
				String activationCode = req.getParameter("code");
				boolean activated = MailConfirmationProducer.getInstance().activateUser(user, activationCode, connection);
				PrintWriter out = resp.getWriter();
				if (activated) {
					out.write("activation-success");
				} else {
					out.write("activation-incorrectCode");
				}
				out.close();
			} else if (action.equals("resend")){
				MailConfirmationProducer.getInstance().sendConfirmationMail(user);
				PrintWriter out = resp.getWriter();
				out.write("resend-success");
				out.close();
			}
		} catch (Exception ex) {
			PrintWriter out = resp.getWriter();
			out.write("error");
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}