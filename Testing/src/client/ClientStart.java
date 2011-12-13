package client;

import java.io.IOException;

import shared.networking.Network;
import client.networking.ClientListener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class ClientStart {
	Client client = new Client();
	
	public void start() {
		initConnection();
		
		//kick off the main client thread
		new ClientMainThread();
	}
	
	//initialize the connection
	private void initConnection() {
		//registers all objects that will be sent over the network for this endpoint
		Network.register(client);
		
		client.addListener(new ClientListener());
		
		client.start();
		try {
			Network.setUpConnection(client);
			client.connect(Network.TIMEOUT_CONNECT, Network.HOST_IP, Network.PORT_TCP);
		} catch (IOException e) {
			System.out.println("Could not connect to server " + Network.HOST_IP + " on port " + Network.PORT_TCP);
			e.printStackTrace();
			System.exit(1);
		}
		
		//save the client in the data store
		ClientDataStore.setClient(client);
	}
	
	public static void main(String[] args) {
		Log.set(Network.LOG_LEVEL);
		ClientStart clientStart = new ClientStart();
		clientStart.start();
	}
}
