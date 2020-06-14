package multi.threading.tuts.semaphore;

import java.util.concurrent.Semaphore;

/**
 * This is Singleton class
 * 
 * @author thoughtoinnovate
 *
 */
public class Connection {

	private static Connection connect = new Connection();
	private int connectionCount = 0;
	private Semaphore semaphore = new Semaphore(10, true);

	public static Connection getInstance() {

		return connect;
	}

	private Connection() {
	}

	public void connect() {

		try {
			semaphore.acquire();
			doConnect();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaphore.release();
		}

	}

	private void doConnect() throws InterruptedException {

		synchronized (this) {
			connectionCount++;
			System.out.println("Cuurent Connections :" + connectionCount);
		}

		
		  Thread.sleep(2000);
		  
		  synchronized (this) { connectionCount--; }
		 

	}

}
