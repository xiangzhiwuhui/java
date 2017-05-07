package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

	public static void main(String[] args) {
		//ExecutorService是具有服务生命周期的Executor；
		//ExecutorService用来管理Thread对象
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Hello4()); //创建线程并启动线程
		}
		
		exec.shutdown(); //关闭服务

	}

}

class Hello4 extends Thread{
	
	int i = 0;
	
	@Override
	public void run(){
		while(true){
			System.out.println(Thread.currentThread().getName() + ": Hello " + ++i);
			if(i == 5) break;
		}
	}
}
