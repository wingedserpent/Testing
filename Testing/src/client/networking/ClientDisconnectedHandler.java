package client.networking;

import com.esotericsoftware.kryonet.Connection;

/**
 * Handles all incoming 'disconnected' notifications from the client listener.
 */
public class ClientDisconnectedHandler {
	
	/**
	 * Handles the activity immediately after this connection ends.
	 */
	public void handleDisconnected(Connection connection) {
		System.out.println("Disconnected. Exiting...");
		System.exit(0);
	}
}
