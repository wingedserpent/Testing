package client.networking.listeners;

import client.networking.handlers.GameClientConnectedHandler;
import client.networking.handlers.GameClientDisconnectedHandler;
import client.networking.handlers.GameClientReceivedHandler;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class GameClientListener extends Listener {
	GameClientConnectedHandler connectedHandler = new GameClientConnectedHandler();
	GameClientReceivedHandler receivedHandler = new GameClientReceivedHandler();
	GameClientDisconnectedHandler disconnectedHandler = new GameClientDisconnectedHandler();
	
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
