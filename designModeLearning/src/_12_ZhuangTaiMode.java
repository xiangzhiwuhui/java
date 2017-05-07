

/**
 * 状态模式
 * 定义：允许对象在内部状态改变时改变它的行为，对象看起来好像改变了它的类。
 * 
 * 要点：
 * ①状态模式允许一个对象基于内部状态而拥有不同的行为
 * ②和程序状态机（PSM）不同，状态模式用类代表状态
 * ③Context(如例子中的糖果机)会将行为委托给当前状态对象
 * ④通过将每个状态封装进一个类，我们把以后需要做的任何改变局部化了
 * ⑤状态模式和策略模式有相同的类图，但是它们的意图不同
 * ⑥策略模式通常会用行为或算法来配置Context类
 * ⑦状态模式允许Context随着状态的改变而改变
 * ⑧状态转换可以由State类或Context类控制
 * ⑨使用状态模式通常会导致设计中类的数目大量增加
 * ⑩状态类可以被多个Context实例共享
 * 
 * 实例：糖果机
 * 糖果售货机一共有四种状态（有钱，没钱，售罄，出售糖果），通过使用状态模式将四种状态分别用一个
 * 对象来表示。
 * ①首先，我们定义一个State接口，在这个接口中，糖果机的每个动作都有一个对应的方法。
 * ②然后为机器中的每个状态实现状态类，这些类将负责在对应的状态下进行机器的行为。
 * ③最后，将动作委托到状态类。
 * 
 * */

public class _12_ZhuangTaiMode {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(5);
		System.out.println(gumballMachine);
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		System.out.println(gumballMachine);
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		System.out.println(gumballMachine);

	}

}

//糖果机类
class GumballMachine {
	State soldOutState;           // 售罄状态
	State noQuarterState;         // 无硬币状态
	State hasQuarterState;        // 有硬币 状态
	State soldState;              // 售出状态
	
	State state = soldOutState;   // 初始时，设置糖果机为售罄状态
	int count = 0;                // 糖果数量，初始时设为0
	
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
		if (numberGumballs > 0) {
			state = noQuarterState;
		}
	}
	
	public void insertQuarter() {
		state.insertQuarter();
	}
	
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void releaseBall() {
		System.out.println("A gumball comes rolling out the slot ...");
		if (count != 0) {
			count = count - 1;
		}
	}

	public State getSoldOutState() {
		return soldOutState;
	}

	public void setSoldOutState(State soldOutState) {
		this.soldOutState = soldOutState;
	}

	public State getNoQuarterState() {
		return noQuarterState;
	}

	public void setNoQuarterState(State noQuarterState) {
		this.noQuarterState = noQuarterState;
	}

	public State getHasQuarterState() {
		return hasQuarterState;
	}

	public void setHasQuarterState(State hasQuarterState) {
		this.hasQuarterState = hasQuarterState;
	}

	public State getSoldState() {
		return soldState;
	}

	public void setSoldState(State soldState) {
		this.soldState = soldState;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public State getState() {
		return state;
	}

	public String toString() {
		return "GumballMachine [soldOutState=" + soldOutState
				+ ", noQuarterState=" + noQuarterState + ", hasQuarterState="
				+ hasQuarterState + ", soldState=" + soldState + ", state="
				+ state + ", count=" + count + "]";
	}
	
	
	
}

//无硬币状态类
class NoQuarterState implements State {
	GumballMachine gumballMachine;
	
	public NoQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	public void insertQuarter() {
		System.out.println("You inserted a quarter");
		gumballMachine.setState(gumballMachine.getHasQuarterState());
	}

	public void ejectQuarter() {
		System.out.println("You haven't inserted a quarter");
	}

	public void turnCrank() {
		System.out.println("You turned, but there's no quarter");
	}

	public void dispense() {
		System.out.println("You need to pay first");
	}
	
}

//有硬币状态类
class HasQuarterState implements State {
	GumballMachine gumballMachine;
	
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}

	public void ejectQuarter() {
		System.out.println("Quarter returned");
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}

	public void turnCrank() {
		System.out.println("You turned ...");
		gumballMachine.setState(gumballMachine.getSoldState());
	}

	public void dispense() {
		System.out.println("No gumball dispensed");
	}
	
}

//售出糖果状态类
class SoldState implements State {
	GumballMachine gumballMachine;
	
	public SoldState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}

	public void ejectQuarter() {
		System.out.println("Sorry, you already turned the crank");
	}

	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball");
	}

	public void dispense() {
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() > 0) {
			gumballMachine.setState(gumballMachine.getNoQuarterState());
		}else {
			System.out.println("Oops, out of gumballs");
			gumballMachine.setState(gumballMachine.getSoldOutState());
		}
	}
	
}

//售罄状态类
class SoldOutState implements State {
	GumballMachine gumballMachine;
	
	public SoldOutState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	public void insertQuarter() {
		System.out.println("You can't insert a quarter, the machine is sold out");
	}

	public void ejectQuarter() {
		System.out.println("You can't eject, you haven't inserted a quarter yet");
	}

	public void turnCrank() {
		System.out.println("You turned, but there are no gumballs");
	}

	public void dispense() {
		System.out.println("No gumball dispensed !");
	}
	
}


//定义状态接口
interface State {
	public void insertQuarter(); // 插入硬币
	public void ejectQuarter();  // 退回硬币
	public void turnCrank();     // 转动手柄
	public void dispense();      // 发放糖果
}



























