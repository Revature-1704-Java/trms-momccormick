package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String username = "trns";
	private static String password = "pass";

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException, IOException {
		return DriverManager.getConnection(url, username, password);
	}
}
