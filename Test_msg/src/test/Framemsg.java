package test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import test.Framemsg;

public class Framemsg{
	private JFrame frame;
	private Panelmsg Panelmsg2;
	
	public Framemsg() {
	      frame = new JFrame();
	      Initialize();
	   }
	
	private void Initialize() {
	      frameInit();
	      Panelmsg2 = new Panelmsg(frame);
	}
	
	private void frameInit() {
	      frame.setTitle("Drim Olympic");
	      frame.setBounds(100, 100, 1280, 960);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().setLayout(null);
	      
	      ImageIcon img = new ImageIcon("./img/drim_img.png");
	      frame.setIconImage(img.getImage());
	      
	      // 메인 Title
	      JLabel tilteLabel = new JLabel("Drim Olympic");
	      tilteLabel.setBounds(0, 50, 1280, 60);

	      tilteLabel.setFont(new Font("Arial", Font.BOLD, 35)); // Font

	      tilteLabel.setForeground(Color.white); // Color
	      tilteLabel.setBackground(new Color(51, 63, 80));
	      tilteLabel.setOpaque(true);

	      tilteLabel.setHorizontalAlignment(JLabel.CENTER); // Position

	      frame.add(tilteLabel);
	      
	   // Footer
	      JLabel underLabel = new JLabel("㈜ 드림시스  ⓒ 명지대 인턴 프로젝트");
	      underLabel.setFont(new Font("맑은고딕", Font.PLAIN, 13));

	      underLabel.setForeground(Color.black);
	      underLabel.setBackground(new Color(204, 204, 204));

	      underLabel.setOpaque(true);

	      underLabel.setHorizontalAlignment(JLabel.CENTER);
	      underLabel.setBounds(0, 880, 1280, 40);

	      frame.add(underLabel);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {
	    	  public void run() {
	              try {
	                 Framemsg window = new Framemsg();

	                 window.frame.setVisible(true);
//	                 window.frame.setResizable(false);
	                 window.frame.setLocationRelativeTo(null);
	              } catch (Exception e) {
	                 e.printStackTrace();
	              }
	         }
	      });

	}

}
