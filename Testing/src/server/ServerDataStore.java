package server;

import java.util.HashMap;
import java.util.Map;

import shared.game.PlayerInfo;

import com.esotericsoftware.kryonet.Server;

//singleton class for storing all data related to a server
public abstract class ServerDataStore {
	//the server object is stored here so any class can send messages through it
	private static Server server;
	
	//a map containing all currently connected players' info, keyed by connection id
	private static Map<Integer, PlayerInfo> playerInfoMap = new HashMap<Integer, PlayerInfo>();
	
	public static void setServer(Server server) {
		ServerDataStore.server = server;
	}
	
	public static Server getServer() {
		return server;
	}
	
	public static void updatePlayerInfoMap(PlayerInfo playerInfo) {
		if(playerInfoMap.containsKey(playerInfo.getConnectionId()))
			playerInfoMap.remove(playerInfo.getConnectionId());
		playerInfoMap.put(playerInfo.getConnectionId(), playerInfo);
	}
	
	public static void removeFromPlayerInfoMap(Integer id) {
		playerInfoMap.remove(id);
	}
	
	//gets the player object associated with any connection
	public static PlayerInfo getPlayerInfo(Integer connectionId) {
		return playerInfoMap.get(connectionId);
	}
}
