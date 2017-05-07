package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

	interface IHello {
		void sayHello();
	}

	static class Hello implements IHello {
		@Override
		public void sayHello() {
			System.out.println("hello world !");
		}
	}
	
	//动态代理类需要继承InvocationHandler接口
	static class DynamicProxy implements InvocationHandler {

		Object originalObj;
		
		Object bind(Object originalObject) {
			this.originalObj = originalObject;
			//下面的this指调用处理器，就是实现InvocationHandler接口的类，此处指DynamicProxy类本身
			return Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), originalObject.getClass().getInterfaces(), this);
		}
		
		@Override
		//当代理Proxy类的方法被调用时，代理Proxy类就会将这个调用发给Invocationhandler的invoke方法，让该方法处理实际的请求
		//invoke参数中：proxy指代理对象，method指Proxy类中的方法，args指method需要的参数列表
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("dynamicProxy before !");
			method.invoke(originalObj, args);
			System.out.println("dynamicProxy after !");
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		IHello hello = (IHello)new DynamicProxy().bind(new Hello());
		hello.sayHello();
	}
}




