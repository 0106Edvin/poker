package ba.bitcamp.texas.networking;

import java.io.IOException;
import java.net.ServerSocket;

public class RoomServer implements Runnable {

	public static final int ROOM_SERVER_PORT = 35721;
	
	private String roomName;
	private ServerSocket roomServer;
	private PlayersActiveList playersActiveList;
	
	public RoomServer(String roomName) {
		
		this.roomName = roomName;
		PlayersActiveList.startGame();
		try {
			roomServer = new ServerSocket(ROOM_SERVER_PORT);
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//playersActiveList = new PlayersActiveList();
		
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				new RoomClient(roomServer.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
