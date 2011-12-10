package server.networking.base;


import java.util.HashMap;
import java.util.Map;

import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Server;

//singleton class for storing all data related to a server
public abstract class GameServerDataStore {
	//the server object is stored here so any class can send messages through it
	public static Server server;
	
	//a map containing all currently connected players' info, keyed by connection id
	public static Map<Integer, PlayerInfo> playerInfoMap = new HashMap<Integer, PlayerInfo>();
	
	//updates the playerInfoMap with new data for the given player
	public static void updatePlayerMap(PlayerInfo playerInfo) {
		playerInfoMap.remove(playerInfo.getConnectionId());
		playerInfoMap.put(playerInfo.getConnectionId(), playerInfo);
	}
	
	//gets the player object associated with any connection
	public static PlayerInfo getPlayerInfo(Integer id) {
		return playerInfoMap.get(id);
	}
}
