package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.jar.Attributes.Name;


public class ReflectionTest {

	public static void main(String[] args) throws Exception {

//		//使用默认构造器构造实例的第一种方法
//		Subject subject = RealSubject.class.newInstance();
		
		//使用默认构造器构造实例的第二种方法
		Subject subject = RealSubject.class.getConstructor(new Class[]{}).newInstance(new Object[]{});
		subject.hello("world !");
		
		//通过反射设置类的字段
		Field nameField = RealSubject.class.getDeclaredField("name");
		nameField.setAccessible(true);
		nameField.set(subject, "xiaoMing1111111");
		System.out.println(nameField.get(subject));
		
		// 通过反射进行反射出类中的方法
		Method method = RealSubject.class.getMethod("hello", new Class[]{String.class});
		method.invoke(subject, new Object[]{"ssl !"});
		
		
	}

}

interface Subject{
	public void rent();
	public void hello(String str);
}

class RealSubject implements Subject{

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	public RealSubject() {
		name = "undefineName";
	}
	
	public String getName(){
		return name;
	}
	
	public RealSubject(String name){
		this.name = name;
	}
	@Override
	public void rent() {
		System.out.println("I want to rent my house.");
	}

	@Override
	public void hello(String str) {
		System.out.println("hello: " + str);
	}
	
	@Override
	public String toString(){
		return "override String: " + name;
	}
	
}
