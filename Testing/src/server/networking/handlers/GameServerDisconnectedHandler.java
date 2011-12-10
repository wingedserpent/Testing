package server.networking.handlers;

import server.networking.base.GameServerDataStore;
import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Connection;


public class GameServerDisconnectedHandler {

	public void handleDisconnected(Connection connection) {
		PlayerInfo player = GameServerDataStore.getPlayerInfo(connection.getID());
		GameServerDataStore.server.sendToAllTCP("Player " + player.getName() + " disconnected.");
		System.out.println(player.getName() + " disconnected.");
	}
}
