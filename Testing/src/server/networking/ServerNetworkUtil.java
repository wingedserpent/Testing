package server.networking;

import server.ServerDataStore;

public abstract class ServerNetworkUtil {

	/**
	 * Dispatches the latest playerStateMap deltas to all clients.
	 */
	public static void sendPlayerStateDeltasToAll() {
		ServerDataStore.getServer().sendToAllTCP(ServerDataStore.getPlayerStateMap());
	}
}
