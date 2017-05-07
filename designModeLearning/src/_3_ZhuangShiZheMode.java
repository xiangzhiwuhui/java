/**
 * 装饰者模式
 * 
 * 定义：
 * 动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案。
 * 
 * 要点：
 * ①装饰者和被装饰者对象有相同的超类。
 * ②可以用一个或多个装饰者包装一个对象。
 * ③既然装饰者和被装饰者对象有相同的超类型，所以在任何需要原始对象(被包装的)场合，
 * 可以用装饰过的对象替代他。
 * ④装饰者可以在所委托被装饰者的行为之前或之后，加上自己的行为，以达到特定目的。
 * ⑤对象可以在任何时候被装饰，所以可以在运行时动态地、不限量地用你喜欢的装饰者来
 * 装饰对象。
 * ⑥装饰者和被装饰者必须是一样的类型，也就是有共同的超类，我们利用继承达到“类型匹配”，
 * 而不是利用继承获得“行为”。
 * ⑦通常装饰者模式是采用抽象类，但是在java中可以使用接口。
 * ⑧在我们的设计中，应该允许行为可以被扩展，而无需修改现有代码。
 * ⑨组合和委托可用于在运行时动态地加上新的行为。
 * ⑩除了继承，装饰者模式也可以让我们扩展行为。
 * ⑪装饰者模式意味着一群装饰者类，这些类用来包装具体组件。
 * ⑫装饰者类反映出被装饰的组件类型(事实上，他们具有相同的类型，都经过接口或者继承实现)。
 * ⑬可以用无数个装饰者包装一个组件。
 * ⑭装饰者一般对组件的客户是透明的，除非客户程序依赖于组件的具体类型。
 * ⑮装饰者会导致设计中出现许多小对象，如果过度使用，会让程序变得很复杂。
 * 
 * 设计原则：
 * ①类应该对扩展开放，对修改关闭。
 * 注：我们的目标是允许类容易扩展，在不修改现有代码的情况下，就可搭配新的行为。如能
 *    实现这样的目标，就可以使得这样的设计具有弹性可以应对改变，可以接受新的功能来
 *    应对改变的需求。
 * 
 * 实例：星巴兹咖啡
 * 通过让不同调料(牛奶、糖等)作为装饰者装饰被装饰者(咖啡)来实现不同口味的咖啡。
 * 
 * */

public class _3_ZhuangShiZheMode {

	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		//订一杯Espresso，不需要调料，打印出它的描述与价钱
		System.out.println(beverage.getDescription()
				+ " $" + beverage.cost());
		
		//订一杯添加了Mocha,Mocha,Whip调料的Espresso咖啡
		Beverage beverage2 = new Espresso();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		System.out.println(beverage2.getDescription()
				+ " $" + beverage2.cost());
		
		//订一杯调料为豆浆，摩卡，奶泡的HouseBlend咖啡
		Beverage beverage3 = new HouseBlend();
		beverage3 = new Soy(beverage3);
		beverage3 = new Mocha(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.println(beverage3.getDescription()
				+ " $" + beverage3.cost());
	}

}

//观察者和被观察者的抽象基类，观察者和被观察者都要继承次抽象类
abstract class Beverage {
	String description = "Unknown Beverage";
	
	//获取调料的描述信息
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
} 

//调料抽象类，必须让CondimentDecorator能够取代Beverage，所以将
//CondimentDecorator扩展自Beverage类。
abstract class CondimentDecorator extends Beverage {
	//所有调料装饰者都必须重新实现getDescription()方法。
	public abstract String getDescription();
}

//浓缩咖啡饮料，让Espresso扩展自Beverage类，因为Espresso是一种饮料
class Espresso extends Beverage {
	//为了要设置饮料的描述，我们写了一个构造器，记住，description实例
	//变量继承的Beverage
	public Espresso() {
		description = "Espresso";
	}
	
	//该饮料价格
	public double cost() {
		return 1.99;
	}
}

//这是另一种饮料做法和Espresso一样
class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "House Blend Coffee";
	}
	
	public double cost() {
		return 0.89;
	}
}

//摩卡调料，摩卡是一个装饰者，所以让他扩展自CondimentDecorator
class Mocha extends CondimentDecorator {
	Beverage beverage;
	
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
	
	public double cost() {
		return 0.20 + beverage.cost();
	}
}

//大豆调料，Soy是一个装饰者，所以让他扩展自CondimentDecorator
class Soy extends CondimentDecorator {
	Beverage beverage;
	
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}
	
	public double cost() {
		return 0.15 + beverage.cost();
	}
}

//奶泡调料，Whip是一个装饰者，所以让他扩展自CondimentDecorator
class Whip extends CondimentDecorator {
	Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}
	
	public double cost() {
		return 0.10 + beverage.cost();
	}
}
