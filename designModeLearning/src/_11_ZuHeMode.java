import java.util.ArrayList;
import java.util.Iterator;


/**
 * 组合模式：
 * 定义：允许你将对象组合成树形结构来表现“整体/部分”层次结构。组合能让客户以一致的
 * 方式处理个别对象以及对象组合。
 * 
 * 要点：
 * ①我们努力让一个类只分配一个责任。
 * ②组合模式提供一个结构，可同时包容个别对象和组合对象。
 * ③组合模式允许客户对个别对象以及组合对象一视同仁。
 * ④组合结构内的任意对象称为组件，组件可以是组合也可以是叶节点。
 * ⑤在实现组合模式时，有许多设计上的折衷。你要根据需要平衡透明性和安全性。
 * 
 * 实例：利用组合设计餐厅的菜单
 * 餐厅的菜单中包含菜单和菜单项，就是说菜单中包含子菜单，我们使用组合模式来设计菜单
 * 
 * */

public class _11_ZuHeMode {

	public static void main(String[] args) {
		//创建所有菜单对象
		MenuComponent pancakeHouseMenu = new Menu("pancake house menu", "breakfast");
		MenuComponent dinerMenu = new Menu("diner menu", "Lunch");
		MenuComponent cafeMenu = new Menu("cafe menu", "Dinner");
		MenuComponent dessertMenu = new Menu("desert menu", "dessert of couse!");
		//顶层菜单
		MenuComponent allMenus = new Menu("all menus", "all menus combined!");
		
		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeMenu);
		
		//加入菜单项
		dinerMenu.add(new MenuItem("Pasta", "spaghetti with Marinara sauce, and a slice of bread", true, 3.89));
		dinerMenu.add(dessertMenu);
		dessertMenu.add(new MenuItem("Apple Pie", "Apple pie with a flakey crust", true, 1.59));
		
		//加入更多菜单项。。。。
		
		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();
	}
	
}

//MenuComponent对每个方法都提供默认的实现
abstract class MenuComponent {
	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	
	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}
	
	public void print() {
		throw new UnsupportedOperationException();
	}
}

//实现菜单项
class MenuItem extends MenuComponent {
	String name;
	String description;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name, String description, boolean vegetarian, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public double getPrice() {
		return price;
	}
	
	public void print() {
		System.out.println(" " + getName());
		if (isVegetarian()) {
			System.out.println("(v)");
		}
		System.out.println(", " + getPrice());
		System.out.println("  ---" + getDescription());
	}
}

//实现组合菜单
class Menu extends MenuComponent {
	ArrayList menuComponents = new ArrayList();
	String name;
	String description;
	
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}
	
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}
	
	public MenuComponent getChild(int i) {
		return (MenuComponent)menuComponents.get(i);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void print() {
		//打印菜单本身信息
		System.out.println("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("--------------------------------");
		
		//打印菜单内所有组件的内容：其他菜单和菜单项
		Iterator iterator = menuComponents.iterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent) iterator.next();
			menuComponent.print();
		}
	}
}

//服务员
class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		allMenus.print();
	}
}


















