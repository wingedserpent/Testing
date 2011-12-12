package server;

import java.util.HashMap;
import java.util.Map;

import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Server;

//singleton class for storing all data related to a server
public abstract class ServerDataStore {
	//the server object is stored here so any class can send messages through it
	private static Server server;
	
	//a map containing all currently connected players' info, keyed by connection id
	private static Map<Integer, PlayerState> playerStateMap = new HashMap<Integer, PlayerState>();
	
	public static void setServer(Server server) {
		ServerDataStore.server = server;
	}
	
	public static Server getServer() {
		return server;
	}
	
	public static Map<Integer, PlayerState> getPlayerStateMap() {
		return playerStateMap;
	}
	
	public static void updatePlayerStateMap(PlayerState playerState) {
		if(playerStateMap.containsKey(playerState.getConnectionId()))
			playerStateMap.remove(playerState.getConnectionId());
		playerStateMap.put(playerState.getConnectionId(), playerState);
	}
	
	public static void removeFromPlayerStateMap(Integer id) {
		playerStateMap.remove(id);
	}
	
	//gets the player object associated with any connection
	public static PlayerState getPlayerState(Integer connectionId) {
		return playerStateMap.get(connectionId);
	}
}
