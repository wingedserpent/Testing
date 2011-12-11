package client;

import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Client;

public abstract class ClientDataStore {
	//the client object is stored here so any class can send messages through it
	private static Client client;
	
	//stores all current player info for this client
	private static PlayerInfo player = new PlayerInfo();

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		ClientDataStore.client = client;
	}

	public static PlayerInfo getPlayer() {
		return player;
	}

	public static void setPlayer(PlayerInfo player) {
		ClientDataStore.player = player;
	}
}
