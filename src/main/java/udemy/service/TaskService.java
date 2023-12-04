package udemy.service;

import java.text.ParseException;
import java.util.List;

import udemy.repository.TaskRepository;
import udemy.groupentity.TaskStatusEntity;
import udemy.groupentity.TaskUserJobEntity;
import udemy.groupentity.TaskUserJobStatusRoleEntity;

public class TaskService {
	public static List<TaskUserJobEntity> getFullTasksInfo(){
		return  TaskRepository.getAllTasksInfo();
	}
	
	public static List<TaskStatusEntity> getTaskByUserId(int userId){
		return TaskRepository.getTasksByUserId(userId);
	}
	
	public static List<TaskUserJobStatusRoleEntity> getTaskDetails(int userId){
		return TaskRepository.getTaskDetails(userId);
	}
	
	public static boolean TaskAddVerify(String taskName, String jobName, String userFullname, String taskStart, String taskEnd) throws ParseException {
		int numrow = TaskRepository.AddTask(taskName, jobName, userFullname, taskStart, taskEnd);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean TaskUpdateFullVerfiy(int taskId, String taskName, String start, String end, int jobId, int userId, int statusId) throws ParseException {
		int numrow = TaskRepository.updateTaskFull(taskId, taskName, start, end, jobId, userId, statusId);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean TaskUpdateByUserVerfiy(int taskId, int statusId) throws ParseException {
		int numrow = TaskRepository.updateTaskByUser(taskId, statusId);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean TaskDelVerify(int taskId) {
		int numrow = TaskRepository.delTask(taskId);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
