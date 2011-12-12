package client.networking;

import java.util.Map;

import shared.game.PlayerState;
import client.ClientDataStore;

import com.esotericsoftware.kryonet.Connection;

public class ClientReceivedHandler {

	public void handleReceived(Connection connection, Object object) {
		if (object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof Map<?, ?>) {
			@SuppressWarnings("unchecked") //there is no way to avoid this...
			Map<Integer, PlayerState> playerStateMap = (Map<Integer, PlayerState>)object;
			handlePlayerStateMap(connection, playerStateMap);
		}
	}
	
	private void handlePlayerStateMap(Connection connection, Map<Integer, PlayerState> playerStateMap) {
		ClientDataStore.setPlayerStateMap(playerStateMap);
		System.out.println("client playerStateMap updated to: "+ClientDataStore.getPlayerStateMap().toString());
	}
}
