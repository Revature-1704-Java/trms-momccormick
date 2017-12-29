package models;

import java.sql.Date;

public class Reimbursement {

	private int id;
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

	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, Employee employee, Date dateSubmitted, Event event, String workTimeMissed,
			String justification, double projectedAmount, Employee benefitsCoordinator, ApprovalDates approvalDates,
			ReimbursementStatus reimbursementStatus, double amountAwarded) {
		this.id = id;
		this.employee = employee;
		this.dateSubmitted = dateSubmitted;
		this.event = event;
		this.workTimeMissed = workTimeMissed;
		this.justification = justification;
		this.projectedAmount = projectedAmount;
		this.benefitsCoordinator = benefitsCoordinator;
		this.approvalDates = approvalDates;
		this.reimbursementStatus = reimbursementStatus;
		this.amountAwarded = amountAwarded;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getWorkTimeMissed() {
		return workTimeMissed;
	}

	public void setWorkTimeMissed(String workTimeMissed) {
		this.workTimeMissed = workTimeMissed;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public double getProjectedAmount() {
		return projectedAmount;
	}

	public void setProjectedAmount(double projectedAmount) {
		this.projectedAmount = projectedAmount;
	}

	public Employee getBenefitsCoordinator() {
		return benefitsCoordinator;
	}

	public void setBenefitsCoordinator(Employee benefitsCoordinator) {
		this.benefitsCoordinator = benefitsCoordinator;
	}

	public ApprovalDates getApprovalDates() {
		return approvalDates;
	}

	public void setApprovalDates(ApprovalDates approvalDates) {
		this.approvalDates = approvalDates;
	}

	public ReimbursementStatus getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	public double getAmountAwarded() {
		return amountAwarded;
	}

	public void setAmountAwarded(double amountAwarded) {
		this.amountAwarded = amountAwarded;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employee=" + employee + ", dateSubmitted=" + dateSubmitted + ", event="
				+ event + ", workTimeMissed=" + workTimeMissed + ", justification=" + justification
				+ ", projectedAmount=" + projectedAmount + ", benefitsCoordinator=" + benefitsCoordinator
				+ ", approvalDates=" + approvalDates + ", reimbursementStatus=" + reimbursementStatus
				+ ", amountAwarded=" + amountAwarded + "]";
	}
	
	
}
