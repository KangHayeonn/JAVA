package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {


	public Connection connectDB() { // 반환값이 있으면 앞에 자료형을 써줘야함.(Connection이 자료형)
		Connection con = null; // DB 커넥션 연결 객체
		String USERNAME = "Test";
		String PASSWORD = "test1234";
		String URL = "jdbc:mysql://192.168.123.60/test"; // test: db명
		
		try {
			System.out.println("생성자");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("드라이버 로딩 성공");
		 } catch(Exception e) {
			System.out.println("드라이버 로딩 실패");
		 }
		
		 
			
		 
		 return con;
		
	}
	
}
