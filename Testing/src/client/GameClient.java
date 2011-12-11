package client;


import java.io.IOException;
import java.util.Scanner;

import server.networking.Network;
import shared.game.PlayerInfo;
import client.networking.listeners.GameClientListener;

import com.esotericsoftware.kryonet.Client;

public class GameClient {
	Client client = new Client();
	Scanner scanner = new Scanner(System.in);
	
	public void start() {
		initialize();
		client.addListener(new GameClientListener());
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
		GameClientDataStore.setClient(client);
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(client);
		
		initPlayer();
	}
	
	//init the player with a name and store it in the data store
	private void initPlayer() {
		System.out.print("Enter your player name: ");
		PlayerInfo player = new PlayerInfo();
		player.setName(scanner.next());
		player.setConnectionId(GameClientDataStore.getClient().getID());
		GameClientDataStore.setPlayer(player);
		//and send it to the server
		GameClientDataStore.getClient().sendTCP(GameClientDataStore.getPlayer());
	}
	
	public static void main(String[] args) {
		GameClient gameClient = new GameClient();
		gameClient.start();
	}
}
