package udemy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import udemy.config.*;
import udemy.entity.PrivilegeEntity;

import udemy.entity.*;

public class RoleRepository {
	private Connection conn = MysqlConfig.initConnection();
	
	public static List<PrivilegeEntity> getAllRoles() {
		String query = "SELECT * FROM roles";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			ResultSet resSet = prStm.executeQuery();
			List<PrivilegeEntity> privLst = new ArrayList<PrivilegeEntity>();
			
			while(resSet.next()) {
				PrivilegeEntity priv = new PrivilegeEntity();
				priv.setId(resSet.getInt("id"));
				priv.setName(resSet.getString("name"));
				priv.setDescription(resSet.getString("description"));
				privLst.add(priv);
			}
			return privLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve role info error: " + e.getLocalizedMessage());
			return null;
		}
	}
}
