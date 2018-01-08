package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoObjects.Employee;
import daoObjects.Event;
import daoObjects.ReimbursementStatus;
import daos.EventDao;
import daos.ReimbursementDao;
import utils.HtmlPageCreator;

public class ViewEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewEventServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		String requestUrl = request.getRequestURI().substring(request.getContextPath().length());
		String eventId = requestUrl.substring(requestUrl.indexOf('/', 2) + 1);

		HtmlPageCreator hpc = new HtmlPageCreator();
		String htmlPage = null;

		htmlPage = hpc.createEventDetails(emp, Integer.parseInt(eventId));

		response.getWriter().write(htmlPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if (emp == null || emp.getType() == null) {
			response.sendRedirect("/trms/LoginServlet");
			return;
		}

		String requestUrl = request.getRequestURI().substring(request.getContextPath().length());
		String eventId = requestUrl.substring(requestUrl.indexOf('/', 2) + 1);

		EventDao eventDao = new EventDao();
		Event updatedEvent = eventDao.getById(Integer.parseInt(eventId));
		updatedEvent.setRecievedGrade(Double.parseDouble(request.getParameter("submitgrade")));
		eventDao.update(updatedEvent);

		// If Employee Received Passing Grade
		if (updatedEvent.getRecievedGrade() >= updatedEvent.getPassingGrade().getMinPercent()) {
			ReimbursementDao accessReimbursementDatabaseTable = new ReimbursementDao();
			ReimbursementStatus status = accessReimbursementDatabaseTable.getStatusForEvent(updatedEvent);
			// If Reimbursement Has Been Assigned to Benefits Coordinator
			if (status == ReimbursementStatus.GRADE_PENDING) {
				accessReimbursementDatabaseTable
						.changeStatusForGradeSubmissionOnEvent(ReimbursementStatus.APPROVAL_PENDING, updatedEvent);
			}
		}

		response.sendRedirect("/trms/HomeServlet");
	}
}
