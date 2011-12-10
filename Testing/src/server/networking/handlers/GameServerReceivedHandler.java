package server.networking.handlers;

import shared.message.ClientRequest;

import com.esotericsoftware.kryonet.Connection;

public class GameServerReceivedHandler {
	
	public void handleReceived(Connection connection, Object object) {
		if(object instanceof ClientRequest) {
			System.out.println(object.toString());
		}
	}
}
