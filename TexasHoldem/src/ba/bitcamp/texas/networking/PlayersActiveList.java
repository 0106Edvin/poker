package ba.bitcamp.texas.networking;

import java.io.IOException;
import java.net.Socket;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ba.bitcamp.texas.run.Player;

public class PlayersActiveList {
	
	private static Player[] players = new Player[6];
	private static Socket[] clients = new Socket[6];
	private static int activePlayers;
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void addPlayer(Player player, Socket client) {
		activePlayers++;		
		clients[player.getPositionAtTable()] = client;
		players[player.getPositionAtTable()] = player;
	}

	public static void removePlayer(Player player){
		activePlayers--;		
		clients[player.getPositionAtTable()] = null;
		players[player.getPositionAtTable()] = null;
	}

	/**
	 * @return the activePlayers
	 */
	public static int getActivePlayers() {
		return activePlayers;
	}
	
	private static class ClientInformer implements Runnable {
		
		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < clients.length; i++) {
					if(clients[i] != null){
						try {
							mapper.writeValue(clients[i].getOutputStream(), players);
							System.out.println("poslao podatke");
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (IOException e) {
							e.printStackTrace();
							try {
								Thread.sleep(20000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}						
					}
				}
				
			}
			
		}
	}
	
	public static void startGame() {
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, false);
		new Thread(new ClientInformer()).start();
	}
	
//	public static boolean isActive(Player player) {
//		return players.contains(player);
//	}
	
}
