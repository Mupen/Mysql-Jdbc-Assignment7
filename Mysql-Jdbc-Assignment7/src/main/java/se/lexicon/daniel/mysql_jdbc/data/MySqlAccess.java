package se.lexicon.daniel.mysql_jdbc.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlAccess {
	
	private static final String Url = "jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC";
	private static final String User = "root";
	private static final String Password = "Per123cp74021MySqlRoot";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(Url, User, Password);
	}
}