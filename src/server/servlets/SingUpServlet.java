package server.servlets;

import common.users.User;
import server.database.connectionpool.ConnectionPool;
import server.database.manager.DatabaseManager;
import server.database.manager.UserManagerImpl;
import server.services.mailservice.confirmation.MailConfirmationProducer;
import utils.ConnectionHashMap;

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


@WebServlet(asyncSupported = true, urlPatterns = {"/signUp"})
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
			DatabaseManager manager = new UserManagerImpl();
			String sessionId = req.getSession().getId();
			connection = ConnectionHashMap.getInstance().get(sessionId);

			if (connection == null) {
				connection = ConnectionPool.getInstance().getConnection();
				ConnectionHashMap.getInstance().put(sessionId, connection);
			}

			req.getSession().setAttribute("user", user);
			manager.createUser(user, connection);
			MailConfirmationProducer.getInstance().sendConfirmationMail(user);
		} catch (Exception e) {
			e.printStackTrace();
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
		resp.setContentType("text/html; charset=UTF-8");

		PrintWriter out = resp.getWriter();

		out.println("<!DOCTYPE html>");

		out.println("<head>");

		out.println("<meta charset=\"UTF-8\" />");

		out.println("<title>A Basic Servlet Example</title>");

		out.println("</head>");

		out.println("<body>");

		out.println("<h1>"+req.getParameter("id")+"</h1>");

		out.println("</body>");

		out.println("</html>");
		System.out.println("recived " + req.getParameter("id"));

	}
}
