package udemy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="admincontroller", urlPatterns= {"/admin", "/login"})
public class AdminPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AdminPageController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		if (path.equals("/admin")) {
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		if (path.equals("/login")) {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
		
	}
	

	


}
