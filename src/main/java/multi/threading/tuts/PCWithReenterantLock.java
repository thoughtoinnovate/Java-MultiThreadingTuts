package multi.threading.tuts;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCWithReenterantLock {
	
	private int val =0;
	
	public int getVal() {
		return val;
	}

	private Lock lock = new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	
	private void increment() {
		for (int i = 0; i < 10000; i++) {
			val++;
		}
	}
	
	public void firstWorker() throws InterruptedException {
		
		System.out.println("first worker started..");
		lock.lock();
		condition.await();
		try {
		increment();
		}finally {
		lock.unlock();
		}
		System.out.println("first end...");
	}
	
	public void secondWorker() throws InterruptedException  {
		Thread.sleep(2000);
		lock.lock();
		System.out.println("second worker started..");
		System.out.println("Please enter key");
		new Scanner(System.in).nextLine();
		condition.signal();
		try{
			increment();
		}finally {
			lock.unlock();
		}
		System.out.println("Second end..");
	}

}

class RenterantApp{
	public static void main(String[] args) throws InterruptedException {
		PCWithReenterantLock pLock = new PCWithReenterantLock();
		Thread t1 = new Thread(()->{try {
			pLock.firstWorker();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		Thread t2 = new Thread(()->{try {
			pLock.secondWorker();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Final val is:"+pLock.getVal());
	}
}
