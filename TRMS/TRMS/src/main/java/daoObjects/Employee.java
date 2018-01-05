package daoObjects;

public class Employee {

	private int id;
	String firstName;
	String lastName;
	String email;
	String password;
	EmployeeType type;
	Employee directSupervisor;
	Employee departmentHead;
	double availableReimbursement;

	/*
	 *  To Store Dummy Employee in Reimbursement
	 */
	public Employee(int id) {
		this.id = id;
	}

	/*
	 * Managerment Employee's Basic information
	 */
	public Employee(int id, String firstName, String lastName, String email, EmployeeType type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.type = type;
	}

	/*
	 * Employee's Basic information
	 */
	public Employee(int id, String firstName, String lastName, String email, EmployeeType type,
			double availableReimbursement) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.type = type;
		this.availableReimbursement = availableReimbursement;
	}

	public Employee(int id, String firstName, String lastName, String email, String password, EmployeeType type,
			Employee directSupervisor, Employee departmentHead, double availableReimbursement) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.directSupervisor = directSupervisor;
		this.departmentHead = departmentHead;
		this.availableReimbursement = availableReimbursement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

	public Employee getDirectSupervisor() {
		return directSupervisor;
	}

	public void setDirectSupervisor(Employee directSupervisor) {
		this.directSupervisor = directSupervisor;
	}

	public Employee getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(Employee departmentHead) {
		this.departmentHead = departmentHead;
	}

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", type=" + type + ", availableReimbursement=" + availableReimbursement + "]";
	}

}
