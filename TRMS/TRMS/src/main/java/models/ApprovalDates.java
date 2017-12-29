package models;

import java.sql.Date;

public class ApprovalDates {

	private int id;
	Date directSupervisorDate;
	Date departmentHeadDate;
	Date benefitsCoordinatorDate;

	public ApprovalDates() {
		super();
	}

	public ApprovalDates(int id, Date directSupervisorDate, Date departmentHeadDate, Date benefitsCoordinatorDate) {
		this.id = id;
		this.directSupervisorDate = directSupervisorDate;
		this.departmentHeadDate = departmentHeadDate;
		this.benefitsCoordinatorDate = benefitsCoordinatorDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDirectSupervisorDate() {
		return directSupervisorDate;
	}

	public void setDirectSupervisorDate(Date directSupervisorDate) {
		this.directSupervisorDate = directSupervisorDate;
	}

	public Date getDepartmentHeadDate() {
		return departmentHeadDate;
	}

	public void setDepartmentHeadDate(Date departmentHeadDate) {
		this.departmentHeadDate = departmentHeadDate;
	}

	public Date getBenefitsCoordinatorDate() {
		return benefitsCoordinatorDate;
	}

	public void setBenefitsCoordinatorDate(Date benefitsCoordinatorDate) {
		this.benefitsCoordinatorDate = benefitsCoordinatorDate;
	}

	@Override
	public String toString() {
		return "ApprovalDates [id=" + id + ", directSupervisorDate=" + directSupervisorDate + ", departmentHeadDate="
				+ departmentHeadDate + ", benefitsCoordinatorDate=" + benefitsCoordinatorDate + "]";
	}

	
}
