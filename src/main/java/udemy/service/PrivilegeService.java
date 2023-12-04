package udemy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import udemy.config.MysqlConfig;
import udemy.entity.PrivilegeEntity;
import udemy.repository.PrivilegeRepository;
import udemy.repository.RoleRepository;

public class PrivilegeService {
	public static boolean PrivAddVerify(String privName, String description) {
		int numrow = PrivilegeRepository.AddPriv(privName, description);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean PrivDelVerify(int roleid) {
		int numrow = PrivilegeRepository.DelPriv(roleid);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static List<PrivilegeEntity> getAllRoles(){
		return  RoleRepository.getAllRoles();
	}

}
