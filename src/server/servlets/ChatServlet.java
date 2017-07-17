package server.servlets;

import utils.AvailableOperatorsList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			AvailableOperatorsList.getInstance().add(req.getSession().getId());
			PrintWriter out = resp.getWriter();
			out.write("success");
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
		try {
			if (AvailableOperatorsList.getInstance().size() > 0) {
				PrintWriter out = resp.getWriter();
				String id = AvailableOperatorsList.getInstance().get(0);
				AvailableOperatorsList.getInstance().remove(id);
				out.write(id);
				out.close();
			} else {
				throw new Exception("no active operators");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			PrintWriter out = resp.getWriter();
			out.write("error");
			out.close();
		}
	}
}
