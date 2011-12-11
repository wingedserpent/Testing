package server.networking.listeners;

import server.networking.handlers.ServerConnectedHandler;
import server.networking.handlers.ServerDisconnectedHandler;
import server.networking.handlers.ServerReceivedHandler;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener {
	ServerConnectedHandler connectedHandler = new ServerConnectedHandler();
	ServerReceivedHandler receivedHandler = new ServerReceivedHandler();
	ServerDisconnectedHandler disconnectedHandler = new ServerDisconnectedHandler();
	
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
