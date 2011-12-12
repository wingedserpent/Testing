package server;

import java.io.IOException;

import server.networking.ServerListener;
import shared.networking.Network;


import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class ServerStart {
	Server server = new Server();
	
	public void start() {
		initialize();
	}
	
	private void initialize() {
		//registers all objects that will be sent over the network for this endpoint
		Network.register(server);
		
		server.addListener(new ServerListener());
				
		server.start();
		try {
			server.bind(Network.PORT_TCP);
		} catch (IOException e) {
			System.out.println("Failed to bind to port " + Network.PORT_TCP);
			System.exit(1);
		}
		
		//save the server in the data store
		ServerDataStore.setServer(server);
	}
	
	public static void main(String[] args) {
		Log.set(Network.LOG_LEVEL);
		ServerStart serverStart = new ServerStart();
		serverStart.start();
	}
}
