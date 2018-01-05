package utils;

import java.util.List;

import daoObjects.Employee;
import daoObjects.EmployeeType;
import daoObjects.Event;
import daoObjects.EventType;
import daoObjects.GradeLetter;
import daoObjects.GradingFormat;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import daos.EventDao;
import daos.ReimbursementDao;

public class HtmlPageCreator {

	public String createEmployeeHome(Employee employee) {
		StringBuilder htmlPage = new StringBuilder();

		htmlPage.append(
				"<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>TRMS HOME</title></head><body>");

		htmlPage.append("<h1>Welcome " + employee.getFirstName() + " " + employee.getLastName()
				+ "</h1><br/><form action='/trms/LoginServlet' method='GET'><input type='submit' value='Logout'/></form>");

		String reimbursementTable = null;
		ReimbursementDao reiDao = new ReimbursementDao();
		List<Reimbursement> reimbursements = null;
		if (employee.getType() == EmployeeType.STANDARD) {
			reimbursements = reiDao.getAllForEmployee(employee);
			reimbursementTable = createStandardReimbursementTable(reimbursements);
		} else if (employee.getType() == EmployeeType.DIRECT_SUPERVISOR
				|| employee.getType() == EmployeeType.DEPARTMENT_HEAD) {
			reimbursements = reiDao.getAllForSubordinatesOf(employee);
			reimbursementTable = createManagementReimbursementTable(reimbursements, employee);
		} else if (employee.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			reimbursements = reiDao.getAllWithAssignedBenefitsCoordinator(employee);
			reimbursements.addAll(reiDao.getAllUnassigned());
			reimbursementTable = createBenefitsCoordinatorReimbursementTable(reimbursements);
		}

		htmlPage.append(reimbursementTable);

		htmlPage.append("</body></html>");

		return htmlPage.toString();
	}

	private String createStandardReimbursementTable(List<Reimbursement> reimbursements) {
		StringBuilder table = new StringBuilder();

		table.append("<h1>Reimbursements</h1><table border='1'><tr><th>Date Submitted</th><th>Work Time Missed</th>"
				+ "<th>Justification</th><th>Projected Amount</th><th>Reimbursement Status</th><th>Amount Awarded</th></tr>");

		double pendingAmount = 0;
		double awardedAmount = 0;

		for (Reimbursement rei : reimbursements) {

			table.append("<tr>");
			table.append("<td>" + rei.getDateSubmitted() + "</td>");
			table.append("<td>" + rei.getWorkTimeMissed() + "</td>");
			table.append("<td>" + rei.getJustification() + "</td>");
			table.append("<td>" + rei.getProjectedAmount() + "</td>");
			table.append("<td>" + rei.getReimbursementStatus() + "</td>");
			table.append("<td>" + rei.getAmountAwarded() + "</td>");
			table.append("<td><form action='/trms/ViewEventServlet/" + rei.getEvent().getId()
					+ "'><input type='submit' value='View Event'/></form></td>");
			if (rei.getReimbursementStatus() == ReimbursementStatus.AWARDED) {
				awardedAmount += rei.getAmountAwarded();
			} else if (rei.getReimbursementStatus() != ReimbursementStatus.DENIED
					&& rei.getReimbursementStatus() != ReimbursementStatus.CANCELED) {
				pendingAmount += rei.getProjectedAmount();
				table.append("<td><form action='/trms/UpdateReimbursementServlet/cancel/" + rei.getId()
						+ "' method='POST'><input type='submit' value='Cancel Request'/></form></td>");
			}

			table.append("</tr>");
		}

		double remainingAmount = 1000 - awardedAmount - pendingAmount;

		table.append("<tr><td colspan='2'>Currently Pending Amount: $" + pendingAmount
				+ "</td><td colspan='2'>Awarded Amount: $" + awardedAmount + "</td><td colspan='2'>Amount Available: $"
				+ remainingAmount + "</td></tr>");

		table.append("</table>");

		if (remainingAmount > 0) {
			table.append(
					"<br/><form action='/trms/SubmitReimbursementServlet'><input type='submit' value='Create New Reimbursement'/></form>");
		}

		return table.toString();
	}

