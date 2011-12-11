package client.networking.handlers;

import com.esotericsoftware.kryonet.Connection;

public class GameClientReceivedHandler {

	public void handleReceived(Connection connection, Object object) {
		if (object instanceof String) {
			System.out.println(object.toString());
		}
	}
}
