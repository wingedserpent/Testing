package server.networking;

import shared.networking.Network;

import com.esotericsoftware.kryonet.Connection;

/**
 * Handles all incoming 'connected' notifications from the server listener.
 */
public class ServerConnectedHandler {
	
	/**
	 * Handles the activity immediately after this connection begins.
	 */
	public void handleConnected(Connection connection) {
		Network.setUpConnection(connection);
	}
}
