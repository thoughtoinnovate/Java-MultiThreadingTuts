package multi.threading.tuts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnderstandingThreadlocking {

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	Random random = new Random();

	public void operateList1() {
		synchronized (lock1) {

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt());
		}
	}

	public void operateList2() {
		synchronized (lock2) {

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt());
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			operateList1();
			operateList2();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		long start = System.currentTimeMillis();

		UnderstandingThreadlocking uThreadlocking = new UnderstandingThreadlocking();

		Thread t1 = new Thread(() -> {
			uThreadlocking.process();
		});
		Thread t2 = new Thread(() -> {
			uThreadlocking.process();
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		long end = System.currentTimeMillis();

		System.out.println("Total Execution Time taken:" + (end - start));
		System.out.println("L1 size:" + uThreadlocking.list1.size() + " L1 size:" + uThreadlocking.list2.size());
	}

}
