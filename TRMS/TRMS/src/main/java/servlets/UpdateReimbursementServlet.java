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
import daoObjects.Event;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import daos.EventDao;
import daos.ReimbursementDao;

/**
 * Servlet implementation class UpdateReimbursementServlet
 */
public class UpdateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		String requestUrl = request.getRequestURI().substring(request.getContextPath().length());
		String action = requestUrl.substring(requestUrl.indexOf('/', 2) + 1);
		String reimbursementId = action.substring(action.indexOf('/', 2) + 1);

		ReimbursementDao reimbursementDao = new ReimbursementDao();
		Reimbursement reimbursement = reimbursementDao.getById(Integer.parseInt(reimbursementId));

		if (action.startsWith("cancel") && emp.getType() == EmployeeType.STANDARD) {
			reimbursement.setReimbursementStatus(ReimbursementStatus.CANCELED);
		} else if (action.startsWith("assign") && emp.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			reimbursement.setBenefitsCoordinator(emp);
			Event event = new EventDao().getForReimbursement(reimbursement);
			if (event.getRecievedGrade() > event.getPassingGrade().getMinPercent()) {
				reimbursement.setReimbursementStatus(ReimbursementStatus.APPROVAL_PENDING);
			} else if (reimbursement.getReimbursementStatus() == ReimbursementStatus.PENDING) {
				reimbursement.setReimbursementStatus(ReimbursementStatus.GRADE_PENDING);
			}
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

				double totalAmountAwarded = reimbursementDao.getAmountAwardedForYearForEmployeeId(
						reimbursement.getDateSubmitted(), reimbursement.getEmployee().getId());

				if (totalAmountAwarded + reimbursement.getProjectedAmount() > 1000) {
					reimbursement.setAmountAwarded(totalAmountAwarded > 1000 ? 0 : 1000 - totalAmountAwarded);
				} else {
					reimbursement.setAmountAwarded(reimbursement.getProjectedAmount());
				}
			}
		}

		reimbursementDao.update(reimbursement);

		response.sendRedirect("/trms/HomeServlet");
	}

}
