package daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daoInterfaces.EmployeeDaoInterface;
import daoObjects.Employee;
import daoObjects.EmployeeType;
import utils.ConnectionUtil;

public class EmployeeDao implements EmployeeDaoInterface {

	public Employee getById(int id) {
		Employee employee = null;
		String query = "SELECT FirstName, LastName, Email, EmployeeType, AvailableReimbursement FROM Employees WHERE ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String email = rs.getString("Email");
				int employeeType = rs.getInt("EmployeeType");
				double availableReimbursement = rs.getDouble("AvailableReimbursement");

				employee = new Employee(id, firstName, lastName, email, null, EmployeeType.getById(employeeType),
						null, null, availableReimbursement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

	public void update(Employee newObj) {
		String query = "UPDATE Employees SET AvailableReimbursement = " + newObj.getAvailableReimbursement()
				+ " WHERE ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newObj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void add(Employee newObj) {
		// Not Adding Employees
	}

	public Employee getEmployeeWithLogin(String email, String password) {
		Employee employee = null;
		String query = "SELECT ID, FirstName, LastName, EmployeeType, AvailableReimbursement FROM Employees WHERE Email = ? AND Password = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int employeeType = rs.getInt("EmployeeType");
				double availableReimbursement = rs.getDouble("AvailableReimbursement");

				employee = new Employee(id, firstName, lastName, email, null, EmployeeType.getById(employeeType),
						null, null, availableReimbursement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

	public Employee getDirectSupervisorForEmployee(Employee employee) {
		return getDirectSupervisorForEmployeeId(employee.getId());
	}

	public Employee getDirectSupervisorForEmployeeId(int id) {
		Employee employee = null;
		String query = "SELECT ID, FirstName, LastName, Email, EmployeeType FROM Employees WHERE ID = (SELECT DirectSupervisor FROM Employees WHERE ID = ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				int dsId = rs.getInt("ID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String email = rs.getString("Email");
				int employeeType = rs.getInt("EmployeeType");

				employee = new Employee(dsId, firstName, lastName, email, null, EmployeeType.getById(employeeType), null, null, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
}
