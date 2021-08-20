package tablepagination;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DB.connectDB;

public class SubPanel2 extends JPanel {

		private JTable table;
		private JScrollPane jscp1; //스크롤바
		
		connectDB cDB = null;
		Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private MouseListener ml = null;
		
		private String header[] = {"제목", "내용", "작성자", "작성기간"};
    	private DefaultTableModel model = new DefaultTableModel(header, 0){ 
    									public boolean isCellEditable(int i, int c){
    										return false; } };
    										
    	public int now ;
    	JTextField s1;
    
    
   // 테이블 데이터 모델 객체 생성

		
	public SubPanel2(JFrame frame) {
		super();
		cDB = new connectDB();
		PanelInit(frame);
		Search_InputBox();
		select();
	}

	private void PanelInit(JFrame frame) {

		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1280, 300);
		this.setLayout(null);

		// Table
   
        table = new JTable(model);
        //table.setLocation(0,0);
        
        jscp1 = new JScrollPane(table); //이런식으로 생성시에 테이블을 넘겨주어야 정상적으로 볼 수 있다.
                                                    //jscp1.add(table); 과 같이 실행하면, 정상적으로 출력되지 않음.
        jscp1.setLocation(70,40);
        jscp1.setSize(1060,315);
       
        //mainPanel.add(jscp1);  // 이 부분을 해줘야 Panel위에 표를 추가할 수 있음.
        this.add(jscp1);

		//테이블 행, 열 크기 조절
        //table.getRow(header).setPreferredHi
		table.setRowHeight(28);
		table.getColumn(header[0]).setPreferredWidth(200);
		table.getColumn(header[1]).setPreferredWidth(400);
		table.getColumn(header[2]).setPreferredWidth(200);
		table.getColumn(header[3]).setPreferredWidth(200);
		
		table.getTableHeader().setBackground(new Color(12, 12, 12));
		table.getTableHeader().setFont(new Font("맑은고딕", Font.BOLD, 16)); 
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setPreferredSize(new Dimension(1,32));
		
		//테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); //디폴트테이블셀렌더러 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); //렌더러의 가로정렬 CENTER
		TableColumnModel tcm = table.getColumnModel(); //정렬할 테이블의 컬럼모델 가져오기
		
