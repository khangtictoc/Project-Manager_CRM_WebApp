package udemy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import udemy.config.MysqlConfig;
import udemy.entity.JobEntity;
import udemy.repository.GroupWorkRepository;
import udemy.repository.LoginRepository;

public class GroupWorkService {
	public static boolean JobAddVerify(String jobName, String jobStart, String jobEnd) throws ParseException {
		int numrow = GroupWorkRepository.AddJob(jobName, jobStart, jobEnd);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean JobUpdateVerfiy(int jobId, String jobName, String start, String end) throws ParseException {
		int numrow = GroupWorkRepository.updateJob(jobId, jobName, start, end);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean JobDelVerify(int roleid) {
		int numrow = GroupWorkRepository.DelJob(roleid);
		if (numrow > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static List<JobEntity> getAllJob(){
		return  GroupWorkRepository.getAllJobs();
	}

}
