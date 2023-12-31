package 图书借阅管理系统;

import java.sql.*;

public class DataBaseManager {
  Connection con = null;
  ResultSet rs = null;
  Statement stmt;
  
  public DataBaseManager() {
    try {
      String url = "jdbc:sqlserver://localhost:1434;DatabaseName=LibraryBase";
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      //String url = "jdbc:odbc:LibraryBase";
      //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      //con = DriverManager.getConnection(url);
	  String user="张威浩";
	  String password="123";
	  Connection con=DriverManager.getConnection(url,user,password);//连接数据库对象
      stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_UPDATABLE);
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
    catch (ClassNotFoundException cnfex) {
      cnfex.printStackTrace();
    }
  }

 
  
  public ResultSet getResult(String strSQL) {
    try {
      rs = stmt.executeQuery(strSQL);
      return rs;
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
      return null;
    }

  }

  public int updateSql(String strSQL) {
    try {   
     int i = stmt.executeUpdate(strSQL);
      con.commit();   
      return i;
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
      return -1;
    }
  }

  public void closeConnection() {
    try {
      con.close();
    }
    catch (SQLException sqle) {
      System.out.println(sqle.toString());
    }
  }

}
