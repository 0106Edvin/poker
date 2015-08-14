package ba.bitcamp.texas.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 5793891620592776938L;

	private Dimension dim = new Dimension(getContentPane().getWidth(), getContentPane().getHeight());
	
	private PanelLogin pnlLogin;
	
	private static MainWindow instance;
	
	private MainWindow() {
		
		
		
		pnlLogin = new PanelLogin(dim);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlLogin);
		
		
		setTitle("Texas Holdem");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static MainWindow getInstance(){
		if(instance == null){
			instance = new MainWindow();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		MainWindow.getInstance();
	}
}
