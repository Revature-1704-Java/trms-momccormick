package models;

public class Employee {
	String firstName;
	String lastName;
	String email;
	String password;
	EmployeeType type;
	Employee directSupervisor;
	Employee departmentHead;
	double availableReimbursement;
	
	public Employee() {
		this.firstName = "John";
		this.lastName = "Doe";
		this.email = "";
		this.password = "";
		this.type = EmployeeType.STANDARD;
		this.availableReimbursement = 0;
	}

	public Employee(String firstName, String lastName, String email, String password, EmployeeType type,
			Employee directSupervisor, Employee departmentHead, double availableReimbursement) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.directSupervisor = directSupervisor;
		this.departmentHead = departmentHead;
		this.availableReimbursement = availableReimbursement;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", type=" + type + 
				directSupervisor==null?"":(", DirectSupervisor=" + directSupervisor) +
				departmentHead==null?"":(", DepartmentHead=" + departmentHead) + 
				", availableReimbursement=" + availableReimbursement + "]";
	}
	
	
}
