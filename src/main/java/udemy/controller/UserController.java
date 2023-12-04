package udemy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import udemy.service.TaskService;
import udemy.service.UserService;
import udemy.entity.TaskEntity;
import udemy.entity.UserEntity;
import udemy.groupentity.TaskStatusEntity;
import udemy.groupentity.UserRoleEntity;

@WebServlet(name = "usercontroller", urlPatterns = { "/user-add", "/user-delete", "/user", "/user-view", "/user-update-page", "/user-update" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/user")) {
			doGetAllUsers(request, response);
		}
		if (path.equals("/user-delete")) {
			doGetUserDelete(request, response);
		}
		if (path.equals("/user-view")) {
			doViewUser(request, response);
		}
		if (path.equals("/user-update-page")) {
			doUserUpdatePage(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/user-add")) {
				try {
					doAddUser(request, response);
				} catch (ServletException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (path.equals("/user-update")) {
			try {
				doUpdateUser(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public void doViewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		
		List<UserEntity> userInfo = UserService.UserDetail(userId);
		List<TaskStatusEntity> taskInfo = TaskService.getTaskByUserId(userId);
		

		request.setAttribute("userInfo", userInfo.get(0));
		request.setAttribute("taskInfo", taskInfo);
		request.getRequestDispatcher("user-details.jsp").forward(request, response);
	}
	
	public void doUserUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("id");

		request.setAttribute("userId", userId);
		request.getRequestDispatcher("user-update.jsp").forward(request, response);
	}
	
	public void doGetAllUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UserRoleEntity> usrRoleLst = UserService.getAllUserRole();

		request.setAttribute("usrRoleLst", usrRoleLst);
		request.getRequestDispatcher("user-table.jsp").forward(request, response);
	}

	public void doGetUserDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String roleid = request.getParameter("id");

		boolean isSuccess = UserService.UserDelVerify(Integer.parseInt(roleid));
		if (isSuccess) {
			System.out.println("Delete user successfully!!");
		} else {
			System.out.println("Delete user failed!!");
		}
		response.sendRedirect("/MyApp5/user");
	}

	public void doAddUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean isSuccess = UserService.UserAddVerify(fullname, email, password);
		if (isSuccess) {
			System.out.println("Add user successfully!!");
		} else {
			System.out.println("Add user failed!!");
		}
		response.sendRedirect("/MyApp5/user");
	}
	
	public void doUpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		int userId = Integer.parseInt(request.getParameter("id"));
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		boolean isSuccess = UserService.UserUpdateverfiy(userId, fullname, email, password);
		if (isSuccess) {
			System.out.println("Update user successfully!!");
		} else {
			System.out.println("Update user failed!!");
		}
		response.sendRedirect("/MyApp5/user");
	}
}
