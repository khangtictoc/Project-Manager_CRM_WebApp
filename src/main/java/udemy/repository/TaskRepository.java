package udemy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import udemy.config.MysqlConfig;
import udemy.entity.TaskEntity;
import udemy.entity.JobEntity;
import udemy.entity.StatusEntity;
import udemy.entity.UserEntity;
import udemy.groupentity.TaskStatusEntity;
import udemy.groupentity.TaskUserJobEntity;
import udemy.groupentity.TaskUserJobStatusRoleEntity;


public class TaskRepository {
	private static Connection conn = MysqlConfig.initConnection();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static int AddTask(String taskName, String jobName, String userFullname, String taskStart, String taskEnd) throws ParseException {
		String query = "INSERT INTO tasks(name, user_id, job_id, start_date, end_date) VALUES(?, ?, ?)";
		
		Date taskStartDate = sdf.parse(taskStart);
		Date taskEndDate = sdf.parse(taskEnd);
		int jobId = GroupWorkRepository.getJobId(jobName);
		int userId = LoginRepository.getUserId(userFullname);
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, taskName);
			prStm.setInt(2, jobId);
			prStm.setInt(3, userId);
			prStm.setDate(2, new java.sql.Date(taskStartDate.getTime()));
			prStm.setDate(3, new java.sql.Date(taskEndDate.getTime()));
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Add task error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int delTask(int taskid) {
		String query = "DELETE FROM tasks WHERE id=?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, taskid);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Delete task error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static List<TaskUserJobEntity> getAllTasksInfo() {
		String query = "SELECT t.id AS id, t.name AS name, u.fullname AS user_name, j.name AS job_name, t.start_date AS start , t.end_date AS end , s.name AS status FROM tasks t JOIN (SELECT * FROM users) AS u ON t.user_id = u.id  \r\n"
				+ "JOIN (SELECT * FROM jobs) AS j ON t.job_id = j.id JOIN (SELECT * FROM status) AS s ON t.status_id = s.id";
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			ResultSet resSet = prStm.executeQuery();
			List<TaskUserJobEntity> taskInfoLst = new ArrayList<TaskUserJobEntity>();
			
			while(resSet.next()) {
				TaskUserJobEntity taskInfo = new TaskUserJobEntity();
				UserEntity user = new UserEntity();
				TaskEntity task = new TaskEntity();
				JobEntity job = new JobEntity();
				StatusEntity status = new StatusEntity();
				
				user.setFullName(resSet.getString("user_name"));
				job.setName(resSet.getString("job_name"));
				status.setName(resSet.getString("status"));
				task.setId(resSet.getInt("id"));
				task.setName(resSet.getString("name"));
				task.setStartDate(resSet.getDate("start").toString());
				task.setEndDate(resSet.getDate("end").toString());
				
				taskInfo.setJob(job);
				taskInfo.setTask(task);
				taskInfo.setUser(user);
				taskInfo.setStatus(status);
				
				taskInfoLst.add(taskInfo);
			}
			return taskInfoLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve task info error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static List<TaskStatusEntity> getTasksByUserId(int userId) {
		String query = "SELECT t.name AS task_name, t.start_date AS start_date, t.end_date AS end_date, s.name AS status_name FROM tasks t JOIN (SELECT * FROM users WHERE users.id = ?) AS u ON t.user_id = u.id JOIN (SELECT * FROM status) AS s ON t.status_id = s.id;\r\n";
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, userId);
			ResultSet resSet = prStm.executeQuery();
			List<TaskStatusEntity> taskInfoLst = new ArrayList<TaskStatusEntity>();
			
			while(resSet.next()) {
				TaskStatusEntity taskInfo = new TaskStatusEntity();
				TaskEntity task = new TaskEntity();
				StatusEntity status  = new StatusEntity();
				
				task.setName(resSet.getString("task_name"));
				task.setStartDate(resSet.getDate("start_date").toString());
				task.setEndDate(resSet.getDate("end_date").toString());
				status.setName(resSet.getString("status_name"));
				
				taskInfo.setTaskEnt(task);
				taskInfo.setStatusEnt(status);
				
				taskInfoLst.add(taskInfo);
			}
			return taskInfoLst;
		} catch (SQLException e) { 
			System.out.println("Get task info by User ID error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static List<TaskUserJobStatusRoleEntity> getTaskDetails(int taskId) {
		String query = "SELECT t.name AS task_name, t.start_date AS start_date , t.end_date AS end_date , u.fullname AS user, s.name AS status, j.name AS job FROM tasks t\r\n"
				+ "JOIN (SELECT * FROM users) AS u ON t.user_id = u.id\r\n"
				+ "JOIN (SELECT * FROM status) AS s ON t.status_id = s.id \r\n"
				+ "JOIN (SELECT * FROM jobs) AS j ON t.job_id = j.id \r\n"
				+ "WHERE t.id = ?";
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, taskId);
			ResultSet resSet = prStm.executeQuery();
			List<TaskUserJobStatusRoleEntity> taskDetailLst = new ArrayList<TaskUserJobStatusRoleEntity>();
			
			while(resSet.next()) {
				TaskUserJobStatusRoleEntity taskDetail = new TaskUserJobStatusRoleEntity();
				TaskEntity task = new TaskEntity();
				StatusEntity status  = new StatusEntity();
				JobEntity job = new JobEntity();
				UserEntity user = new UserEntity();
				
				task.setName(resSet.getString("task_name"));
				task.setStartDate(resSet.getDate("start_date").toString());
				task.setEndDate(resSet.getDate("end_date").toString());
				status.setName(resSet.getString("status"));
				job.setName(resSet.getString("job"));
				user.setFullName(resSet.getString("user"));
				
				taskDetail.setTaskEnt(task);
				taskDetail.setJobEnt(job);
				taskDetail.setUserEnt(user);
				taskDetail.setStatusEnt(status);
				
				taskDetailLst.add(taskDetail);
			}
			return taskDetailLst;
		} catch (SQLException e) { 
			System.out.println("Get task info by User ID error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static int updateTaskFull(int taskId, String taskName, String jobStart, String jobEnd, int jobId, int userId, int statusId) throws ParseException {
		String query = "UPDATE tasks SET name=?, start_date=?, end_date=?, job_id=?, user_id=?, status_id=? WHERE id=?";
		Date jobStartDate = sdf.parse(jobStart);
		Date jobEndDate = sdf.parse(jobEnd);
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, taskName);
			prStm.setDate(2, new java.sql.Date(jobStartDate.getTime()));
			prStm.setDate(3, new java.sql.Date(jobEndDate.getTime()));
			prStm.setInt(4, jobId);
			prStm.setInt(5, userId);
			prStm.setInt(6, statusId);
			prStm.setInt(7, taskId);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Update task full error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int updateTaskByUser(int taskId, int statusId) throws ParseException {
		String query = "UPDATE tasks SET status_id=? WHERE id=?";

		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, statusId);
			prStm.setInt(2, taskId);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Update task by user error: " + e.getLocalizedMessage());
			return 0;
		}
	}
}
