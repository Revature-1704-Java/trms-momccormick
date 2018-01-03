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
import utils.SessionAttribute;

public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReimbursementServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee emp = (Employee) request.getSession().getAttribute(SessionAttribute.EMPLOYEE);

		if (emp == null) {
			return;
		}

		ReimbursementDao dao = new ReimbursementDao();
		List<Reimbursement> reimbursements = null;
		if (emp.getType() == EmployeeType.STANDARD) {
			reimbursements = dao.getAllForEmployee(emp);
			
		}
		else if (emp.getType() == EmployeeType.MANAGEMENT) {
			reimbursements = dao.getAllForSubordinatesOf(emp);
		}
		else if (emp.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			reimbursements = dao.getAllWithAssignedBenefitsCoordinator(emp);
			reimbursements.addAll(dao.getAllUnassigned());
		}


		String json = new Gson().toJson(reimbursements);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
}
