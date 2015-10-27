package no.plasmid.order;

public class AiClient {

	private static final Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Vi er i gang");
		
		while (true) {
			System.out.println("Det loopes");
			synchronized (lock) {
				lock.wait(10000);
			}
		}
	}

}
