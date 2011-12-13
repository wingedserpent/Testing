package client;

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
		while(t.isAlive()) {
			ClientDataStore.getClient().sendTCP("string...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
