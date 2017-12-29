package models;

import java.sql.Date;

public class ApprovalDates {
	
	Date directSupervisorDate;
	Date departmentHeadDate;
	Date benefitsCoordinatorDate;
	
	public ApprovalDates(Date directSupervisorDate, Date departmentHeadDate, Date benefitsCoordinatorDate) {
		super();
		this.directSupervisorDate = directSupervisorDate;
		this.departmentHeadDate = departmentHeadDate;
		this.benefitsCoordinatorDate = benefitsCoordinatorDate;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benefitsCoordinatorDate == null) ? 0 : benefitsCoordinatorDate.hashCode());
		result = prime * result + ((departmentHeadDate == null) ? 0 : departmentHeadDate.hashCode());
		result = prime * result + ((directSupervisorDate == null) ? 0 : directSupervisorDate.hashCode());
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
		ApprovalDates other = (ApprovalDates) obj;
		if (benefitsCoordinatorDate == null) {
			if (other.benefitsCoordinatorDate != null)
				return false;
		} else if (!benefitsCoordinatorDate.equals(other.benefitsCoordinatorDate))
			return false;
		if (departmentHeadDate == null) {
			if (other.departmentHeadDate != null)
				return false;
		} else if (!departmentHeadDate.equals(other.departmentHeadDate))
			return false;
		if (directSupervisorDate == null) {
			if (other.directSupervisorDate != null)
				return false;
		} else if (!directSupervisorDate.equals(other.directSupervisorDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApprovalDates [directSupervisorDate=" + directSupervisorDate + ", departmentHeadDate="
				+ departmentHeadDate + ", benefitsCoordinatorDate=" + benefitsCoordinatorDate + "]";
	}
	
}
