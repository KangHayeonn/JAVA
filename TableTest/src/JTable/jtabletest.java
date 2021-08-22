package JTable;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.connectDB;

public class jtabletest extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow = null;
	private JButton jBtnSaveRow = null;
	private JButton jBtnEditRow = null;
	private JButton jBtnDelRow = null;
	private JTable table;
	private JScrollPane scrollPane; //스크롤바
	
	private String colNames[] = {"제목", "내용", "작성자", "작성기간"};
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); // 테이블 데이터 모델 객체 생성
	
	connectDB cDB = null;
	Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private MouseListener ml = null;
	
	public jtabletest() {
		cDB = new connectDB();
		ml = new JTableMouseListener();
		setLayout(null);
		table = new JTable(model);
		table.addMouseListener(ml);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(500,200);
		add(scrollPane);
		initialize();
		select();
		
	}
	
	
	private void select() {
		String query = "select title, content, writer, wridate from user_info";
		
		try {
			con = cDB.connectDB();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String wridate = rs.getString("wridate");
				
				Object data[] = {title, content, writer, wridate};
				
				model.addRow(data);
				System.out.println(title +", "+ content+ ", "+ writer+", "+wridate);
				/*
				model.addRow(new Object[] {rs.getString("title"), rs.getString("content"),
											rs.getString("writer"), rs.getString("wridate")});*/
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {}
		}
	}
	
	private void initialize() {
		
		jBtnAddRow = new JButton();
		jBtnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel)table.getModel();
				model2.addRow(new String[]{"","","",""});
			}
		});
		jBtnAddRow.setBounds(30,222,120,25);
		jBtnAddRow.setText("추가");
		add(jBtnAddRow);
		
		jBtnSaveRow = new JButton();
		jBtnSaveRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "insert into user_info(title, content, writer, wridate)" 
				+ "values(?,?,?,"+null+")";
				
				try {
					con = cDB.connectDB();
					pstmt =  con.prepareStatement(query);
					
					pstmt.setString(1,(String) model2.getValueAt(row, 0));
					pstmt.setString(2,(String) model2.getValueAt(row, 1));
					pstmt.setString(3,(String) model2.getValueAt(row, 2));
					//pstmt.setString(4,(String) model2.getValueAt(row, 3));
					
					int cnt = pstmt.executeUpdate();
		
				} catch(Exception eeee) {
					System.out.println(eeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch(Exception e2) {}
				}
				model2.setRowCount(0);
				select();
			}
		});
		jBtnSaveRow.setBounds(182,222,120,25);
		jBtnSaveRow.setText("저장");
		add(jBtnSaveRow);
		
		jBtnEditRow = new JButton();
		jBtnEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				
				String query = "UPDATE user_info SET content=?, writer=?, wridate" + "where id=?";
						
					try {
						con = cDB.connectDB();
						pstmt = con.prepareStatement(query);
						
						pstmt.setString(1, (String) model2.getValueAt(row, 1));
						pstmt.setString(1, (String) model2.getValueAt(row, 2));
						pstmt.setString(1, (String) model2.getValueAt(row, 0));
						
						int cnt = pstmt.executeUpdate();
					} catch(Exception eeee) {
						System.out.println(eeee.getMessage());
					} finally {
						try {
							pstmt.close();
							con.close();
						} catch (Exception e2) {}
					}
					model2.setRowCount(0);
					select();
			}
		});
		
		jBtnEditRow.setBounds(182,270,120,25);
		jBtnEditRow.setText("수정");
		add(jBtnEditRow);
		
		jBtnDelRow = new JButton();
		jBtnDelRow.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel)table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return;
				String query = "delete from user_info where id=?";
				
				try {
					con = cDB.connectDB();
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1,  (String)model2.getValueAt(row, 0));
					int cnt = pstmt.executeUpdate();
				} catch(Exception eeee) {
					System.out.println(eeee.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch(Exception e2) {}
				}
				model2.removeRow(row);
			}
		});
		
		jBtnDelRow.setBounds(new Rectangle(320,222,120,25));
		jBtnDelRow.setText("삭제");
		add(jBtnDelRow);
	}
	
	public static void main(String[] args) {
		
		jtabletest panel = new jtabletest();
		JFrame win = new JFrame();
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		win.add(panel);
		win.setSize(540,400);
		win.setVisible(true);
	}
}
