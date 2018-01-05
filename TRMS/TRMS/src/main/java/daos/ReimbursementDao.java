package daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.ReimbursementDaoInterface;
import daoObjects.Employee;
import daoObjects.Event;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import utils.ConnectionUtil;

public class ReimbursementDao implements ReimbursementDaoInterface {

	public Reimbursement getById(int id) {
		Reimbursement reimbursement = null;

		String query = "SELECT Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, BenefitsCoordinator, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Employee employee = new Employee(rs.getInt("Employee"));
				Date dateSubmitted = rs.getDate("DateSubmitted");
				Event event = new Event(rs.getInt("Event"));
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Employee benefitsCoordinator = new Employee(rs.getInt("BenefitsCoordinator"));
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				reimbursement = new Reimbursement(id, employee, dateSubmitted, event, workTimeMissed, justification,
						projectedAmount, benefitsCoordinator, directSupervisorApproveDate, departmentHeadApproveDate,
						benefitsCoordinatorApproveDate, ReimbursementStatus.getById(reimbursementStatus),
						amountAwarded);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reimbursement;
	}

	public void update(Reimbursement newObj) {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("UPDATE Reimbursements SET ");

		queryBuilder.append(newObj.getEmployee() != null ? "Employee = " + newObj.getEmployee().getId() + ", " : "");
		queryBuilder.append(newObj.getDateSubmitted() != null
				? "DateSubmitted = TO_DATE('" + newObj.getDateSubmitted() + "','yyyy-mm-dd'), "
				: "");
		queryBuilder.append(newObj.getEvent() != null ? "Event = " + newObj.getEvent().getId() + ", " : "");
		queryBuilder.append(
				newObj.getWorkTimeMissed() != null ? "WorkTimeMissed = '" + newObj.getWorkTimeMissed() + "', " : "");
		queryBuilder.append(
				newObj.getJustification() != null ? "Justification = '" + newObj.getJustification() + "', " : "");
		queryBuilder.append(
				newObj.getProjectedAmount() != 0 ? "ProjectedAmount = " + newObj.getProjectedAmount() + ", " : "");
		queryBuilder.append(newObj.getBenefitsCoordinator().getId() != 0
				? "BenefitsCoordinator = " + newObj.getBenefitsCoordinator().getId() + ", "
				: "");
		queryBuilder.append(newObj.getDirectSupervisorApproved() != null
				? "DirectSupervisorApproveDate = TO_DATE('" + newObj.getDirectSupervisorApproved() + "','yyyy-mm-dd'), "
				: "");
		queryBuilder.append(newObj.getDepartmentHeadApproved() != null
				? "DepartmentHeadApproveDate = TO_DATE('" + newObj.getDepartmentHeadApproved() + "','yyyy-mm-dd'), "
				: "");
		queryBuilder
				.append(newObj.getBenefitsCoordinatorApproved() != null
						? "BenefitsCoordinatorApproveDate = TO_DATE('" + newObj.getBenefitsCoordinatorApproved()
								+ "','yyyy-mm-dd'), "
						: "");
		queryBuilder.append(newObj.getReimbursementStatus() != null
				? "ReimbursementStatus = " + newObj.getReimbursementStatus().getId() + ", "
				: "");
		queryBuilder.append(newObj.getAttachmentDirectory() != null
				? "AttachmentDirectory = " + newObj.getAttachmentDirectory() + ", "
				: "");
		queryBuilder
				.append(newObj.getAmountAwarded() != 0 ? "AmountAwarded = " + newObj.getAmountAwarded() + ", " : "");

		queryBuilder.deleteCharAt(queryBuilder.length() - 2);
		queryBuilder.append(" WHERE ID = ?");

		System.out.println(queryBuilder.toString());

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());
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

