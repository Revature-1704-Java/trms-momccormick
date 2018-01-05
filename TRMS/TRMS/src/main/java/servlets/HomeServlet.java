package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import utils.HtmlPageCreator;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		HtmlPageCreator hpc = new HtmlPageCreator();
		String htmlPage = null;

		htmlPage = hpc.createEmployeeHome(emp);

		response.getWriter().write(htmlPage);
	}
}
