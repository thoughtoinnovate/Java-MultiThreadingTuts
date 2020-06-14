package multi.threading.tuts;

public class Proc1 extends Thread{
	private volatile boolean isRunning=true;
	public void run() {
		while(isRunning) {
			System.out.println("Running....");
		}
	}
	
	public void shutdown() {
		isRunning=false;
	}

}
