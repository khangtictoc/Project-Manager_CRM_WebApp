package udemy.groupentity;

import udemy.entity.UserEntity;
import udemy.entity.TaskEntity;
import udemy.entity.JobEntity;
import udemy.entity.StatusEntity;

public class TaskUserJobEntity {
	private UserEntity user;
	private TaskEntity task;
	private JobEntity job;
	private StatusEntity status;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public TaskEntity getTask() {
		return task;
	}
	public void setTask(TaskEntity task) {
		this.task = task;
	}
	public JobEntity getJob() {
		return job;
	}
	public void setJob(JobEntity job) {
		this.job = job;
	}
	public StatusEntity getStatus() {
		return status;
	}
	public void setStatus(StatusEntity status) {
		this.status = status;
	}
	
	
	
	
}
