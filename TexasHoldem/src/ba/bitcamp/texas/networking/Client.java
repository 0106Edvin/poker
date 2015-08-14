package ba.bitcamp.texas.networking;

import java.io.IOException;
import java.net.Socket;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import ba.bitcamp.texas.gui.PanelLogin;


public class Client {

	private static Socket socket;
	private static ObjectMapper playerMappper = new ObjectMapper();
	
	public Client() {
		try {
			playerMappper.configure(Feature.AUTO_CLOSE_SOURCE, false);

			socket = new Socket(PanelLogin.getPlayer().getHost(), RoomServer.ROOM_SERVER_PORT);
			//playerMappper.writeValue(socket.getOutputStream(), PanelLogin.getPlayer());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static class Update implements Runnable {
		
		
		@Override
		public void run() {
			try {
				playerMappper.writeValue(socket.getOutputStream(), PanelLogin.getPlayer());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
