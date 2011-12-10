import java.io.IOException;
import java.util.Scanner;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.ObjectSpace.InvokeMethod;

public class TestClient {
	Client client;
	//this dude is private!
	private ISharedObject sharedObj;
	
	public void start() {
		client = new Client();
		client.start();
		try {
			client.connect(5000, "localhost", 54555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Kryo kryo = client.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		kryo.register(InvokeMethod.class);
		kryo.register(ISharedObject.class);
		
		client.addListener(new Listener() {
			@Override
			public void received (Connection connection, Object object) {
				if (object instanceof SomeResponse) {
					SomeResponse response = (SomeResponse)object;
					System.out.println(response.text);
		      	}
			}
			@Override
			public void disconnected (Connection connection) {
				client.stop();
				System.exit(0);
			}
		});
		
		//register the obj with an ObjectSpace so it can be used by the server
		ObjectSpace objectSpace = new ObjectSpace();
		sharedObj = new SharedObject();
		objectSpace.register(42, sharedObj);
		objectSpace.addConnection(client);

		SomeRequest request = new SomeRequest();
		request.text = "Here is the request!";
		client.sendTCP(request);
		
		System.out.println("Now accepting input. Enter /exit to quit.");
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.next();
		while(input != null && !input.equals("/exit")) {
			client.sendTCP(input);
			input = scanner.next();
		}
		System.out.println("Finished accepting input.");
		
		client.stop();
	}

	public static void main(String[] args) {
		TestClient testClient = new TestClient();
		testClient.start();
	}
}
