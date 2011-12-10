package networking;

import java.io.IOException;

import com.esotericsoftware.kryonet.Server;

public class GameServer {
	Server server = new Server();
	
	public void start() {
		initialize();
		server.addListener(new GameServerListener(server));
	}
	
	private void initialize() {
		server.start();
		try {
			server.bind(Network.PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(server);
	}
	
	public static void main(String[] args) {
		GameServer gameServer = new GameServer();
		gameServer.start();
	}
}
