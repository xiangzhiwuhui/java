package interview;



public class InstanceInit {

	public static void main(String[] args) {
//		String s = "hello";
//		String t = "hello";
//		char[] c = {'h', 'e', 'l', 'l', 'o'};
//		Object object = c;
//		System.out.println(object instanceof String);
//		t.equals(c);
//		System.out.println(t.equals(c));
		
		//java程序初始化顺序
		new Derived();
		
		
		
	}

}




//对象初始化顺序测试
class Base{
	
	static int i = 2;
	
	static{
		System.out.println("i = " + i);
		System.out.println("Base static block");
	}
	
	static int j = 3;
	
	{
		System.out.println("Base block");
	}
	
	public Base(){
		System.out.println("Base constructor");
	}
}

class Derived extends Base{
	
	static{
		System.out.println("Derived static block");
	}
	
	static int i = 5;
	
	{
		System.out.println("Derived block");
	}
	
	public Derived(){
		System.out.println("Derived constructor");
	}
}











































