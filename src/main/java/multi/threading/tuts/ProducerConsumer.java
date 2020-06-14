package multi.threading.tuts;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumer {

	private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void producer() throws InterruptedException {

		System.out.println("Producer Started!");
		Random random = new Random();
		while (1==1) {
			queue.put(random.nextInt(100));
		}
	}

	public static void consumer() throws InterruptedException {

		System.out.println("Consumer Started...");
		Random random = new Random();
		while (true) {
			Thread.sleep(100);
				Integer val = queue.take();
				System.out.println("Value Consumed val:"+val+" queue size:"+queue.size());
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Thread producer = new Thread(()->{
			try {
				producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread consumer = new Thread(()->{
			try {
				consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		producer.start();
		consumer.start();
		producer.join();
		consumer.join();

	}

}
