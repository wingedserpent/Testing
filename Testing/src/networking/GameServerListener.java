package networking;

import java.util.HashMap;
import java.util.Map;

import game.IPlayer;
import message.ClientRequest;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;

public class GameServerListener extends Listener {
	Server server;
	Map<Long, IPlayer> playerMap = new HashMap<Long, IPlayer>();
	
	GameServerListener(Server server) {
		this.server = server;
	}
	
	@Override
	public void connected (Connection connection) {
		connection.sendTCP("Welcome to the server!");
		IPlayer player = getPlayer(connection);
		server.sendToAllTCP(player.getName() + " connected.");
		System.out.println(player.getName() + " connected.");
	}
	
	@Override
	public void received (Connection connection, Object object) {
		if(object instanceof ClientRequest) {
			handleRequest((ClientRequest)object);
		}
	}
	
	@Override
	public void disconnected (Connection connection) {
		IPlayer player = getPlayer(connection);
		server.sendToAllTCP("Player " + player.getName() + " disconnected.");
		System.out.println(player.getName() + " disconnected.");
	}
	
	//gets the player object associated with any connection
	private IPlayer getPlayer(Connection connection) {
		IPlayer player = (IPlayer) ObjectSpace.getRemoteObject(connection, Network.OBJECTSPACE_ID_PLAYER, IPlayer.class);
		((RemoteObject)player).setNonBlocking(true, true);
		return player;
	}
	
	//handles any case of a generic client request
	private void handleRequest(ClientRequest request) {
		System.out.println(request);
	}
}
