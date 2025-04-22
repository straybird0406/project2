package JDBC;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCutil {
	public static Connection getConnection() {
	    Connection connection = null;

	    try {
	        Context context = new InitialContext();
	        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
	        connection = ds.getConnection();

	        boolean status = connection != null && !connection.isClosed();
	        System.out.println("連線狀態: " + status);
	    } catch (NamingException e) {
	        System.err.println("JNDI 查找失敗: " + e.getMessage());
	    } catch (SQLException e) {
	        System.err.println("獲取連線失敗: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("發生未知錯誤: " + e.getMessage());
	    }

	    return connection;
	}
	
	//關閉資源方法
		public static void closeResources(AutoCloseable... resources) {
		    for (AutoCloseable resource : resources) {
		        if (resource != null) {
		            try {
		                resource.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
}
