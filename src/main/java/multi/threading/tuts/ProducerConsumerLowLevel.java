package multi.threading.tuts;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumerLowLevel {

	private LinkedList<Integer> list = new LinkedList<>();
	private int LIMIT = 10;
	private Object lock = new Object();

	public void producer() throws InterruptedException {
		System.out.println("Producer started");

		int val = 0;
		while (true) {
			synchronized (lock) {
				while (LIMIT == list.size()) {
					lock.wait();
				}
				list.add(val++);
				lock.notify();
			}
		}
	}

	public void consumer() throws InterruptedException {
		System.out.println("consumer started!");
		Random rnRandom = new Random();
		while (true) {
			synchronized (lock) {

				while (0 == list.size()) {
					lock.wait();
				}
				int val = list.removeFirst();
				System.out.println("Val:" + val + " removed , list size:" + list.size());
				lock.notify();
			}
		}

	}

}

class PCLLApp {
	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerLowLevel pcNotify = new ProducerConsumerLowLevel();
		Thread t1 = new Thread(() -> {
			try {
				pcNotify.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				pcNotify.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
