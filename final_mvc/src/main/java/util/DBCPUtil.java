package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DBCPUtil {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MySqlDB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println("dataSource 이름??"+e.toString());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL??"+e.toString());
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(AutoCloseable... closer) {
		for(AutoCloseable c : closer) {
			try {
				if(c != null) c.close();
			} catch (Exception e) {}
		}
	}
}
