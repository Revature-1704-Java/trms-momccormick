package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import daoObjects.Reimbursement;
import daos.ReimbursementDao;
import utils.SessionAttribute;

public class EmployeeHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeHomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute(SessionAttribute.EMPLOYEE);
		
		if (emp == null || emp.getType() != EmployeeType.STANDARD) {
			response.sendRedirect("login");
			return;
		} 
		
		
		
//		ReimbursementDao reiDao = new ReimbursementDao();
//		List<Reimbursement> reimbursements = reiDao.getAllForEmployee(emp);
//		request.setAttribute(SessionAttribute.EMPLOYEE_REIMBURSEMENTS, reimbursements);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/employeeHome.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
