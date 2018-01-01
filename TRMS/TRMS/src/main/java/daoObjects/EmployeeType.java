package daoObjects;

public enum EmployeeType {
	STANDARD(1, "Standard"), BENEFITS_COORDINATOR(2, "Benefits Coordinator"), MANAGEMENT(3, "Management");

	private int id;
	private String employeeType;

	private EmployeeType(int id, String employeeType) {
		this.id = id;
		this.employeeType = employeeType;
	}

	public int getId() {
		return id;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public static EmployeeType getById(int id) {
		for (EmployeeType v : values()) {
			if (v.getId() == id) {
				return v;
			}
		}
		return null;
	}

	public static EmployeeType getByEmployeeType(String employeeType) {
		for (EmployeeType v : values()) {
			if (v.getEmployeeType().equals(employeeType)) {
				return v;
			}
		}
		return null;
	}

}
