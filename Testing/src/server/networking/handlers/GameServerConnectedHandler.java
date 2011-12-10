package server.networking.handlers;

import server.networking.base.GameServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;

public class GameServerConnectedHandler {

	public void handleConnected(Connection connection) {
		//get the new player and put him in the data store
		
		connection.sendTCP("Welcome to the server!");
		PlayerInfo player = GameServerDataStore.getPlayerInfo(connection.getID());
		GameServerDataStore.server.sendToAllTCP(player.getName() + " connected.");
		System.out.println(player.getName() + " connected.");
	}
}
