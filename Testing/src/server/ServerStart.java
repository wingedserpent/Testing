package server;

import java.io.IOException;

import server.networking.ServerListener;
import shared.networking.Network;


import com.esotericsoftware.kryonet.Server;

public class ServerStart {
	Server server = new Server();
	
	public void start() {
		initialize();
		server.addListener(new ServerListener());
	}
	
	private void initialize() {
		server.start();
		try {
			server.bind(Network.PORT_TCP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//save the server in the data store
		ServerDataStore.setServer(server);
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(server);
	}
	
	public static void main(String[] args) {
		ServerStart serverStart = new ServerStart();
		serverStart.start();
	}
}
