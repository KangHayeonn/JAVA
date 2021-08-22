package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JOptionPane;

import test.MsgVO;

//import test.GetterSetter;

public class drimstudyDB {
	
	//String color; //게터를 이용해서 데이터를 사용해 줄것이므로 이부분을 지움
	//String cheertext;
	
	connectDB cDB = null;
	Connection con = null;
	
	//ArrayList<GetterSetter> ar = new ArrayList<GetterSetter>(); // 게터를 부를때는 이부분이 필요없음
	public drimstudyDB() {
		//public Connection con; // DB 커넥션 연결 객체
		cDB = new connectDB();
		con = cDB.connectDB();
		if(con==null) {
			System.out.println("안녕하세요 오류입니다.");
		}
		if(cDB ==null) {
			System.out.println("cDB null 입니다.");
		}

	}
	
	public void sendDB(MsgVO s) { // MsgVO 에서 data를 GET 해서 사용함
		
		try {
	
			String query = "INSERT INTO test1(bgcolor, cheertext) VALUES('" + s.getColor() + "','" + s.getCheertext() + "')";
			
			JOptionPane.showMessageDialog
            (null, "응원메시지 등록완료!", "CHEER MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("배경색: " + s.getColor() + " / 내용: '" + s.getCheertext() + "' 가 " + "DB로 전송되었습니다!");
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
