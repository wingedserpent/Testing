package client;

import shared.game.PlayerState;
import client.display.DisplayMain;

/**
 * The main control loop for the client.
 */
public class ClientMainThread implements Runnable {
	Thread t;
	
	/**
	 * Inits and starts a new thread, which runs this {@link ClientMainThread}'s {@link #run}.
	 */
	public ClientMainThread() {
		t = new Thread(this);
		t.setName("ClientMain");
		t.start();
	}
	
	/**
	 * The main running loop for a client.
	 */
	@Override
	public void run() {
		initPlayer();
		
		DisplayMain displayMain = new DisplayMain();
		displayMain.start(); //NOTE: this will block until the display is closed. display should run in its own thread?
		while(t.isAlive()) {
			ClientDataStore.getClient().sendTCP("string...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//init the player with a name, store it in the data store, and send it to the server to say hello
	private void initPlayer() {
		PlayerState player = new PlayerState();
		player.setConnectionId(ClientDataStore.getClient().getID());
		player.setName("dan");
		player.setXPos(400f);
		player.setyPos(300f);
		ClientDataStore.setPlayerState(player);
		//and send it to the server
		ClientDataStore.getClient().sendTCP(ClientDataStore.getPlayerState());
	}
}
