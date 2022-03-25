  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DataBaseConnection {
	private static Connection conn = null;
	private DataBaseConnection() {}
	static Logger log = Logger.getLogger(DataBaseConnection.class.getName());
	static
	{
		String url = "jdbc:mysql://localhost:3306/pagination";
		String user = "root";
		String password = "Jiyani@123.";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(url,user,password);
		    log.info("Connection Successful....");
		}
		catch (ClassNotFoundException | SQLException e) {
			 log.error(e);
		}
	}
	 public static Connection getConnection()
	    {
			return conn;
	    }
}
