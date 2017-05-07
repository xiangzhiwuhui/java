package multithread;

public class ThreadTerminateTest {

	public static void main(String[] args) throws Exception{
		int i = 0;
		Hello3 h = new Hello3();
		Thread t = new Thread(h);
		t.setPriority(Thread.MAX_PRIORITY); //设置线程优先级
		t.start(); //启动线程
		System.out.println("Please stop saying Hello and say good morning !");
		h.stopRunning();
		
		while(i < 5){
			System.out.println("Good Morning " + ++i);
		}

	}

}

class Hello3 implements Runnable{
	int i = 0;
	private boolean timeToQuit = false;
	//标志没有设置前，将每隔10ms输出2行Hello
	@Override
	public void run(){
		while(!timeToQuit){
			System.out.println("Hello " + ++i);
			try {
				if(i % 2 == 0)
				Thread.sleep(10);
			} catch (Exception e) {}
		}
	}
	
	public void stopRunning(){
		timeToQuit = true;
	}
	
}
