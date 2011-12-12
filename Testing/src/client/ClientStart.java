package client;

import java.io.IOException;
import java.util.Scanner;

import shared.game.PlayerState;
import shared.networking.Network;
import client.networking.ClientListener;

import com.esotericsoftware.kryonet.Client;

public class ClientStart {
	Client client = new Client();
	Scanner scanner = new Scanner(System.in);
	
	public void start() {
		initConnection();
		client.addListener(new ClientListener());
		
		initPlayer();
		
		//kick off the main client thread
		new ClientMainThread();
	}
	
	//initialize the connection
	private void initConnection() {
		client.start();
		try {
			client.setKeepAliveTCP(Network.KEEP_ALIVE_TCP);
			client.setTimeout(Network.TIMEOUT_TCP);
			client.connect(Network.TIMEOUT_CONNECT, Network.HOST_IP, Network.PORT_TCP);
		} catch (IOException e) {
			System.out.println("Could not connect to server " + Network.HOST_IP + " on port " + Network.PORT_TCP);
			System.exit(1);
		}
		
		//save the client in the data store
		ClientDataStore.setClient(client);
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(client);
	}
	
	//init the player with a name and store it in the data store
	private void initPlayer() {
		PlayerState player = new PlayerState();
		//System.out.print("Enter your player name: ");
		//player.setName(scanner.next());
		player.setName("dan");
		player.setConnectionId(ClientDataStore.getClient().getID());
		ClientDataStore.setPlayer(player);
		//and send it to the server
		ClientDataStore.getClient().sendTCP(ClientDataStore.getPlayer());
	}
	
	public static void main(String[] args) {
		ClientStart clientStart = new ClientStart();
		clientStart.start();
	}
}
