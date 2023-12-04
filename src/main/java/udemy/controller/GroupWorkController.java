package udemy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import udemy.service.GroupWorkService;
import udemy.service.UserService;
import udemy.entity.JobEntity;

@WebServlet(name="groupworkController", urlPatterns= {"/groupwork-add", "/groupwork-delete", "/groupwork", "/groupwork-update-page", "/groupwork-update"})
public class GroupWorkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupWorkController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/groupwork")) {
			doGetAllJobs(request, response);
		}
		if (path.equals("/groupwork-delete")) {
			doGetJobDelete(request, response);
		}
		if (path.equals("/groupwork-update-page")) {
			doGroupworkUpdatePage(request, response);
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/groupwork-add")) {
			try {
				doPostAddJob(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (path.equals("/groupwork-update")) {
			try {
				doUpdateJob(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	}

	public void doGetAllJobs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<JobEntity> jobLst = GroupWorkService.getAllJob();

		request.setAttribute("jobLst", jobLst);
		request.getRequestDispatcher("groupwork.jsp").forward(request, response);
	}
	
	public void doGetJobDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String jobId = request.getParameter("id");
		
		boolean isSuccess = GroupWorkService.JobDelVerify(Integer.parseInt(jobId));
		if (isSuccess) {
			System.out.println("Delete job successfully!!");
		}
		else {
			System.out.println("Delete job failed!!");
		}
		response.sendRedirect("/MyApp5/groupwork");
	}
	
	public void doPostAddJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException{
		String jobName = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		boolean isSuccess = GroupWorkService.JobAddVerify(jobName, startDate, endDate);
		if (isSuccess) {
			System.out.println("Add job successfully!!");
		}
		else {
			System.out.println("Add job failed!!");
		}
		response.sendRedirect("/MyApp5/groupwork");
	}
	
	public void doGroupworkUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jobId = request.getParameter("id");

		request.setAttribute("jobId", jobId);
		request.getRequestDispatcher("groupwork-update.jsp").forward(request, response);
	}
	
	public void doUpdateJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		int userId = Integer.parseInt(request.getParameter("id"));
		
		String jobName = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		boolean isSuccess = GroupWorkService.JobUpdateVerfiy(userId, jobName, startDate, endDate);
		if (isSuccess) {
			System.out.println("Update job successfully!!");
		} else {
			System.out.println("Update job failed!!");
		}
		response.sendRedirect("/MyApp5/groupwork");
	}
}
