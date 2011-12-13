package client;

import shared.game.PlayerState;
import client.display.DisplayMain;

/**
 * The main control loop for the client.
 */
public class ClientMainThread implements Runnable {
	Thread mainThread, displayThread;
	
	/**
	 * Starts a new thread, which runs this {@link ClientMainThread}'s {@link #run}.
	 */
	public ClientMainThread() {
		mainThread = new Thread(this);
		mainThread.setName("ClientMain");
		mainThread.start();
	}
	
	/**
	 * Inits game values, the display thread, and controls the main running loop for a client.
	 */
	@Override
	public void run() {
		initPlayer();
		
		displayThread = new Thread(new DisplayMain());
		displayThread.start();
		
		//60 ticks/second??
		{
			tick();
		}
	}
	
	/**
	 * Processes a single game tick.
	 */
	public void tick() {
		//TODO this is where the playerState should be sent
	}
	
	/**
	 * Inits the client player with a name, stores it in the data store, and sends it to the server to say hello.
	 */
	private void initPlayer() {
		PlayerState player = new PlayerState();
		player.setConnectionId(ClientDataStore.getClient().getID());
		player.setName("dan");
		player.setXPos(400f);
		player.setYPos(300f);
		ClientDataStore.setPlayerState(player);
		//and send it to the server
		ClientDataStore.getClient().sendTCP(ClientDataStore.getPlayerState());
	}
}
