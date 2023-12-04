package udemy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import udemy.entity.JobEntity;
import udemy.entity.StatusEntity;
import udemy.groupentity.TaskUserJobEntity;
import udemy.groupentity.TaskUserJobStatusRoleEntity;
import udemy.groupentity.UserRoleEntity;
import udemy.service.GroupWorkService;
import udemy.service.StatusService;
import udemy.service.TaskService;
import udemy.service.UserService;
import udemy.utils.RoleUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(name="taskController", urlPatterns= {"/task-add", "/task-delete", "/task", "/task-detail", "/task-update-page", "/task-update-full", "/task-update-user"})
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TaskController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/task")) {
			doGetAllTasks(request, response);
		}
		if (path.equals("/task-delete")) {
			doGetTaskDelete(request, response);
		}
		if (path.equals("/task-detail")) {
			doGetTaskInfo(request, response);
		}
		if (path.equals("/task-update-page")) {
			doTaskUpdatePage(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/task-add")) {
			try {
				doAddTask(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/task-update-full")) {
			try {
				doUpdateTaskFull(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/task-update-user")) {
			try {
				doUpateTaskByUser(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void doGetTaskInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int taskid = Integer.parseInt(request.getParameter("id"));
		List<TaskUserJobStatusRoleEntity> taskDetailLst = TaskService.getTaskDetails(taskid);

		request.setAttribute("taskDetailItem", taskDetailLst.get(0));
		request.getRequestDispatcher("task-detail.jsp").forward(request, response);
	}
	
	public void doGetAllTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<TaskUserJobEntity> taskLstInfo = TaskService.getFullTasksInfo();

		request.setAttribute("taskLstInfo", taskLstInfo);
		request.getRequestDispatcher("task.jsp").forward(request, response);
	}
	
	public void doGetTaskDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int taskid = Integer.parseInt(request.getParameter("id"));
		
		boolean isSuccess = TaskService.TaskDelVerify(taskid);
		if (isSuccess) {
			System.out.println("Delete task successfully!!");
		}
		else {
			System.out.println("Delete task failed!!");
		}
		response.sendRedirect("/MyApp5/task");
	}
	
	public void doAddTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException{
		String taskName = request.getParameter("taskName");
		String jobName =request.getParameter("jobName");
		String userFullName =request.getParameter("userFullName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		boolean isSuccess = TaskService.TaskAddVerify(taskName, jobName, userFullName, startDate, endDate);
		if (isSuccess) {
			System.out.println("Add task successfully!!");
		}
		else {
			System.out.println("Add task failed!!");
		}
		response.sendRedirect("/MyApp5/task");
	}
	
	public void doUpdateTaskFull(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException{
		int taskId = Integer.parseInt(request.getParameter("id"));
		String taskName =request.getParameter("name");
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int jobId = Integer.parseInt(request.getParameter("jobId"));
		int statusId = Integer.parseInt(request.getParameter("statusId"));
		
		boolean isSuccess = TaskService.TaskUpdateFullVerfiy(taskId, taskName, start, end, jobId, userId, statusId);
		if (isSuccess) {
			System.out.println("Update task full successfully!!");
		}
		else {
			System.out.println("Update task full failed!!");
		}
		response.sendRedirect("/MyApp5/task");
	}
	
	public void doUpateTaskByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException{
		int taskId = Integer.parseInt(request.getParameter("id"));
		int statusId = Integer.parseInt(request.getParameter("statusId"));
		
		boolean isSuccess = TaskService.TaskUpdateByUserVerfiy(taskId, statusId);
		if (isSuccess) {
			System.out.println("Update task by user successfully!!");
		}
		else {
			System.out.println("Update task by user failed!!");
		}
		response.sendRedirect("/MyApp5/task");
	}
	
	public void doTaskUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role = RoleUtil.getRole(request, response);
		boolean isEnabled = true;
		String submitURL = null;
		String disabled = null;
		if (role.equals("ROLE_ADMIN") || role.equals("ROLE_LEADER")) {
			submitURL = "/MyApp5/task-update-full?id=";
			disabled = "";
		}
		else {
			submitURL = "/MyApp5/task-update-user?id=";
			disabled = "disabled";
		}
				
		String taskId = request.getParameter("id");
		List<UserRoleEntity> usrLst = UserService.getAllUserRole();
		List<StatusEntity> statLst = StatusService.getAllStatus();
		List<JobEntity> jobLst = GroupWorkService.getAllJob();
		
		request.setAttribute("taskId", taskId);
		request.setAttribute("usrLst", usrLst);
		request.setAttribute("statLst", statLst);
		request.setAttribute("jobLst", jobLst);
		request.setAttribute("submitURL", submitURL);
		request.setAttribute("disabled", disabled);
		
		
		request.getRequestDispatcher("task-update.jsp").forward(request, response);
	}
}
