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

@WebFilter(urlPatterns= {"/groupwork", "/groupwork-add", "/groupwork-delete", "/groupwork-update-page", "/groupwork-update",  "/task-add", "/task-delete", "/task-update-full"})
public class LeaderFilter extends HttpFilter implements Filter {
       
    public LeaderFilter() {
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
				if (ck.getValue().equals("3e8d15cd54a30a88f86f2fcf5a4e534af94a88c6071edd1b25312c1cf049db4b") && ck.getName().equals("leader_token")) {
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

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
