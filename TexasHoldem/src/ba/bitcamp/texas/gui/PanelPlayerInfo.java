package ba.bitcamp.texas.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelPlayerInfo extends JPanel {
	private static final long serialVersionUID = 3499880290356750121L;

	public enum Played {
		FOLD, ALL_IN, CHECK
	};

	private String playerName;
	private Played played;
	BufferedImage cash;

	public PanelPlayerInfo(String playerName, Played played) {
		super();
		this.playerName = playerName;
		this.played = played;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Played getPlayed() {
		return played;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.GREEN.darker());
		g.fillRoundRect(0, 0, 180, 60, 50, 50);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Sans", Font.BOLD, 20));
		g.drawString(playerName, 20, 35);
		try {
			cash = ImageIO.read(new File("dollars.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(cash.getScaledInstance(35, 30, Image.SCALE_SMOOTH), 140,
				15, null);
		repaint();

	}
}
