package udemy.groupentity;

import udemy.entity.JobEntity;
import udemy.entity.PrivilegeEntity;
import udemy.entity.StatusEntity;
import udemy.entity.TaskEntity;
import udemy.entity.UserEntity;

public class TaskUserJobStatusRoleEntity {
	
	private TaskEntity taskEnt;
	private UserEntity userEnt;
	private PrivilegeEntity roleEnt;
	private JobEntity jobEnt;
	private StatusEntity statusEnt;
	
	public TaskEntity getTaskEnt() {
		return taskEnt;
	}
	public void setTaskEnt(TaskEntity taskEnt) {
		this.taskEnt = taskEnt;
	}
	public UserEntity getUserEnt() {
		return userEnt;
	}
	public void setUserEnt(UserEntity userEnt) {
		this.userEnt = userEnt;
	}
	public PrivilegeEntity getRoleEnt() {
		return roleEnt;
	}
	public void setRoleEnt(PrivilegeEntity roleEnt) {
		this.roleEnt = roleEnt;
	}
	public JobEntity getJobEnt() {
		return jobEnt;
	}
	public void setJobEnt(JobEntity jobEnt) {
		this.jobEnt = jobEnt;
	}
	public StatusEntity getStatusEnt() {
		return statusEnt;
	}
	public void setStatusEnt(StatusEntity statusEnt) {
		this.statusEnt = statusEnt;
	}
}
