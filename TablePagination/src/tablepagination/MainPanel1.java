package tablepagination;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainPanel1 extends JPanel {

	private JFrame frame;
	private SubPanel1 SubPanel1;

	public MainPanel1(JFrame frame) {
		super();
		FrameInit(frame);
		PanelInit();
	}

	private void FrameInit(JFrame frame) {

		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1280, 340);

		frame.getContentPane().add(this);
		this.setLayout(null);
	}

	private void PanelInit() {

		SubPanel1 = new SubPanel1(frame);

		SubPanel1.setBounds(30, 200, 1200, 400);
		SubPanel1.setBackground(new Color(242, 242, 242));
		SubPanel1.setBorder(null); // 테두리 없애기

		this.add(SubPanel1);
	}
}