		for(int i = 0;i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr); // 각각의 셀렌더러를 dtcr에 set
		}
		
		//////////////////////////////////////////////////////////////////
		
		/*
		// 글쓰기 버튼
		JButton writebtn = new JButton("글쓰기");
		writebtn.setFont(new Font("맑은고딕", Font.BOLD, 13));
		writebtn.setBackground(new Color(217, 217, 217));
		writebtn.setBounds(1055,365, 75, 30);
		writebtn.setBorderPainted(false);
		writebtn.setFocusPainted(false);
		this.add(writebtn);
		
		writebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		writebtn.addMouseListener(new java.awt.event.MouseAdapter() {
	          public void mouseEntered(java.awt.event.MouseEvent evt) {
	             writebtn.setBackground(Color.black);
	             writebtn.setForeground(Color.white);
	             //player_Btn.setBorderPainted(true);
	          }
	          public void mouseExited(java.awt.event.MouseEvent evt) {
	             writebtn.setBackground(new Color(217, 217, 217));
	             writebtn.setForeground(Color.black);
	          }
       });
       */
		
	}
	private void select() {
		String query = "select title, content, writer, wridate from user_info Limit 0,10;";
		
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
				//column 갯수
		        pagination();
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {}
		}
	}
	
	private void pagination() {
		//column 갯수
        System.out.println(table.getRowCount());
       
        final int PAGES_PER_BLOCK = 10;
        final int POST_PER_PAGE = 10;

        // 인스턴스 생성
        tablepagination paginator;
        
		
        
        String count = "select count(*) as cnt from user_info;";
		
		try {
			con = cDB.connectDB();
			pstmt = con.prepareStatement(count);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			
			while(rs.next()) {
				String countData = rs.getString("cnt");
				
				
				System.out.println("이부분 출력되나??"+countData);
				
		        // 총 게시글 수
		        int totalPostCount = Integer.parseInt(countData);
		        paginator= new tablepagination(PAGES_PER_BLOCK, POST_PER_PAGE, totalPostCount);
		        
		        System.out.println(PAGES_PER_BLOCK+"/"+POST_PER_PAGE+"/"+totalPostCount);
		      
		        try {
		        	System.out.println(paginator.getPagesPerBlock());
		        	System.out.println(paginator.getPostsPerPage());
		        	System.out.println(paginator.getTotalPostCount());
		            for(int i = 1; i <= paginator.getTotalLastPageNum(); i++) {
		                System.out.println(i);
		            }
		        } catch (Exception e) {
		            System.err.println(e);
		        }
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
        
        JButton btnfirst = new JButton("<<");
        JButton btnprev = new JButton("<");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btn10 = new JButton("10");
        JButton btnnext = new JButton(">");
        JButton btnlast = new JButton(">>");
        
        
        btnfirst.setBounds(239, 400, 60, 30);
		btnfirst.setBackground(Color.black);
		btnfirst.setForeground(Color.white);
		
		btnprev.setBounds(299, 400, 50, 30);
		btnprev.setBackground(Color.black);
		btnprev.setForeground(Color.white);
		
		btn1.setBounds(360, 400, 48, 30);
		btn1.setBackground(Color.black);
		btn1.setForeground(Color.white);
		
		btn2.setBounds(410, 400, 48, 30);
		btn2.setBackground(Color.black);
		btn2.setForeground(Color.white);
		
		btn3.setBounds(460, 400, 48, 30);
		btn3.setBackground(Color.black);
		btn3.setForeground(Color.white);
		
		btn4.setBounds(510, 400, 48, 30);
		btn4.setBackground(Color.black);
		btn4.setForeground(Color.white);
		
		btn5.setBounds(560, 400, 48, 30);
		btn5.setBackground(Color.black);
		btn5.setForeground(Color.white);
		
		btn6.setBounds(610, 400, 48, 30);
		btn6.setBackground(Color.black);
		btn6.setForeground(Color.white);
		
		btn7.setBounds(660, 400, 48, 30);
		btn7.setBackground(Color.black);
		btn7.setForeground(Color.white);
		
		btn8.setBounds(710, 400, 48, 30);
		btn8.setBackground(Color.black);
		btn8.setForeground(Color.white);
		
		btn9.setBounds(760, 400, 48, 30);
		btn9.setBackground(Color.black);
		btn9.setForeground(Color.white);
		
		btn10.setBounds(810, 400, 48, 30);
		btn10.setBackground(Color.black);
		btn10.setForeground(Color.white);
		
		btnnext.setBounds(870, 400, 50, 30);
		btnnext.setBackground(Color.black);
		btnnext.setForeground(Color.white);
		
		btnlast.setBounds(920, 400, 60, 30);
		btnlast.setBackground(Color.black);
		btnlast.setForeground(Color.white);
		
		this.add(btnfirst);
		this.add(btnprev);
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
		this.add(btn6);
		this.add(btn7);
		this.add(btn8);
		this.add(btn9);
		this.add(btn10);
		this.add(btnnext);
		this.add(btnlast);
		
		btnfirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =1;
				btn_active(now);
			}
		});
		
		btnprev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("prev:"+now);
				if(now==1) now=1;
				else now = now-1;
				System.out.println("prev:"+now);
				btn_active(now);
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =1;
				System.out.println("1:"+now);
				btn_active(now);
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =2;
				System.out.println("2:"+now);
				btn_active(now);
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =3;
				btn_active(now);
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =4;
				btn_active(now);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =5;
				btn_active(now);
			}
		});
		
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =6;
				btn_active(now);
			}
		});
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =7;
				btn_active(now);
			}
		});
		
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =8;
				btn_active(now);
			}
		});
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =9;
				btn_active(now);
			}
		});
		
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =10;
				btn_active(now);
			}
		});
		
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(now==10) now=10;
				else now = now+1;
				btn_active(now);
			}
		});
		
		btnlast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				now =10;
				btn_active(now);
			}
		});
		/*
		//map 선언
		Map<String, String> btn = new HashMap<String, String>();
		 
		//반복분
		for(int i=1; i<11; i++)
		{
		    //원하는 변수명으로 해당 변수를 선언한 다음 map에다 추가
		    btn.put("btn", "btn"+Integer.toString(i));
		    
		    //변수 사용할 땐 이름으로 바로 호출
		    System.out.println(btn.get("btn"));
		}*/
	}
	
	// 내식대로 pagination.. (구글 코드 포기)
	private void btn_active(int count) {
		model.setNumRows(0); // 테이블 초기화
		
		String query2 = "select * from user_info Limit "+0+(count-1)*10+","+10+";";
		
		try {
			con = cDB.connectDB();
			pstmt = con.prepareStatement(query2);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
			
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String wridate = rs.getString("wridate");
				
				Object data[] = {title, content, writer, wridate};
				
				model.addRow(data);
				System.out.println(title +", "+ content+ ", "+ writer+", "+wridate);
				
			}
		} catch(Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch(Exception e2) {}
		}
	}
	
	// 검색어 입력
	private void Search_InputBox() {
		// 이름 입력
		s1 = new JTextField("   검색어를 입력하시오.");
		s1.setBounds(820, 0, 240, 30);

		this.add(s1);
		
		s1.addMouseListener(new MouseAdapter(){
	          @Override
	          public void mouseClicked(MouseEvent e){
	             s1.setText("");
	          }
	    });
		// input 결과물 출력
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(1060, 0, 68, 30);
		btnSearch.setBackground(Color.black);
		btnSearch.setForeground(Color.white);
		
		this.add(btnSearch);
				
				
		// 적용버튼 리스너
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search;

				search = (String) s1.getText();
				
				// 임시 출력
				System.out.println(search);
				
				model.setNumRows(0);
				
				String query2 = "SELECT * FROM user_info WHERE content LIKE '%"+search+"%';";
				
				try {
					con = cDB.connectDB();
					pstmt = con.prepareStatement(query2);
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
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						s1.setText("   검색어를 입력하시오.");
						rs.close();
						pstmt.close();
						con.close();
					} catch(Exception e2) {}
				}

			}
		});
	}
}