package daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import daoInterfaces.ReimbursementDaoInterface;
import daoObjects.Employee;
import daoObjects.Reimbursement;
import daoObjects.ReimbursementStatus;
import utils.ConnectionUtil;

public class ReimbursementDao implements ReimbursementDaoInterface{

	public Reimbursement getById(int id) {
		Reimbursement reimbursement = null;
		
		String query = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			conn.close();
		}
		
		return reimbursement;
	}

	public void update(Reimbursement newObj) {
		// TODO Auto-generated method stub
		
	}

	public void add(Reimbursement newObj) {
		// TODO Auto-generated method stub
		
	}

	public List<Reimbursement> getAllForEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getAllForEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getAllForSubordinatesOf(Employee manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getAllWithAssignedBenefitsCoordinator(Employee benefitsCoordinator) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> getAllWithReimbursementStatus(ReimbursementStatus reimbursementStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
