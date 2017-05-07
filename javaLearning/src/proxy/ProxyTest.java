package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {

		Subject realSubject = new RealSubject();
		
		InvocationHandler handler = new DynamicProxy(realSubject);
		
		Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		
		System.out.println(subject.getClass().getName());
		subject.rent();
		subject.hello("world");
		
	}

}

interface Subject{
	public void rent();
	public void hello(String str);
}

class RealSubject implements Subject{

	@Override
	public void rent() {
		System.out.println("I want to rent my house.");
		
	}

	@Override
	public void hello(String str) {
		System.out.println("hello: " + str);
	}
	
}

class DynamicProxy implements InvocationHandler{
	//这个就是我们要代理的真是对象
	private Object subject;
	
	//构造方法，给我们要代理的真实对象赋初值
	public DynamicProxy(Object subject){
		this.subject = subject;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		//在代理真
		System.out.println("before rent house");
		System.out.println("Method: " + method);
		System.out.println("---->" + proxy.getClass().getName());
		method.invoke(subject, args);
		System.out.println("after rent house");
		System.out.println(method);
		
		return null;
	}
}
