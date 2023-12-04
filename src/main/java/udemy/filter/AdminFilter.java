package udemy.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;


@WebFilter(urlPatterns= {"/admin", "/role", "/role-add", "/role-delete", "/user", "/user-add", "/user-delete", "/user-view", "/user-udpate-page", "/user-update"})
public class AdminFilter extends HttpFilter implements Filter {
       
    public AdminFilter() {
        super();
    }


	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		Cookie[] ckLst = req.getCookies();
		if (ckLst != null) {
			for (Cookie ck: ckLst) {
				if (ck.getValue().equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918") && ck.getName().equals("admin_token")) {
					chain.doFilter(req, res);
					return;
				}
			}
			res.sendRedirect("/MyApp5/login");
		}
		else {
			res.sendRedirect("/MyApp5/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
