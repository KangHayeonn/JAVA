package tablepagination;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;


public class SubPanel1 extends JPanel {

	public SubPanel1(JFrame frame) {
		super();
		PanelInit(frame);
	}

	private void PanelInit(JFrame frame) {

		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1280, 300);
		this.setLayout(null);

		//picture1 
		ImageIcon icon1 = new ImageIcon("./img/1.jpg");
		
		Image playerImg = icon1.getImage();
		Image changeImg = playerImg.getScaledInstance(200, 140, Image.SCALE_SMOOTH);
		
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		JLabel pictureLabel1 = new JLabel(changeIcon);
		
		pictureLabel1.setBounds(40, 0, 200, 140);
		this.add(pictureLabel1);
		//add(pictureLabel1);
		
		pictureLabel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI("https://tv.naver.com/v/21690104"));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});

		// Picture1
		/*
		JLabel pictureLabel = new JLabel("»çÁø 1");
		pictureLabel.setBounds(40,0,200, 140);

		pictureLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35)); // Font

		pictureLabel.setForeground(Color.black); // Color
		pictureLabel.setBackground(new Color(166, 166, 166));
		pictureLabel.setOpaque(true);

		pictureLabel.setHorizontalAlignment(JLabel.CENTER); // Position

		this.add(pictureLabel);
		*/
		
		// Picture2
		ImageIcon icon2 = new ImageIcon("./img/2.jpg");
		
		Image playerImg2 = icon2.getImage();
		Image changeImg2 = playerImg2.getScaledInstance(200, 140, Image.SCALE_SMOOTH);
		
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		
		JLabel pictureLabel2 = new JLabel(changeIcon2);
		
		pictureLabel2.setBounds(270, 0, 200, 140);
		this.add(pictureLabel2);
		
		pictureLabel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI("https://tv.naver.com/v/21601189"));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		
		// Picture3
		ImageIcon icon3 = new ImageIcon("./img/3.PNG");
		
		Image playerImg3 = icon3.getImage();
		Image changeImg3 = playerImg3.getScaledInstance(200, 140, Image.SCALE_SMOOTH);
		
		ImageIcon changeIcon3 = new ImageIcon(changeImg3);
		
		JLabel pictureLabel3 = new JLabel(changeIcon3);
		
		pictureLabel3.setBounds(500, 0, 200, 140);
		this.add(pictureLabel3); 
			
		pictureLabel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI("https://tv.naver.com/v/21610398"));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		
		// Picture4
		ImageIcon icon4 = new ImageIcon("./img/4.PNG");
		
		Image playerImg4 = icon4.getImage();
		Image changeImg4 = playerImg4.getScaledInstance(200, 140, Image.SCALE_SMOOTH);
		
		ImageIcon changeIcon4 = new ImageIcon(changeImg4);
		
		JLabel pictureLabel4 = new JLabel(changeIcon4);
		
		pictureLabel4.setBounds(730, 0, 200, 140);
		this.add(pictureLabel4);
			
		pictureLabel4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI("https://tv.naver.com/v/21570110"));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});
	
		
		// Picture5
		ImageIcon icon5 = new ImageIcon("./img/5.jpg");
		
		Image playerImg5 = icon5.getImage();
		Image changeImg5 = playerImg5.getScaledInstance(200, 140, Image.SCALE_SMOOTH);
		
		ImageIcon changeIcon5 = new ImageIcon(changeImg5);
		
		JLabel pictureLabel5 = new JLabel(changeIcon5);
		
		pictureLabel5.setBounds(960, 0, 200, 140);
		this.add(pictureLabel5);
		
		pictureLabel5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI("https://tv.naver.com/v/21643693"));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});

		
		
	}
}