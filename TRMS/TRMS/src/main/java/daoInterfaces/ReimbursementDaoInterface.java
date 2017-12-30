package daoInterfaces;

import java.util.List;

import daoObjects.Employee;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;

public interface ReimbursementDaoInterface extends BaseDaoInterface<Reimbursement> {

	List<Reimbursement> getAllForEmployee(Employee employee);
	List<Reimbursement> getAllForEmployeeId(int employeeId);
	List<Reimbursement> getAllForSubordinatesOf(Employee manager);
	List<Reimbursement> getAllWithAssignedBenefitsCoordinator(Employee benefitsCoordinator);
	List<Reimbursement> getAllWithReimbursementStatus(ReimbursementStatus reimbursementStatus);
}