	private String createManagementReimbursementTable(List<Reimbursement> reimbursements, Employee manager) {
		StringBuilder table = new StringBuilder();

		table.append("<h1>Reimbursements</h1><table border='1'><tr><th>Date Submitted</th><th>Work Time Missed</th>"
				+ "<th>Justification</th><th>Projected Amount</th><th>Reimbursement Status</th>");

		if (manager.getType() == EmployeeType.DEPARTMENT_HEAD) {
			table.append("<th>Direct Supervisor Approved</th>");
		}

		table.append("</tr>");

		for (Reimbursement rei : reimbursements) {
			// if (rei.getDepartmentHeadApproved() != null) {
			// continue;
			// }
			// if (rei.getDirectSupervisorApproved() != null && manager.getType() ==
			// EmployeeType.DIRECT_SUPERVISOR) {
			// continue;
			// }
			table.append("<tr>");
			table.append("<td>" + rei.getDateSubmitted() + "</td>");
			table.append("<td>" + rei.getWorkTimeMissed() + "</td>");
			table.append("<td>" + rei.getJustification() + "</td>");
			table.append("<td>" + rei.getProjectedAmount() + "</td>");
			table.append("<td>" + rei.getReimbursementStatus() + "</td>");
			table.append("<td>" + rei.getDirectSupervisorApproved() + "</td>");
			if (manager.getType() == EmployeeType.DEPARTMENT_HEAD) {
				table.append("<td>" + rei.getDepartmentHeadApproved() + "</td>");
			}
			table.append("<td><form action='/trms/ViewEventServlet/" + rei.getEvent().getId()
					+ "'><input type='submit' value='View Event'/></form></td>");
			if ((manager.getType() == EmployeeType.DIRECT_SUPERVISOR && rei.getDirectSupervisorApproved() == null)
					|| (manager.getType() == EmployeeType.DEPARTMENT_HEAD && rei.getDepartmentHeadApproved() == null)) {
				table.append("<td><form action='/trms/UpdateReimbursementServlet/approve/" + rei.getId()
						+ "' method='POST'><input type='submit' value='Submit Approval'/></form></td>");
			}

			table.append("</tr>");
		}

		table.append("</table>");

		return table.toString();
	}

	private String createBenefitsCoordinatorReimbursementTable(List<Reimbursement> reimbursements) {
		StringBuilder table = new StringBuilder();

		table.append("<h1>Reimbursements</h1><table border='1'><tr><th>Date Submitted</th><th>Work Time Missed</th>"
				+ "<th>Justification</th><th>Projected Amount</th><th>Reimbursement Status</th><th>Amount Awarded</th><th>Direct Supervisor Approved</th><th>Department Head Approved</th>");

		table.append("</tr>");

		for (Reimbursement rei : reimbursements) {
			table.append("<tr>");
			table.append("<td>" + rei.getDateSubmitted() + "</td>");
			table.append("<td>" + rei.getWorkTimeMissed() + "</td>");
			table.append("<td>" + rei.getJustification() + "</td>");
			table.append("<td>$" + rei.getProjectedAmount() + "</td>");
			table.append("<td>" + rei.getReimbursementStatus() + "</td>");
			table.append("<td>$" + rei.getAmountAwarded() + "</td>");
			table.append("<td>" + rei.getDirectSupervisorApproved() + "</td>");
			table.append("<td>" + rei.getDepartmentHeadApproved() + "</td>");
			table.append("<td><form action='/trms/ViewEventServlet/" + rei.getEvent().getId()
					+ "'><input type='submit' value='View Event'/></form></td>");
			if (rei.getBenefitsCoordinator() == null && rei.getReimbursementStatus() != ReimbursementStatus.CANCELED) {
				table.append("<td><form action='/trms/UpdateReimbursementServlet/assign/" + rei.getId()
						+ "' method='POST'><input type='submit' value='Assign to Myself'/></form></td>");
				table.append("</tr>");
			} else {
				if (rei.getDirectSupervisorApproved() != null && rei.getDepartmentHeadApproved() != null
						&& (rei.getReimbursementStatus() == ReimbursementStatus.PENDING
								|| rei.getReimbursementStatus() == ReimbursementStatus.GRADE_PENDING
								|| rei.getReimbursementStatus() == ReimbursementStatus.APPROVAL_PENDING
								|| rei.getReimbursementStatus() == ReimbursementStatus.URGENT)) {
					table.append("<td><form action='/trms/UpdateReimbursementServlet/approve/" + rei.getId()
							+ "' method='POST'><input type='submit' value='Submit Approval'/></form></td>");
				}
			}
		}

		table.append("</table>");

		return table.toString();
	}

