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
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import utils.ConnectionUtil;

public class ReimbursementDao implements ReimbursementDaoInterface {

	public Reimbursement getById(int id) {
		Reimbursement reimbursement = null;

		String query = "SELECT DateSubmitted, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Date dateSubmitted = rs.getDate("DateSubmitted");
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				reimbursement = new Reimbursement(id, dateSubmitted, workTimeMissed, justification, projectedAmount,
						directSupervisorApproveDate, departmentHeadApproveDate, benefitsCoordinatorApproveDate,
						ReimbursementStatus.getById(reimbursementStatus), amountAwarded);
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
		// TODO Auto-generated method stub

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

		String query = "SELECT ID, DateSubmitted, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE Employee = ?";

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
				Date dateSubmitted = rs.getDate("DateSubmitted");
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, dateSubmitted, workTimeMissed, justification,
						projectedAmount, directSupervisorApproveDate, departmentHeadApproveDate,
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

	public List<Reimbursement> getAllForSubordinatesOf(Employee manager) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String subquery = "(Select ID FROM Employees WHERE DirectSupervisor = ? OR DepartmentHead = ?)";
		String query = "SELECT ID, DateSubmitted, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE Employee IN ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query + subquery);
			ps.setInt(1, manager.getId());
			ps.setInt(2, manager.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Date dateSubmitted = rs.getDate("DateSubmitted");
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, dateSubmitted, workTimeMissed, justification,
						projectedAmount, directSupervisorApproveDate, departmentHeadApproveDate,
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

	public List<Reimbursement> getAllWithAssignedBenefitsCoordinator(Employee benefitsCoordinator) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, DateSubmitted, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, ReimbursementStatus, AmountAwarded FROM Reimbursements WHERE BenefitsCoordinator = ?";

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
				Date dateSubmitted = rs.getDate("DateSubmitted");
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, dateSubmitted, workTimeMissed, justification,
						projectedAmount, directSupervisorApproveDate, departmentHeadApproveDate,
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
	
	public List<Reimbursement> getAllUnassigned() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, DateSubmitted, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, ReimbursementStatus FROM Reimbursements WHERE BenefitsCoordinator = null";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Date dateSubmitted = rs.getDate("DateSubmitted");
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				int reimbursementStatus = rs.getInt("ReimbursementStatus");

				Reimbursement reimbursement = new Reimbursement(id, dateSubmitted, workTimeMissed, justification,
						projectedAmount, directSupervisorApproveDate, departmentHeadApproveDate,
						ReimbursementStatus.getById(reimbursementStatus));

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

	public List<Reimbursement> getAllWithReimbursementStatus(ReimbursementStatus reimbursementStatus) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();

		String query = "SELECT ID, DateSubmitted, WorkTimeMissed, Justification, ProjectedAmount, DirectSupervisorApproveDate, DepartmentHeadApproveDate, BenefitsCoordinatorApproveDate, AmountAwarded FROM Reimbursements WHERE ReimbursementStatus = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, reimbursementStatus.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				Date dateSubmitted = rs.getDate("DateSubmitted");
				String workTimeMissed = rs.getString("WorkTimeMissed");
				String justification = rs.getString("Justification");
				double projectedAmount = rs.getDouble("ProjectedAmount");
				Date directSupervisorApproveDate = rs.getDate("DirectSupervisorApproveDate");
				Date departmentHeadApproveDate = rs.getDate("DepartmentHeadApproveDate");
				Date benefitsCoordinatorApproveDate = rs.getDate("BenefitsCoordinatorApproveDate");
				double amountAwarded = rs.getDouble("AmountAwarded");

				Reimbursement reimbursement = new Reimbursement(id, dateSubmitted, workTimeMissed, justification,
						projectedAmount, directSupervisorApproveDate, departmentHeadApproveDate,
						benefitsCoordinatorApproveDate, reimbursementStatus, amountAwarded);

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

}
