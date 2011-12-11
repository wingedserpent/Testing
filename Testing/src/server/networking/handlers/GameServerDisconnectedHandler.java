package server.networking.handlers;

import server.GameServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;


public class GameServerDisconnectedHandler {

	public void handleDisconnected(Connection connection) {
		PlayerInfo player = GameServerDataStore.getPlayerInfo(connection.getID());
		GameServerDataStore.getServer().sendToAllTCP(player.getName() + " disconnected.");
		System.out.println(player.getName() + " disconnected.");
		
		//remove the player from the data store
		GameServerDataStore.removeFromPlayerInfoMap(player.getConnectionId());
	}
}
