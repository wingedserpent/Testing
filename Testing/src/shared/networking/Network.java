package shared.networking;

import java.util.HashMap;

import shared.game.PlayerState;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.ObjectSpace.InvokeMethod;
import com.esotericsoftware.minlog.Log;

public abstract class Network {
	static public final String HOST_IP = "localhost";
	static public final int PORT_TCP = 54777;
	static public final int TIMEOUT_CONNECT = 10000;
	static public final int TIMEOUT_TCP = 0;
	static public final int KEEP_ALIVE_TCP = 0;
	static public final int LOG_LEVEL = Log.LEVEL_INFO;

	// These IDs are used to register objects in ObjectSpaces.
	static public final short OBJECTSPACE_ID_PLAYER = 1;

	// This registers objects that are going to be sent over the network.
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		
		// This must be called in order to use ObjectSpaces.
		ObjectSpace.registerClasses(kryo);
		
		// The interfaces that will be used as remote objects must be registered.
		kryo.register(PlayerState.class);
		kryo.register(InvokeMethod.class);
		kryo.register(HashMap.class);
	}
	
	static public void setUpConnection(Connection connection) {
		connection.setKeepAliveTCP(KEEP_ALIVE_TCP);
		connection.setTimeout(TIMEOUT_TCP);
	}
}
