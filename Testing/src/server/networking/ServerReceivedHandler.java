package server.networking;

import server.ServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;

public class ServerReceivedHandler {
	
	public void handleReceived(Connection connection, Object object) {
		if(object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof PlayerInfo) {
			PlayerInfo playerInfo = (PlayerInfo)object;
			handlePlayerInfo(connection, playerInfo);
		}
	}
	
	//this is a request to update the playerInfo map in the data store
	private void handlePlayerInfo(Connection connection, PlayerInfo playerInfo) {
		if(ServerDataStore.getPlayerInfo(connection.getID()) == null) {
			//the player has just connected
			initialConnect(connection, playerInfo);
		}
		//update player in data store
		ServerDataStore.updatePlayerInfoMap(playerInfo);
		
		//dispatch the updated map to all players
		ServerDataStore.getServer().sendToAllTCP(ServerDataStore.getPlayerInfoMap());
	}
	
	//handle first update from a player
	private void initialConnect(Connection connection, PlayerInfo playerInfo) {
		connection.sendTCP("Welcome to the server!");
		ServerDataStore.getServer().sendToAllExceptTCP(connection.getID(), playerInfo.getName() + " connected.");
		System.out.println(playerInfo.getName() + " connected.");
	}
}
