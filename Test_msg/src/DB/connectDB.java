package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {


	public Connection connectDB() { // ��ȯ���� ������ �տ� �ڷ����� �������.(Connection�� �ڷ���)
		Connection con = null; // DB Ŀ�ؼ� ���� ��ü
		String USERNAME = "test";
		String PASSWORD = "test1234";
		String URL = "jdbc:mysql://127.0.0.1/test"; // test: db��
		
		try {
			System.out.println("������");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("����̹� �ε� ����");
		 } catch(Exception e) {
			System.out.println("����̹� �ε� ����");
		 }
			
		 
		 return con;
		
	}
	
}
