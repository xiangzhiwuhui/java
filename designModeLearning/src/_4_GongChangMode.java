import java.util.ArrayList;


/**
 * 工厂模式
 * 将实例化具体类的代码从应用中抽离，或者封装起来，使它们不会干扰应用的其他部分。
 * 
 * 一、简单工厂：
 * 将创建对象的代码用一个对象封装起来(只需简单的将创建对象的代码封装起来就行了)，工厂处理创建对象的细节。
 * 
 * 实例：比萨工厂
 * 将创建多种类型比萨对象的过程放到一个工厂中来实现，只需传入想要创建的比萨类型，工厂就会返回指定比萨类型
 * 的对象。
 * 
 * 二、工厂方法模式：
 * 定义了一个创建对象的接口，但是由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类。
 * 
 * 要点：
 * ①工厂方法用来处理对象的创建，并将这样的行为封装在子类中，这样，客户程序中关于超类的代码就和子类对象
 * 创建代码解耦了。
 * ②允许子类做决定
 * ③工厂方法和创建者不一定总是抽象的，可以定义一个默认的工厂方法来生产某些具体产品，这么一来，即使创建者没有
 * 任何子类，依然可以创建产品。
 * 
 * 实例：比萨工厂
 * 由于地域差异，每个不同地域的比萨商店可能想要提供不同风味的比萨(比如纽约，芝加哥等),所以希望建立一个框架，
 * 把比萨店和创建比萨捆绑在一起的同时又保持一定得弹性。这样不同地域有不同的比萨店，比如纽约比萨店(NYPizzaStore2),
 * 芝加哥比萨店(ChicagoPizzaStore),对应有不同风味的比萨，比如纽约有纽约风味的比萨(NYStyleCheesePizza)，
 * 芝加哥有芝加哥风味的比萨(ChicagoStyleCheesePizza)。
 * 
 * 三、抽象工厂模式
 * 提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。
 * 
 * 注：抽象工厂允许客户使用抽象的接口来创建一组相关的产品，而不需要知道(或关心)实际产出的具体产品是什么。这样一来，客户
 * 就从具体的产品中被解耦。
 * 
 * 要点总结：
 * ①所有的工厂都是用来封装对象的创建
 * ②简单工厂，虽然不是真正的设计模式，但仍不失为一个简单的方法，可以将客户程序从具体类解耦
 * ③工厂方法使用继承：把对象的创建委托给子类，子类实现工厂方法来创建对象。
 * ④抽象工厂使用对象组合：对象的创建被实现在工厂接口所暴露出来的方法中
 * ⑤所有工厂模式都通过减少应用程序和具体类之间的依赖促进松耦合
 * ⑥工厂方法允许类将实例化延迟到子类进行
 * ⑦抽象工厂创建相关的对象家族，而不需要依赖它们的具体实现
 * ⑧工厂是很有威力的技巧，帮助我们针对抽象编程，而不是针对具体类编程
 * 
 * 设计原则：
 * 依赖倒置原则：要依赖抽象，不要依赖具体类。
 * 这个原则说明了：不能让高层组件依赖底层组件，而且，不管高层或底层组件，两者都应该依赖于抽象。
 * 
 * 几个方针帮助遵循此原则： 
 * ①变量不可以持有具体类的引用
 * 原因：如果使用new，就会持有具体类的引用。可以改用工厂来避开这样的做法。
 * ②不要让类派生自具体类
 * 原因：如果派生自具体类，就会依赖具体类，请派生自一个抽象(抽象类或接口)
 * ③不要覆盖基类中已实现的方法
 * 原因：如果覆盖基类已实现的方法，那么你的基类就不是一个真正适合被继承的抽象。基类中已实现的方法，应该由
 * 所有的子类共享。
 * 
 * */

public class _4_GongChangMode {

