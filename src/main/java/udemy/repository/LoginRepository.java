package udemy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import udemy.config.*;
import udemy.entity.UserEntity;
import udemy.entity.PrivilegeEntity;

import udemy.groupentity.UserRoleEntity;

public class LoginRepository {
	private Connection conn = MysqlConfig.initConnection();
	
	public static List<UserEntity> LoginUser(String email, String password, HttpServletResponse response) {
		String query = "SELECT * FROM users WHERE email=? AND password=?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, email);
			prStm.setString(2, password);
			ResultSet resSet = prStm.executeQuery();
			List<UserEntity> usrLst = new ArrayList<UserEntity>();
			
			while(resSet.next()) {
				UserEntity usr = new UserEntity();
				usr.setId(resSet.getInt("id"));
				usr.setFullName(resSet.getString("fullname"));
				usr.setRoleId(resSet.getInt("role_id"));
				usrLst.add(usr);
			}
			return usrLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve user info error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static List<UserEntity> ViewUser(int userId) {
		String query = "SELECT * FROM users WHERE id = ?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, userId);
			ResultSet resSet = prStm.executeQuery();
			List<UserEntity> usrLst = new ArrayList<UserEntity>();
			
			while(resSet.next()) {
				UserEntity usr = new UserEntity();
				usr.setFullName(resSet.getString("fullname"));
				usr.setEmail(resSet.getString("email"));
				usr.setRoleId(resSet.getInt("role_id"));
				usrLst.add(usr);
			}
			return usrLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve user info error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static List<UserEntity> getAllUser() {
		String query = "SELECT * FROM users";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			ResultSet resSet = prStm.executeQuery();
			List<UserEntity> usrLst = new ArrayList<UserEntity>();
			
			while(resSet.next()) {
				UserEntity usr = new UserEntity();
				usr.setId(resSet.getInt("id"));
				usr.setFullName(resSet.getString("fullname"));
				usr.setEmail(resSet.getString("email"));
				usr.setAvar(resSet.getString("avatar"));
				usr.setRoleId(resSet.getInt("role_id"));
				usrLst.add(usr);
			}
			return usrLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve user info error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static List<UserRoleEntity> getUserRole() {
		String query = "SELECT users.id AS user_id, users.email AS user_email, users.fullname AS user_fullname, r.name AS role_name FROM users  JOIN (SELECT * FROM roles ) AS r ON users.role_id = r.id";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			ResultSet resSet = prStm.executeQuery();
			List<UserRoleEntity> usrRoleLst = new ArrayList<UserRoleEntity>();
			
			while(resSet.next()) {
				UserRoleEntity usrrole = new UserRoleEntity();
				
				UserEntity user = new UserEntity();
				user.setId(resSet.getInt("user_id"));
				user.setEmail(resSet.getString("user_email"));
				user.setFullName(resSet.getString("user_fullname"));
				
				PrivilegeEntity priv = new PrivilegeEntity();
				priv.setName(resSet.getString("role_name"));
				
				usrrole.setUser(user);
				usrrole.setPriv(priv);
				usrRoleLst.add(usrrole);
			}
			return usrRoleLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve user info error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static int AddUser(String fullname, String email, String password) throws ParseException {
		String query = "INSERT INTO users(fullname, email, password, role_id) VALUES(?, ?, ?, 3)";
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, fullname);
			prStm.setString(2, email);
			prStm.setString(3, password);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Add user error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int DelUser(int userid) {
		String query = "DELETE FROM users WHERE id=?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, userid);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Add user error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int updateUser(int userid, String fullname, String email, String password) {
		String query = "UPDATE users SET fullname=?, email=?, password=? WHERE id=?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, fullname);
			prStm.setString(2, email);
			prStm.setString(3, password);
			prStm.setInt(4, userid);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Update user error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int getUserId(String userFullname) {
		String query = "SELECT id FROM users WHERE name = ?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, userFullname);
			ResultSet resSet = prStm.executeQuery();
			int jobId =0 ;
			while(resSet.next()) {
				jobId = resSet.getInt("id");
			}
			return jobId;
		} catch (SQLException e) { 
			System.out.println("Retrieve user id error: " + e.getLocalizedMessage());
			return -1;
		}
	}
}
