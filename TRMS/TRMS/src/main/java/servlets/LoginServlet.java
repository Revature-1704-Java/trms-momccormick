package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daos.EmployeeDao;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("login.html").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		EmployeeDao empDao = new EmployeeDao();
		Employee emp = empDao.getEmployeeWithLogin(email, password);

		System.out.println(emp == null ? "NULL" : emp.toString());

		if (emp != null) {
			HttpSession session = request.getSession();
			session.setAttribute("employee", emp);
			
			response.sendRedirect("/trms/HomeServlet");
		}
		else {
			response.getWriter().append("Login Failed");
		}
	}
}
