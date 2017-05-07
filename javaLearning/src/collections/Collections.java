package collections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Collections {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Map map = new HashMap<String, Integer>();
		System.out.println(1<<30);

		LinkedHashMap<String, Integer> lm = new LinkedHashMap<String, Integer>(16, 0.75f, true);
		lm.put("1", 1);
		lm.put("2", 2);
		lm.put("3", 3);
		lm.put("4", 4);
		lm.get("1");
		Iterator<Entry<String, Integer>> iterator= lm.entrySet().iterator();  
		while(iterator.hasNext()){  
		    Map.Entry entry = iterator.next();  
		    System.out.println(entry.getKey()+":"+entry.getValue());  
		}
		
		
		List<Animal> list = new ArrayList<>();
		list.add(new Bird("bird"));
		list.add(new Cat("cat"));
		list.add(new Animal("animal"));
		for (Animal animal : list) {
			animal.say("Hello !");
		}
		HashSet<Animal> animals = new HashSet<>();
		animals.add(new Bird("bird0"));
		animals.add(new Bird("Bird1"));
		animals.add(new Cat("cat0"));
		animals.add(new Cat("cat0"));
//		for(Animal a : animals){
//			System.out.println(a);
//		}
		Iterator<Animal> iter = animals.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
			
		}
		animals.remove(new Bird("bird"));
		System.out.println("-------------------------------");
		Iterator<Animal> iter1 = animals.iterator();
		while (iter1.hasNext()) {
			System.out.println(iter1.next());
			
		}
		
		char[] name = "shishengle".toCharArray();
		char[] firstName = new char[]{'1', '2', '3', '4'};
		System.arraycopy(name, 0, firstName, 0, 3);
		System.out.println(firstName);
		String id = "012345678";
		System.out.println(id.compareTo("012345678"));
		String[] idArray = id.split("");
		System.out.println(idArray[2]);
		
//		Map
//		HashMap
//		LinkedHashMap
//		SortedMap //接口
//		TreeMap
		
		
//		Collection
		
//		Set
//		HashSet
//		LinkedHashSet
//		SortedSet //接口
//		TreeSet
		
//		List
//		ArrayList
//		LinkedList
//		Vector
		
//		Class<?> sc = String.class;
//		for(int i = 0; i < sc.getDeclaredMethods().length; i++){
//			System.out.println(sc.getDeclaredMethods()[i].getName());
//		}
		
		Class<?> catClass = Cat.class;
		Constructor constructor = catClass.getConstructor(new Class[]{String.class});
		Cat cat = (Cat)constructor.newInstance(new Object[]{"cat!"});
		cat.say("niaho");
		
//		int a = 12_2;
//		System.out.println(a);
		
		
	}

}

class Animal{
	String name;
	public Animal(String name) {
		this.name = name;
	}
	void say(String content){
		System.out.println("Animal say " + content);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Animal) {
			return this.name.equals(((Animal) obj).name) ? true : false;
			
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public String toString() {
		
		return "My name is " + this.name;
	}
}

class Bird extends Animal{
	public Bird(String name) {
		super(name);
	}
	@Override
	void say(String content){
		System.out.println("Bird say " + content);
	}
	
}

class Cat extends Animal{
	public Cat(String name) {
		super(name);
	}
	@Override
	void say(String content){
		System.out.println("Cat say " + content);
	}
}
