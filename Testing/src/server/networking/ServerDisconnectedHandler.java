package server.networking;

import server.ServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;


public class ServerDisconnectedHandler {

	public void handleDisconnected(Connection connection) {
		PlayerInfo player = ServerDataStore.getPlayerInfo(connection.getID());
		ServerDataStore.getServer().sendToAllTCP(player.getName() + " disconnected.");
		System.out.println(player.getName() + " disconnected.");
		
		//remove the player from the data store
		ServerDataStore.removeFromPlayerInfoMap(player.getConnectionId());
	}
}
