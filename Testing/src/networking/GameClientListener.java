package networking;

import message.ServerResponse;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class GameClientListener extends Listener {
	Client client;
	
	public GameClientListener(Client client) {
		this.client = client;
	}
	
	@Override
	public void connected (Connection connection) {
		
	}
	
	@Override
	public void received (Connection connection, Object object) {
		if(object instanceof ServerResponse) {
			ServerResponse response = (ServerResponse)object;
			System.out.println(response);
		} else if (object instanceof String) {
			System.out.println(object.toString());
		}
	}
	
	@Override
	public void disconnected (Connection connection) {
		
	}
}
