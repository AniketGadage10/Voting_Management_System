package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbutils {

	public static Connection cn;
	
	public static Connection getconnection() throws SQLException
	{
		String p="jdbc:mysql://localhost:3306/aniket1?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		return cn=DriverManager.getConnection(p,"root","1810");
	}
	
	public static void closeconnection() throws SQLException
	{
		if(cn!=null)
		{
			cn.close();
		}
	}
}
	
