package udemy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import udemy.config.MysqlConfig;
import udemy.entity.UserEntity;
import udemy.groupentity.UserRoleEntity;
import udemy.repository.LoginRepository;

public class UserService {
	public static boolean UserAddVerify(String fullname, String email, String password) throws ParseException {
		int numrow = LoginRepository.AddUser(fullname, email, password);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean UserUpdateverfiy(int userId, String fullname, String email, String password) throws ParseException {
		int numrow = LoginRepository.updateUser(userId, fullname, email, password);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean UserDelVerify(int userid) {
		int numrow = LoginRepository.DelUser(userid);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static List<UserEntity> getAllUser(){
		return  LoginRepository.getAllUser();
	}
	
	public static List<UserEntity> UserDetail(int userId){
		return LoginRepository.ViewUser(userId);
	}
	
	public static List<UserRoleEntity> getAllUserRole(){
		return LoginRepository.getUserRole();
	}

}