	public String createEventDetails(Employee employee, int eventId) {
		StringBuilder htmlPage = new StringBuilder();

		htmlPage.append(
				"<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>Event Details</title></head><body>");

		htmlPage.append("<h1>Welcome " + employee.getFirstName() + " " + employee.getLastName()
				+ "</h1><br/><form action='/trms/LoginServlet'><input type='submit' value='Logout'/></form>");

		EventDao eventDao = new EventDao();
		Event event = eventDao.getById(eventId);

		htmlPage.append("<table border='1'>");
		htmlPage.append("<tr><td>Name</td><td>" + event.getName() + "</td></tr>");
		htmlPage.append("<tr><td>Type</td><td>" + event.getEventType() + "</td></tr>");
		htmlPage.append("<tr><td>Description</td><td>" + event.getDescription() + "</td></tr>");
		htmlPage.append("<tr><td>Start Date</td><td>" + event.getStartDate() + "</td></tr>");
		htmlPage.append("<tr><td>End Date</td><td>" + event.getEndDate() + "</td></tr>");
		htmlPage.append("<tr><td>Time</td><td>" + event.getTime() + "</td></tr>");
		htmlPage.append("<tr><td>Location</td><td>" + event.getLocation() + "</td></tr>");
		htmlPage.append("<tr><td>Cost</td><td>" + event.getCost() + "</td></tr>");
		htmlPage.append("<tr><td>Grading Format</td><td>" + event.getGradingFormat() + "</td></tr>");
		htmlPage.append("<tr><td>Passing Grade</td><td>" + event.getPassingGrade() + " ("
				+ event.getPassingGrade().getMinPercent() * 100 + "%-" + event.getPassingGrade().getMaxPercent() * 100
				+ "%) </td></tr>");

		if (employee.getType() == EmployeeType.STANDARD) {
			htmlPage.append("<tr><form action='/trms/ViewEventServlet/" + event.getId()
					+ "' method='POST'><td>Recieved Grade (%):</td><td><input type='number' name='submitgrade' placeholder='Recieved Grade' value='"
					+ event.getRecievedGrade()
					+ "'/></td><td><input type='submit' value='Submit Grade'/></td></form></tr>");
		} else {
			htmlPage.append("<tr><td>Recieved Grade</td><td>" + event.getRecievedGrade() + "</td></tr>");
		}

		htmlPage.append("</tr></table>");

		htmlPage.append(
				"<br/><form action='/trms/HomeServlet'><input type='submit' value='Home'/></form></body></html>");

		return htmlPage.toString();
	}

	public String createReimbursementSubmission(Employee employee) {
		StringBuilder htmlPage = new StringBuilder();

		htmlPage.append(
				"<html><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>TRMS HOME</title></head><body>");

		htmlPage.append("<h1>Welcome " + employee.getFirstName() + " " + employee.getLastName()
				+ "</h1><br/><form action='/trms/LoginServlet'><input type='submit' value='Logout'/></form>");

		htmlPage.append(
				"<form action='/trms/SubmitReimbursementServlet' method='POST'><table><tr><th colspan='2'>Reimbursement Request Information</th></tr>");
		htmlPage.append(
				"<tr><td>Event Name:</td><td><input type='text' name='eventname' placeholder='Event Name' value='TRMS Presentation'/></td></tr>");

		htmlPage.append("<tr><td>Event Type:</td><td><select name='eventtype'>");
		for (EventType et : EventType.values()) {
			htmlPage.append("<option value='" + et.getId() + "'>" + et.getEventType() + "</option>");
		}
		htmlPage.append("</select></td></tr>");

		htmlPage.append("<tr><td>Description:</td><td><textarea name='description'></textarea></td></tr>");
		htmlPage.append(
				"<tr><td>Start Date:</td><td><input type='date' name='startdate' placeholder='Start Date (yyyy-mm-dd)' value='2018-01-08'/></td></tr>");
		htmlPage.append(
				"<tr><td>End Date:</td><td><input type='date' name='enddate' placeholder='End Date (yyyy-mm-dd)' value='2018-01-08'/></td></tr>");
		htmlPage.append(
				"<tr><td>Time:</td><td><input type='text' name='time' placeholder='Time' value='11:00 PM'/></td></tr>");
		htmlPage.append(
				"<tr><td>Location:</td><td><input type='text' name='location' placeholder='Location' value='Revature Office'/></td></tr>");
		htmlPage.append(
				"<tr><td>Cost:</td><td><input type='number' name='cost' min='0.00' step='0.01' placeholder='Cost' value='150.00'/></td></tr>");

		htmlPage.append("<tr><td>Grading Format:</td><td><select name='gradingformats'>");
		for (GradingFormat gf : GradingFormat.values()) {
			htmlPage.append("<option value='" + gf.getId() + "'>" + gf.getGradingFormat() + "</option>");
		}
		htmlPage.append("</select></td></tr>");

		htmlPage.append("<tr><td>Passing Grade:</td><td><select name='passinggrade'>");
		for (GradeLetter gl : GradeLetter.values()) {
			htmlPage.append("<option value='" + gl.getId() + "'>" + gl.getGradeLetter() + "</option>");
		}
		htmlPage.append("</select></td></tr>");

		htmlPage.append(
				"<tr><td>Work Time Missed:</td><td><input type='text' name='timemissed' placeholder='Work Time Missed' value='1 hour'/></td></tr>");
		htmlPage.append(
				"<tr><td>Justification:</td><td><input type='text' name='justification' placeholder='Justification' value='To Prove My Worth'/></td></tr>");
		htmlPage.append(
				"</table><tr><td colspan='2' align='right'><input type='submit' value='Submit Request' /></td></tr></form>");

		htmlPage.append(
				"<br/><form action='/trms/HomeServlet'><input type='submit' value='Home'/></form></body></html>");

		htmlPage.append("</body></html>");

		return htmlPage.toString();
	}
}
