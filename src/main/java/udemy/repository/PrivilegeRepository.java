package udemy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import udemy.config.MysqlConfig;
import udemy.entity.PrivilegeEntity;

public class PrivilegeRepository {
private Connection conn = MysqlConfig.initConnection();
	
	public static int AddPriv(String privName, String description) {
		String query = "INSERT INTO roles(name, description) VALUES(?, ?)";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, privName);
			prStm.setString(2, description);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Add privilege error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int DelPriv(int roleid) {
		String query = "DELETE FROM roles WHERE id=?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, roleid);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Add privilege error: " + e.getLocalizedMessage());
			return 0;
		}
	}
}
