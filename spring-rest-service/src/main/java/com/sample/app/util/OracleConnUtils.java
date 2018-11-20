package com.sample.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnUtils {

	public static Connection getOracleConnection() throws ClassNotFoundException, SQLException {

		String connectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "ACPMITDE";
		String password = "root";
		return getOracleConnection(connectionURL, userName, password);
	}

	public static Connection getOracleConnection(String connectionURL, String userName, String password)
			throws SQLException, ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}

}
