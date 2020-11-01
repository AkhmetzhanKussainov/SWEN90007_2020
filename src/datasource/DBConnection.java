package datasource;

import java.sql.*;

public class DBConnection {
	//change the db settings as your environment
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/robertDB";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "3158";


	public static PreparedStatement prepare(String stm) throws SQLException {

		PreparedStatement preparedStatement = null;
		try {

			Connection dbConnection = getDBConnection();

			preparedStatement = dbConnection.prepareStatement(stm);

		} catch (SQLException e) {

			System.out.println(e.getMessage());


		}

		return preparedStatement;
	}
	private static Connection getDBConnection() {



		try {
			DriverManager.registerDriver(new org.postgresql.Driver());

			/*Connection dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER,DB_PASSWORD);*/
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			Connection dbConnection = DriverManager.getConnection(dbUrl);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		System.out.println("Connection problem");
		return null;

	}


}