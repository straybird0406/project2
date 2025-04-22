package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	// 靜態開啟連線
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=project_planeticket;encrypt=false";
		String user = "sandra";
		String password = "1234";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, user, password);
			boolean status = !conn.isClosed();
			System.out.println("連線狀態" + status);
		} catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}



	// 靜態關閉連線
	public static void closeResource(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResource(Connection conn, PreparedStatement stmt) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResource(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
