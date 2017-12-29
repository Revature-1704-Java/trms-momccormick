package models;

import java.sql.Date;

public class Reimbursement {

	Employee employee;
	Date dateSubmitted;
	Event event;
	String workTimeMissed;
	String justification;
	double projectedAmount;
	Employee benefitsCoordinator;
	ApprovalDates approvalDates;
	ReimbursementStatus reimbursementStatus;
	double amountAwarded;
}
