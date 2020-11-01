	package Concurrency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datasource.DBConnection;

public class ExclusiveLockManager {
	
	private static final String createLock = "INSERT INTO locks (objectID, sessionID, locktype) VALUES (?, ?, ?)";
	
	private static final String checkLock = "SELECT sessionID FROM locks WHERE objectID = ? AND lockType = ?";
	
	private static final String deleteLock = "DELETE FROM locks WHERE objectID = ? AND sessionID = ? AND lockType = ?";
	
	private static final String resetAll = "DELETE FROM locks";
	private static final String deleteAllLock = "DELETE FROM locks WHERE sessionID = ?";	
	
	//New code - Fanyu
	public boolean releaseAllLocksForUser(String sessionID) {
		boolean result = false;
		
		try {
				PreparedStatement stmt = DBConnection.prepare(deleteAllLock);				
				stmt.setString(1, sessionID);
				
				int r = stmt.executeUpdate();
					
				if(r == 1) {
					result = true;
				}
		} catch (SQLException e) {
				
		}		
		return result;
	}
	
	//New code - Rob
	public boolean releaseAllLocks() {
		boolean result = false;
		
		try {
				PreparedStatement stmt = DBConnection.prepare(resetAll);				
				
				
				int r = stmt.executeUpdate();
					
				if(r == 1) {
					result = true;
				}
		} catch (SQLException e) {
				
		}		
		return result;
	}
			
	private static ExclusiveLockManager Lock = new ExclusiveLockManager();
	
	public static ExclusiveLockManager getLock() {	
		return Lock;
	}
	
	
	public boolean acquireLock(String objectID, String sessionID, String type) throws Exception {
		
		boolean result = false;
		
		//if the lock has not taken by others, means he could have lock
		if(!hasLock(objectID, sessionID, type)) {
			try {
					PreparedStatement stmt = DBConnection.prepare(createLock);
					System.out.println("acquireLock start");
					stmt.setString(1, objectID);
					System.out.println(objectID);
					stmt.setString(2, sessionID);
					System.out.println(sessionID);
					stmt.setString(3, type);
					stmt.executeUpdate();
					
					
					result = true;
					
					
			} catch (SQLException e) {
				System.out.println(e);
			}
		}else System.out.println("lock has taken by others");
		
		return result;
	}
	
	
	public boolean releaseLock(String objectID, String sessionID, String type) {
		
		boolean result = false;
		
		try {
				PreparedStatement stmt = DBConnection.prepare(deleteLock);
					
				stmt.setString(1, objectID);
				stmt.setString(2, sessionID);
				stmt.setString(3, type);
				int r = stmt.executeUpdate();
					
				if(r == 1) {
					result = true;
				}
		} catch (SQLException e) {
				
		}

		return result;
		
	}

	
	public boolean hasLock(String objectID, String sessionID, String type) {
		
		boolean result = false;
		System.out.println("lock checking");
		
		try {
				PreparedStatement stmt = DBConnection.prepare(checkLock);
					
				stmt.setString(1, objectID);
				stmt.setString(2, type);
				ResultSet rs = stmt.executeQuery();
					
				while(rs.next()) {
					String id = rs.getString(1);
					
					//if the lock taken by others
					if(!id.equals(sessionID)) {
						result = true;
						System.out.println("someone took the lock");
						return result;
					}
					//else the lock is unused or already been taken by self
				}
				System.out.println("no one has the lock");
		} catch (Exception e) {
				System.out.println(e);
		}

		return result;
		
	}
	
}
