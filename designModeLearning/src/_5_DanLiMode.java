

/**
 * 单例模式
 * 定义：确保一个类只有一个实例，并提供一个全局访问点
 * 
 * 实现单例模式的三种方法：
 * ①把getInstance()变成同步(synchronized)方法。
 * 这是保证可行性的最直接方法，对创建对象没有性能考虑时时可以使用该方法创建单例
 * 
 * ②在静态初始化器中创建单例。
 * 当一定需要使用一个单例对象时，可以使用该方法。
 * 
 * ③双重检查加锁，在getInstance()中减少使用同步
 * 如果性能是你关注的重点，使用该方法可以帮助大大减少getInstance()的时间耗费。
 * 注：双重检查加锁方法不适用1.4及更早版本的Java，因为在早期版本中许多JVM对于
 * volatile关键字的实现会导致双重加锁的失效。
 * 
 * 要点：
 * ①单例模式确保程序中一个类最多只有一个实例。
 * ②单例模式也提供访问这个实例的全局点。
 * ③在Java中实现单例模式需要私有的构造器，一个静态变量和一个静态方法。
 * ④确定在性能和资源上的限制，然后小心的选择适当的方案来实现单件，以解决多线程的问题。
 * ⑤如果不是采用第五版的java，双重检查加锁实现会失效。
 * ⑥如果使用多个类加载器，可能导致单例失效而产生多个实例。
 * ⑦如果使用JVM1.2或之前的版本，必须建立单件注册表，以免垃圾收集器将单例回收。
 * 
 * */

public class _5_DanLiMode {

	public static void main(String[] args) {
		Singleton1 instance1 = Singleton1.getInstance();
		Singleton2 instance2 = Singleton2.getInstance();
		Singleton3 instance3 = Singleton3.getInstance();

	}

}

// 方法一：把getInstance()变成同步(synchronized)方法。
class Singleton1 {
	//将变量设为静态
	private static Singleton1 instance;
	//将构造器设为私有
	private Singleton1(){}
	//将getInstance()设为静态同步方法
	public static synchronized Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}

//方法二：在静态初始化器中创建单例。
class Singleton2 {
	//将变量设为静态,并初始化
	private static Singleton2 instance = new Singleton2();
	//将构造器设为私有
	private Singleton2(){}
	//将getInstance()设为静态方法
	public static Singleton2 getInstance() {
		return instance;
	}
}

//方法三：双重检查加锁，在getInstance()中减少使用同步
class Singleton3 {
	//声明volatile类型的私有静态变量
	private volatile static Singleton3 instance;
	//默认构造器私有化
	private Singleton3(){};
	
	public static Singleton3 getInstance() {
		if (instance == null) {
			//下面同步块不能使用this，因为该同步块在静态方法中，因此使用Singleton3.class
			synchronized (Singleton3.class) { 
				if (instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}



