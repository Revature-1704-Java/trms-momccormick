package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import daos.ReimbursementDao;

/**
 * Servlet implementation class UpdateReimbursementServlet
 */
public class UpdateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		String requestUri = request.getRequestURI();
		System.out.println("RequestURI: " + requestUri);
		String requestUrl = requestUri.substring(request.getContextPath().length());
		System.out.println("RequestURL: " + requestUrl);
		String action = requestUrl.substring(requestUrl.indexOf('/', 2) + 1);
		System.out.println("Action: " + action);
		String reimbursementId = action.substring(action.indexOf('/', 2) + 1);
		System.out.println("ReimbursementID: " + reimbursementId);

		ReimbursementDao reimbursementDao = new ReimbursementDao();
		Reimbursement reimbursement = reimbursementDao.getById(Integer.parseInt(reimbursementId));

		if (action.startsWith("assign") && emp.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			reimbursement.setBenefitsCoordinator(emp);
		} else if (action.startsWith("approve")) {
			if (emp.getType() == EmployeeType.DIRECT_SUPERVISOR) {
				reimbursement.setDirectSupervisorApproved(new Date(System.currentTimeMillis()));
			}
			if (emp.getType() == EmployeeType.DEPARTMENT_HEAD) {
				reimbursement.setDepartmentHeadApproved(new Date(System.currentTimeMillis()));
			}
			if (emp.getType() == EmployeeType.BENEFITS_COORDINATOR) {
				reimbursement.setBenefitesCoordinatorApproved(new Date(System.currentTimeMillis()));
				reimbursement.setReimbursementStatus(ReimbursementStatus.AWARDED);
				reimbursement.setAmountAwarded(reimbursement.getProjectedAmount());
			}
		}else if (action.startsWith("cancel") && emp.getType() == EmployeeType.STANDARD) {
			reimbursement.setReimbursementStatus(ReimbursementStatus.CANCELED);
		}

		reimbursementDao.update(reimbursement);
		System.out.println("Reimbursement Updated");

		response.sendRedirect("/trms/HomeServlet");
	}

}
