package server.networking;

import server.ServerDataStore;

/**
 * Abstract class containing networking utility methods specific to the server.
 */
public abstract class ServerNetworkUtil {

	/**
	 * Dispatches the latest playerStateMap deltas to all clients.
	 */
	public static void sendPlayerStateDeltasToAll() {
		//TODO make this send deltas instead of the whole map
		ServerDataStore.getServer().sendToAllTCP(ServerDataStore.getPlayerStateMap());
	}
}
