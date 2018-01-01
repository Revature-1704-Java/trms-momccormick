package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import daos.EmployeeDao;
import utils.SessionAttribute;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getRequestURI());
		response.sendRedirect("login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		EmployeeDao empDao = new EmployeeDao();
		Employee emp = empDao.getEmployeeWithLogin(email, password);

		System.out.println(email + " " + password);
		System.out.println(emp == null ? "NULL" : emp.toString());

		if (emp != null) {
			HttpSession session = request.getSession();
			session.setAttribute(SessionAttribute.EMPLOYEE, emp);

//			response.sendRedirect("WEB-INF/viewReimbursements.html");
			switch(emp.getType()) {
			case STANDARD:
				response.sendRedirect("EmployeeHomeServlet");
				break;
			case MANAGEMENT:
				response.sendRedirect("ManagementHomeServlet");
				break;
			case BENEFITS_COORDINATOR:
				response.sendRedirect("BenefitsCoordinatorHomeServlet");
				break;
			}
		}

		response.getWriter().append("Login Failed");
	}
}
