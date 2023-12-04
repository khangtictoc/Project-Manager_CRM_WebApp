package udemy.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleUtil {
	public static String getRole(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] ckLst = request.getCookies();
		if (ckLst != null) {
			for (Cookie ck: ckLst) {
				if (ck.getValue().equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918") && ck.getName().equals("admin_token")) {
					return "ROLE_ADMIN";
				}
				if (ck.getValue().equals("3e8d15cd54a30a88f86f2fcf5a4e534af94a88c6071edd1b25312c1cf049db4b") && ck.getName().equals("leader_token")) {
					return "ROLE_LEADER";
				}
				if (ck.getValue().equals("22deef2fefa16857af5be20192d0f79a26289a23a7bd5644308b12887be4b695") && ck.getName().equals("member_token")) {
					return "ROLE_MEMBER";
				}
			}
		}
		return "ROLE_MEMBER";
	}
}
