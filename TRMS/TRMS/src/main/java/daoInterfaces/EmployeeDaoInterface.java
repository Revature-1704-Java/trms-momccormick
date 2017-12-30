package daoInterfaces;

import daoObjects.Employee;

public interface EmployeeDaoInterface extends BaseDaoInterface<Employee> {

	Employee getEmployeeWithLogin(String email, String password);
	Employee getDirectSupervisorForEmployee(Employee employee);
	Employee getDirectSupervisorForEmployeeId(int id);
	
}
