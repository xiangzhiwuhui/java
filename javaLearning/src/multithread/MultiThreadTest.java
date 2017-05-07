package multithread;


/**
 * 多线程学习
 * */

public class MultiThreadTest {

	public static void main(String[] args) {
//		 //1.通过继承Thread类实现多线程
//		Hello t1 = new Hello();
//		Hello t2 = new Hello();
//		
//		t1.start();
//		t2.start();
		
//		//2.通过实现Runnable接口实现多线程
//		Thread t3 = new Thread(new Hello1());
//		Thread t4 = new Thread(new Hello1());
//		t3.start();
//		t4.start();
		
		//3.测试多线程
		TestThread tt = new TestThread();
		Thread t5 = new Thread(tt);
		Thread t6 = new Thread(tt);
		t5.start();
		t6.start();
		
		
		

	}

}


// 1.通过继承Thread类实现多线程
class Hello extends Thread{
	int i;
	@Override
	public void run(){
		while (true) {
			System.out.println(Thread.currentThread().getName() + ": Hello " + ++i);
			if (i == 5) break;
			Thread.yield();
		}
	}
}

//2.通过实现Runnable接口实现多线程
class Hello1 implements Runnable{
	int i;
	@Override
	public void run(){
		while (true) {
			System.out.println(Thread.currentThread().getName() + ": Hello " + ++i);
			if (i == 5) break;
		}
	}
}

//3.测试多线程
class TestThread implements Runnable{
	
	private static int i = 0;
	int n = 0;
	
	@Override
	public void run(){
		while(true){
			System.out.println(Thread.currentThread().getName() + ": Hello " + ++n);
			Thread.yield();
			if (n >= 30) break;
		}
	}
}





















