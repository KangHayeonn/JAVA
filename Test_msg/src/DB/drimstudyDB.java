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
		//public Connection con; // DB Ŀ�ؼ� ���� ��ü
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
            (null, "�����޽��� ��ϿϷ�!", "CHEER MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("����: " + color + " / ����: '" + cheertext + "' �� " + "DB�� ���۵Ǿ����ϴ�!");
			Statement state = con.createStatement();
			
			int x = state.executeUpdate(query);
			System.out.println("������ query�� : " + x);
			
			query= "SELECT * from test1";
			ResultSet rs = state.executeQuery(query);
			
			try {
				rs.close();
				state.close();
				con.close();
			 } catch (SQLException e1) { }
			
		} catch(Exception e) {
			System.out.println("ERROR : query�� DB�� ���� ����");
		    e.printStackTrace();
		}
}
	
	
	
}
