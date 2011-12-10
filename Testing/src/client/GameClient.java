package client;


import java.io.IOException;
import java.util.Scanner;

import server.networking.base.Network;
import shared.game.PlayerInfo;



import client.networking.listeners.GameClientListener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class GameClient {
	Client client = new Client();
	ObjectSpace objectSpace = new ObjectSpace();
	Scanner scanner = new Scanner(System.in);
	PlayerInfo player;
	
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
			e.printStackTrace();
		}
		
		//registers all objects that will be sent over the network for this endpoint
		Network.register(client);
		
		//init the player with a name
		System.out.print("Enter your player name: ");
		player = new PlayerInfo(scanner.next());
	}
	
	public static void main(String[] args) {
		GameClient gameClient = new GameClient();
		gameClient.start();
	}
}
