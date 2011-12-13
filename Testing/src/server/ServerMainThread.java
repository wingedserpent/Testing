package server;

import client.ClientMainThread;

/**
 * The main control loop for the server.
 */
public class ServerMainThread implements Runnable {
	Thread mainThread;
	
	/**
	 * Inits and starts a new thread, which runs this {@link ClientMainThread}'s {@link #run}.
	 */
	public ServerMainThread() {
		mainThread = new Thread(this);
		mainThread.setName("ClientMain");
		mainThread.start();
	}
	
	@Override
	public void run() {
		//TODO send playerStateMapDeltas every x millis
	}

}
