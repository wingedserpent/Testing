package server;

import java.util.HashMap;
import java.util.Map;

import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Server;

/**
 * Abstract class for storing all state data related to a server.
 */
public abstract class ServerDataStore {
	/** The {@link Server} object the server has instantiated. Can be used to send messages. */
	private static Server server;
	
	/** A map containing all currently connected players' state info, keyed by connection id. */
	private static Map<Integer, PlayerState> playerStateMap = new HashMap<Integer, PlayerState>();
	
	/** A map containing all changes to the playerStateMap since this map was last cleared. */
	private static Map<Integer, PlayerState> playerStateMapDeltas = new HashMap<Integer, PlayerState>();
	
	/**
	 * Sets the {@link #server} in this data store so that any class may use it to send messages.
	 */
	public static void setServer(Server server) {
		ServerDataStore.server = server;
	}
	
	/**
	 * @return The {@link #server} object, which can be used to send messages.
	 */
	public static Server getServer() {
		return server;
	}
	
	/**
	 * @return The current {@link #playerStateMap}.
	 */
	public static Map<Integer, PlayerState> getPlayerStateMap() {
		return playerStateMap;
	}
	
	/**
	 * Updates the current {@link #playerStateMap} by replacing this playerState entry in it. If playerState is not in the map, it is created.
	 */
	public static void updatePlayerStateMap(PlayerState playerState) {
		playerStateMap.put(playerState.getConnectionId(), playerState);
	}
	
	/**
	 * Removes the {@link PlayerState} with this connectionId from the {@link #playerStateMap}.
	 */
	public static void removeFromPlayerStateMap(Integer connectionId) {
		playerStateMap.remove(connectionId);
	}
	
	/**
	 * @return The {@link PlayerState} associated with this connectionId.
	 */
	public static PlayerState getPlayerState(Integer connectionId) {
		return playerStateMap.get(connectionId);
	}
	
	/**
	 * @return The {@link #playerStateMapDeltas} gathered since the map was last cleared.
	 */
	public static Map<Integer, PlayerState> getPlayerStateMapDeltas() {
		return playerStateMapDeltas;
	}
	
	/**
	 * Adds playerState to the current {@link #playerStateMapDeltas} object.
	 */
	public static void addPlayerStateMapDelta(PlayerState playerState) {
		playerStateMapDeltas.put(playerState.getConnectionId(), playerState);
	}
	
	/**
	 * Resets the current {@link #playerStateMapDeltas} object.
	 */
	public static void clearPlayerStateMapDeltas() {
		playerStateMapDeltas.clear();
	}
}