	public void add(Reimbursement newObj) {
		String query = "INSERT INTO Reimbursements (ID,Employee,DateSubmitted,Event,WorkTimeMissed,Justification,ProjectedAmount,ReimbursementStatus) VALUES (?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, newObj.getId());
			ps.setInt(2, newObj.getEmployee().getId());
			ps.setDate(3, newObj.getDateSubmitted());
			ps.setInt(4, newObj.getEvent().getId());
			ps.setString(5, newObj.getWorkTimeMissed());
			ps.setString(6, newObj.getJustification());
			ps.setDouble(7, newObj.getProjectedAmount());
			ps.setInt(8, newObj.getReimbursementStatus().getId());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Reimbursement> getAllForEmployee(Employee employee) {
		return getAllForEmployeeId(employee.getId());
	}

	public List<Reimbursement> getAllForEmployeeId(int employeeId) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, BenefitsCoordinator, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE Employee = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, employeeId);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Employee employee = new Employee(rs.getInt("Employee"));
				Date dateSubmitted = rs.getDate("DateSubmitted");
				Event event = new Event(rs.getInt("Event"));
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Employee benefitsCoordinator = new Employee(rs.getInt("BenefitsCoordinator"));
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, employee, dateSubmitted, event, workTimeMissed,
						justification, projectedAmount, benefitsCoordinator, directSupervisorApproveDate,
						departmentHeadApproveDate, benefitsCoordinatorApproveDate,
						ReimbursementStatus.getById(reimbursementStatus), amountAwarded);

				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reimbursements;
	}

	public List<Reimbursement> getAllForSubordinatesOf(Employee manager) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, BenefitsCoordinator, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE ReimbursementStatus <> ? AND Employee IN (Select ID FROM Employees WHERE DirectSupervisor = ? OR DepartmentHead = ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, ReimbursementStatus.CANCELED.getId());
			ps.setInt(2, manager.getId());
			ps.setInt(3, manager.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Employee employee = new Employee(rs.getInt("Employee"));
				Date dateSubmitted = rs.getDate("DateSubmitted");
				Event event = new Event(rs.getInt("Event"));
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Employee benefitsCoordinator = new Employee(rs.getInt("BenefitsCoordinator"));
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, employee, dateSubmitted, event, workTimeMissed,
						justification, projectedAmount, benefitsCoordinator, directSupervisorApproveDate,
						departmentHeadApproveDate, benefitsCoordinatorApproveDate,
						ReimbursementStatus.getById(reimbursementStatus), amountAwarded);

				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reimbursements;
	}

	public List<Reimbursement> getAllWithAssignedBenefitsCoordinator(Employee benefitsCoordinator) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, BenefitsCoordinator, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE BenefitsCoordinator = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, benefitsCoordinator.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Employee employee = new Employee(rs.getInt("Employee"));
				Date dateSubmitted = rs.getDate("DateSubmitted");
				Event event = new Event(rs.getInt("Event"));
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, employee, dateSubmitted, event, workTimeMissed,
						justification, projectedAmount, benefitsCoordinator, directSupervisorApproveDate,
						departmentHeadApproveDate, benefitsCoordinatorApproveDate,
						ReimbursementStatus.getById(reimbursementStatus), amountAwarded);

				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reimbursements;
	}

	public List<Reimbursement> getAllUnassigned() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, Employee, DateSubmitted, Event, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE BenefitsCoordinator IS NULL";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Employee employee = new Employee(rs.getInt("Employee"));
				Date dateSubmitted = rs.getDate("DateSubmitted");
				Event event = new Event(rs.getInt("Event"));
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, employee, dateSubmitted, event, workTimeMissed,
						justification, projectedAmount, null, directSupervisorApproveDate, departmentHeadApproveDate,
						benefitsCoordinatorApproveDate, ReimbursementStatus.getById(reimbursementStatus),
						amountAwarded);

				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reimbursements;
	}

	public void changeStatusForGradeSubmissionOnEvent(ReimbursementStatus reimbursementStatus, Event event) {
		String query = "UPDATE Reimbursements SET ReimbursementStatus = ? WHERE Event = (SELECT ID FROM Events WHERE ID = ?)";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, reimbursementStatus.getId());
			ps.setInt(2, event.getId());
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

	public ReimbursementStatus getStatusForEvent(Event event) {
		ReimbursementStatus status = null;

		String query = "SELECT ReimbursementStatus FROM Reimbursements WHERE Event = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, event.getId());

			rs = ps.executeQuery();

			if (rs.next()) {
				status = ReimbursementStatus.getById(rs.getInt("ReimbursementStatus"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return status;

	}

	public double getAmountAwardedForYearForEmployee(Date year, Employee employee) {
		return getAmountAwardedForYearForEmployeeId(year, employee.getId());
	}

	public double getAmountAwardedForYearForEmployeeId(Date year, int employeeId) {
		double amountAwardedForYear = 0;

		String query = "SELECT SUM(AmountAwarded) AS AwardedAmountForYear FROM Reimbursements WHERE Employee = (SELECT ID FROM Employees WHERE ID = ?) AND ReimbursementStatus = ? AND TO_CHAR(DateSubmitted, 'YYYY') = (SELECT TO_CHAR(?, 'YYYY') FROM DUAL)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, employeeId);
			ps.setInt(2, ReimbursementStatus.AWARDED.getId());
			ps.setDate(3, year);

			rs = ps.executeQuery();

			if (rs.next()) {
				amountAwardedForYear = rs.getDouble("AwardedAmountForYear");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return amountAwardedForYear;
	}
}
