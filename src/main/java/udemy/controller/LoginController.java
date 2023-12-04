package udemy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import udemy.service.*;

@WebServlet(name="logincontroller", urlPatterns= {"/login-verify"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/login-verify")) {
			verifyLogin(request, response);
		}
		
	}
	
	public void verifyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rem = request.getParameter("remember");
		
		boolean isSuccess = LoginService.LoginVerify(email, password, rem, response);
		System.out.println(isSuccess);
		if (isSuccess) {
			response.sendRedirect(request.getContextPath() + "/admin");
			System.out.println("Login successfully !!!");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/login");
			System.out.println("Login failed !!!");
		}
	}

}
