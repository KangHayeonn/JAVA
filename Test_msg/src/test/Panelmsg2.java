package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.connectDB;
import DB.drimstudyDB;



public class Panelmsg2 extends JPanel {

	
	int count =0;
	String color = null;
	
	public Panelmsg2(JFrame frame) {
		super();
		panelInit(frame);
	}

	private void panelInit(JFrame frame) {
		
		// 메인 화면 패널
		this.setBackground(new Color(255, 255, 255));
	    this.setBounds(0, 0, 1280, 960);
	    this.setLayout(null);
	    
	    
	    // Title
 		JLabel tilteLabel = new JLabel("응원 메시지 남기기");
 		tilteLabel.setBounds(300, 40, 480, 40);

 		tilteLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24)); // Font

 		tilteLabel.setForeground(Color.white); // Color
 		tilteLabel.setBackground(Color.black);
 		tilteLabel.setOpaque(true);

 		tilteLabel.setHorizontalAlignment(JLabel.CENTER); // Position

 		this.add(tilteLabel);
 		
 		
 	    // 내용작성
 		/*
		JLabel content = new JLabel("내용작성");
		content.setBounds(330, 110, 420, 420);

		content.setFont(new Font("맑은 고딕", Font.BOLD, 15)); // Font

		content.setForeground(Color.black); // Color
		content.setBackground(new Color(166, 166, 166));
		content.setOpaque(true);

		content.setHorizontalAlignment(JLabel.CENTER); // Position

		this.add(content);
		*/
		JTextField content = new JTextField("내용작성");
		content.setBounds(330, 110, 420, 420);
		content.setFont(new Font("맑은 고딕", Font.BOLD, 15)); // Font

		content.setForeground(Color.black); // Color
		content.setBackground(new Color(166, 166, 166));
		content.setOpaque(true);
		content.setHorizontalAlignment(JLabel.CENTER); // Position

		this.add(content);
		
		content.addMouseListener(new MouseAdapter(){
	          @Override
	          public void mouseClicked(MouseEvent e){
	             content.setText("");
	          }
	    });
		
		
		// 배경색 변경 버튼
		JButton btnChangeColor = new JButton("배경색 선택");
		btnChangeColor.setBounds(110, 150, 100, 30);
		btnChangeColor.setFont(new Font("맑은 고딕", Font.BOLD, 12)); // Font
		btnChangeColor.setBackground(new Color(242,242,242));
		btnChangeColor.setForeground(Color.black);
		btnChangeColor.setBorderPainted(false);
	    btnChangeColor.setFocusPainted(false);
	    btnChangeColor.setBorderPainted(true);
		
		this.add(btnChangeColor);
		
		JButton redbtn = new JButton("");
		JButton yellowbtn = new JButton("");
		JButton orangebtn = new JButton("");
		JButton skybluebtn = new JButton("");
		JButton greenbtn = new JButton("");
		JButton whitebtn = new JButton("");
		
		redbtn.setBounds(148, 200, 20, 28);
		redbtn.setBackground(new Color(247,146,129));
		redbtn.setFocusPainted(false);
		redbtn.setBorderPainted(false);
		redbtn.setVisible(false);
		
		yellowbtn.setBounds(148, 240, 20, 28);
		yellowbtn.setBackground(new Color(255,255,0));
		yellowbtn.setFocusPainted(false);
		yellowbtn.setBorderPainted(false);
		yellowbtn.setVisible(false);
		
		orangebtn.setBounds(148, 280, 20, 28);
		orangebtn.setBackground(new Color(255,192,0));
		orangebtn.setFocusPainted(false);
		orangebtn.setBorderPainted(false);
		orangebtn.setVisible(false);
		
		skybluebtn.setBounds(148, 320, 20, 28);
		skybluebtn.setBackground(new Color(105,216,255));
		skybluebtn.setFocusPainted(false);
		skybluebtn.setBorderPainted(false);
		skybluebtn.setVisible(false);
		
		greenbtn.setBounds(148, 360, 20, 28);
		greenbtn.setBackground(new Color(85,241,59));
		greenbtn.setFocusPainted(false);
		greenbtn.setBorderPainted(false);
		greenbtn.setVisible(false);
		
		whitebtn.setBounds(148, 400, 20, 28);
		whitebtn.setBackground(new Color(255,255,255));
		whitebtn.setFocusPainted(false);
		whitebtn.setBorderPainted(false);
		whitebtn.setVisible(false);
		
		this.add(redbtn);
		this.add(yellowbtn);
		this.add(orangebtn);
		this.add(skybluebtn);
		this.add(greenbtn);
		this.add(whitebtn);
		

		
		redbtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 content.setBackground(new Color(247,146,129));
	        	 color = "RED";
	         }
	    });
		
		yellowbtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 content.setBackground(new Color(255,255,0));
	        	 color = "YELLOW";
	         }
	    });
		
		orangebtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 content.setBackground(new Color(255,192,0));
	        	 color = "ORANGE";
	         }
	    });
		
		skybluebtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 content.setBackground(new Color(105,216,255));
	        	 color = "SKYBLUE";
	         }
	    });
		
		greenbtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 content.setBackground(new Color(85,241,59));
	        	 color = "GREEN";
	         }
	    });
		
		whitebtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 content.setBackground(new Color(166, 166, 166));
	        	 color = "WHITE";
	         }
	    });
		
		btnChangeColor.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(count==0) {
	        		redbtn.setVisible(true);
	 	            yellowbtn.setVisible(true);
	 	            orangebtn.setVisible(true);
	 	            skybluebtn.setVisible(true);
	 	            greenbtn.setVisible(true);
	 	            whitebtn.setVisible(true);
	 	            count=1;
	        	 }
	        	 else if(count==1) {
	        		 redbtn.setVisible(false);
	 	             yellowbtn.setVisible(false);
	 	             orangebtn.setVisible(false);
	 	             skybluebtn.setVisible(false);
	 	             greenbtn.setVisible(false);
	 	             whitebtn.setVisible(false);
	 	             count=0;
	        	 }
	         }
	      });
		
		
        btnChangeColor.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseEntered(java.awt.event.MouseEvent evt) {
             btnChangeColor.setBackground(Color.black);
             btnChangeColor.setForeground(Color.white);
             //player_Btn.setBorderPainted(true);
          }
          public void mouseExited(java.awt.event.MouseEvent evt) {
         	 btnChangeColor.setBackground(new Color(242,242,242));
             btnChangeColor.setForeground(Color.black);
         }
        });
        
     // input 결과물 출력 (응원메시지 작성)
	 // 등록버튼
	 JButton btnPostcheer = new JButton("등록");
	 btnPostcheer.setBounds(690, 540, 90, 34);
	 btnPostcheer.setFont(new Font("맑은 고딕", Font.BOLD, 16)); // Font
	 btnPostcheer.setBackground(new Color(230,230,230));
	 btnPostcheer.setForeground(Color.black);
	 btnPostcheer.setBorderPainted(false);
     btnPostcheer.setFocusPainted(false);
     btnPostcheer.setBorderPainted(true);
	
	 this.add(btnPostcheer);
			
			
	 // 적용버튼 리스너
	 btnPostcheer.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			
		 	//connectDB cDB = new connectDB();
		 	String postCheer;
		 	postCheer = (String) content.getText();
		 	
		 	//MsgVO data = new MsgVO();
		 	//data.setColor(color);
		 	ArrayList<MsgVO> list = new ArrayList<MsgVO>(); // 게터세터를 받아줄 배열을 먼저 만들어줌
		 	MsgVO data = new MsgVO(); // 해당 클래스를 호출
		 	
		 	data.setColor(color); // 해당 클래스에 데이터를 SET
		 	data.setCheertext(postCheer); // 해당 클래스에 데이터를 SET
		 	
		 	list.add(data); // data를 list에 저장시켜줌
		 	
		 	drimstudyDB s = new drimstudyDB(); // DB함수를 호출
		 	s.sendDB(data); // 해당 함수에 data를 보내줌
		 	
			// 임시 출력
		 	System.out.println(data.getColor() + data.getCheertext());
			
		 }
      });
		
	}
}
