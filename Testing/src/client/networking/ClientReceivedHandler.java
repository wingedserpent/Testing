package client.networking;

import java.util.Map;

import shared.game.PlayerState;
import client.ClientDataStore;

import com.esotericsoftware.kryonet.Connection;

/**
 * Handles all incoming 'received' notifications from the client listener.
 */
public class ClientReceivedHandler {
	
	/**
	 * Handles an incoming object from this connection.
	 */
	public void handleReceived(Connection connection, Object object) {
		if (object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof Map<?, ?>) {
			@SuppressWarnings("unchecked") //there is no way to avoid this...
			Map<Integer, PlayerState> playerStateMap = (Map<Integer, PlayerState>)object;
			handlePlayerStateMap(connection, playerStateMap);
		}
	}
	
	/**
	 * Handles the case of an incoming playerStateMap. Updates the playerStateMap in the data store.
	 */
	private void handlePlayerStateMap(Connection connection, Map<Integer, PlayerState> playerStateMap) {
		//TODO make this handle deltas instead of a whole new map
		ClientDataStore.updatePlayerStateMap(playerStateMap);
		System.out.println("client playerStateMap updated to: "+ClientDataStore.getPlayerStateMap().toString());
	}
}
