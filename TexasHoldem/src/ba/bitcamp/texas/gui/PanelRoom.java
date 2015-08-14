package ba.bitcamp.texas.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelRoom extends JPanel {
	
	private static final long serialVersionUID = -3914216266531551017L;

	private PanelTable pnlTable = new PanelTable();
	
	
	public PanelRoom() {
		
		setLayout(new BorderLayout());
		
		add(pnlTable);
		
	}
}
