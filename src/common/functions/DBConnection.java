package common.functions;

import java.sql.*;

public class DBConnection {
	private Connection conn = null;
	
	public DBConnection(String Host, String DB, String User, String PassWord, String DBEngine){
		
		
		if(DBEngine == "Mssql"){
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://"+Host+";databaseName="+DB+";user="+User+";password="+PassWord+";");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+Host+"/"+DB,User,PassWord);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public ResultSet RunSql(String sql) throws SQLException {
		Statement stmt = this.conn.createStatement();
        ResultSet rs;

        rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public void closecon(){
		if (this.conn != null) {
			try { this.conn.close(); } catch(Exception e) {}
		}
	}
	
}
