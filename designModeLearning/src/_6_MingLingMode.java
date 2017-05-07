
/**
 * 命令模式
 * 定义：将“请求”封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象。
 * 命令模式也支持可撤销的操作。
 * 
 * 解释：一个命令对象通过在特定接收者上绑定一组动作来封装一个请求。要达到这一点，
 * 命令对象将动作和接收者包进对象中。这个命令对象只暴露出一个execute()方法，
 * 当此方法被调用的时候，接收者就会进行这些动作。从外面来看，其他对象不知道究竟哪个
 * 接收者进行了哪些动作，只知道如果调用execte()方法，请求的目的就能达到。
 * 
 * 用途：日程安排，线程池，日志请求，工作队列等
 * ①队列请求
 * 命令可以将运算块打包(一个接收者和一组动作)，然后将它传来传去。
 * 想像有一个工作队列：你在某一端添加命令，然后另一端则是线程。线程进行下面的动作：从队列中取出
 * 一个命令，调用它的execut()方法，等待这个调用完成，然后将此命令对象丢弃，再取下一个命令。。。
 * 请注意，工作队列类和进行计算的对象之间是完全解耦的。此刻线程可能在财务运算，下一刻却在读取网络
 * 数据。工作队列对象不在乎到底做了什么，它们只知道取出命令对象，然后调用其execute()方法。类似的，
 * 它们只要实现命令模式的对象，就可以放入队列里，当线程可用时，就调用此对象的execut()方法。
 * 
 * 要点：
 * ①命令模式将发出请求的对象和执行请求的对象解耦。
 * ②在被解耦的两者之间是通过命令对象进行沟通的。命令对象封装了接收者和一个或一组动作。
 * ③调用者通过调用命令对象的execute()发出请求，这会使得接收者的动作被调用。
 * ④调用者可以接收命令当做参数，甚至在运行时动态地进行。
 * ⑤命令可以支持撤销，做法是在命令对象中实现一个undo()方法来回到execute()被执行前的状态。
 * ⑥宏命令是命令的一种简单的延伸，允许调用多个命令。宏方法可以支持撤销。
 * ⑦实际操作时，很常见使用“聪明”命令对象，也就是直接实现了请求，而不是将工作委托给接收者。
 * ⑧命令也可以用来实现日志和事务系统。
 * 
 * 
 * 实例：遥控器控制:遥控器能够控制目前的装置和未来任何可能出现的装置。
 * 使用命令模式来设计遥控器的每个插槽(slot)，遥控器对象持有命令对象的引用，有设置命令对象(电灯命令对象)的方法。
 * 命令对象中持有接收者(电灯)的引用，和操作电灯的execute()方法。这样就能够使遥控器和具体的接收者者(电灯)之间
 * 解耦。
 * 
 * */

public class _6_MingLingMode {
	public static void main(String[] args) {
		//构建遥控器对象
		SimpleRemoteControl remoteControl = new SimpleRemoteControl();
		//构建接收者对象电灯
		Light light = new Light();
		//构建打开电灯的命令对象
		LightOnCommand lightOnCommand = new LightOnCommand(light);
		//为遥控器插槽设置打开电灯命令
		remoteControl.setCommand(lightOnCommand);
		//模拟遥控器被按下按钮
		remoteControl.buttonWasPressed();
		
		LightOffCommand lightOffCommand = new LightOffCommand(light);
		//为遥控器插槽设置关闭电灯命令
		remoteControl.setCommand(lightOffCommand);
		//模拟遥控器被按下按钮
		remoteControl.buttonWasPressed();
	}

}

//遥控器对象
class SimpleRemoteControl {
	Command slot; //遥控器上的插槽
	public SimpleRemoteControl() {}
	
	//设置命令
	public void setCommand(Command command) {
		slot = command;
	}
	
	//模拟遥控器按钮被按下
	public void buttonWasPressed() {
		slot.execute();
	}
}

//电灯打开命令对象
class LightOnCommand implements Command {
	//接收者对象引用
	Light light;
	public LightOnCommand(Light light) {
		this.light = light;
	}
	//使用下面的方法调用接收者对象(light)的on()方法
	public void execute() {
		light.on();
	}
}

//电灯关闭命令对象
class LightOffCommand implements Command {
	//接收者对象引用
	Light light;
	public LightOffCommand(Light light) {
		this.light = light;
	}
	//使用下面的方法调用接收者对象(light)的on()方法
	public void execute() {
		light.off();
	}
}

//NoCommand对象可以不进行命令检查
class NoCommand implements Command {
	//该方法什么也不做
	public void execute() {}
	
}

//命令对象接口，所有命令对象必须实现此接口
interface Command {
	public void execute();
}

//电灯对象
class Light {
	//打开电灯
	public void on(){
		System.out.println("Light on !");
	}
	//关闭电灯
	public void off(){
		System.out.println("Light off !");
	}
}



