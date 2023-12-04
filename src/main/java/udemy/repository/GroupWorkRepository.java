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
import udemy.entity.JobEntity;

public class GroupWorkRepository {
	private Connection conn = MysqlConfig.initConnection();
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static int AddJob(String jobName, String jobStart, String jobEnd) throws ParseException {
		String query = "INSERT INTO jobs(name, start_date, end_date) VALUES(?, ?, ?)";
		
		Date jobStartDate = sdf.parse(jobStart);
		Date jobEndDate = sdf.parse(jobEnd);
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, jobName);
			prStm.setDate(2, new java.sql.Date(jobStartDate.getTime()));
			prStm.setDate(3, new java.sql.Date(jobEndDate.getTime()));
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Add job error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int updateJob(int jobId, String jobName, String jobStart, String jobEnd) throws ParseException {
		String query = "UPDATE jobs SET name=?, start_date=?, end_date=? WHERE id=?";
		Date jobStartDate = sdf.parse(jobStart);
		Date jobEndDate = sdf.parse(jobEnd);
		
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, jobName);
			prStm.setDate(2, new java.sql.Date(jobStartDate.getTime()));
			prStm.setDate(3, new java.sql.Date(jobEndDate.getTime()));
			prStm.setInt(4, jobId);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Update job error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static int DelJob(int jobid) {
		String query = "DELETE FROM jobs WHERE id=?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setInt(1, jobid);
			int numrow = prStm.executeUpdate();
			return numrow;
		} catch (SQLException e) { 
			System.out.println("Delete job error: " + e.getLocalizedMessage());
			return 0;
		}
	}
	
	public static List<JobEntity> getAllJobs() {
		String query = "SELECT * FROM jobs";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			ResultSet resSet = prStm.executeQuery();
			List<JobEntity> jobLst = new ArrayList<JobEntity>();
			
			while(resSet.next()) {
				JobEntity job = new JobEntity();
				job.setId(resSet.getInt("id"));
				job.setName(resSet.getString("name"));
				job.setStartDate(resSet.getDate("start_date").toString());
				job.setEndDate(resSet.getDate("end_date").toString());
				jobLst.add(job);
			}
			return jobLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve job info error: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static int getJobId(String jobName) {
		String query = "SELECT id FROM jobs WHERE name = ?";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			prStm.setString(1, jobName);
			ResultSet resSet = prStm.executeQuery();
			int jobId =0 ;
			while(resSet.next()) {
				jobId = resSet.getInt("id");
			}
			return jobId;
		} catch (SQLException e) { 
			System.out.println("Retrieve job id error: " + e.getLocalizedMessage());
			return -1;
		}
	}
}
