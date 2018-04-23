package be.pxl.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;

	public static Connection getConnection (String url, String username, String password) throws SQLException {
		if (connection == null || connection.isClosed() || !connection.isValid(2)) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}


	public static void closeConnection() {
		try {
			if (connection!=null) {
				connection.close();
				connection=null;
			}
		} catch (Exception e) {
			// ignore
		}
	}
}
