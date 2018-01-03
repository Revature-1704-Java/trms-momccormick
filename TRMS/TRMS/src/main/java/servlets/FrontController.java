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
import utils.SessionAttribute;

@WebServlet("")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		System.out.println("RequestURI: " + requestUri);
		String requestUrl = requestUri.substring(request.getContextPath().length());
		System.out.println("RequestURL: " + requestUrl);

		if (requestUrl.equals("login")) {
			request.getRequestDispatcher("login.html").forward(request, response);
			return;
		}

		HttpSession session = request.getSession();

		Employee loggedInEmployee = (Employee) session.getAttribute(SessionAttribute.EMPLOYEE);

		if (loggedInEmployee == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		if (loggedInEmployee.getType() == EmployeeType.STANDARD) {
			response.sendRedirect("EmployeeHomeServlet");
		} else if (loggedInEmployee.getType() == EmployeeType.MANAGEMENT) {
			response.sendRedirect("ManagementHomeServlet");
		} else if (loggedInEmployee.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			response.sendRedirect("BenefitsCoordinatorHomeServlet");
		} else {
			response.sendRedirect("LoginServlet");
		}
	}

}
