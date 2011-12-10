import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.ObjectSpace.InvokeMethod;
import com.esotericsoftware.kryonet.rmi.RemoteObject;

public class Main {
	Server server;
	
	public void start() {
		server = new Server();
		server.start();
		try {
			server.bind(54555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Kryo kryo = server.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		kryo.register(InvokeMethod.class);
		kryo.register(ISharedObject.class);
		
		server.addListener(new Listener() {
			@Override
			public void received (Connection connection, Object object) {
				if (object instanceof SomeRequest) {
		        SomeRequest request = (SomeRequest)object;
		        System.out.println(request.text);
		
		        SomeResponse response = new SomeResponse();
		        response.text = "Screw that, I'm going to touch your innards!";
		        connection.sendTCP(response);
		         
		        //get a proxy copy of the client's private object
		        ISharedObject otherObject = (ISharedObject) ObjectSpace.getRemoteObject(connection, 42, ISharedObject.class);
			 	((RemoteObject)otherObject).setNonBlocking(true, true);
			 	//and make it dance
			 	otherObject.printMessage();
		      } else if (object instanceof String) {
		    	  String rcvd = (String)object;
		    	  System.out.println("Client sent: " + rcvd);
		    	  server.sendToAllTCP(connection.getRemoteAddressTCP()+" says: "+rcvd);
		      }
			}
			@Override
			public void disconnected (Connection connection) {
				if(server.getConnections().length == 0)
					server.stop();
			}
		});
		
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		//init opengl
		
		
		while (!Display.isCloseRequested()) {
			Display.update();
		}
		Display.destroy();
		server.stop();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
}

