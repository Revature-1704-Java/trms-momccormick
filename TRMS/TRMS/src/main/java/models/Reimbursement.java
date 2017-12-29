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

	public Reimbursement(Employee employee, Date dateSubmitted, Event event, String workTimeMissed,
			String justification, double projectedAmount, Employee benefitsCoordinator, ApprovalDates approvalDates,
			ReimbursementStatus reimbursementStatus, double amountAwarded) {
		super();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [employee=" + employee + ", dateSubmitted=" + dateSubmitted + ", event=" + event
				+ ", workTimeMissed=" + workTimeMissed + ", justification=" + justification + ", projectedAmount="
				+ projectedAmount + ", benefitsCoordinator=" + benefitsCoordinator + ", approvalDates=" + approvalDates
				+ ", reimbursementStatus=" + reimbursementStatus + ", amountAwarded=" + amountAwarded + "]";
	}

}
