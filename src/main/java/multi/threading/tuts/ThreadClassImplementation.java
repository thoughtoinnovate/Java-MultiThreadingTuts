package multi.threading.tuts;

public class ThreadClassImplementation extends Thread {

	public void run() {
		for(int i=0; i<11;i++) {
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
