package server.networking;

import server.ServerDataStore;
import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Connection;

/**
 * Handles all incoming 'received' notifications from the server listener.
 */
public class ServerReceivedHandler {
	
	/**
	 * Handles an incoming object from this connection according to the object's type.
	 */
	public void handleReceived(Connection connection, Object object) {
		if(object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof PlayerState) {
			PlayerState playerState = (PlayerState)object;
			handlePlayerState(connection, playerState);
		}
	}
	
	/**
	 * Handles the case of an incoming playerState. Updates playerStateMap and playerStateMapDeltas in the data store.
	 */
	private void handlePlayerState(Connection connection, PlayerState playerState) {
		if(ServerDataStore.getPlayerState(connection.getID()) == null) {
			//the player has just connected
			initialConnect(connection, playerState);
		}
		ServerDataStore.updatePlayerStateMap(playerState);
		ServerDataStore.addPlayerStateMapDelta(playerState);
		System.out.println("server playerStateMap updated to: "+ServerDataStore.getPlayerStateMap().toString());
	}
	
	/**
	 * Handles the initial connection of a client to this server.
	 */
	private void initialConnect(Connection connection, PlayerState playerState) {
		connection.sendTCP("Welcome to the server!");
		ServerDataStore.getServer().sendToAllExceptTCP(connection.getID(), playerState.getName() + " connected.");
		System.out.println(playerState.getName() + " connected.");
	}
}
