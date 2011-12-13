package server;

import java.io.IOException;

import server.networking.ServerListener;
import shared.networking.Network;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

/**
 * Starts up a server thread, opens it for connections, adds a listener, and initializes the server's data store.
 */
public class ServerStart {
	Server server = new Server();
	
	public void start() {
		initialize();
	}
	
	private void initialize() {
		Network.register(server);
		server.addListener(new ServerListener());
		server.start();
		try {
			server.bind(Network.PORT_TCP, Network.PORT_UDP);
		} catch (IOException e) {
			System.out.println("Failed to bind to ports " + Network.PORT_TCP + "tcp, " + Network.PORT_UDP + "udp");
			e.printStackTrace();
			System.exit(1);
		}
		
		ServerDataStore.setServer(server);
	}
	
	public static void main(String[] args) {
		Log.set(Network.LOG_LEVEL);
		ServerStart serverStart = new ServerStart();
		serverStart.start();
	}
}
