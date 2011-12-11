package client.networking;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientListener extends Listener {
	ClientConnectedHandler connectedHandler = new ClientConnectedHandler();
	ClientReceivedHandler receivedHandler = new ClientReceivedHandler();
	ClientDisconnectedHandler disconnectedHandler = new ClientDisconnectedHandler();
	
	@Override
	public void connected (Connection connection) {
		connectedHandler.handleConnected(connection);
	}
	
	@Override
	public void received (Connection connection, Object object) {
		receivedHandler.handleReceived(connection, object);
	}
	
	@Override
	public void disconnected (Connection connection) {
		disconnectedHandler.handleDisconnected(connection);
	}
}
