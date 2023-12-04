package udemy.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import udemy.service.PrivilegeService;
import udemy.entity.PrivilegeEntity;

/**
 * Servlet implementation class RoleTable
 */
@WebServlet(name="rolecontroller", urlPatterns= {"/role-add", "/role-delete", "/role"})
public class RoleTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		if (path.equals("/role")) {
			doGetAllRoles(request, response);
		}
		if (path.equals("/role-delete")) {
			doGetRoleDelete(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/role-add")) {
			doAddRole(request, response);
		}
	}
	
	public void doGetAllRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<PrivilegeEntity> privLst = PrivilegeService.getAllRoles();

		request.setAttribute("privLst", privLst);
		request.getRequestDispatcher("role-table.jsp").forward(request, response);
	}
	
	public void doGetRoleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String roleid = request.getParameter("id");
		
		boolean isSuccess = PrivilegeService.PrivDelVerify(Integer.parseInt(roleid));
		if (isSuccess) {
			System.out.println("Delete privileged successfully!!");
		}
		else {
			System.out.println("Delete privileged failed!!");
		}
		response.sendRedirect("/MyApp5/role");
	}
	
	protected void doAddRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String privName = request.getParameter("priv");
		String description = request.getParameter("desc");
		
		boolean isSuccess = PrivilegeService.PrivAddVerify(privName, description);
		if (isSuccess) {
			System.out.println("Add privileged successfully!!");
		}
		else {
			System.out.println("Add privileged failed!!");
		}
		response.sendRedirect("/MyApp5/role");
	}
	
}
