package multi.threading.tuts;



public class ThreadRunner {

	public static void main(String[] args) {

		/*
		 * ThreadClassImplementation t1 = new ThreadClassImplementation();
		 * ThreadClassImplementation t2 = new ThreadClassImplementation();
		 * 
		 * t1.start(); t2.start();
		 */
		
		//Runnable way
		/*
		 * Thread t1 = new Thread(new Runner()); Thread t2 = new Thread(new Runner());
		 * t1.start();t2.start();
		 */
		
		//Anonymous class way
		Runnable r1 =()->{for (int i = 0; i < 11; i++) {
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		t1.start(); t2.start();
	}

}
