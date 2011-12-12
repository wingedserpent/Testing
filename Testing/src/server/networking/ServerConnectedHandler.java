package server.networking;

import shared.networking.Network;

import com.esotericsoftware.kryonet.Connection;

public class ServerConnectedHandler {

	public void handleConnected(Connection connection) {
		Network.setUpConnection(connection);
	}
}
