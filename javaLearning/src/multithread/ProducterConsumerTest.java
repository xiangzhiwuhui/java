package multithread;

import java.util.Vector;

public class ProducterConsumerTest {

	//生产者消费者问题：生产者不断向一个固定容量的共享数组中添加字母，消费者从共享数组中取出字母
	public static void main(String[] args) throws Exception{
		SyncStack stack = new SyncStack();
		
		Producter p1 = new Producter(stack);
		Thread prodT1 = new Thread(p1, "p1");
		prodT1.start();
		
		Producter p2 = new Producter(stack);
		Thread prodT2 = new Thread(p1, "p2");
		prodT2.start();
		
		Consumer c1 = new Consumer(stack);
		Thread consT1 = new Thread(c1, "consT1");
		consT1.start();
		
//		Consumer c2 = new Consumer(stack);
//		Thread consT2 = new Thread(c1, "consT2");
//		consT2.start();
//		
		
		

	}

}

class SyncStack{
	
	//指定固定大小为10的缓冲区
	private Vector<Character> buffer = new Vector<Character>(1); //存放生产的产品
	
	public synchronized void put(char c){
		while(buffer.size() >= 2){ //如果产品数量等于缓冲区数量则等待
			try {
				this.wait();
			} catch (Exception e) {}
		}
//		System.out.println("put " + buffer.size());
		Character charObj = new Character(c);
		buffer.addElement(charObj);
		this.notify(); //通知等待的线程
	}
	
	public synchronized char pop(){
		char c;
		while(buffer.size() == 0){ //如果没有产品可消费则等待
			try {
				this.wait();
			} catch (Exception e) {}
		}
//		System.out.println("pop " + buffer.size());
		c = ((Character)buffer.remove(buffer.size() - 1)).charValue();
		this.notify(); //通知等待的线程
		return c;
	}
	
}

class Producter implements Runnable{
	private SyncStack syncStack;
	
	public Producter(SyncStack syncStack){
		this.syncStack = syncStack;
	}
	
	//生产一个产品
	@Override
	public void run() {
		while(true){
			char c = (char)(Math.random() * 26 + 'A'); //随机产生一个大写字母
			synchronized (syncStack) {
				syncStack.put(c);
				System.out.println(Thread.currentThread().getName() + " productes: " + c);
			}
			
			try {
				Thread.sleep(2000);
			} catch (Exception e) {}
		}
		
		
	}
}

class Consumer implements Runnable{
private SyncStack syncStack;
	
	public Consumer(SyncStack syncStack){
		this.syncStack = syncStack;
	}
	
	//消费一个产品
	@Override
	public void run() {
		while(true){
			synchronized (syncStack) {
				char c = syncStack.pop(); //消费一个产品
				System.out.println(Thread.currentThread().getName() + " consumes: " + c);
			}
			try {
				Thread.sleep(2000);
			} catch (Exception e) {}
		}
		
	}
}


