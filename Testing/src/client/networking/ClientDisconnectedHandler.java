package client.networking;

import com.esotericsoftware.kryonet.Connection;

public class ClientDisconnectedHandler {

	public void handleDisconnected(Connection connection) {
		System.out.println("Disconnected. Exiting...");
		System.exit(0);
	}
}
