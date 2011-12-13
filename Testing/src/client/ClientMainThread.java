package client;

import shared.game.PlayerState;
import client.display.DisplayMain;

public class ClientMainThread implements Runnable {
	Thread t;
	
	public ClientMainThread() {
		t = new Thread(this);
		t.setName("ClientMain");
		t.start();
	}

	@Override
	public void run() {
		initPlayer();
		
		DisplayMain displayMain = new DisplayMain();
		displayMain.start(); //NOTE: this will block until the display is closed. display should run in its own thread?
	}
	
	//init the player with a name, store it in the data store, and send it to the server to say hello
	private void initPlayer() {
		PlayerState player = new PlayerState();
		player.setConnectionId(ClientDataStore.getClient().getID());
		player.setName("dan");
		player.setXPos(400f);
		player.setyPos(300f);
		ClientDataStore.setPlayer(player);
		//and send it to the server
		ClientDataStore.getClient().sendTCP(ClientDataStore.getPlayer());
	}
}
