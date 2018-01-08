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
				"<html><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css' integrity='sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy' crossorigin='anonymous'><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>TRMS HOME</title></head><body>");

		htmlPage.append("<h1>Welcome " + employee.getFirstName() + " " + employee.getLastName()
				+ "</h1><br/><form action='/trms/LoginServlet' method='GET'><input type='submit' value='Logout'/></form>");

		String reimbursementView = null;
		ReimbursementDao reiDao = new ReimbursementDao();
		List<Reimbursement> reimbursements = null;
		if (employee.getType() == EmployeeType.STANDARD) {
			reimbursements = reiDao.getAllForEmployee(employee);
			reimbursementView = createStandardReimbursementView(reimbursements);
		} else if (employee.getType() == EmployeeType.DIRECT_SUPERVISOR
				|| employee.getType() == EmployeeType.DEPARTMENT_HEAD) {
			reimbursements = reiDao.getAllForSubordinatesOf(employee);
			reimbursementView = createManagementReimbursementView(reimbursements, employee);
		} else if (employee.getType() == EmployeeType.BENEFITS_COORDINATOR) {
			reimbursements = reiDao.getAllWithAssignedBenefitsCoordinator(employee);
			reimbursements.addAll(reiDao.getAllUnassigned());
			reimbursementView = createBenefitsCoordinatorReimbursementView(reimbursements, employee);
		}

		htmlPage.append(reimbursementView);

		htmlPage.append("</body></html>");

		return htmlPage.toString();
	}

	private String createStandardReimbursementView(List<Reimbursement> reimbursements) {
		StringBuilder view = new StringBuilder();

		view.append(getUniformTableSetUp());

		view.append("<tr><th>Date Submitted</th><th>Work Time Missed</th>"
				+ "<th>Justification</th><th>Projected Amount</th><th>Reimbursement Status</th><th>Amount Awarded</th></tr>");

		double pendingAmount = 0;
		double awardedAmount = 0;

		view.append("<tbody>");
		for (Reimbursement rei : reimbursements) {

			view.append("<tr>");
			view.append("<td>" + rei.getDateSubmitted() + "</td>");
			view.append("<td>" + rei.getWorkTimeMissed() + "</td>");
			view.append("<td>" + rei.getJustification() + "</td>");
			view.append("<td>$" + rei.getProjectedAmount() + "</td>");
			view.append("<td>" + rei.getReimbursementStatus() + "</td>");
			view.append("<td>$" + rei.getAmountAwarded() + "</td>");
			view.append("<td><form action='/trms/ViewEventServlet/" + rei.getEvent().getId()
					+ "'><input type='submit' value='View Event'/></form></td>");
			if (rei.getReimbursementStatus() == ReimbursementStatus.AWARDED) {
				awardedAmount += rei.getAmountAwarded();
			} else if (rei.getReimbursementStatus() != ReimbursementStatus.DENIED
					&& rei.getReimbursementStatus() != ReimbursementStatus.CANCELED) {
				pendingAmount += rei.getProjectedAmount();
				view.append("<td><form action='/trms/UpdateReimbursementServlet/cancel/" + rei.getId()
						+ "' method='POST'><input type='submit' value='Cancel Request'/></form></td>");
			}

			view.append("</tr>");
		}
		view.append("</tbody>");

		double remainingAmount = 1000 - awardedAmount - pendingAmount;

		view.append("<tr><td colspan='2'>Currently Pending Amount: $" + pendingAmount
				+ "</td><td colspan='2'>Awarded Amount: $" + awardedAmount + "</td><td colspan='2'>Amount Available: $"
				+ remainingAmount + "</td></tr></table>");

		if (remainingAmount > 0) {
			view.append(
					"<br/><form action='/trms/SubmitReimbursementServlet'><input type='submit' value='Create New Reimbursement'/></form>");
		} else {
			view.append("<br/><input type='submit' value='Create New Reimbursement' disabled='true'/>");
		}

		return view.toString();
	}

	private String getUniformTableSetUp() {
		return "<table class='table table-bordered table-striped table-hover' width='100%'>";
	}

	private String createManagementReimbursementView(List<Reimbursement> reimbursements, Employee manager) {
		StringBuilder view = new StringBuilder();

		view.append(getUniformTableSetUp());

		view.append("<tr><th>Date Submitted</th><th>Work Time Missed</th>"
				+ "<th>Justification</th><th>Projected Amount</th><th>Reimbursement Status</th><th>Direct Supervisor Approved</th>");

		if (manager.getType() == EmployeeType.DEPARTMENT_HEAD) {
			view.append("<th>Department Head Approved</th>");
		}

		view.append("</tr>");

		for (Reimbursement rei : reimbursements) {
			// if (rei.getDepartmentHeadApproved() != null) {
			// continue;
			// }
			// if (rei.getDirectSupervisorApproved() != null && manager.getType() ==
			// EmployeeType.DIRECT_SUPERVISOR) {
			// continue;
			// }
			view.append("<tr>");
			view.append("<td>" + rei.getDateSubmitted() + "</td>");
			view.append("<td>" + rei.getWorkTimeMissed() + "</td>");
			view.append("<td>" + rei.getJustification() + "</td>");
			view.append("<td>$" + rei.getProjectedAmount() + "</td>");
			view.append("<td>" + rei.getReimbursementStatus() + "</td>");
			view.append("<td>" + rei.getDirectSupervisorApproved() + "</td>");
			if (manager.getType() == EmployeeType.DEPARTMENT_HEAD) {
				view.append("<td>" + rei.getDepartmentHeadApproved() + "</td>");
			}
			view.append("<td><form action='/trms/ViewEventServlet/" + rei.getEvent().getId()
					+ "'><input type='submit' value='View Event'/></form></td>");
			if (canManagementApproveReimbursement(rei, manager)) {
				view.append("<td><form action='/trms/UpdateReimbursementServlet/approve/" + rei.getId()
						+ "' method='POST'><input type='submit' value='Submit Approval'/></form></td>");
			}

			view.append("</tr>");
		}

		view.append("</table>");

		return view.toString();
	}

	private boolean canManagementApproveReimbursement(Reimbursement reimbursement, Employee manager) {
		if (reimbursement.getReimbursementStatus() != ReimbursementStatus.APPROVAL_PENDING
				&& reimbursement.getReimbursementStatus() != ReimbursementStatus.URGENT) {
			return false;
		}

		if (reimbursement.getDirectSupervisorApproved() == null) {
			return manager.getType() == EmployeeType.DIRECT_SUPERVISOR;
		} else if (reimbursement.getDepartmentHeadApproved() == null) {
			return manager.getType() == EmployeeType.DEPARTMENT_HEAD;
		} else if (reimbursement.getBenefitsCoordinatorApproved() == null) {
			return manager.getType() == EmployeeType.BENEFITS_COORDINATOR;
		}

		return false;
	}

	private String createBenefitsCoordinatorReimbursementView(List<Reimbursement> reimbursements,
			Employee benefitsCoordinator) {
		StringBuilder view = new StringBuilder();

		view.append(getUniformTableSetUp());

		view.append(
				"<tr><th>Date Submitted</th><th>Work Time Missed</th><th>Justification</th><th>Projected Amount</th><th>Reimbursement Status</th><th>Amount Awarded</th><th>Direct Supervisor Approved</th><th>Department Head Approved</th></tr>");

		for (Reimbursement rei : reimbursements) {
			view.append("<tr>");
			view.append("<td>" + rei.getDateSubmitted() + "</td>");
			view.append("<td>" + rei.getWorkTimeMissed() + "</td>");
			view.append("<td>" + rei.getJustification() + "</td>");
			view.append("<td>$" + rei.getProjectedAmount() + "</td>");
			view.append("<td>" + rei.getReimbursementStatus() + "</td>");
			view.append("<td>$" + rei.getAmountAwarded() + "</td>");
			view.append("<td>" + rei.getDirectSupervisorApproved() + "</td>");
			view.append("<td>" + rei.getDepartmentHeadApproved() + "</td>");
			view.append("<td><form action='/trms/ViewEventServlet/" + rei.getEvent().getId()
					+ "'><input type='submit' value='View Event'/></form></td>");
			if (rei.getBenefitsCoordinator() == null && rei.getReimbursementStatus() != ReimbursementStatus.CANCELED) {
				view.append("<td><form action='/trms/UpdateReimbursementServlet/assign/" + rei.getId()
						+ "' method='POST'><input type='submit' value='Assign to Myself'/></form></td>");
				view.append("</tr>");
			} else {
				if (canManagementApproveReimbursement(rei, benefitsCoordinator)) {
					view.append("<td><form action='/trms/UpdateReimbursementServlet/approve/" + rei.getId()
							+ "' method='POST'><input type='submit' value='Submit Approval'/></form></td>");
				}
			}
		}

		view.append("</table>");

		return view.toString();
	}

	public String createEventDetails(Employee employee, int eventId) {
		StringBuilder htmlPage = new StringBuilder();

		htmlPage.append(
				"<html><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css' integrity='sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy' crossorigin='anonymous'><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>Event Details</title></head><body>");

		htmlPage.append("<h1>Welcome " + employee.getFirstName() + " " + employee.getLastName()
				+ "</h1><br/><form action='/trms/LoginServlet'><input type='submit' value='Logout'/></form>");

		EventDao eventDao = new EventDao();
		Event event = eventDao.getById(eventId);

		htmlPage.append("<table class='table table-bordered' width='50%'>");
		htmlPage.append("<tr><td class='col-sm-2'>Name</td><td class='col-sm-2'>" + event.getName() + "</td></tr>");
		htmlPage.append("<tr><td>Type</td><td>" + event.getEventType() + "</td></tr>");
		htmlPage.append("<tr><td>Description</td><td>" + event.getDescription() + "</td></tr>");
		htmlPage.append("<tr><td>Start Date</td><td>" + event.getStartDate() + "</td></tr>");
		htmlPage.append("<tr><td>End Date</td><td>" + event.getEndDate() + "</td></tr>");
		htmlPage.append("<tr><td>Time</td><td>" + event.getTime() + "</td></tr>");
		htmlPage.append("<tr><td>Location</td><td>" + event.getLocation() + "</td></tr>");
		htmlPage.append("<tr><td>Cost</td><td>" + event.getCost() + "</td></tr>");
		htmlPage.append("<tr><td>Grading Format</td><td>" + event.getGradingFormat().toString() + "</td></tr>");

		double minGrade = event.getPassingGrade().getMinPercent();
		double maxGrade = event.getPassingGrade().getMaxPercent();
		htmlPage.append("<tr><td>Passing Grade</td><td>" + event.getPassingGrade() + " (" + minGrade + "%-" + maxGrade
				+ "%) </td></tr>");

		if (employee.getType() == EmployeeType.STANDARD && event.getRecievedGrade() == 0) {
			htmlPage.append("<tr><form action='/trms/ViewEventServlet/" + event.getId()
					+ "' method='POST'><td>Recieved Grade (%):</td><td class='col-sm-1'><input type='number' name='submitgrade' placeholder='Recieved Grade' value='"
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
				"<html><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css' integrity='sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy' crossorigin='anonymous'><head><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'><title>TRMS HOME</title></head><body>");

		htmlPage.append("<h1>Welcome " + employee.getFirstName() + " " + employee.getLastName()
				+ "</h1><br/><form action='/trms/LoginServlet'><input type='submit' value='Logout'/></form>");
		
		htmlPage.append("<h2>Reimbursement Request Information</h2>");

		htmlPage.append(
				"<form action='/trms/SubmitReimbursementServlet' method='POST'><table class='table table-bordered'>");
		htmlPage.append(
				"<tr><td style='width:10%'>Event Name:</td><td style='width:30%'><input type='text' name='eventname' placeholder='Event Name' value='TRMS Presentation'/></td></tr>");

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
				"<tr><td>Cost ($):</td><td><input type='number' name='cost' min='0.00' step='0.01' placeholder='Cost' value='150.00'/></td></tr>");

		htmlPage.append("<tr><td>Grading Format:</td><td><select name='gradingformats'>");
		for (GradingFormat gf : GradingFormat.values()) {
			htmlPage.append("<option value='" + gf.getId() + "'");
			if (gf == GradingFormat.LETTER_GRADE) {
				htmlPage.append(" selected='selected'");
			}
			htmlPage.append(">" + gf.getGradingFormat() + "</option>");
		}
		htmlPage.append("</select></td></tr>");

		htmlPage.append("<tr><td>Passing Grade:</td><td><select name='passinggrade'>");
		for (GradeLetter gl : GradeLetter.values()) {
			htmlPage.append("<option value='" + gl.getId() + "'");
			if (gl == GradeLetter.C) {
				htmlPage.append(" selected='selected'");
			}
			htmlPage.append(">" + gl.getGradeLetter() + "</option>");
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
