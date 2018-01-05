package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import daoObjects.Reimbursement;
import daos.ReimbursementDao;

public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReimbursementServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee emp = (Employee) request.getSession().getAttribute("employee");
		if (emp == null || emp.getType() != EmployeeType.STANDARD) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		ReimbursementDao reiDao = new ReimbursementDao();
		List<Reimbursement> reimbursements = null;
		if (emp.getType() == EmployeeType.STANDARD) {
			reimbursements = reiDao.getAllForEmployee(emp);
			
		}
		else if (emp.getType() == EmployeeType.DIRECT_SUPERVISOR || emp.getType() == EmployeeType.DEPARTMENT_HEAD) {
			reimbursements = reiDao.getAllForSubordinatesOf(emp);
		}
		else if (emp.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			reimbursements = reiDao.getAllWithAssignedBenefitsCoordinator(emp);
			reimbursements.addAll(reiDao.getAllUnassigned());
		}

		String json = new Gson().toJson(reimbursements);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
