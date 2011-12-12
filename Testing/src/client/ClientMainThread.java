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
			System.out.println("running...");
		}
	}

}
