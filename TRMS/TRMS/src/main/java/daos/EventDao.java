package daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daoInterfaces.EventDaoInterface;
import daoObjects.Event;
import daoObjects.EventType;
import daoObjects.GradeLetter;
import daoObjects.GradingFormat;
import daoObjects.Reimbursement;
import utils.ConnectionUtil;

public class EventDao implements EventDaoInterface {

	public Event getById(int id) {
		Event event = null;
		String query = "SELECT Name, EventType, Description, StartDate, EndDate, Time, Location, Cost, GradingFormat, PassingGrade, RecievedGrade FROM Events WHERE ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("Name");
				int eventType = rs.getInt("EventType");
				String description = rs.getString("Description");
				Date startDate = rs.getDate("StartDate");
				Date endDate = rs.getDate("EndDate");
				String time = rs.getString("Time");
				String location = rs.getString("Location");
				double cost = rs.getDouble("Cost");
				int gradingFormat = rs.getInt("GradingFormat");
				int passingGrade = rs.getInt("PassingGrade");
				double recievedGrade = rs.getDouble("RecievedGrade");

				event = new Event(id, name, EventType.getById(eventType), description, startDate, endDate, time,
						location, cost, GradingFormat.getById(gradingFormat), GradeLetter.getById(passingGrade),
						recievedGrade);
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
		return event;
	}

	public void update(Event newObj) {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("UPDATE Events SET ");
		queryBuilder.append(newObj.getName() != null ? "Name = '" + newObj.getName() + "', " : "");
		queryBuilder.append(
				newObj.getEventType().getId() != -1 ? "EventType = " + newObj.getEventType().getId() + ", " : "");
		queryBuilder.append(newObj.getDescription() != null ? "Description = '" + newObj.getDescription() + "', " : "");
		queryBuilder.append(
				newObj.getStartDate() != null ? "StartDate = TO_DATE('" + newObj.getStartDate() + "','yyyy-mm-dd'), "
						: "");
		queryBuilder.append(
				newObj.getEndDate() != null ? "EndDate = TO_DATE('" + newObj.getEndDate() + "','yyyy-mm-dd'), " : "");
		queryBuilder.append(newObj.getTime() != null ? "Time = '" + newObj.getTime() + "', " : "");
		queryBuilder.append(newObj.getLocation() != null ? "Location = '" + newObj.getLocation() + "', " : "");
		queryBuilder.append(newObj.getCost() != -1 ? "Cost = " + newObj.getCost() + ", " : "");
		queryBuilder.append(
				newObj.getGradingFormat().getId() != -1 ? "GradingFormat = " + newObj.getGradingFormat().getId() + ", "
						: "");
		queryBuilder.append(
				newObj.getPassingGrade().getId() != -1 ? "PassingGrade = " + newObj.getPassingGrade().getId() + ", "
						: "");
		queryBuilder
				.append(newObj.getRecievedGrade() != -1 ? "RecievedGrade = " + newObj.getRecievedGrade() + ", " : "");

		queryBuilder.deleteCharAt(queryBuilder.length() - 2);
		queryBuilder.append(" WHERE ID = ?");

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

	public void add(Event newObj) {
		String query = "INSERT INTO Events (ID,Name,EventType,Description,StartDate,EndDate,Time,Location,Cost,GradingFormat,PassingGrade,RecievedGrade) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			ps.setInt(1, newObj.getId()); // ID
			ps.setString(2, newObj.getName()); // Name
			ps.setInt(3, newObj.getEventType().getId()); // EventType
			ps.setString(4, newObj.getDescription()); // Description
			ps.setDate(5, newObj.getStartDate()); // StartDate
			ps.setDate(6, newObj.getEndDate()); // EndDate
			ps.setString(7, newObj.getTime()); // Time
			ps.setString(8, newObj.getLocation()); // Location
			ps.setDouble(9, newObj.getCost()); // Cost
			ps.setInt(10, newObj.getGradingFormat().getId()); // GradingFormat
			ps.setInt(11, newObj.getPassingGrade().getId()); // PassingGrade
			ps.setDouble(12, newObj.getRecievedGrade()); // RecievedGrade

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
	
	public Event persist(Event event) {
		Event persistEvent = null;
		String query = "SELECT ID, EventType, Description, StartDate, EndDate, Time, Location, Cost, GradingFormat, PassingGrade, RecievedGrade FROM Events WHERE Name = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, event.getName());

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("ID");
				int eventType = rs.getInt("EventType");
				String description = rs.getString("Description");
				Date startDate = rs.getDate("StartDate");
				Date endDate = rs.getDate("EndDate");
				String time = rs.getString("Time");
				String location = rs.getString("Location");
				double cost = rs.getDouble("Cost");
				int gradingFormat = rs.getInt("GradingFormat");
				int passingGrade = rs.getInt("PassingGrade");
				double recievedGrade = rs.getDouble("RecievedGrade");

				persistEvent = new Event(id, event.getName(), EventType.getById(eventType), description, startDate, endDate, time,
						location, cost, GradingFormat.getById(gradingFormat), GradeLetter.getById(passingGrade),
						recievedGrade);
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
		return persistEvent;
	}

	public Event getForReimbursement(Reimbursement reimbursement) {
		return getForReimbursementId(reimbursement.getId());
	}

	public Event getForReimbursementId(int reimbursementId) {
		Event event = null;
		String query = "SELECT ID, Name, EventType, Description, StartDate, EndDate, Time, Location, Cost, GradingFormat, PassingGrade, RecievedGrade FROM Events WHERE ID = (SELECT ID FROM Reimbursements WHERE ID = ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, reimbursementId);

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				int eventType = rs.getInt("EventType");
				String description = rs.getString("Description");
				Date startDate = rs.getDate("StartDate");
				Date endDate = rs.getDate("EndDate");
				String time = rs.getString("Time");
				String location = rs.getString("Location");
				double cost = rs.getDouble("Cost");
				int gradingFormat = rs.getInt("GradingFormat");
				int passingGrade = rs.getInt("PassingGrade");
				double recievedGrade = rs.getDouble("RecievedGrade");

				event = new Event(id, name, EventType.getById(eventType), description, startDate, endDate, time,
						location, cost, GradingFormat.getById(gradingFormat), GradeLetter.getById(passingGrade),
						recievedGrade);
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
		return event;
	}
}
