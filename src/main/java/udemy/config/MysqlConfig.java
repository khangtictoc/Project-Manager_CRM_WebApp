package udemy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {
	public static Connection initConnection() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Connection debug (ClassNotFound): " + e.getLocalizedMessage());
			}
			return DriverManager.getConnection("jdbc:mysql://localhost:9999/crm_app", "root", "my-secret-pw");
			//return DriverManager.getConnection("jdbc:mysql://database-1.cztocyrv1ybc.us-east-1.rds.amazonaws.com:3306/crm_app", "admin", "root1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection debug (MySQL): " + e.getLocalizedMessage());
		} 
		return null;
	}
}
