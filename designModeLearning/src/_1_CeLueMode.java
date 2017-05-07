

/**
 * 策略模式
 * 
 * 定义：
 * 定义了算法簇，分别封装起来，让它们之间可以相互替换，此模式让算法的变化独立于使用算法的客户。
 * 
 * 设计原则：
 * ①找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起。
 * 注：把会变化的部分取出并“封装”起来，好让其他部分不会受到影响，使得代码变化引起的不经意后果
 *    变少，系统变得更有弹性。
 * ②这对接口编程，而不是针对实现编程。
 * ③多用组合，少用继承。
 * 
 * 实例：通过鸭子模拟器的设计来学习策略模式。
 * 鸭子模拟器的设计通过将鸭子变化的部分(飞行行为和呱呱叫行为)封装起来，这样飞行行为之间(呱呱叫行为之间)
 * 可以相互替换，让飞行行为和呱呱叫行为这两个算法的变化独立于使用算法的客户(鸭子)。
 * 
 * */
public class _1_CeLueMode {

	public static void main(String[] args) {
		Duck mallard = new ModelDuck();
		mallard.performFly();
		mallard.performQuack();
		mallard.setFlyBehavior(new FlyWithWings());
		mallard.performFly();
		
		//动态改变鸭子飞行动力
		mallard.setFlyBehavior(new FlyRocketpowered());
		mallard.performFly();
	}
}

class ModelDuck extends Duck {

	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}
	
	public void display() {
		System.out.println("I'm a model duck.");
	}
	
}

//鸭子抽象类，所有鸭子都需要继承此抽象类
abstract class Duck {
	//将鸭子易变化的行为声明两个引用变量。这样可以根据传递不同的行为实现来改变鸭子的行为
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public Duck() {}
	
	public abstract void display();
	
	public void performFly() { // 将飞行行为委托给行为类
		flyBehavior.fly();
	}
	
	public void performQuack() {
		quackBehavior.quack();
	}
	
	public void swim() { // 所有的鸭子都会游泳
		System.out.println("All ducks float, even decoys!");
	}
	
	public void setFlyBehavior(FlyBehavior fb) { //通过该方法可以动态设置鸭子行为
		flyBehavior = fb;
	}
	
	public void setQuackBehavior(QuackBehavior qb) {
		quackBehavior = qb;
	}
}

// 鸭子飞行行为接口，所有飞行行为必须实现的接口。
interface FlyBehavior { 
	public void fly();
}

//这是飞行行为的一个实现，给真会飞的鸭子用。
class FlyWithWings implements FlyBehavior {
	public void fly() {
		System.out.println("I'm flying !!");
	}
}

//这是飞行行为的实现，给不会飞的鸭子用。
class FlyNoWay implements FlyBehavior {
	public void fly() {
		System.out.println("I can't fly.");
	}
}


//这是鸭子发出声音的接口，所有发声行为都必须实现此接口。
interface QuackBehavior {
	public void quack();
}

//鸭子咕咕叫的行为
class Quack implements QuackBehavior {
	public void quack() {
		System.out.println("Quack");
	}
}

//不会叫的鸭子
class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("Silence");
	}
}

//发声的另一种实现类
class Squeak implements QuackBehavior {
	public void quack() {
		System.out.println("Squeak");
	}
}

//如果之后的某一天想建立一个利用火箭动力的飞行行为，只需要实现FlyBehavior接口即可,
//不需要对Duck类做任何改动。
class FlyRocketpowered implements FlyBehavior {
	public void fly() {
		System.out.println("I'm flying with a rocket!");
	}
}



