
/**
 * 模板方法模式
 * 定义：在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法结构
 * 的情况下，重新定义算法中的某些步骤。
 * 
 * 注：模板就是一个方法。更具体地说，这个方法将算法定义成一组步骤，其中的任何步骤都可以是抽象的，由子类
 * 负责实现，这可以确保算法的结构保持不变，同时由子类提供部分实现。
 * 
 * 要点：
 * ①“模板方法”定义了算法的步骤，把这些步骤的实现延迟到子类。
 * ②模板方法模式为我们提供了一种代码复用的重要技巧。
 * ③模板方法的抽象类可以定义具体方法、抽象方法和钩子。
 * ④抽象方法由子类实现。
 * ⑤钩子是一种方法，它在抽象类中不做事，或者只做默认的事情，子类可以选择要不要去覆盖它。
 * ⑥为了防止子类改变模板方法中的算法，可以将模板方法声明为final。
 * ⑦好莱坞原则告诉我们，将决策权放在高层模块中，以便决定如何以及何时调用底层模块。
 * ⑧你将在真实世界代码中看到模板方法模式的许多变体，不要期待它们全都是一眼就可以被你认出的。
 * ⑨策略模式和模板方法模式都封装算法，一个用组合，一个用继承。
 * 
 * 常见使用场景：Java中的Arrays.sort()等。
 * 
 * 钩子：
 * 钩子是一种被声明在抽象类中的方法，但是只有空的或者默认的实现。钩子的存在，可以让子类有能力对算法的不同
 * 点进行挂钩，要不要挂钩，由子类自行决定。
 * 
 * 当创建一个模板方法时，当你的子类“必须”提供算法中某个方法或步骤的实现时，就使用抽象方法。如果算法的这个
 * 部分是可选的，就用钩子。如果是钩子的话，子类可以选择实现这个钩子，但并不是强制这么做。
 * 
 * 钩子常见使用场景：Java的Swing中paint()方法等。
 * 
 * 设计原则：
 * 好莱坞原则：别调用我们，我们会调用你。
 * 注：好莱坞原则可以给我们一种防止“依赖腐败”的方法。当高层组件依赖底层组件，而底层组件又依赖高层组件，而
 * 高层组件又依赖边侧组件，而边侧组件又依赖底层组件时，依赖腐败就发生了。在好莱坞原则下，我们允许底层组件将
 * 自己挂钩到系统上，但是高层组件会决定什么时候和怎样使用这些低层组件。换句话说，高层组件对底层组件的方式是
 * “别调用我们，我们会调用你。”
 * 
 * 好莱坞原则和依赖倒置原则的关系：
 * 依赖倒置原则教我们尽量避免使用具体类，而多使用抽象。而好莱坞原则是用在创建框架或组件上的一种技巧，好让底层
 * 组件能够被挂钩进计算中，而且又不会让高层组件依赖底层组件。两者的目标都是在于解耦，但是依赖倒置原则更加注重
 * 如何在设计中避免依赖。好莱坞原则教我们一个技巧，创建一个有弹性的设计，允许底层结构能够相互操作，而又防止其
 * 他类太过依赖它们。
 * 
 * 实例：咖啡冲泡方法和茶冲泡方法
 * 咖啡和茶的冲泡方法基本相同，只是其中一些步骤不同，这样可以使用模板方法来设计咖啡和茶的冲泡过程，让子类来处理
 * 它们冲泡过程的差异。
 * 
 * */


public class _9_MoBanFangFaMode {

	public static void main(String[] args) {
		//冲泡茶
		CaffeineBeverage tea = new Tea();
		tea.prepareRecipe();
		
		//冲泡咖啡
		CaffeineBeverage coffee = new Coffee();
		coffee.prepareRecipe();

	}

}

//咖啡因饮料抽象类
abstract class CaffeineBeverage {
	//模板方法
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
	
	/**下面两个方法对于具体的咖啡和茶处理是不同的，所以设置成抽象的**/
	//冲泡方法
	abstract void brew();
	//添加调料方法
	abstract void addCondiments();
	
	/**下面两个方法对于具体的咖啡和茶处理是相同的，所以设置成具体的**/
	//烧水
	void boilWater() {
		System.out.println("Boiling water");
	}
	//倒进杯子中
	void pourInCup() {
		System.out.println("Pouring into cup");
	}
}

//茶
class Tea extends CaffeineBeverage {
	public void brew() {
		System.out.println("Steeping the tea");
	}
	
	void addCondiments() {
		System.out.println("Adding Lemon");
	}
}

//咖啡
class Coffee extends CaffeineBeverage {
	public void brew() {
		System.out.println("Dripping coffee through filter");
	}
	
	void addCondiments() {
		System.out.println("Adding Sugar and milk");
	}
}


















