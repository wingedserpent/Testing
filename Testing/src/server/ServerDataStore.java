package server;

import java.util.HashMap;
import java.util.Map;

import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Server;

/**
 * Abstract class for storing all state data related to a server.
 */
public abstract class ServerDataStore {
	/**
	 * The current {@link Server} object the server is using.
	 */
	private static Server server;
	
	/**
	 * A map containing all currently connected players' state info, keyed by connection id.
	 */
	private static Map<Integer, PlayerState> playerStateMap = new HashMap<Integer, PlayerState>();
	
	/**
	 * Sets the {@link #server} in this data store so that any class may use it to send messages.
	 */
	public static void setServer(Server server) {
		ServerDataStore.server = server;
	}
	
	/**
	 * Returns the {@link #server} object, which can be used to send messages.
	 */
	public static Server getServer() {
		return server;
	}
	
	/**
	 * Returns the current {@link #playerStateMap}.
	 */
	public static Map<Integer, PlayerState> getPlayerStateMap() {
		return playerStateMap;
	}
	
	/**
	 * Updates the current {@link #playerStateMap} by replacing this playerState object in it. If this object is not in the map, it is created.
	 */
	public static void updatePlayerStateMap(PlayerState playerState) {
		if(playerStateMap.containsKey(playerState.getConnectionId()))
			playerStateMap.remove(playerState.getConnectionId());
		playerStateMap.put(playerState.getConnectionId(), playerState);
	}
	
	/**
	 * Remove the {@link PlayerState} with this connectionId from the {@link #playerStateMap}.
	 */
	public static void removeFromPlayerStateMap(Integer connectionId) {
		playerStateMap.remove(connectionId);
	}
	
	/**
	 * Returns the {@link PlayerState} associated with this connectionId.
	 */
	public static PlayerState getPlayerState(Integer connectionId) {
		return playerStateMap.get(connectionId);
	}
}
