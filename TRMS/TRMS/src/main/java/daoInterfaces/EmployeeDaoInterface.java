package daoInterfaces;

import daoObjects.Employee;

public interface EmployeeDaoInterface extends BaseDaoInterface<Employee> {

	Employee getEmployeeByLogin(String email, String password);
}
