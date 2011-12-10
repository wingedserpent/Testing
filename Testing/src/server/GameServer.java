package server;

import java.io.IOException;

import server.networking.base.GameServerDataStore;
import server.networking.base.Network;
import server.networking.listeners.GameServerListener;


import com.esotericsoftware.kryonet.Server;

public class GameServer {
	Server server = new Server();
	
	public void start() {
		initialize();
		server.addListener(new GameServerListener());
	}
	
	private void initialize() {
		server.start();
		try {
			server.bind(Network.PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//save the server in the data store
		GameServerDataStore.server = server;
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(server);
	}
	
	public static void main(String[] args) {
		GameServer gameServer = new GameServer();
		gameServer.start();
	}
}
