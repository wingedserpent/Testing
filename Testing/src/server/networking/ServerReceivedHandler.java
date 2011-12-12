package server.networking;

import server.ServerDataStore;
import shared.game.PlayerState;

import com.esotericsoftware.kryonet.Connection;

public class ServerReceivedHandler {
	
	public void handleReceived(Connection connection, Object object) {
		if(object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof PlayerState) {
			PlayerState playerState = (PlayerState)object;
			handlePlayerInfo(connection, playerState);
		}
	}
	
	//this is a request to update the playerInfo map in the data store
	private void handlePlayerInfo(Connection connection, PlayerState playerState) {
		if(ServerDataStore.getPlayerState(connection.getID()) == null) {
			//the player has just connected
			initialConnect(connection, playerState);
		}
		//update player in data store
		ServerDataStore.updatePlayerStateMap(playerState);
		
		//dispatch the updated map to all players
		ServerDataStore.getServer().sendToAllTCP(ServerDataStore.getPlayerStateMap());
		
		System.out.println("server playerStateMap updated to: "+ServerDataStore.getPlayerStateMap().toString());
	}
	
	//handle first update from a player
	private void initialConnect(Connection connection, PlayerState playerState) {
		connection.sendTCP("Welcome to the server!");
		ServerDataStore.getServer().sendToAllExceptTCP(connection.getID(), playerState.getName() + " connected.");
		System.out.println(playerState.getName() + " connected.");
	}
}
