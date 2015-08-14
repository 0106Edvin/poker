package ba.bitcamp.texas.gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ba.bitcamp.texas.gui.PanelPlayerInfo.Played;
import ba.bitcamp.texas.run.Card;
import ba.bitcamp.texas.run.Card.Suit;
import ba.bitcamp.texas.run.Card.Value;
import ba.bitcamp.texas.run.Player;

public class PanelPlayer extends JLayeredPane {

	private static final long serialVersionUID = -4366640253233963633L;

	JLabel lblCard1 = new JLabel("Card 1");
	JLabel lblCard2 = new JLabel("Card 2");

	private PanelCardImage card1;
	private PanelCardImage card2; 
	private PanelPlayerInfo playerInfo;

	private Player player;
	
	public PanelPlayer() {
		playerInfo = new PanelPlayerInfo("Take a seat", Played.FOLD);
		playerInfo.setBounds(10, 80, 180, 60);
		add(playerInfo);
	}
	
	public PanelPlayer(Player player) {

		this.player = player;
		card1 = new PanelCardImage(new Card(Suit.CLUBS, Value.EIGHT));
		card2 = new PanelCardImage(new Card(Suit.SPADES, Value.QUEEN));
		playerInfo = new PanelPlayerInfo(player.getName(), PanelPlayerInfo.Played.ALL_IN);
		
		card1.setBounds(30, 10, 70, 100);
		card2.setBounds(90, 10, 70, 100);
		playerInfo.setBounds(10, 80, 180, 60);

		add(card1, 0);
		add(card2, 0);
		add(playerInfo);

	}

	/**
	 * @return the playerInfo
	 */
	public PanelPlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.add(new PanelPlayer());
		f.setSize(200, 170);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
}
