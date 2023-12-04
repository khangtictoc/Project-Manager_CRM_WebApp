package udemy.groupentity;

import udemy.entity.StatusEntity;
import udemy.entity.TaskEntity;

public class TaskStatusEntity {
	private TaskEntity taskEnt;
	private StatusEntity statusEnt;
	
	public TaskEntity getTaskEnt() {
		return taskEnt;
	}
	public void setTaskEnt(TaskEntity taskEnt) {
		this.taskEnt = taskEnt;
	}
	public StatusEntity getStatusEnt() {
		return statusEnt;
	}
	public void setStatusEnt(StatusEntity statusEnt) {
		this.statusEnt = statusEnt;
	}
}
