package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcdemo1 {

	public static void main(String[] args) {
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		  String dbURL="jdbc:sqlserver://localhost:1434;DatabaseName=LibraryBase";//������д������ݿ����֡��������½�����Test��������Test
		  String userName="������";//������д��ĵ�½���ݿ��������װ��Ĭ����sa
		  String userPwd="123";//������д��ĵ�½���ݿ������
		  try{
			    Class.forName(driverName);
			    System.out.println("���������ɹ���");
		  }
		  catch(Exception e){
			    e.printStackTrace();
			    System.out.println("��������ʧ�ܣ�");
		  }
		  try{
			    Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			        System.out.println("�������ݿ�ɹ���");
		  }
		  catch(Exception e){
			    e.printStackTrace();
			    System.out.print("SQL Server����ʧ�ܣ�");
		  }       	
		
	}
	
}

