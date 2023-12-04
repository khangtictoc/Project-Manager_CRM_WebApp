package udemy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import udemy.config.MysqlConfig;
import udemy.entity.StatusEntity;

public class StatusRepository {
	public static List<StatusEntity> getAllStatus() {
		String query = "SELECT * FROM status";
		Connection conn = MysqlConfig.initConnection();
		try {
			PreparedStatement prStm = conn.prepareStatement(query);
			ResultSet resSet = prStm.executeQuery();
			List<StatusEntity> statLst = new ArrayList<StatusEntity>();
			
			while(resSet.next()) {
				StatusEntity stat = new StatusEntity();
				stat.setId(resSet.getInt("id"));
				stat.setName(resSet.getString("name"));
				statLst.add(stat);
			}
			return statLst;
		} catch (SQLException e) { 
			System.out.println("Retrieve job info error: " + e.getLocalizedMessage());
			return null;
		}
	}
}
