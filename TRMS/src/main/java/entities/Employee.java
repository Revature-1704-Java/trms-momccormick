package entities;

public class Employee {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String username;
	private String password;

	public Employee() {
		firstName = "John";
		lastName = "Doe";
		phone = "123-456-7890";
		email = "john.doe@email.com";
		username = "johndoe";
		password = "password";
	}

	public Employee(String firstName, String lastName, String phone, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
