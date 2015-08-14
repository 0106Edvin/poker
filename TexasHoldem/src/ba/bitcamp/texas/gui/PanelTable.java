package ba.bitcamp.texas.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import ba.bitcamp.texas.networking.Client;

public class PanelTable extends JLayeredPane {
	
	private static final long serialVersionUID = 1604522399060949298L;

	public static final Point PLAYER_ONE = new Point(400, 400);
	public static final Point PLAYER_TWO = new Point(100, 300);
	public static final Point PLAYER_THREE = new Point(100, 100);
	public static final Point PLAYER_FOUR = new Point(400, 30);
	public static final Point PLAYER_FIVE = new Point(700, 100);
	public static final Point PLAYER_SIX = new Point(700, 300);
	public static final Point[] PLAYER_POSITIONS = {PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR, PLAYER_FIVE, PLAYER_SIX};
	
	static BufferedImage table;
	private PanelPlayer[] players = new PanelPlayer[6];
	private Action action = new Action();
	
	
	public PanelTable() {

		try {
			table = ImageIO.read(new File("table.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < players.length; i++) {
			addPanelPlayer(new PanelPlayer(), i);
			players[i].getPlayerInfo().addMouseListener(action);
		}
		
	}
		  public void paint( Graphics g ) { 
			    super.paint(g);
			    g.drawImage(table.getScaledInstance(1000, 800, Image.SCALE_SMOOTH), 0, 0, null);
			   
			  };
			
	
	private void addPanelPlayer(PanelPlayer pnlPlayer, int position) {
		if(getComponentCount() > position){
			remove(players[position]);			
		}
		players[position] = pnlPlayer;
		players[position].getPlayerInfo().setName(String.valueOf(position));
		players[position].setBounds((int)PLAYER_POSITIONS[position].getX(), (int)PLAYER_POSITIONS[position].getY(), 200, 170);
		add(players[position]);
		
	}
	
	private class Action extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < players.length; i++) {
				players[i].getPlayerInfo().removeMouseListener(this);
			}
			PanelLogin.getPlayer().setPositionAtTable(Integer.parseInt(e.getComponent().getName()));
			new Thread(new Client.Update()).start();
			updateTable();
		}
		
	}
	
	private void updateTable() {
		System.out.println(PanelLogin.getPlayer().getName());
		addPanelPlayer(new PanelPlayer(PanelLogin.getPlayer()), PanelLogin.getPlayer().getPositionAtTable());
		repaint();
	}
	
	public static void main(String[] args) {
		
		
		JFrame f = new JFrame();
		
		f.add(new PanelTable());
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
