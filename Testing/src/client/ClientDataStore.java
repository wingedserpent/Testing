package client;

import java.util.HashMap;
import java.util.Map;

import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Client;

public abstract class ClientDataStore {
	//the client object is stored here so any class can send messages through it
	private static Client client;
	
	//stores all current player info for this client
	private static PlayerState player = new PlayerState();
	
	//a map containing all the players' info that this client knows about, keyed by connection id
	private static Map<Integer, PlayerState> playerStateMap = new HashMap<Integer, PlayerState>();

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		ClientDataStore.client = client;
	}

	public static PlayerState getPlayer() {
		return player;
	}

	public static void setPlayer(PlayerState player) {
		ClientDataStore.player = player;
	}
	
	public static void setPlayerStateMap(Map<Integer, PlayerState> playerStateMap) {
		ClientDataStore.playerStateMap = playerStateMap;
	}
	
	public static Map<Integer, PlayerState> getPlayerStateMap() {
		return playerStateMap;
	}
}
