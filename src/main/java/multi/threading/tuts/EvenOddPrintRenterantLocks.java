package multi.threading.tuts;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Program to do context switch between two threads using {@link ReentrantLock}
 * @author Hitesh
 *
 */
public class EvenOddPrintRenterantLocks {

	private int val = 1;
	private Lock lock = new ReentrantLock();
	private Condition oddCondition = lock.newCondition();
	private Condition evenCondition = lock.newCondition();

	private void printOdd() throws InterruptedException {
				
		while(val<101) {
			lock.lock();
		if (val % 2 == 0) {
			evenCondition.signal();
			if(val<100) {
			oddCondition.await();
			}else {
				lock.unlock();
				return;
			}
			
		}
		
		System.out.println(Thread.currentThread().getName() + " Odd#" + val);
		val++;
		lock.unlock();
		}
		
	}

	private void printEven() throws InterruptedException {
		
		while(val<101) {
			lock.lock();
		if (val % 2 != 0) {
			oddCondition.signal();
			evenCondition.await();
			
		}
		System.out.println(Thread.currentThread().getName() + " Even#" + val);
		val++;
		lock.unlock();
		}
		
		
	}
	
	

	public static void main(String[] args) {
		EvenOddPrintRenterantLocks evenOddPrint = new EvenOddPrintRenterantLocks();
		Runnable oddRunner = () -> {
			try {
				evenOddPrint.printOdd();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		Runnable evenRunner = () -> {
			try {
				evenOddPrint.printEven();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};

		Thread t1 = new Thread(oddRunner);
		Thread t2 = new Thread(evenRunner);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
