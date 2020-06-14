package multi.threading.tuts;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableExample {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> future = executorService.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("Work Started....");
				Integer waitTime = new Random().nextInt(4000);
				if (waitTime > 2000) {
					throw new IOException("Too much wait time..");
				}
				Thread.sleep(waitTime);
				System.out.println("Work Finished!");
				return waitTime;
			}
		});

		executorService.shutdown();

		try {
			Integer result = future.get();
			System.out.println("The Result from callable is :" + result);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}

