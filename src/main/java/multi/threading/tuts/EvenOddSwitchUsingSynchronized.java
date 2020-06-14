package multi.threading.tuts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EvenOddSwitchUsingSynchronized {

	private Integer val = 1;
	private Object lock = new Object();

	public void printOdd() throws InterruptedException {
		synchronized (lock) {
			while (val < 100) {
				if (isEven(val)) {
					lock.wait();
				}
				System.out.println(Thread.currentThread().getName() + " Odd#" + val);
				val++;
				lock.notify();
			}
		}

	}

	private boolean isEven(Integer val) {
		return val % 2 == 0;
	}

	public void printEven() throws InterruptedException {

		synchronized (lock) {
			while (val < 101) {
				if (!isEven(val)) {
					lock.wait();
				}
				System.out.println(Thread.currentThread().getName() + " Even#" + val);
				val++;
				lock.notify();
			}
			
		}
	}

	public static void main(String[] args) throws InterruptedException {

		EvenOddSwitchUsingSynchronized evnOdd = new EvenOddSwitchUsingSynchronized();
		Runnable odd = () -> {
			try {
				evnOdd.printOdd();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		Runnable even = () -> {
			try {
				evnOdd.printEven();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(odd);
		executorService.submit(even);
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);
		

	}
}
