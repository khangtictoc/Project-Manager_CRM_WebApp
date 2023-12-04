package udemy.service;

import java.util.List;

import udemy.entity.StatusEntity;
import udemy.repository.StatusRepository;

public class StatusService {
	public static List<StatusEntity> getAllStatus(){
		return  StatusRepository.getAllStatus();
	}
}
