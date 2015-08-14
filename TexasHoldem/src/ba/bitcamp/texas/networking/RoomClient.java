package ba.bitcamp.texas.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ba.bitcamp.texas.run.Player;

public class RoomClient implements Runnable {

	private Socket client;
	private BufferedReader reader;
	private ObjectMapper playerMapper = new ObjectMapper();
	private BufferedWriter writer;
	
	private Player player;
	
	
	public RoomClient(Socket client) {
		
		this.client = client;
		System.out.println("client in main: " + client);
		playerMapper.configure(Feature.AUTO_CLOSE_SOURCE, false);
		
		new Thread(this).start();
		
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				System.out.println("client in run: " + client.getInputStream());
				player = playerMapper.readValue(client.getInputStream(),Player.class);
				//player = playerMapper.readValue(client.getInputStream(),Player.class);
				System.out.println("pla: "+player);
				if(player.getPositionAtTable() != null) {
					PlayersActiveList.addPlayer(player, client);
					System.out.println(player.getPositionAtTable());
					break;
				}
				System.out.println("d");
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		
	}
	
}
