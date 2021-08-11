package test;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import test.Panelmsg;

public class Panelmsg extends JPanel{
	
	private JFrame frame;
	private Panelmsg2 Panelmsg2;

	public Panelmsg(JFrame frame) {
		super();
		FrameInit(frame);
		PanelInit();
	}

	private void FrameInit(JFrame frame) {

		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1280, 960);

		frame.getContentPane().add(this);
		this.setLayout(null);
	}

	private void PanelInit() {

		Panelmsg2 = new Panelmsg2(frame);

		Panelmsg2.setBounds(80, 200, 1100, 600);
		Panelmsg2.setBackground(new Color(242, 242, 242));
		Panelmsg2.setBorder(null); // 테두리 없애기

		this.add(Panelmsg2);
	}
}
