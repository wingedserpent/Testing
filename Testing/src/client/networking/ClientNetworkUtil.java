package client.networking;

import client.ClientDataStore;

/**
 * Abstract class containing networking utility methods specific to the server.
 */
public abstract class ClientNetworkUtil {

	/**
	 * Sends the latest {@link ClientDataStore#playerState} to the server.
	 */
	public static void sendPlayerState() {
		ClientDataStore.getClient().sendUDP(ClientDataStore.getPlayerState());
	}
}
