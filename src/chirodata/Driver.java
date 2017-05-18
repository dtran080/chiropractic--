package chirodata;
import java.sql.*;
import java.util.*;

public class Driver {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{ 
		String sDriverName = "org.sqlite.JDBC";
	    Class.forName(sDriverName);
	
	    // now we set up a set of fairly basic string variables to use in the body of the code proper
	    String sTempDb = "chiro1.sqlite";
	    String sJdbc = "jdbc:sqlite";
	    String sDbUrl = sJdbc + ":" + sTempDb;
	    // which will produce a legitimate Url for SqlLite JDBC :
	    // jdbc:sqlite:hello.db
	    int iTimeout = 30;
	  /*  String sMakeTable = "CREATE TABLE dummy (id numeric, response text)";
	    String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
	    String sMakeSelect = "SELECT response from dummy";*/
	    String sql = "Select * from chiro;";
	
	    // create a database connection
	    Connection conn = DriverManager.getConnection(sDbUrl);
	    try {
	        Statement stmt = conn.createStatement();
	        try {
	            stmt.setQueryTimeout(iTimeout);
	            ResultSet rs = stmt.executeQuery(sql);
	            try {
	                while(rs.next())
	                    {
	                        String sResult = rs.getString("URL");
	                        System.out.println(sResult);
	                    }
	            } finally {
	                try { rs.close(); } catch (Exception ignore) {}
	            }
	        } finally {
	            try { stmt.close(); } catch (Exception ignore) {}
	        }
	    } finally {
	        try { conn.close(); } catch (Exception ignore) {}
	    }
	}
}

