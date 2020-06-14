package multi.threading.tuts.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConnectionTest {

	public static void main(String[] args) {

		Connection connection = Connection.getInstance();

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 200; i++) {
			executorService.submit(() -> {
				connection.connect();
			});
		}

		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
