package server.networking;

import server.ServerDataStore;
import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Connection;

/**
 * Handles all incoming 'disconnected' notifications from the server listener.
 */
public class ServerDisconnectedHandler {
	
	/**
	 * Handles the activity immediately after this connection ends.
	 */
	public void handleDisconnected(Connection connection) {
		PlayerState player = ServerDataStore.getPlayerState(connection.getID());
		ServerDataStore.getServer().sendToAllTCP(player.getName() + " disconnected.");
		System.out.println(player.getName() + " disconnected.");
		
		//remove the player from the data store
		ServerDataStore.removeFromPlayerStateMap(player.getConnectionId());
		
		System.out.println("server playerStateMap updated to: "+ServerDataStore.getPlayerStateMap().toString());
	}
}
