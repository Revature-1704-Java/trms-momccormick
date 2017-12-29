import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Employee;
import utils.ConnectionUtil;

public class ManualTest {
	
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void addNewEmployee(Employee emp, int password) {
		String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?)";

		try {
			Connection conn = ConnectionUtil.getConnection()
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, emp.getEmployeeId());
			statement.setString(2, emp.getFirstName());
			statement.setString(3, emp.getLastName());
			statement.setString(4, emp.getEmail());
			statement.setInt(5, password);
			statement.setInt(6, EMP_ROLE.EMPLOYEE.getId());
			
			statement.addBatch();
			statement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Employee getEmployeeById(int id) {
//		Employee employee = null;
//		String sql = "SELECT first_name, last_name, email FROM employee WHERE emp_id=" + id;
//		ResultSet result = null;
//
//		try (Connection conn = ConnectionUtil.getConnection()) {
//			Statement statement = conn.createStatement();
//			result = statement.executeQuery(sql);
//			if (result.next()) {
//				String firstName = result.getString("first_name");
//				String lastName = result.getString("last_name");
//				String email = result.getString("email");
//				employee = new Employee(firstName, lastName, email);
//			}
//
//			if (result != null) {
//				result.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return employee;
		return null;
	}
}
