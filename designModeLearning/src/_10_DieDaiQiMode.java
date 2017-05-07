import java.util.HashSet;
import java.util.Iterator;


/**
 * 迭代器模式
 * 定义：提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。
 * 
 * 要点：
 * ①迭代器允许访问聚合的元素，而不需要暴露它的内部结构。
 * ②迭代器将遍历聚合的工作封装进一个对象中。
 * ③当使用迭代器的时候，我么依赖聚合提供遍历。
 * ④迭代器提供了一个通用的接口，让我们遍历聚合的项，当我们编码使用聚合的项时，就可以
 * 用多态机制。
 * ⑤我们努力让一个类只分配一个责任。
 * ⑥组合模式提供一个结构，可同时包容个别对象和组合对象。
 * ⑦组合模式允许客户对个别对象以及组合对象一视同仁。
 * ⑧组合结构内的任意对象称为组件，组件可以是组合也可以是叶节点。
 * ⑨在实现组合模式时，有许多设计上的折衷。你要根据需要平衡透明性和安全性。
 * 
 * 实例：java中的集合类
 * java中的集合类都实现了Iterator接口，然后在具体的集合类中实现iterator接口中的方法，
 * 这样就可以为集合提供统一的遍历，而不用关心底层每个集合类的具体实现。
 * 
 * 
 * */

public class _10_DieDaiQiMode {
	public static void main(String[] args) {
		HashSet<String> strSet = new HashSet<String>();
		strSet.add("a");
		strSet.add("a");
		strSet.add("b");
		Iterator<String> it = strSet.iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			System.out.println(string);
		}
	}
}
