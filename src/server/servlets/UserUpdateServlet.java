package server.servlets;

import common.users.User;
import server.database.connectionpool.ConnectionPool;
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

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet  extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection;
		try {
			String name = req.getParameter("name");
			String mobile = req.getParameter("mobile");
			String mail = req.getParameter("email");
			String pass = req.getParameter("password");
			String sessionId = req.getSession().getId();
			connection = ConnectionHashMap.getInstance().get(sessionId);
			if (connection == null) {
				connection = ConnectionPool.getInstance().getConnection();
				ConnectionHashMap.getInstance().put(sessionId, connection);
			}
			UserManagerImpl manager = new UserManagerImpl();
			User user = (User) req.getSession().getAttribute("user");
			user.setName(name);
			boolean mailChanged = false;
			if(user.getEmail().equals(mail)) {
				user.setEmail(mail);
				mailChanged = true;
				user.setActive(false);
			}
			user.setMobile(mobile);
			user.setPassword(pass);
			manager.updateObject(user, connection);
			req.getSession().setAttribute("user", user);
			if (mailChanged) {
				MailConfirmationProducer.getInstance().sendConfirmationMail(user);
				PrintWriter out = resp.getWriter();
				out.write("mailChanged");
				out.close();
			}
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
