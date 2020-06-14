package multi.threading.tuts;

public class SynchronizedExample {

	private int i = 0;

	public  synchronized void increment() {
		i++;
	}

	public static void main(String... main) throws InterruptedException {
		SynchronizedExample s = new SynchronizedExample();

		Thread t1 = new Thread(() -> {
			{
				for (int i = 0; i < 1000; i++) {
					s.increment();

				}
			}

		});
		Thread t2 = new Thread(() -> {
			{
				for (int i = 0; i < 1000; i++) {
					s.increment();

				}
			}

		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Value of i is :"+s.i);

	}
}
