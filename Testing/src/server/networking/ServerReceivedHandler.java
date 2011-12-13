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
			handlePlayerInfo(connection, playerState);
		}
	}
	
	/**
	 * Handles the case of an incoming playerState. Updates the playerStateMap in the data store and dispatches deltas to clients.
	 */
	private void handlePlayerInfo(Connection connection, PlayerState playerState) {
		if(ServerDataStore.getPlayerState(connection.getID()) == null) {
			//the player has just connected
			initialConnect(connection, playerState);
		}
		//update player in data store
		ServerDataStore.updatePlayerStateMap(playerState);
		
		ServerNetworkUtil.sendPlayerStateDeltasToAll();
		
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
