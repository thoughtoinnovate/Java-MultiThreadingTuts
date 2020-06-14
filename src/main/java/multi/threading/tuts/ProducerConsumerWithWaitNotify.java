package multi.threading.tuts;

import java.util.Scanner;

public class ProducerConsumerWithWaitNotify {

	public void producer() throws InterruptedException {

		synchronized (this) {
			System.out.println("Producer Running..");
			wait();
			System.out.println("Producer resumed");
		}

	}

	public void consumer() throws InterruptedException {
		Thread.sleep(2000);
		java.util.Scanner scanner = new Scanner(System.in);

		synchronized (this) {
			System.out.println("Consumer started running..");
//			/notify();
			System.out.println("Enter key");
			scanner.hasNextLine();
		}
	}
}

class PCWNApp{
	public static void main(String[] args) {
		ProducerConsumerWithWaitNotify pcNotify = new ProducerConsumerWithWaitNotify();
		Thread t1 = new Thread(()->{
			try {
				pcNotify.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				pcNotify.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}
}
