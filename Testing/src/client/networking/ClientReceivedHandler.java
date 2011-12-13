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
			Map<Integer, PlayerState> playerStateMapDeltas = (Map<Integer, PlayerState>)object;
			handlePlayerStateMapDeltas(connection, playerStateMapDeltas);
		}
	}
	
	/**
	 * Handles the case of an incoming playerStateMapDeltas object. Updates the {@link ClientDataStore#playerStateMap}.
	 */
	private void handlePlayerStateMapDeltas(Connection connection, Map<Integer, PlayerState> playerStateMapDeltas) {
		ClientDataStore.updatePlayerStateMap(playerStateMapDeltas);
		System.out.println("client playerStateMap updated to: "+ClientDataStore.getPlayerStateMap().toString());
	}
}
