package udemy.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import udemy.repository.LoginRepository;
import udemy.entity.*;

public class LoginService {
	public static boolean LoginVerify(String email, String password, String rem, HttpServletResponse response) {
		List<UserEntity> usrLst = new ArrayList<UserEntity>();
		usrLst = LoginRepository.LoginUser(email, password, response);
		// Cookie is hashed by sha256
		if (usrLst.size() == 1) {
			if (rem.equals("on")) {
				if (usrLst.get(0).getRoleId() == 1) {
					Cookie cookie = new Cookie("admin_token", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
					cookie.setMaxAge(38400);
					response.addCookie(cookie);
					return true;
				}
				if (usrLst.get(0).getRoleId() == 2) {
					Cookie cookie = new Cookie("leader_token", "3e8d15cd54a30a88f86f2fcf5a4e534af94a88c6071edd1b25312c1cf049db4b");
					cookie.setMaxAge(38400);
					response.addCookie(cookie);
					return true;
				}
				if (usrLst.get(0).getRoleId() == 3) {
					Cookie cookie = new Cookie("member_token", "22deef2fefa16857af5be20192d0f79a26289a23a7bd5644308b12887be4b695");
					cookie.setMaxAge(38400);
					response.addCookie(cookie);
					return true;
				}
			}
			else {
				return true;
			}
			
		}
		else {
			return false;
		}
		return false;
	}
	
	
	public static boolean LoginPrivileged(HttpServletRequest request) {
		Cookie[] ckLst = request.getCookies();
		if (ckLst != null) {
			for (Cookie ck: ckLst) {
				if (ck.getValue().equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918") && ck.getName().equals("admin_token")) {
					return true;
				}
			}
		}
		else {
			return false;
		}
		return false;
	}
}
