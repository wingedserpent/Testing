package client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Client;

/**
 * Abstract class for storing all state data related to a client.
 */
public abstract class ClientDataStore {
	/**
	 * The current {@link Client} object the client is using.
	 */
	private static Client client;
	
	/**
	 * Stores the current {@link PlayerState} of this player.
	 */
	private static PlayerState playerState = new PlayerState();
	
	/**
	 * A map containing all currently connected players' state info, keyed by connection id.
	 */
	private static Map<Integer, PlayerState> playerStateMap = new ConcurrentHashMap<Integer, PlayerState>();
	
	/**
	 * Returns the {@link #client} object, which can be used to send messages.
	 */
	public static Client getClient() {
		return client;
	}
	
	/**
	 * Sets the {@link #client} in this data store so that any class may use it to send messages.
	 */
	public static void setClient(Client client) {
		ClientDataStore.client = client;
	}
	
	/**
	 * Returns the current {@link #playerState}.
	 */
	public static PlayerState getPlayerState() {
		return playerState;
	}
	
	/**
	 * Sets the current {@link #playerState}.
	 */
	public static void setPlayerState(PlayerState playerState) {
		ClientDataStore.playerState = playerState;
	}
	
	/**
	 * Updates the current {@link #playerStateMap} with the given delta map.
	 */
	public static void updatePlayerStateMap(Map<Integer, PlayerState> playerStateMapDeltas) {
		ClientDataStore.playerStateMap.putAll(playerStateMapDeltas);
	}
	
	/**
	 * Returns the current {@link #playerStateMap}.
	 */
	public static Map<Integer, PlayerState> getPlayerStateMap() {
		return playerStateMap;
	}
}