	public static void main(String[] args) {
		// 一、简单工厂
		System.out.println("########简单工厂########");
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore pizzaStore = new PizzaStore(factory);
		pizzaStore.orderPizza("cheese");
		
		// 二、工厂方法
		System.out.println("########工厂方法########");
		PizzaStore2 nyStore = new NYPizzaStore2();
		PizzaStore2 chicagoStore = new ChicagoPizzaStore2();
		//使用纽约比萨商店生产纽约类型的比萨
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + "\n");
		//使用芝加哥比萨商店生产芝加哥类型的比萨
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza.getName() + "\n");

	}

}

/**********一、简单工厂************/

//创建简单工厂,它只做一件事：帮它的客户创建比萨
class SimplePizzaFactory {
	//首先，定义createPizza(String type)方法，
	//所有客户用这个方法来实例化新对象
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		
		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		}else if (type.equals("veggie")) {
			pizza = new VeggiePizza();
		}
		return pizza;
	}
}

//比萨商店类
class PizzaStore {
	//生产比萨的工厂
	SimplePizzaFactory factory;
	
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}
	
	//订购比萨
	public Pizza orderPizza(String type) {
		Pizza pizza;
		//使用工厂方法生产指定类型的比萨
		pizza = factory.createPizza(type); 
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
}

class CheesePizza extends Pizza {}

class VeggiePizza extends Pizza {}


/**********二、工厂方法************/
abstract class PizzaStore2 {
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		//制造比萨
		pizza = createPizza(type);
		
		return pizza;
	}
	
	//实例化比萨的责任被移到createPizza方法中，此方法就是工厂方法。
	abstract Pizza createPizza(String type);
}

//纽约比萨店
class NYPizzaStore2 extends PizzaStore2 {
	//子类实现具体的工厂方法
	Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new NYStyleCheesePizza();
		}else if (type.equals("veggie")) {
			return new NYStyleVeggiePizza();
		}
		return null;
	}
}

//纽约类型的Cheese比萨
class NYStyleCheesePizza extends Pizza {
	public NYStyleCheesePizza() {
		//纽约比萨有自己的大蒜番茄酱（Marinara）和薄饼。
		name = "NY Style Sauce and Cheese Pizza.";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		//上面覆盖的是意大利Reggiano高级干酪
		toppings.add("Grated Reggiano Cheese");
	}
}

//纽约类型的Veggie比萨
class NYStyleVeggiePizza extends Pizza {
	public NYStyleVeggiePizza() {
		name = "NY Style Sauce and Veggie Pizza.";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		toppings.add("Grated Reggiano Veggie");
	}
}

//芝加哥比萨店
class ChicagoPizzaStore2 extends PizzaStore2 {
	Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new ChicagoStyleCheesePizza();
		}else if (type.equals("veggie")) {
			return new ChicagoStyleVeggiePizza();
		}
		
		return null;
	}
}

//芝加哥类型的Cheese比萨
class ChicagoStyleCheesePizza extends Pizza {
	public ChicagoStyleCheesePizza() {
		//芝加哥比萨使用小番茄作为酱料，并使用厚饼
		name = "Chicago Style Deep Dish Cheese Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		//芝加哥风味的深盘比萨使用许多Mozzarella(意大利白干酪)
		toppings.add("Shredded Mozzarella Cheese");
	}
}

//芝加哥类型的Veggie比萨
class ChicagoStyleVeggiePizza extends Pizza {
	public ChicagoStyleVeggiePizza() {
		name = "Chicago Style Deep Dish Veggie Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Veggie");
	}
}







//比萨
abstract class Pizza {
	//比萨名称，面团类型，酱料类型，一套作料
	String name;
	String dough;
	String sauce;
	ArrayList toppings = new ArrayList();
	
	//比萨制作流程
	void prepare() {
		System.out.println("Preparing " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce..");
		for (int i = 0; i < toppings.size(); i++) {
			System.out.println(" " + toppings.get(i));
		}
	}
	
	void bake() {
		System.out.println("Bake 25 minutes at 350");
	}
	
	void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}
	
	void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}
	
	public String getName() {
		return name;
	}
	
}








