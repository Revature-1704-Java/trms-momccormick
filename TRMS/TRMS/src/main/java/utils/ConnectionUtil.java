package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "trns";
		String password = "pass";

		return DriverManager.getConnection(url, user, password);
	}
}
