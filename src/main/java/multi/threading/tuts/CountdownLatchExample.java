package multi.threading.tuts;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountdownLatchExample implements Runnable {
	
	private CountDownLatch CDLatch;
	
	
	
	public CountdownLatchExample(CountDownLatch cDLatch) {
		super();
		CDLatch = cDLatch;
	}



	@Override
	public void run() {
		System.out.println("Started.. by thread: "+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Work Completed!");
	}

}

class CDLApp{
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 4; i++) {
			executorService.submit(new CountdownLatchExample(countDownLatch));
		}
		
		countDownLatch.await();
		
		
	}
}