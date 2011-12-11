package client.networking;

import com.esotericsoftware.kryonet.Connection;

public class ClientReceivedHandler {

	public void handleReceived(Connection connection, Object object) {
		if (object instanceof String) {
			System.out.println(object.toString());
		}
	}
}
