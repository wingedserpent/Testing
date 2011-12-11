package server.networking.handlers;

import server.GameServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;

public class GameServerReceivedHandler {
	
	public void handleReceived(Connection connection, Object object) {
		if(object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof PlayerInfo) {
			//this is a request to update the playerInfo map in the data store
			
			PlayerInfo playerInfo = (PlayerInfo)object;
			if(GameServerDataStore.getPlayerInfo(connection.getID()) == null) {
				//initial connect. send welcome messages
				connection.sendTCP("Welcome to the server!");
				GameServerDataStore.getServer().sendToAllExceptTCP(connection.getID(), playerInfo.getName() + " connected.");
				System.out.println(playerInfo.getName() + " connected.");
			}
			//update player in data store
			GameServerDataStore.updatePlayerInfoMap(playerInfo);
		}
	}
}
