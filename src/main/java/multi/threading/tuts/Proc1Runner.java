package multi.threading.tuts;

public class Proc1Runner {
public static void main(String... args) {
	Proc1 t1 = new Proc1();
	t1.start();
	
	System.out.println("Press retunr to stop..");
	java.util.Scanner scn = new java.util.Scanner(System.in);
	scn.nextLine();
	t1.shutdown();
	

}
}
