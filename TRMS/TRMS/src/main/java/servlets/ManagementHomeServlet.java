package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import utils.SessionAttribute;

public class ManagementHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagementHomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute(SessionAttribute.EMPLOYEE);
		
		if (emp == null || emp.getType() != EmployeeType.MANAGEMENT) {
			response.sendRedirect("login");
			return;
		} 
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/managementHome.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
