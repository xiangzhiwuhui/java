package multithread;

public class ThreadJoinTest {

	public static void main(String[] args) throws Exception{
		
		int i = 0;
		
		Thread t = new Hello2();
		t.start();
		
		while(true){
			System.out.println("Good Morning " + ++i);
			
			if (i == 2 && t.isAlive()) {
				System.out.println("Main waiting for Hello2 !");
				t.join(); //等待t运行结束
			}
			
			if(i == 5) break;
		}

	}

}

class Hello2 extends Thread{
	
	int i = 0;
	
	@Override
	public void run(){
		while(true){
			System.out.println(Thread.currentThread().getName() + ": Hello " + ++i);
			if(i == 5) break;
		}
	}
}
