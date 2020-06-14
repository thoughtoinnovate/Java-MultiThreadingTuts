package multi.threading.tuts;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadInterruptionExample {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		Random rndm = new Random();
		Future<Integer> future = executorService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("Started...");
				
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Hey Thread was interrupted!");
				}

				int nextInt = rndm.nextInt(10000);
				Thread.sleep(nextInt);
				
				System.out.println("Finished!");
				return nextInt;
			}
		});
		executorService.shutdown();
		
		//below  interrupts the running thread
		//executorService.shutdownNow();
		try {
			Integer val = future.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(" It was Interrupted!");
		}
		
	}

}
