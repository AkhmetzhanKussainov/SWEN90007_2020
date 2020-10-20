package Concurrency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datasource.DBConnection;

public class ExclusiveLockManager {
	
	private static final String createLock = "INSERT INTO locks (objectID, teacherNumber, lockType) "
			+ "VALUES ('?', '?', '?')";
	
	private static final String checkLock = "SELECT teacherNumber FROM locks WHERE objectID = ? AND teacherNumber = ? AND lockType = ?";
	
	private static final String deleteLock = "DELETE FROM locks WHERE objectID = ? AND teacherNumber = ? AND lockType = ?";
	
	private static ExclusiveLockManager Lock = new ExclusiveLockManager();;
	
	public static ExclusiveLockManager getLock() {	
		return Lock;
	}
	
	
	public boolean acquireLock(String objectID, String teacherNumber, String type) throws Exception {
		
		boolean result = false;
		
		//if the lock has not taken by others, means he could have lock
		if(!hasLock(objectID, teacherNumber, type)) {
			try {
					PreparedStatement stmt = DBConnection.prepare(createLock);
					
					stmt.setString(1, objectID);
					stmt.setString(2, teacherNumber);
					stmt.setString(3, type);
					stmt.executeUpdate();
					
					result = true;
					
					
			} catch (SQLException e) {
				
			}
		}
		
		return result;
	}
	
	
	public boolean releaseLock(String objectID, String teacherNumber, String type) {
		
		boolean result = false;
		
		try {
				PreparedStatement stmt = DBConnection.prepare(deleteLock);
					
				stmt.setString(1, objectID);
				stmt.setString(2, teacherNumber);
				stmt.setString(3, type);
				int r = stmt.executeUpdate();
					
				if(r == 1) {
					result = true;
				}
		} catch (SQLException e) {
				
		}

		return result;
		
	}

	
	public boolean hasLock(String objectID, String teacherNumber, String type) {
		
		boolean result = true;
		
		try {
				PreparedStatement stmt = DBConnection.prepare(checkLock);
					
				stmt.setString(1, objectID);
				stmt.setString(2, teacherNumber);
				stmt.setString(3, type);
				ResultSet rs = stmt.executeQuery();
					
				while(rs.next()) {
					String id = rs.getString(1);
					
					//if the lock taken by others
					if(!id.equals(teacherNumber)) {
						result = false;
					}
					//else the lock is unused or already been taken by self
				}
		} catch (SQLException e) {
				
		}

		return result;
		
	}
	
}
