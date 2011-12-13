package client;

import java.io.IOException;

import shared.networking.Network;
import client.networking.ClientListener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

/**
 * Starts up a client thread, joins to a server, adds a listener, and initializes the client's data store.
 */
public class ClientStart {
	Client client = new Client();
	
	public void start() {
		initConnection();
		
		//kick off the main client thread
		new ClientMainThread();
	}
	
	//initialize the connection
	private void initConnection() {
		Network.register(client);
		client.addListener(new ClientListener());
		client.start();
		try {
			Network.setUpConnection(client);
			client.connect(Network.TIMEOUT_CONNECT, Network.HOST_IP, Network.PORT_TCP, Network.PORT_UDP);
		} catch (IOException e) {
			System.out.println("Could not connect to server " + Network.HOST_IP + " on ports " + Network.PORT_TCP + "tcp, " + Network.PORT_UDP + "udp");
			e.printStackTrace();
			System.exit(1);
		}
		
		ClientDataStore.setClient(client);
	}
	
	public static void main(String[] args) {
		Log.set(Network.LOG_LEVEL);
		ClientStart clientStart = new ClientStart();
		clientStart.start();
	}
}
