import java.util.ArrayList;

/**
 * 观察者模式
 * 
 * 定义：定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的
 *     所有依赖者都会收到通知并自动更新。
 * 要点：
 * ①观察者模式定义了对象之间一对多的关系。
 * ②主题(也就是被观察者)用一个共同的接口来更新观察者。
 * ③观察者和可观察者之间用松耦合方式结合，被观察者不知道观察者的细节，只知道观察者实现了观察者
 *  接口。
 * ④使用此模式时，你可以从被观察者处推或拉数据(然而，推的方式被认为更正确)。
 * ⑤有多个观察者时，不可以依赖特定的通知次序。
 * ⑥Swing大量使用观察者模式，许多GUI框架也是如此。
 * 
 * 设计原则：
 * ①找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起。
 * 例子：在观察者模式中，会改变的是主题的状态，以及观察者的数目和类型。用这个模式，你可以改变
 *     依赖于主题状态的对象，却不必改变主题。这就叫提前规则。
 * 注：把会变化的部分取出并“封装”起来，好让其他部分不会受到影响，使得代码变化引起的不经意后果
 *    变少，系统变得更有弹性。
 * ②这对接口编程，而不是针对实现编程。
 * 例子：主题与观察者都是用接口：观察者利用主题的接口向主题注册，而主题利用观察者接口通知观察者。
 *     这样可以让两者之间运作正常，又同时具有松耦合的优点。
 * ③多用组合，少用继承。
 * 例子：观察者模式利用“组合”将许多观察者组合进主题中，对象之间的这种关系不是通过继承产生的，而是
 *     在运行时利用组合的方式而产生的。
 * ④为了交互对象之间的松耦合设计而努力。
 * 注：在观察者模式中，因为主题唯一依赖的东西是一个实现Observer接口的对象列表，
 *    所以我们可以随时增加观察者。事实上，在运行时我们可以用新的观察者取代现有的
 *    观察者，主题不会受到任何影响。同样，可以在任何时候删除某些观察者。
 * 
 * 实例：通过建立气象站来学习使用观察者模式。
 * 气象站例子中，通过将天气数据对象WeatherData作为被观察者，将显示面板作为观察者来实现观察者模式。
 * */

public class _2_GuanChaZheMode {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		
		//模拟天气数据改变
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(85, 67, 35.4f);
	}
}

// 被观察者接口，所有被观察者对象都需要实现此接口。
interface Subject {
	// 下面两个方法都需要一个观察者作为变量，该观察者是用来注册或被删除的
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	
	// 当主题状态改变时，这个方法会被调用，以通知所有的观察者
	public void notifyObservers();
}

//观察者接口，所有观察者必须实现此接口
interface Observer {
	// 当气象观测值改变时，主题会把这些状态值作为方法参数传给观察者
	public void update(float temp, float humidity, float pressure);
}

//该接口只包含一个方法display(),当布告板需要显示时，调用此方法。
interface DisplayElement {
	public void display();
}

// WeatherData是被观察者的一个实现类
class WeatherData implements Subject {
	private ArrayList<Observer> observers;  // 记录注册的观察者
	private float temperature;              // 气温
	private float humidity;                 // 湿度
	private float pressure;                 // 气压
	
	public WeatherData() {
		observers = new ArrayList<>();
	}

	// 注册一个观察者
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	// 移除观察者
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	// 把状态告诉每一个注册该主题的观察者
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(temperature, humidity, pressure);
		}
	}
	
	// 当从气象站得到更新观测值时，下面方法被自动调用，通知观察者。
	public void measurementChanged() {
		notifyObservers();
	}
	
	// 模拟硬件装置，给天气数据设置值,模拟天气数据发生改变。
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementChanged();
	}
}

// 建立目前状况布告板，它就是观察者，因此需要实现观察者接口
class CurrentConditionsDisplay implements Observer, DisplayElement {
	private float temperature;    // 气温
	private float humidity;       // 湿度
	private Subject weatherData;  // 保存被观察者引用，可以用来取消注册
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	
	// 当update()方法被调用时，我们把温度和湿度保存起来，然后调用display().
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
		
	}
	
	// 显示最近的温度和湿度
		public void display() {
			System.out.println("Current condition: " + temperature
					+ "F degrees and " + humidity + "% humidity.");
		}
}
