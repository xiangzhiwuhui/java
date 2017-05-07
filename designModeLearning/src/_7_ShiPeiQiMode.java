
/**
 * 适配器模式
 * 定义：将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口
 * 不兼容的类可以合作无间。
 * 
 * 客户使用适配器的过程如下：
 * ①客户通过目标接口调用适配器的方法对适配器发出请求。
 * ②适配器使用被适配者接口把请求转换成被适配者的一个或多个调用接口。
 * ③客户接收到调用的结果，但并未察觉这一切是适配器在起转换作用。
 * 
 * 要点：
 * ①当需要使用一个现有的类而其他接口并不符合你的需求时，就使用适配器。
 * ②当需要简化并统一一个很大的接口或者一群复杂的接口时，使用外观模式。
 * ③适配器改变接口以符合客户的期望。
 * ④外观将客户从一个复杂的子系统中解耦。
 * ⑤实现一个适配器可能需要一番功夫，也可能不费功夫，视目标接口的大小与复杂程度而定。
 * ⑥实现一个外观，需要将子系统组合进外观中，然后将工作委托给子系统执行。
 * ⑦适配器有两种形式：对象适配器和类适配器。类适配器需要用到多继承。
 * ⑧你可以为一个子系统实现一个以上的外观。
 * ⑨适配器将一个对象包装起来以改变其接口；装饰者将一个对象包装起来以增加新的行为
 * 和责任；而外观将一群对象“包装”起来以简化其接口。
 * 
 * 实例：火鸡适配器
 * 现在假设你缺鸭子对象，想用一些火鸡对象来冒充，显然，因为火鸡的接口不同，所以不能公然
 * 拿来用。因此需要使用一个火鸡适配器，做法如下：
 * ①首先，你需要实现想转换成的类型接口，也就是你的客户所期望的接口(实例中指鸭子接口)。
 * ②接着，需要取得适配的对象引用(实例中指火鸡对象的引用)。
 * ③接着实现接口中的所有方法：接口中的方法体是调用适配的对象(火鸡)中的方法来实现。
 * 
 * */

public class _7_ShiPeiQiMode {
	public static void main(String[] args) {
		//先创建一只鸭子和一只火鸡
		MallardDuck duck = new MallardDuck();
		WildTurkey turkey = new WildTurkey();
		//创建火鸡适配器对象
		Duck2 turkeyAdapter = new TurkeyAdapter(turkey);
		
		System.out.println("The Turkey says ...");
		turkey.gobble();
		turkey.fly();
		
		System.out.println("test TurkeyAdapter。。。");
		duck.quack();
		duck.fly();
	}
}

//火鸡适配器
class TurkeyAdapter implements Duck2 {
	//被适配的对象引用
	Turkey turkey;
	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	
	public void quack() {
		turkey.gobble();
	}
	
	public void fly() {
		//因为火鸡飞的不远，所以多飞几次
		for (int i = 0; i < 5; i++) {
			turkey.fly();
		}
	}
}

//鸭子接口
interface Duck2 {
	public void quack(); //咕咕叫行为
	public void fly(); // 飞行行为
}

//具体的鸭子：绿头鸭
class MallardDuck implements Duck2 {
	public void quack() {
		System.out.println("Quack");
	}

	public void fly() {
		System.out.println("I'm flying");
	}
}

//火鸡接口
interface Turkey {
	public void gobble(); //火鸡咯咯叫
	public void fly();    //火鸡会飞，虽然飞不远
}

//火鸡的一个具体实现：野火鸡
class WildTurkey implements Turkey {
	public void gobble() {
		System.out.println("Gobble gobble");
	}

	public void fly() {
		System.out.println("I'm flying a short distance");
	}
}

