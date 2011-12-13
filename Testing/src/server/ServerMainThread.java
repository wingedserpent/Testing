package server;

import server.networking.ServerNetworkUtil;

/**
 * The main control loop for the server.
 */
public class ServerMainThread implements Runnable {
	Thread mainThread;
	
	/**
	 * Inits and starts a new thread, which runs this {@link ServerMainThread}'s {@link #run}.
	 */
	public ServerMainThread() {
		mainThread = new Thread(this);
		mainThread.setName("ClientMain");
		mainThread.start();
	}
	
	/**
	 * Inits game values and controls the main running loop, {@link #tick}, for a server.
	 */
	@Override
	public void run() {
		//TODO run tick only 30x/sec!!
		while(true) {
			tick();
		}
	}
	
	/**
	 * Processes a single server tick.
	 */
	public void tick() {
		if(!ServerDataStore.getPlayerStateMapDeltas().isEmpty()) {
			ServerNetworkUtil.sendPlayerStateDeltasToAll();
			ServerDataStore.clearPlayerStateMapDeltas();
		}
	}
}
