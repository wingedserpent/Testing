package client.networking.handlers;

import shared.message.ServerResponse;

import com.esotericsoftware.kryonet.Connection;

public class GameClientReceivedHandler {

	public void handleReceived(Connection connection, Object object) {
		if(object instanceof ServerResponse) {
			ServerResponse response = (ServerResponse)object;
			System.out.println(response);
		} else if (object instanceof String) {
			System.out.println(object.toString());
		}
	}
}
