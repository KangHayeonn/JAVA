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

public class MainPanel2 extends JPanel {

	private JFrame frame;
	private SubPanel2 SubPanel2;

	public MainPanel2(JFrame frame) {
		super();
		FrameInit(frame);
		PanelInit();
	}

	private void FrameInit(JFrame frame) {

		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 340, 1280, 600);

		frame.getContentPane().add(this);
		this.setLayout(null);
	}

	private void PanelInit() {

		SubPanel2 = new SubPanel2(frame);

		SubPanel2.setBounds(30, 40, 1200, 430);
		SubPanel2.setBackground(new Color(255, 255, 255));
		SubPanel2.setBorder(null); // 테두리 없애기

		this.add(SubPanel2);
	}
}
