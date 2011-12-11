package client;


import java.io.IOException;
import java.util.Scanner;

import server.networking.Network;
import shared.game.PlayerInfo;
import client.networking.listeners.ClientListener;

import com.esotericsoftware.kryonet.Client;

public class ClientStart {
	Client client = new Client();
	Scanner scanner = new Scanner(System.in);
	
	public void start() {
		initialize();
		client.addListener(new ClientListener());
	}
	
	//initialize the connection and player
	private void initialize() {
		client.start();
		try {
			client.connect(Network.CONNECT_TIMEOUT, Network.HOST_IP, Network.PORT);
		} catch (IOException e) {
			System.out.println("Could not connect to server.");
			System.exit(1);
		}
		
		//save the client in the data store
		ClientDataStore.setClient(client);
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(client);
		
		initPlayer();
	}
	
	//init the player with a name and store it in the data store
	private void initPlayer() {
		System.out.print("Enter your player name: ");
		PlayerInfo player = new PlayerInfo();
		player.setName(scanner.next());
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
