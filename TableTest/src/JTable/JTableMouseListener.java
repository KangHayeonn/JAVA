package JTable;

import java.awt.event.MouseListener;

import javax.swing.JTable;

public class JTableMouseListener implements MouseListener{
	public void mouseClicked(java.awt.event.MouseEvent e) {
		
		JTable jtable = (JTable)e.getSource();
		int row = jtable.getSelectedRow();
		int col = jtable.getSelectedColumn();
		
		
	}
	public void mouseEntered(java.awt.event.MouseEvent e) {
	}
	public void mouseExited(java.awt.event.MouseEvent e) {
	}
	public void mousePressed(java.awt.event.MouseEvent e) {
	}
	public void mouseReleased(java.awt.event.MouseEvent e) {
	}
	
}
