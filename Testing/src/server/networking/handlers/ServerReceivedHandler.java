package server.networking.handlers;

import server.ServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;

public class ServerReceivedHandler {
	
	public void handleReceived(Connection connection, Object object) {
		if(object instanceof String) {
			System.out.println(object.toString());
		} else if (object instanceof PlayerInfo) {
			//this is a request to update the playerInfo map in the data store
			
			PlayerInfo playerInfo = (PlayerInfo)object;
			if(ServerDataStore.getPlayerInfo(connection.getID()) == null) {
				//initial connect. send welcome messages
				connection.sendTCP("Welcome to the server!");
				ServerDataStore.getServer().sendToAllExceptTCP(connection.getID(), playerInfo.getName() + " connected.");
				System.out.println(playerInfo.getName() + " connected.");
			}
			//update player in data store
			ServerDataStore.updatePlayerInfoMap(playerInfo);
		}
	}
}
