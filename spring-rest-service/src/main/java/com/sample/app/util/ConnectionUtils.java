package com.sample.app.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {

		return OracleConnUtils.getOracleConnection();
	}

	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}

	
}