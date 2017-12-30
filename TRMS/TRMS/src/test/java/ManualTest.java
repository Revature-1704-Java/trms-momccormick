import daoObjects.Employee;
import daos.EmployeeDao;

public class ManualTest {
	
	public static void main(String[] args) {
		
		EmployeeDao employeeDao = new EmployeeDao();
		Employee codeMonkee = employeeDao.getById(5);
		
		System.out.println(codeMonkee.toString());
		System.out.println(employeeDao.getEmployeeWithLogin(codeMonkee.getEmail(), "password"));
		System.out.println(employeeDao.getDirectSupervisorForEmployee(codeMonkee));
		System.out.println(employeeDao.getDirectSupervisorForEmployeeId(codeMonkee.getId()));
		System.out.println();
		codeMonkee.setAvailableReimbursement(1000);
		employeeDao.update(codeMonkee);
		codeMonkee = employeeDao.getById(5);
		System.out.println(codeMonkee.toString());
		
	}
}
