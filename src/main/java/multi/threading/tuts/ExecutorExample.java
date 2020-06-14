package multi.threading.tuts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample implements Runnable {

	private int id;

	public ExecutorExample(int id) {
		super();
		this.id = id;
	}
	
	

	@Override
	public void run() {
		
		String threadName = Thread.currentThread().getName();
		
		System.out.println("Started task with id:"+id+" executed by thread:"+threadName);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed task with id:"+id+" completed by thread:"+threadName);
		
	}

}

 class App {

	public static void main(String[] args)  {

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			executorService.submit(new ExecutorExample(i));	
		}
		System.out.println("All tasks submitted!");
		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All taks Completed!");
	}
}
