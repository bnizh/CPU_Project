package server.servlets;

import utils.ConnectionHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class LogOutServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cookie[] cookies = request.getCookies();
			String sessionId = request.getSession().getId();
			Connection connection = ConnectionHashMap.getInstance().get(sessionId);
			if (connection != null) {
				connection.close();
			}
			ConnectionHashMap.getInstance().remove(sessionId);
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if (request.getSession()!= null) {
					request.getSession().invalidate();
				}
				PrintWriter out = response.getWriter();
				out.write("success");
				out.close();
			}
		} catch (SQLException ex) {
			PrintWriter out = response.getWriter();
			out.write("error");
			out.close();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}