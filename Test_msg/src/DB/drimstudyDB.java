package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JOptionPane;

import test.MsgVO;

//import test.GetterSetter;

public class drimstudyDB {
	
	//String color; //���͸� �̿��ؼ� �����͸� ����� �ٰ��̹Ƿ� �̺κ��� ����
	//String cheertext;
	
	connectDB cDB = null;
	Connection con = null;
	
	//ArrayList<GetterSetter> ar = new ArrayList<GetterSetter>(); // ���͸� �θ����� �̺κ��� �ʿ����
	public drimstudyDB() {
		//public Connection con; // DB Ŀ�ؼ� ���� ��ü
		cDB = new connectDB();
		con = cDB.connectDB();
		if(con==null) {
			System.out.println("�ȳ��ϼ��� �����Դϴ�.");
		}
		if(cDB ==null) {
			System.out.println("cDB null �Դϴ�.");
		}

	}
	
	public void sendDB(MsgVO s) { // MsgVO ���� data�� GET �ؼ� �����
		
		try {
	
			String query = "INSERT INTO test1(bgcolor, cheertext) VALUES('" + s.getColor() + "','" + s.getCheertext() + "')";
			
			JOptionPane.showMessageDialog
            (null, "�����޽��� ��ϿϷ�!", "CHEER MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("����: " + s.getColor() + " / ����: '" + s.getCheertext() + "' �� " + "DB�� ���۵Ǿ����ϴ�!");
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
