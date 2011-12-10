package server.networking.listeners;

import server.networking.handlers.GameServerConnectedHandler;
import server.networking.handlers.GameServerDisconnectedHandler;
import server.networking.handlers.GameServerReceivedHandler;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class GameServerListener extends Listener {
	GameServerConnectedHandler connectedHandler = new GameServerConnectedHandler();
	GameServerReceivedHandler receivedHandler = new GameServerReceivedHandler();
	GameServerDisconnectedHandler disconnectedHandler = new GameServerDisconnectedHandler();
	
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
