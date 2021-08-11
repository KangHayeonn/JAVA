package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

//import test.GetterSetter;

public class drimstudyDB {
	
	//String color;
	//String cheertext;
	
	connectDB cDB = null;
	Connection con = null;
	
	public drimstudyDB() {
		//public Connection con; // DB 커넥션 연결 객체
		cDB = new connectDB();
		con = cDB.connectDB();
		
		/*
		GetterSetter s = new GetterSetter(color, cheertext);
	 	
		String color = s.getColor();
		String cheertext = s.getCheertext();
	
	*/  
		
	}
	
	public void sendDB(String color, String cheertext) {
		
		try {
	
			String query = "INSERT INTO test1(bgcolor, cheertext) VALUES('" + color + "','" + cheertext + "')";
			
			JOptionPane.showMessageDialog
            (null, "응원메시지 등록완료!", "CHEER MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("배경색: " + color + " / 내용: '" + cheertext + "' 가 " + "DB로 전송되었습니다!");
			Statement state = con.createStatement();
			
			int x = state.executeUpdate(query);
			System.out.println("보내진 query문 : " + x);
			
			query= "SELECT * from test1";
			ResultSet rs = state.executeQuery(query);
			
			try {
				rs.close();
				state.close();
				con.close();
			 } catch (SQLException e1) { }
			
		} catch(Exception e) {
			System.out.println("ERROR : query문 DB로 전송 실패");
		    e.printStackTrace();
		}
}
	
	
	
}
