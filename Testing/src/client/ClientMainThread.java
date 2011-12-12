package client;

public class ClientMainThread implements Runnable {
	Thread t;
	
	public ClientMainThread() {
		t = new Thread(this);
		t.setName("ClientMain");
		t.start();
	}

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
