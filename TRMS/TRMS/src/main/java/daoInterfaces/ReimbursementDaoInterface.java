package daoInterfaces;

import java.sql.Date;
import java.util.List;

import daoObjects.Employee;
import daoObjects.Event;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;

public interface ReimbursementDaoInterface extends BaseDaoInterface<Reimbursement> {

	List<Reimbursement> getAllForEmployee(Employee employee);

	List<Reimbursement> getAllForEmployeeId(int employeeId);

	List<Reimbursement> getAllForSubordinatesOf(Employee manager);

	List<Reimbursement> getAllWithAssignedBenefitsCoordinator(Employee benefitsCoordinator);
	
	void changeStatusForGradeSubmissionOnEvent(ReimbursementStatus status, Event event);
	
	ReimbursementStatus getStatusForEvent(Event event);

	double getAmountAwardedForYearForEmployee(Date year, Employee employee);
	
	double getAmountAwardedForYearForEmployeeId(Date year, int employeeId);
}
